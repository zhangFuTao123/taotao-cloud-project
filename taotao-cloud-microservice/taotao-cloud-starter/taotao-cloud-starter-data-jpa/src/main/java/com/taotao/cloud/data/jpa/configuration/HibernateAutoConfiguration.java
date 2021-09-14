/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.data.jpa.configuration;

import static org.hibernate.cfg.AvailableSettings.DIALECT;
import static org.hibernate.cfg.AvailableSettings.IMPLICIT_NAMING_STRATEGY;
import static org.hibernate.cfg.AvailableSettings.INTERCEPTOR;
import static org.hibernate.cfg.AvailableSettings.JDBC_TIME_ZONE;
import static org.hibernate.cfg.AvailableSettings.MULTI_TENANT;
import static org.hibernate.cfg.AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER;
import static org.hibernate.cfg.AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER;
import static org.hibernate.cfg.AvailableSettings.PHYSICAL_NAMING_STRATEGY;

import com.taotao.cloud.common.constant.StarterNameConstant;
import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.data.jpa.bean.AuditorBean;
import com.taotao.cloud.data.jpa.bean.TenantConnectionProvider;
import com.taotao.cloud.data.jpa.bean.TenantIdentifierResolver;
import com.taotao.cloud.data.jpa.listener.HibernateInspector;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.HibernateException;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.event.internal.DefaultLoadEventListener;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * HibernateAutoConfiguration
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-04 07:31:41
 */
@Configuration
@EnableJpaAuditing
public class HibernateAutoConfiguration implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		LogUtil.started(HibernateAutoConfiguration.class, StarterNameConstant.JPA_STARTER);
	}

	private final JpaProperties jpaProperties;

	public HibernateAutoConfiguration(@Autowired final JpaProperties jpaProperties) {
		this.jpaProperties = jpaProperties;
	}

	@Bean
	public AuditorBean auditorBean() {
		LogUtil.started(AuditorBean.class, StarterNameConstant.JPA_STARTER);
		return new AuditorBean();
	}

	@Bean
	public MultiTenantConnectionProvider tenantConnectionProvider(DataSource dataSource) {
		LogUtil.started(TenantConnectionProvider.class, StarterNameConstant.JPA_STARTER);
		return new TenantConnectionProvider(dataSource);
	}

	@Bean
	public CurrentTenantIdentifierResolver tenantIdentifierResolver() {
		LogUtil.started(TenantIdentifierResolver.class, StarterNameConstant.JPA_STARTER);
		return new TenantIdentifierResolver();
	}

	@Bean
	JpaVendorAdapter jpaVendorAdapter() {
		LogUtil.started(JpaVendorAdapter.class, StarterNameConstant.JPA_STARTER);
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public HibernateInspector hibernateListener() {
		return new HibernateInspector();
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(
		final DataSource dataSource,
		final JpaVendorAdapter jpaVendorAdapter,
		final MultiTenantConnectionProvider multiTenantConnectionProvider,
		final CurrentTenantIdentifierResolver currentTenantIdentifierResolver,
		HibernateInspector hibernateInspector) {
		LogUtil.started(LocalContainerEntityManagerFactoryBean.class,
			StarterNameConstant.JPA_STARTER);

		final Map<String, Object> newJpaProperties = new HashMap<>(jpaProperties.getProperties());

		newJpaProperties.put(MULTI_TENANT, MultiTenancyStrategy.DISCRIMINATOR);
		newJpaProperties.put(
			MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
		newJpaProperties.put(
			MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);

		newJpaProperties.put(
			IMPLICIT_NAMING_STRATEGY, SpringImplicitNamingStrategy.class.getName());
		newJpaProperties.put(
			PHYSICAL_NAMING_STRATEGY, SpringPhysicalNamingStrategy.class.getName());
		newJpaProperties.put(DIALECT, MySQL8Dialect.class.getName());
		newJpaProperties.put(JDBC_TIME_ZONE, "Asia/Shanghai");
		newJpaProperties.put(INTERCEPTOR, hibernateInspector);

		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
			new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaPropertyMap(newJpaProperties);
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

		entityManagerFactoryBean
			.setPackagesToScan("com.taotao.cloud.*.biz.entity", "com.taotao.cloud.*.entity");
		entityManagerFactoryBean.setPersistenceUnitName("default");

		SessionFactoryImpl sessionFactory = entityManagerFactoryBean.getNativeEntityManagerFactory()
			.unwrap(SessionFactoryImpl.class);
		EventListenerRegistry registry = sessionFactory.getServiceRegistry()
			.getService(EventListenerRegistry.class);
		registry.getEventListenerGroup(EventType.SAVE_UPDATE).appendListener(
			new HibernateInspector.SaveOrUpdateListener(hibernateInspector));
		registry.getEventListenerGroup(EventType.DELETE)
			.appendListener(new HibernateInspector.DeleteListener(hibernateInspector));
		registry.getEventListenerGroup(EventType.LOAD)
			.appendListener(new HibernateInspector.LoadListener(hibernateInspector));

		return entityManagerFactoryBean;
	}
}