package com.taotao.cloud.health.filter;

import com.taotao.cloud.common.base.Collector;
import com.taotao.cloud.common.utils.StringUtil;
import com.taotao.cloud.health.base.HealthException;
import java.util.Properties;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * @author: chejiangyi
 * @version: 2019-08-02 09:43
 **/
@Intercepts({
	@Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class}),
	@Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
	@Signature(method = "update", type = Executor.class, args = {MappedStatement.class,
		Object.class})})
public class SqlMybatisInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		// 得到sql语句
		Object parameter = null;
		if (invocation.getArgs().length > 1) {
			parameter = invocation.getArgs()[1];
		}
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		String sql = boundSql.getSql();
		try {
			Object returnObj = Collector.Default.hook("bsf.mybatis.sql.hook").run(
				StringUtil.nullToEmpty(sql).replace("\r", "").replace("\n", ""), () -> {
					try {
						return invocation.proceed();
					} catch (Exception e) {
						throw new HealthException(e);
					}
				});
			return returnObj;
		} catch (HealthException exp) {
			throw exp;
		}
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}

}
