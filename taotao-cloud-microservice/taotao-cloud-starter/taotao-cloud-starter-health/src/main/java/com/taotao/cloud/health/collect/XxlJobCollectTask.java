package com.taotao.cloud.health.collect;

import com.taotao.cloud.common.utils.ContextUtil;
import com.taotao.cloud.common.utils.PropertyUtil;
import com.taotao.cloud.common.utils.ReflectionUtil;
import com.taotao.cloud.health.base.AbstractCollectTask;
import com.taotao.cloud.health.base.FieldReport;
import java.lang.annotation.Annotation;

/**
 * @author Huang Zhaoping
 */
public class XxlJobCollectTask extends AbstractCollectTask {

	@Override
	public int getTimeSpan() {
		return PropertyUtil.getPropertyCache("bsf.health.xxljob.timeSpan", 20);
	}

	@Override
	public boolean getEnabled() {
		return PropertyUtil.getPropertyCache("bsf.health.xxljob.enabled", true);
	}

	@Override
	public String getDesc() {
		return "定时任务性能采集";
	}

	@Override
	public String getName() {
		return "xxljob.info";
	}

	@Override
	protected Object getData() {
		if (ContextUtil.getBean(
			ReflectionUtil.tryClassForName("com.xxl.job.core.executor.impl.XxlJobSpringExecutor"),
			false) == null) {
			return null;
		}
		JobInfo data = new JobInfo();
		Class<?> aClass = ReflectionUtil.classForName(
			"com.xxl.job.core.handler.annotation.JobHandler");
		data.count = ContextUtil.getApplicationContext().getBeanNamesForAnnotation(
			(Class<? extends Annotation>) aClass).length;
		return data;
	}

	private static class JobInfo {

		@FieldReport(name = "xxljob.count", desc = "xxljob任务数量")
		private Integer count;


		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}
	}
}
