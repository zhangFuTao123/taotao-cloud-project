/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
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

package com.taotao.cloud.auth.biz.controller; // package com.taotao.cloud.auth.biz.controller;
//
// import cn.hutool.core.util.IdUtil;
// import cn.hutool.crypto.asymmetric.KeyType;
// import cn.hutool.crypto.asymmetric.RSA;
// import com.wf.captcha.ArithmeticCaptcha;
// import io.undertow.util.BadRequestException;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.concurrent.TimeUnit;
// import jakarta.servlet.http.HttpServletRequest;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.autoconfigure.security.SecurityProperties;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
/// **
// * AuthController
// *
// * @author shuigedeng
// * @version 2021.10
// * @since 2022-02-24 11:21:38
// */
//
// @RestController
// @RequestMapping("/auth")
// @Api(tags = "系统：系统授权接口")
// public class AuthController {
//
//    @Value("${loginCode.expiration}")
//    private Long expiration;
//    @Value("${rsa.private_key}")
//    private String privateKey;
//    @Value("${single.login}")
//    private Boolean singleLogin;
//
//    private final SecurityProperties properties;
//    private final RedisUtils redisUtils;
//    private final UserDetailsService userDetailsService;
//    private final OnlineUserService onlineUserService;
//    private final TokenUtil tokenUtil;
//    private final AuthenticationManagerBuilder authenticationManagerBuilder;
//
//    public AuthController(SecurityProperties properties, RedisUtils redisUtils, UserDetailsService
// userDetailsService, OnlineUserService onlineUserService, TokenUtil tokenUtil,
// AuthenticationManagerBuilder authenticationManagerBuilder) {
//        this.properties = properties;
//        this.redisUtils = redisUtils;
//        this.userDetailsService = userDetailsService;
//        this.onlineUserService = onlineUserService;
//        this.tokenUtil = tokenUtil;
//        this.authenticationManagerBuilder = authenticationManagerBuilder;
//    }
//
//    @Log("用户登录")
//    @ApiOperation("登录授权")
//    @AnonymousAccess
//    @PostMapping(value = "/login")
//    public ResponseEntity<Object> login(@Validated @RequestBody AuthUser authUser,
// HttpServletRequest request) {
//        // 密码解密
//        RSA rsa = new RSA(privateKey, null);
//        String password = new String(rsa.decrypt(authUser.getPassword(), KeyType.PrivateKey));
//        // 查询验证码
//        String code = (String) redisUtils.get(authUser.getUuid());
//        // 清除验证码
//        redisUtils.del(authUser.getUuid());
//        if (StringUtils.isBlank(code)) {
//            throw new BadRequestException("验证码不存在或已过期");
//        }
//        if (StringUtils.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code))
// {
//            throw new BadRequestException("验证码错误");
//        }
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
//
//        Authentication authentication =
// authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        // 生成令牌
//        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String token = tokenUtil.generateToken(userDetails);
//        final JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
//        // 保存在线信息
//        onlineUserService.save(jwtUser, token, request);
//        // 返回 token 与 用户信息
//        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
//            put("token", properties.getTokenStartWith() + token);
//            put("user", jwtUser);
//        }};
//        if (singleLogin) {
//            //踢掉之前已经登录的token
//            onlineUserService.checkLoginOnUser(authUser.getUsername(), token);
//        }
//        return ResponseEntity.ok(authInfo);
//    }
//
//    @ApiOperation("获取用户信息")
//    @GetMapping(value = "/info")
//    public ResponseEntity<Object> getUserInfo() {
//        JwtUser jwtUser = (JwtUser)
// userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
//        return ResponseEntity.ok(jwtUser);
//    }
//
//    @AnonymousAccess
//    @ApiOperation("获取验证码")
//    @GetMapping(value = "/code")
//    public ResponseEntity<Object> getCode() {
//        // 算术类型 https://gitee.com/whvse/EasyCaptcha
//        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
//        // 几位数运算，默认是两位
//        captcha.setLen(2);
//        // 获取运算的结果
//        String result = "";
//        try {
//            result = new Double(Double.parseDouble(captcha.text())).intValue() + "";
//        } catch (Exception e) {
//            result = captcha.text();
//        }
//        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
//        // 保存
//        redisUtils.set(uuid, result, expiration, TimeUnit.MINUTES);
//        // 验证码信息
//        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
//            put("img", captcha.toBase64());
//            put("uuid", uuid);
//        }};
//        return ResponseEntity.ok(imgResult);
//    }
//
//    @ApiOperation("退出登录")
//    @AnonymousAccess
//    @DeleteMapping(value = "/logout")
//    public ResponseEntity<Object> logout(HttpServletRequest request) {
//        onlineUserService.logout(tokenUtil.getToken(request));
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
// }
