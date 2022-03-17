package com.taotao.cloud.sys.biz.timetask.xxljob;
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

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

/**
 * Cs1DbJobHandler
 *
 * @author shuigedeng
 * @version 1.0.0
 * @since 2021/07/02 14:45
 */
@Component
public class Cs1DbJobHandler {

	//@Autowired
	//private ICs1DbService cs1DbService;

	@XxlJob("Cs1DbJobHandler")
	public ReturnT<String> execute(String s) {
		try {
			String[] tableNameList = new String[]{"order_amazon", "order_amazon_detail"};//要同步的表名

			CountDownLatch latch = new CountDownLatch(tableNameList.length);//设置与表相同的线程计数器，同时备份表
			for (String tableName : tableNameList) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							//cs1DbService.tableOperation(tableName);
							Thread.sleep(10000);
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							latch.countDown();
						}
					}
				}).start();
			}
			latch.await();
			return ReturnT.SUCCESS;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return ReturnT.FAIL;
		}
	}
}
