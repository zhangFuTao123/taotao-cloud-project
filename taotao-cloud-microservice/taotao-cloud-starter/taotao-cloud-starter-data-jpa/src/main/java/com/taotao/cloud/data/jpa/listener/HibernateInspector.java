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
package com.taotao.cloud.data.jpa.listener;

import com.taotao.cloud.common.utils.ContextUtil;
import com.taotao.cloud.common.utils.StringUtil;
import com.taotao.cloud.core.model.Collector;
import java.util.Objects;
import org.hibernate.HibernateException;
import org.hibernate.event.internal.DefaultDeleteEventListener;
import org.hibernate.event.internal.DefaultLoadEventListener;
import org.hibernate.event.internal.DefaultSaveOrUpdateEventListener;
import org.hibernate.event.spi.DeleteEvent;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.resource.jdbc.spi.StatementInspector;

/**
 * HibernateInterceptor
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-10 17:10:27
 */
public class HibernateInspector implements StatementInspector {

	private String sql;

	public static class SaveOrUpdateListener extends DefaultSaveOrUpdateEventListener {
		private HibernateInspector hibernateInspector;

		public SaveOrUpdateListener(HibernateInspector hibernateInspector) {
			this.hibernateInspector = hibernateInspector;
		}

		@Override
		public void onSaveOrUpdate(SaveOrUpdateEvent event) {
			Collector collector = ContextUtil.getBean(Collector.class, true);
			if (Objects.nonNull(collector)) {
				String replace = StringUtil.nullToEmpty(hibernateInspector.getSql()).replace("\r", "").replace("\n", "");
				collector.hook("taotao.cloud.health.jpa.onSaveOrUpdate.sql.hook")
					.run(replace, () -> {
						try {
							super.onSaveOrUpdate(event);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					});
			}
		}
	}

	public static class DeleteListener extends DefaultDeleteEventListener {
		private HibernateInspector hibernateInspector;

		public DeleteListener(HibernateInspector hibernateInspector) {
			this.hibernateInspector = hibernateInspector;
		}
		@Override
		public void onDelete(DeleteEvent event) throws HibernateException {
			Collector collector = ContextUtil.getBean(Collector.class, true);
			if (Objects.nonNull(collector)) {
				String replace = StringUtil.nullToEmpty(hibernateInspector.getSql()).replace("\r", "").replace("\n", "");
				collector.hook("taotao.cloud.health.jpa.delete.sql.hook")
					.run(replace, () -> {
						try {
							super.onDelete(event);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					});
			}
		}
	}

	public static class LoadListener extends DefaultLoadEventListener {
		private HibernateInspector hibernateInspector;

		public LoadListener(HibernateInspector hibernateInspector) {
			this.hibernateInspector = hibernateInspector;
		}

		@Override
		public void onLoad(LoadEvent event,
			LoadType loadType) throws HibernateException {
			Collector collector = ContextUtil.getBean(Collector.class, true);
			if (Objects.nonNull(collector)) {
				String replace = StringUtil.nullToEmpty(hibernateInspector.getSql()).replace("\r", "").replace("\n", "");
				collector.hook("taotao.cloud.health.jpa.load.sql.hook")
					.run(replace, () -> {
						try {
							super.onLoad(event, loadType);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					});
			}
		}
	}


	@Override
	public String inspect(String sql) {
		this.sql = sql;
		return sql;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}