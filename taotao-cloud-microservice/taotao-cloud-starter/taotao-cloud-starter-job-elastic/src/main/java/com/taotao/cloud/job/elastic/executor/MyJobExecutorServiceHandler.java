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
package com.taotao.cloud.job.elastic.executor;

import java.util.concurrent.ExecutorService;
import org.apache.shardingsphere.elasticjob.infra.concurrent.ElasticJobExecutorService;
import org.apache.shardingsphere.elasticjob.infra.handler.threadpool.JobExecutorServiceHandler;

/**
 * MyJobExecutorServiceHandler
 *
 * @author shuigedeng
 * @version 1.0.0
 * @since 2022/03/11 09:22
 */
public class MyJobExecutorServiceHandler implements JobExecutorServiceHandler {

	@Override
	public ExecutorService createExecutorService(String jobName) {
		return new ElasticJobExecutorService("taotao-cloud-elastic-job-" + jobName, 20).createExecutorService();
	}

	@Override
	public String getType() {
		return "myJobExecutorServiceHandler";
	}
}