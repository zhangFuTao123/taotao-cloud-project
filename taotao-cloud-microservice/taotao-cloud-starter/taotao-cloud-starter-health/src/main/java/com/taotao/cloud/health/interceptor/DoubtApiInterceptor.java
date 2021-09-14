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
package com.taotao.cloud.health.interceptor;

import com.taotao.cloud.core.model.Collector;
import com.taotao.cloud.health.properties.DoubtApiProperties;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器，统计接口内存增长
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-10 17:06:43
 */
public class DoubtApiInterceptor implements HandlerInterceptor {

	private ThreadLocal<Long> beforeMem = new ThreadLocal<>();
	private Map<String, DoubtApiInfo> statisticMap = new ConcurrentHashMap<>();
	private DoubtApiProperties properties;
	private Collector collector;

	public DoubtApiInterceptor(Collector collector, DoubtApiProperties properties) {
		this.collector = collector;
		this.properties = properties;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object handler) throws Exception {
		beforeMem.set(getJVMUsed());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
		Object handler, Exception ex) throws Exception {
		Long data = beforeMem.get();
		beforeMem.remove();

		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			String methodPath =
				method.getBean().getClass().getName() + "." + method.getMethod().getName();
			String url = request.getRequestURI();

			long incrMem = getJVMUsed() - data;
			if (incrMem > properties.getThreshold()) {
				if (statisticMap.containsKey(methodPath)) {
					DoubtApiInfo staticInfo = statisticMap.get(methodPath);
					staticInfo.uri = url;
					staticInfo.count += 1;
					staticInfo.totalIncreMem += incrMem;
					if (staticInfo.totalIncreMem <= 0) {
						staticInfo.totalIncreMem = incrMem;
						staticInfo.count = 1;
					}
				} else {
					DoubtApiInfo staticInfo = new DoubtApiInfo();
					staticInfo.method = methodPath;
					staticInfo.uri = url;
					//第一次不计算内存
					staticInfo.count = 0;
					staticInfo.totalIncreMem = 0;
					statisticMap.put(methodPath, staticInfo);
				}

				this.collector.value("taotao.cloud.health.doubtapi.info").set(statisticMap);
			}
		}
	}

	/**
	 * 获取JVM内存
	 *
	 * @return long
	 * @author shuigedeng
	 * @since 2021-09-10 17:08:03
	 */
	private long getJVMUsed() {
		Runtime rt = Runtime.getRuntime();
		return (rt.totalMemory() - rt.freeMemory());
	}

	/**
	 * DoubtApiInfo
	 *
	 * @author shuigedeng
	 * @version 2021.9
	 * @since 2021-09-10 17:08:13
	 */
	public static class DoubtApiInfo implements Comparable<DoubtApiInfo> {

		/**
		 * 请求URL
		 */
		private String uri;
		/**
		 * 请求接口方法
		 */
		private String method;
		/**
		 * 总增加内存
		 */
		private long totalIncreMem;
		/**
		 * 请求命中次数
		 */
		private int count;

		public DoubtApiInfo() {
		}

		public DoubtApiInfo(String uri, String method, long totalIncreMem, int count) {
			this.uri = uri;
			this.method = method;
			this.totalIncreMem = totalIncreMem;
			this.count = count;
		}

		/**
		 * 倒序
		 */
		@Override
		public int compareTo(DoubtApiInfo doubtApiInfo) {
			if (doubtApiInfo == null) {
				return -1;
			}
			long cha = doubtApiInfo.count > 0 ?
				doubtApiInfo.totalIncreMem / doubtApiInfo.count
				: doubtApiInfo.totalIncreMem - this.count > 0 ?
					this.totalIncreMem / this.count : this.totalIncreMem;

			if (cha > 0) {
				return 1;
			} else if (cha < 0) {
				return -1;
			} else {
				return 0;
			}
		}

		public String getUri() {
			return uri;
		}

		public void setUri(String uri) {
			this.uri = uri;
		}

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public long getTotalIncreMem() {
			return totalIncreMem;
		}

		public void setTotalIncreMem(long totalIncreMem) {
			this.totalIncreMem = totalIncreMem;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}
}