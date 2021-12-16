package com.taotao.cloud.oauth2.biz.configuration;

import static com.taotao.cloud.oauth2.biz.authentication.mobile.OAuth2ResourceOwnerMobileAuthenticationConverter.MOBILE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.taotao.cloud.common.utils.ContextUtil;
import com.taotao.cloud.oauth2.biz.authentication.mobile.OAuth2ResourceOwnerMobileAuthenticationConverter;
import com.taotao.cloud.oauth2.biz.authentication.mobile.OAuth2ResourceOwnerMobileAuthenticationProvider;
import com.taotao.cloud.oauth2.biz.authentication.mobile.OAuth2ResourceOwnerMobileAuthenticationToken;
import com.taotao.cloud.oauth2.biz.jwt.Jwks;
import com.taotao.cloud.oauth2.biz.jwt.JwtCustomizer;
import com.taotao.cloud.oauth2.biz.jwt.JwtCustomizerServiceImpl;
import com.taotao.cloud.oauth2.biz.authentication.password.OAuth2ResourceOwnerPasswordAuthenticationConverter;
import com.taotao.cloud.oauth2.biz.authentication.password.OAuth2ResourceOwnerPasswordAuthenticationProvider;
import com.taotao.cloud.oauth2.biz.models.CloudUserDetails;
import com.taotao.cloud.oauth2.biz.models.CloudUserDetailsMixin;
import com.taotao.cloud.oauth2.biz.models.LongMixin;
import com.taotao.cloud.oauth2.biz.service.CloudJdbcOAuth2AuthorizationConsentService;
import com.taotao.cloud.oauth2.biz.service.CloudOAuth2AuthorizationService;
import com.taotao.cloud.redis.repository.RedisRepository;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.security.oauth2.server.authorization.web.authentication.DelegatingAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2AuthorizationCodeAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2ClientCredentialsAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2RefreshTokenAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;


@Configuration
public class AuthorizationServerConfiguration {

	@Value("${oauth2.token.issuer}")
	private String tokenIssuer;

	@Autowired
	private RedisRepository redisRepository;

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
		throws Exception {

		OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer<>();

		http.apply(authorizationServerConfigurer.tokenEndpoint(
			(tokenEndpoint) -> tokenEndpoint.accessTokenRequestConverter(
				new DelegatingAuthenticationConverter(Arrays.asList(
					new OAuth2AuthorizationCodeAuthenticationConverter(),
					new OAuth2RefreshTokenAuthenticationConverter(),
					new OAuth2ClientCredentialsAuthenticationConverter(),
					new OAuth2ResourceOwnerMobileAuthenticationConverter(redisRepository),
					new OAuth2ResourceOwnerPasswordAuthenticationConverter()))
			)));

		authorizationServerConfigurer.authorizationEndpoint(
			authorizationEndpoint -> authorizationEndpoint.consentPage("/oauth2/consent"));

		RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();

		http
			.requestMatcher(endpointsMatcher)
			.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
			.csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
			.apply(authorizationServerConfigurer);

		SecurityFilterChain securityFilterChain = http.formLogin(Customizer.withDefaults()).build();

		/**
		 *
		 * Custom configuration for Resource Owner Password grant type. Current implementation has no support for Resource Owner 
		 * Password grant type
		 */
		addCustomOAuth2ResourceOwnerPasswordAuthenticationProvider(http);

		addCustomOAuth2ResourceOwnerMobileAuthenticationProvider(http);

		return securityFilterChain;
	}

	@Bean
	public JWKSource<SecurityContext> jwkSource() {
		RSAKey rsaKey = Jwks.generateRsa();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
	}

	@Bean
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}

	@Bean
	public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate,
		RegisteredClientRepository registeredClientRepository,
		RedisRepository redisRepository,
		JwtDecoder jwtDecoder) {

		JdbcOAuth2AuthorizationService service = new CloudOAuth2AuthorizationService(jdbcTemplate,
			registeredClientRepository, redisRepository, jwtDecoder);

		JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper rowMapper = new JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper(
			registeredClientRepository);

		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = JdbcOAuth2AuthorizationService.class.getClassLoader();
		List<Module> securityModules = SecurityJackson2Modules.getModules(classLoader);
		objectMapper.registerModules(securityModules);
		objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());

		//// You will need to write the Mixin for your class so Jackson can marshall it.
		//objectMapper.addMixIn(UserAuthority.class, UserAuthorityMixin.class);
		objectMapper.addMixIn(CloudUserDetails.class, CloudUserDetailsMixin.class);
		//objectMapper.addMixIn(AuditDeletedDate.class, AuditDeletedDateMixin.class);
		objectMapper.addMixIn(Long.class, LongMixin.class);
		//
		rowMapper.setObjectMapper(objectMapper);
		service.setAuthorizationRowMapper(rowMapper);

		return service;
	}

	@Bean
	public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate,
		RegisteredClientRepository registeredClientRepository) {

		return new CloudJdbcOAuth2AuthorizationConsentService(jdbcTemplate,
			registeredClientRepository);
	}

	@Bean
	public ProviderSettings providerSettings() {
		return ProviderSettings.builder().issuer(tokenIssuer).build();
	}

	@Bean
	public OAuth2TokenCustomizer<JwtEncodingContext> buildCustomizer() {
		JwtCustomizer jwtCustomizer = new JwtCustomizerServiceImpl();
		return jwtCustomizer::customizeToken;
	}

	private void addCustomOAuth2ResourceOwnerPasswordAuthenticationProvider(HttpSecurity http) {

		AuthenticationManager authenticationManager = http.getSharedObject(
			AuthenticationManager.class);
		ProviderSettings providerSettings = http.getSharedObject(ProviderSettings.class);
		OAuth2AuthorizationService authorizationService = http.getSharedObject(
			OAuth2AuthorizationService.class);
		JwtEncoder jwtEncoder = http.getSharedObject(JwtEncoder.class);

		OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer = buildCustomizer();

		OAuth2ResourceOwnerPasswordAuthenticationProvider resourceOwnerPasswordAuthenticationProvider =
			new OAuth2ResourceOwnerPasswordAuthenticationProvider(authenticationManager,
				authorizationService, jwtEncoder);

		if (jwtCustomizer != null) {
			resourceOwnerPasswordAuthenticationProvider.setJwtCustomizer(jwtCustomizer);
		}

		resourceOwnerPasswordAuthenticationProvider.setProviderSettings(providerSettings);

		// This will add new authentication provider in the list of existing authentication providers.
		http.authenticationProvider(resourceOwnerPasswordAuthenticationProvider);
	}

	private void addCustomOAuth2ResourceOwnerMobileAuthenticationProvider(HttpSecurity http) {
		AuthenticationManager authenticationManager = authentication -> {
			OAuth2ResourceOwnerMobileAuthenticationToken authenticationToken = (OAuth2ResourceOwnerMobileAuthenticationToken) authentication;
			String mobile =  authenticationToken.getMobile();
			Authentication clientPrincipal = (Authentication)authenticationToken.getPrincipal();

			//调用自定义的userDetailsService认证
			UserDetailsService userDetailsService = ContextUtil.getBean(UserDetailsService.class, true);
			UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);

			OAuth2ResourceOwnerMobileAuthenticationToken authenticationResult
			 = new OAuth2ResourceOwnerMobileAuthenticationToken(mobile, MOBILE,
				clientPrincipal, authenticationToken.getScopes(), authenticationToken.getAdditionalParameters(), clientPrincipal.getAuthorities());

			authenticationResult.setDetails(authenticationToken.getDetails());
			return authenticationResult;
		};

		ProviderSettings providerSettings = http.getSharedObject(ProviderSettings.class);
		OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);
		JwtEncoder jwtEncoder = http.getSharedObject(JwtEncoder.class);

		OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer = buildCustomizer();

		OAuth2ResourceOwnerMobileAuthenticationProvider resourceOwnerMobileAuthenticationProvider =
			new OAuth2ResourceOwnerMobileAuthenticationProvider(authenticationManager,
				authorizationService, jwtEncoder);

		if (jwtCustomizer != null) {
			resourceOwnerMobileAuthenticationProvider.setJwtCustomizer(jwtCustomizer);
		}

		resourceOwnerMobileAuthenticationProvider.setProviderSettings(providerSettings);

		// This will add new authentication provider in the list of existing authentication providers.
		http.authenticationProvider(resourceOwnerMobileAuthenticationProvider);
	}
}