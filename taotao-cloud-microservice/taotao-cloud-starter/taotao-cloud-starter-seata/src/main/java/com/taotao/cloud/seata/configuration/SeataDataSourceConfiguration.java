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
package com.taotao.cloud.seata.configuration;

import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.common.utils.LogUtil;
import com.zaxxer.hikari.HikariDataSource;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * DataSourceConfiguration
 *
 * @author shuigedeng
 * @version 1.0.0
 * @since 2020/10/22 14:48
 */
@ConditionalOnBean(DataSource.class)
@EnableAutoDataSourceProxy
public class SeataDataSourceConfiguration {

	@Primary
	@Bean(name = "seataDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource(DataSourceProperties properties) {
		HikariDataSource hikariDataSource =
			properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
		return new DataSourceProxy(hikariDataSource);
	}

	@Bean
	public SeataXidFilter seataXidFilter(){
		return new SeataXidFilter();
	}


	@Bean
	public SeataInterceptor seataInterceptor(){
		return new SeataInterceptor();
	}


	/**
	 * 判断当前数据库是否有undo_log 该表，如果没有，
	 * 创建该表 undo_log 为seata 记录事务sql执行的记录表 第二阶段时，如果confirm会清除记录，如果是cancel 会根据记录补偿原数据
	 */
	@PostConstruct
	public void detectTable(DataSource dataSource) {
		try {
			dataSource.getConnection().prepareStatement(undoLogSql).execute();
		} catch (SQLException e) {
			LogUtil.error("创建[seata] undo_log表错误。", e);
		}
	}

	public static final String undoLogSql = "CREATE TABLE IF NOT EXISTS undo_log(" +
		"`id` bigint(20) NOT NULL AUTO_INCREMENT," +
		"`branch_id` bigint(20) NOT NULL," +
		"`xid` varchar(100) NOT NULL," +
		"`context` varchar(128) NOT NULL," +
		"`rollback_info` longblob NOT NULL," +
		"`log_status` int(11) NOT NULL," +
		"`log_created` datetime NOT NULL," +
		"`log_modified` datetime NOT NULL," +
		"`ext` varchar(100) DEFAULT NULL," +
		"PRIMARY KEY (`id`)," +
		"UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)" +
		")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";

	/**
	 * @author shuigedeng
	 * @version 1.0.0
	 * @since 2020/10/22 17:01
	 */
	public class SeataXidFilter extends OncePerRequestFilter {

		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
			String restXid = request.getHeader("xid");
			if (StrUtil.isNotBlank(restXid)) {
				RootContext.bind(restXid);
				LogUtil.info("bind[" + restXid + "] to RootContext");
			}
			filterChain.doFilter(request, response);
		}
	}


	/**
	 * SeataInterceptor
	 *
	 * @author shuigedeng
	 * @version 1.0.0
	 * @since 2020/10/22 17:00
	 */
	public class SeataInterceptor implements RequestInterceptor {

		// 这里在feign请求的header中加入xid
		// 注意：这里一定要将feign.hystrix.enabled设为false，因为为true时feign是通过线程池调用，而XID并不是一个InheritablThreadLocal变量。
		@Override
		public void apply(RequestTemplate template) {
			String xid = RootContext.getXID();
			if (StrUtil.isNotBlank(xid)) {
				template.header("xid", xid);
			}
		}
	}
}