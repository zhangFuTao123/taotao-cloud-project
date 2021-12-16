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
package com.taotao.cloud.core.configuration;

import com.taotao.cloud.common.constant.StarterName;
import com.taotao.cloud.common.utils.JsonUtil;
import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.core.properties.OkHttpProperties;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config
 *
 * @author shuigedeng
 * @version 1.0.0
 * @since 2021/06/17 17:21
 */
@Configuration
@EnableConfigurationProperties({OkHttpProperties.class})
@ConditionalOnProperty(prefix = OkHttpProperties.PREFIX, name = "enabled", havingValue = "true")
public class OkhttpAutoConfiguration implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		LogUtil.started(OkhttpAutoConfiguration.class, StarterName.CORE_STARTER);
	}

	@Bean
	public OkHttpService okHttpService(OkHttpProperties okHttpProperties) {
		OkHttpService okHttpService = OkHttpService.builder();
		okHttpService.setOkHttpProperties(okHttpProperties);
		return okHttpService;
	}

	/**
	 * OkHttpService
	 *
	 * @author shuigedeng
	 * @version 2021.9
	 * @since 2021-09-02 16:25:02
	 */
	public static class OkHttpService {

		/**
		 * okHttpClient
		 */
		private static volatile OkHttpClient okHttpClient = null;
		/**
		 * semaphore
		 */
		private static volatile Semaphore semaphore = null;
		/**
		 * headerMap
		 */
		private Map<String, String> headerMap;
		/**
		 * paramMap
		 */
		private Map<String, String> paramMap;
		/**
		 * url
		 */
		private String url;
		/**
		 * request
		 */
		private Request.Builder request;

		private OkHttpProperties okHttpProperties;

		public void setOkHttpProperties(OkHttpProperties okHttpProperties) {
			this.okHttpProperties = okHttpProperties;
		}

		/**
		 * 初始化okHttpClient，并且允许https访问
		 *
		 * @author shuigedeng
		 * @since 2021-09-02 16:26:25
		 */
		private OkHttpService() {
			if (okHttpClient == null) {
				synchronized (OkHttpService.class) {
					if (okHttpClient == null) {
						TrustManager[] trustManagers = buildTrustManagers();
						okHttpClient = new OkHttpClient.Builder()
							.connectTimeout(okHttpProperties.getConnectTimeout(), TimeUnit.SECONDS)
							.writeTimeout(okHttpProperties.getWriteTimeout(), TimeUnit.SECONDS)
							.readTimeout(okHttpProperties.getReadTimeout(), TimeUnit.SECONDS)
							.sslSocketFactory(createSSLSocketFactory(trustManagers),
								(X509TrustManager) trustManagers[0])
							.hostnameVerifier((hostName, session) -> true)
							.retryOnConnectionFailure(true)
							.build();
						addHeader("User-Agent",
							"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
					}
				}
			}
		}

		/**
		 * 用于异步请求时，控制访问线程数，返回结果
		 *
		 * @return {@link java.util.concurrent.Semaphore }
		 * @author shuigedeng
		 * @since 2021-09-02 16:26:34
		 */
		private static Semaphore getSemaphoreInstance() {
			//只能1个线程同时访问
			synchronized (OkHttpService.class) {
				if (semaphore == null) {
					semaphore = new Semaphore(0);
				}
			}
			return semaphore;
		}

		/**
		 * 创建OkHttpService
		 *
		 * @author shuigedeng
		 * @since 2021-09-02 16:26:43
		 */
		public static OkHttpService builder() {
			return new OkHttpService();
		}

		/**
		 * 获取url
		 *
		 * @param url url
		 * @author shuigedeng
		 * @since 2021-09-02 16:27:06
		 */
		public OkHttpService url(String url) {
			this.url = url;
			return this;
		}


		/**
		 * 添加参数
		 *
		 * @param key   参数名
		 * @param value 参数值
		 * @return {@link OkHttpService }
		 * @author shuigedeng
		 * @since 2021-09-02 16:27:17
		 */
		public OkHttpService addParam(String key, String value) {
			if (paramMap == null) {
				paramMap = new LinkedHashMap<>(16);
			}
			paramMap.put(key, value);
			return this;
		}

		/**
		 * 添加请求头
		 *
		 * @param key   参数名
		 * @param value 参数值
		 * @author shuigedeng
		 * @since 2021-09-02 16:28:19
		 */
		public OkHttpService addHeader(String key, String value) {
			if (headerMap == null) {
				headerMap = new LinkedHashMap<>(16);
			}
			headerMap.put(key, value);
			return this;
		}

		/**
		 * get请求，方法顺序按照这种方式，切记选择post/get一定要放在倒数第二，同步或者异步倒数第一，才会正确执行
		 * <p>
		 * OkHttpServices.builder().url("请求地址，http/https都可以") // 有参数的话添加参数，可多个 .addParam("参数名",
		 * "参数值") .addParam("参数名", "参数值") // 也可以添加多个 .addHeader("Content-Type", "application/json;
		 * charset=utf-8") .get() // 可选择是同步请求还是异步请求 //.async(); .sync();
		 *
		 * @author shuigedeng
		 * @since 2021-09-02 16:28:38
		 */
		public OkHttpService get() {
			request = new Request.Builder().get();
			StringBuilder urlBuilder = new StringBuilder(url);
			if (paramMap != null) {
				urlBuilder.append("?");
				try {
					for (Map.Entry<String, String> entry : paramMap.entrySet()) {
						urlBuilder.append(URLEncoder.encode(entry.getKey(), "utf-8")).
							append("=").
							append(URLEncoder.encode(entry.getValue(), "utf-8")).
							append("&");
					}
				} catch (Exception e) {
					LogUtil.error(e);
				}
				urlBuilder.deleteCharAt(urlBuilder.length() - 1);
			}
			request.url(urlBuilder.toString());
			return this;
		}

		/**
		 * post请求，分为两种，一种是普通表单提交，一种是json提交
		 * <p>
		 * OkHttpServices.builder().url("请求地址，http/https都可以") // 有参数的话添加参数，可多个 .addParam("参数名",
		 * "参数值") .addParam("参数名", "参数值") // 也可以添加多个 .addHeader("Content-Type", "application/json;
		 * charset=utf-8") // 如果是true的话，会类似于postman中post提交方式的raw，用json的方式提交，不是表单 //
		 * 如果是false的话传统的表单提交 .post(true) .sync();
		 * </p>
		 * <p>
		 * // 选择异步有两个方法，一个是带回调接口，一个是直接返回结果 OkHttpServices.builder().url("") .post(false) .async();
		 * </p>
		 * <p>
		 * OkHttpServices.builder().url("") .post(false) .async(new OkHttpServices.ICallBack() {
		 * </p>
		 *
		 * @param isJsonPost true等于json的方式提交数据，类似postman里post方法的raw false等于普通的表单提交
		 * @author shuigedeng
		 * @since 2021-09-02 16:29:02
		 */
		public OkHttpService post(boolean isJsonPost) {
			RequestBody requestBody;
			if (isJsonPost) {
				String json = "";
				if (paramMap != null) {
					json = JsonUtil.toJSONString(paramMap);
				}
				requestBody = RequestBody
					.create(MediaType.parse("application/json; charset=utf-8"), json);
			} else {
				FormBody.Builder formBody = new FormBody.Builder();
				if (paramMap != null) {
					paramMap.forEach(formBody::add);
				}
				requestBody = formBody.build();
			}
			request = new Request.Builder().post(requestBody).url(url);
			return this;
		}


		/**
		 * 同步请求
		 *
		 * @return {@link java.lang.String }
		 * @author shuigedeng
		 * @since 2021-09-02 16:29:32
		 */
		public String sync() {
			setHeader(request);
			try {
				Response response = okHttpClient.newCall(request.build()).execute();
				return Objects.requireNonNull(response.body()).string();
			} catch (IOException e) {
				LogUtil.error(e);
				return "请求失败：" + e.getMessage();
			}
		}


		/**
		 * 异步请求，有返回值
		 *
		 * @return {@link java.lang.String }
		 * @author shuigedeng
		 * @since 2021-09-02 16:29:37
		 */
		public String async() {
			StringBuilder buffer = new StringBuilder("");
			setHeader(request);
			okHttpClient.newCall(request.build()).enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					buffer.append("请求出错：").append(e.getMessage());
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					assert response.body() != null;
					buffer.append(response.body().string());
					getSemaphoreInstance().release();
				}
			});
			try {
				getSemaphoreInstance().acquire();
			} catch (InterruptedException e) {
				LogUtil.error(e);
			}
			return buffer.toString();
		}


		/**
		 * 异步请求，带有接口回调
		 *
		 * @param callBack callBack
		 * @author shuigedeng
		 * @since 2021-09-02 16:29:42
		 */
		public void async(ICallBack callBack) {
			setHeader(request);

			okHttpClient.newCall(request.build()).enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					callBack.onFailure(call, e.getMessage());
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					assert response.body() != null;
					callBack.onSuccessful(call, response.body().string());
				}
			});
		}

		/**
		 * 为request添加请求头
		 *
		 * @param request request
		 * @author shuigedeng
		 * @since 2021-09-02 16:29:48
		 */
		private void setHeader(Request.Builder request) {
			if (headerMap != null) {
				try {
					for (Map.Entry<String, String> entry : headerMap.entrySet()) {
						request.addHeader(entry.getKey(), entry.getValue());
					}
				} catch (Exception e) {
					LogUtil.error(e);
				}
			}
		}


		/**
		 * 生成安全套接字工厂，用于https请求的证书跳过
		 *
		 * @param trustAllCerts trustAllCerts
		 * @return {@link javax.net.ssl.SSLSocketFactory }
		 * @author shuigedeng
		 * @since 2021-09-02 16:29:54
		 */
		private static SSLSocketFactory createSSLSocketFactory(TrustManager[] trustAllCerts) {
			SSLSocketFactory ssfFactory = null;
			try {
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new SecureRandom());
				ssfFactory = sc.getSocketFactory();
			} catch (Exception e) {
				LogUtil.error(e);
			}
			return ssfFactory;
		}

		/**
		 * buildTrustManagers
		 *
		 * @return javax.net.ssl.TrustManager[]
		 * @author shuigedeng
		 * @since 2021-09-02 17:10:34
		 */
		private static TrustManager[] buildTrustManagers() {
			return new TrustManager[]{
				new X509TrustManager() {
					@Override
					public void checkClientTrusted(X509Certificate[] chain, String authType) {
					}

					@Override
					public void checkServerTrusted(X509Certificate[] chain, String authType) {
					}

					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return new X509Certificate[]{};
					}
				}
			};
		}

		/**
		 * ICallBack
		 *
		 * @author shuigedeng
		 * @version 2021.9
		 * @since 2021-09-02 16:30:13
		 */
		public interface ICallBack {

			/**
			 * onSuccessful
			 *
			 * @param call call
			 * @param data data
			 * @author shuigedeng
			 * @since 2021-12-01 15:21:47
			 */
			void onSuccessful(Call call, String data);

			/**
			 * onFailure
			 *
			 * @param call     call
			 * @param errorMsg errorMsg
			 * @author shuigedeng
			 * @since 2021-12-01 15:21:52
			 */
			void onFailure(Call call, String errorMsg);

		}

	}
}