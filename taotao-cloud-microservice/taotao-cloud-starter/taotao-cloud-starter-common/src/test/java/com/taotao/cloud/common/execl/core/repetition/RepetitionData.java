package com.taotao.cloud.common.execl.core.repetition;

import com.alibaba.excel.annotation.ExcelProperty;



/**

 */
public class RepetitionData {
    @ExcelProperty("字符串")
    private String string;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
}
