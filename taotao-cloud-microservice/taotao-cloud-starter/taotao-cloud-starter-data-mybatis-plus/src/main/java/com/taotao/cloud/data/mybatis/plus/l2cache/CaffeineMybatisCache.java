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

package com.taotao.cloud.data.mybatis.plus.l2cache;

import com.taotao.cloud.caffeine.manager.CaffeineAutoCacheManager;
import com.taotao.cloud.common.utils.context.ContextUtil;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: 扩展的Mybatis二级缓存 </p>
 *
 * @CacheNamespace(implementation = CaffeineMybatisCache.class, eviction = CaffeineMybatisCache.class)
 * public interface ILogMapper extends BaseMapper<Log>
 *
 * @author : gengwei.zheng
 * @date : 2021/10/26 17:02
 */
public class CaffeineMybatisCache implements Cache {

	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

	private static final Logger log = LoggerFactory.getLogger(CaffeineMybatisCache.class);

	private final String id;
	private final org.springframework.cache.Cache cache;
	private final AtomicInteger counter = new AtomicInteger(0);

	public CaffeineMybatisCache(final String id) {
		this.id = id;
		Object obj = ContextUtil.getBean("caffeineCacheManager", true);
		if (Objects.isNull(obj)) {
			throw new RuntimeException("请开启caffeineCacheManager");
		}
		CaffeineAutoCacheManager cacheManager = (CaffeineAutoCacheManager) obj;

		this.cache = cacheManager.getCache(this.id);
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		cache.put(key.toString(), value);
		counter.incrementAndGet();
		log.debug("CACHE - Put data into Mybatis Cache, with key: [{}]", key);
	}

	@Override
	public Object getObject(Object key) {
		Object obj = cache.get(key.toString());
		log.debug("CACHE - Get data from Mybatis Cache, with key: [{}]", key);
		return obj;
	}

	@Override
	public Object removeObject(Object key) {
		Object obj = cache.get(key.toString());
		cache.evict(key);
		counter.decrementAndGet();
		log.debug("CACHE - Remove data from Mybatis Cache, with key: [{}]", key);
		return obj;
	}

	@Override
	public void clear() {
		cache.clear();
		log.debug("CACHE - Clear Mybatis Cache.");
	}

	@Override
	public int getSize() {
		return counter.get();
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}
}