/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.common.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 跟踪类变动比较
 *
  * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 19:41:13
 */
public class BeanDiff {

	/**
	 * 变更字段
	 */
	@JsonIgnore
	private transient Set<String> fields = new HashSet<>();
	/**
	 * 旧值
	 */
	@JsonIgnore
	private transient Map<String, Object> oldValues = new HashMap<>();
	/**
	 * 新值
	 */
	@JsonIgnore
	private transient Map<String, Object> newValues = new HashMap<>();

	public Set<String> getFields() {
		return fields;
	}

	public Map<String, Object> getOldValues() {
		return oldValues;
	}

	public Map<String, Object> getNewValues() {
		return newValues;
	}

	public void setFields(Set<String> fields) {
		this.fields = fields;
	}

	public void setOldValues(Map<String, Object> oldValues) {
		this.oldValues = oldValues;
	}

	public void setNewValues(Map<String, Object> newValues) {
		this.newValues = newValues;
	}
}