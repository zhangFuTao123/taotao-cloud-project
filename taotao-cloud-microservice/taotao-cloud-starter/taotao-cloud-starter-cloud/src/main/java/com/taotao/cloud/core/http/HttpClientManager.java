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
package com.taotao.cloud.core.http;


import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.core.model.ProcessExitEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HttpClientManager
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 20:20:42
 */
public class HttpClientManager {

	/**
	 * pool
	 */
	private ConcurrentHashMap<String, DefaultHttpClient> pool = new ConcurrentHashMap<>();
	/**
	 * lock
	 */
	private Object lock = new Object();

	public HttpClientManager() {
		ProcessExitEvent.register(() -> {
			try {
				this.closeAll();
			} catch (Exception e) {
				LogUtil.error(e, "关闭httpclient时出错");
			}
		}, Integer.MAX_VALUE - 1, false);
	}

	/**
	 * register
	 *
	 * @param httpClientId httpClientId
	 * @param client       client
	 * @return {@link com.taotao.cloud.core.http.DefaultHttpClient }
	 * @author shuigedeng
	 * @since 2021-09-02 20:21:12
	 */
	public DefaultHttpClient register(String httpClientId, DefaultHttpClient client) {
		try {
			client.open();

			synchronized (lock) {
				if (!pool.containsKey(httpClientId)) {
					pool.put(httpClientId, client);
					return client;
				} else {
					throw new HttpException("已注册HttpClient:" + httpClientId);
				}
			}
		} catch (RuntimeException exp) {
			try {
				client.close();
			} catch (Exception e) {
			}
			throw exp;
		}
	}

	/**
	 * get
	 *
	 * @param httpClientId httpClientId
	 * @return {@link com.taotao.cloud.core.http.DefaultHttpClient }
	 * @author shuigedeng
	 * @since 2021-09-02 20:21:16
	 */
	public DefaultHttpClient get(String httpClientId) {
		return pool.get(httpClientId);
	}

	/**
	 * remove
	 *
	 * @param httpClientId httpClientId
	 * @return boolean
	 * @author shuigedeng
	 * @since 2021-09-02 20:21:20
	 */
	public boolean remove(String httpClientId) {
		DefaultHttpClient httpClient = pool.get(httpClientId);
		if (httpClient != null) {
			synchronized (lock) {
				pool.remove(httpClient);
			}
			httpClient.close();
			return true;
		}
		return false;
	}

	/**
	 * remove
	 *
	 * @param httpClient httpClient
	 * @return boolean
	 * @author shuigedeng
	 * @since 2021-09-02 20:21:23
	 */
	public boolean remove(DefaultHttpClient httpClient) {
		String httpClientId = null;
		for (Map.Entry<String, DefaultHttpClient> e : pool.entrySet()) {
			if (e.getValue() == httpClient) {
				httpClientId = e.getKey();
				break;
			}
		}
		if (httpClientId != null) {
			return remove(httpClientId);
		}
		return false;
	}

	/**
	 * closeAll
	 *
	 * @author shuigedeng
	 * @since 2021-09-02 20:21:25
	 */
	public void closeAll() {
		ConcurrentHashMap<String, DefaultHttpClient> temp = new ConcurrentHashMap<>();

		synchronized (lock) {
			temp.putAll(pool);
			pool.clear();
		}

		RuntimeException exception = null;
		for (Map.Entry<String, DefaultHttpClient> e : temp.entrySet()) {
			try {
				e.getValue().close();
			} catch (RuntimeException ex) {
				exception = ex;
			}
		}
		if (exception != null) {
			throw exception;
		}
	}

	public ConcurrentHashMap<String, DefaultHttpClient> getPool() {
		return pool;
	}

	public void setPool(
		ConcurrentHashMap<String, DefaultHttpClient> pool) {
		this.pool = pool;
	}

	public Object getLock() {
		return lock;
	}

	public void setLock(Object lock) {
		this.lock = lock;
	}
}