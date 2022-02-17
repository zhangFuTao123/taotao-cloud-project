package com.taotao.cloud.sys.biz.service.impl;

import com.taotao.cloud.redis.repository.RedisRepository;
import com.taotao.cloud.sys.api.vo.redis.RedisVo;
import com.taotao.cloud.sys.biz.service.IRedisService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.connection.DataType;
import org.springframework.stereotype.Service;

/**
 * RedisServiceImpl
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2022-02-11 16:19:25
 */
@Service
public class RedisServiceImpl implements IRedisService {

	@Autowired
	private RedisRepository redisRepository;

	//@Value("${loginCode.expiration}")
	//private Long expiration;

	@Override
	public Page<RedisVo> findByKey(String key, Pageable pageable) {
		List<RedisVo> redisVos = new ArrayList<>();
		if (!"*".equals(key)) {
			key = "*" + key + "*";
		}
		for (Object s : redisRepository.keys(key)) {
			// 过滤掉权限的缓存
			if (s.toString().indexOf("role::loadPermissionByUser") != -1
				|| s.toString().indexOf("user::loadUserByUsername") != -1
				|| s.toString().indexOf("wechat") != -1
				|| s.toString().indexOf("wxpay") != -1
				//|| s.toString().indexOf(ShopKeyUtils.getSiteUrl()) != -1
			) {
				continue;
			}
			DataType dataType = redisRepository.type(s.toString());
			if (!"string".equals(dataType.code())) {
				continue;
			}
			RedisVo redisVo = new RedisVo(s.toString(),
				redisRepository.get(s.toString()).toString());
			redisVos.add(redisVo);
		}
		//Page<RedisVo> page = new PageImpl<RedisVo>(
		//	PageUtil.toPage(pageable.getPageNumber(), pageable.getPageSize(), redisVos),
		//	pageable,
		//	redisVos.size());
		return null;
	}

	@Override
	public void delete(String key) {
		redisRepository.del(key);
	}

	@Override
	public void flushdb() {
		redisRepository.getConnectionFactory().getConnection().flushDb();
	}

	@Override
	public String getCodeVal(String key) {
		try {
			return redisRepository.get(key).toString();
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	public void saveCode(String key, Object val) {
		redisRepository.set(key, val);
		//redisTemplate.expire(key, expiration, TimeUnit.MINUTES);
	}
}