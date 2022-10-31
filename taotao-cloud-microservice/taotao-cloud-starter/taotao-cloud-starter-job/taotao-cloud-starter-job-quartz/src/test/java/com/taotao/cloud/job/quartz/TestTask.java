package com.taotao.cloud.job.quartz;

import cn.hutool.core.thread.ThreadUtil;
import lombok.Setter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * 测试定时任务
 *
 * @author xxm
 * @date 2021/11/8
 */
// @Slf4j
// @Component
// @DisallowConcurrentExecution
// @PersistJobDataAfterExecution
// @RequiredArgsConstructor
public class TestTask implements Job {
	/**
	 * 若参数变量名修改 QuartzJobScheduler 中也需对应修改
	 * 需要给一个set方法, 让系统设置值
	 */
	@Setter
	private String parameter;

	@Override
	public void execute(JobExecutionContext context) {
		// log.info("定时任务start");
		ThreadUtil.safeSleep(5000L);
		// log.info("定时任务end");
		// log.info("参数: {}",parameter);
	}
}