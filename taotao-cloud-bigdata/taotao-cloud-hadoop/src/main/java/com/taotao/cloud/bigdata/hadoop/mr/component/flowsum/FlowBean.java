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
package com.taotao.cloud.bigdata.hadoop.mr.component.flowsum;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * FlowBean
 *
 * @author dengtao
 * @date 2020/11/26 下午8:20
 * @since v1.0
 */
public class FlowBean implements WritableComparable<FlowBean> {

	private long upFlow;
	private long dFlow;
	private long sumFlow;

	//反序列化时，需要反射调用空参构造函数，所以要显示定义一个
	public FlowBean() {
	}

	public FlowBean(long upFlow, long dFlow) {
		this.upFlow = upFlow;
		this.dFlow = dFlow;
		this.sumFlow = upFlow + dFlow;
	}


	public void set(long upFlow, long dFlow) {
		this.upFlow = upFlow;
		this.dFlow = dFlow;
		this.sumFlow = upFlow + dFlow;
	}


	public long getUpFlow() {
		return upFlow;
	}

	public void setUpFlow(long upFlow) {
		this.upFlow = upFlow;
	}

	public long getdFlow() {
		return dFlow;
	}

	public void setdFlow(long dFlow) {
		this.dFlow = dFlow;
	}

	public long getSumFlow() {
		return sumFlow;
	}

	public void setSumFlow(long sumFlow) {
		this.sumFlow = sumFlow;
	}

	/**
	 * 序列化方法
	 */
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeLong(upFlow);
		out.writeLong(dFlow);
		out.writeLong(sumFlow);
	}

	/**
	 * 反序列化方法
	 * 注意：反序列化的顺序跟序列化的顺序完全一致
	 */
	@Override
	public void readFields(DataInput in) throws IOException {
		upFlow = in.readLong();
		dFlow = in.readLong();
		sumFlow = in.readLong();
	}

	@Override
	public String toString() {
		return upFlow + "\t" + dFlow + "\t" + sumFlow;
	}

	@Override
	public int compareTo(FlowBean o) {
		return this.sumFlow > o.getSumFlow() ? -1 : 1;    //从大到小, 当前对象和要比较的对象比, 如果当前对象大, 返回-1, 交换他们的位置(自己的理解)
	}
}
