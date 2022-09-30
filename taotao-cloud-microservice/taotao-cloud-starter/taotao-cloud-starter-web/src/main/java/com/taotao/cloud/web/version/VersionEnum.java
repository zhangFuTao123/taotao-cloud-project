
package com.taotao.cloud.web.version;

/**
 * 版本枚举
 *
 * @author shuigedeng
 * @version 2022.07
 * @since 2022-07-18 10:21:22
 */
public enum VersionEnum {
	V2021_01("2021.01", "初始化版本"),
	V2021_02("2021.02", "初始化版本"),
	V2021_03("2021.03", "初始化版本"),
	V2021_04("2021.04", "初始化版本"),
	V2021_05("2021.05", "初始化版本"),
	V2021_06("2021.06", "初始化版本"),
	V2021_07("2021.07", "初始化版本"),
	V2021_08("2021.08", "初始化版本"),
	V2021_09("2021.09", "初始化版本"),
	V2021_10("2021.10", "初始化版本"),
	V2021_11("2021.11", "初始化版本"),
	V2021_12("2021.12", "初始化版本"),
	V2022_01("2022.01", "初始化版本"),
	V2022_03("2022.03", "初始化版本"),
	V2022_04("2022.04", "初始化版本"),
	V2022_05("2022.05", "初始化版本"),
	V2022_06("2022.06", "初始化版本"),
	V2022_07("2022.10", "初始化版本"),
	V2022_08("2022.10", "初始化版本"),
	V2022_09("2022.10", "下一个版本"),
	V2022_10("2022.10", "下一个版本"),
	V2022_11("2022.11", "下一个版本"),
	V2022_12("2022.12", "下一个版本"),
	V2023_01("2023.01", "下一个版本"),
	V2023_02("2023.02", "下一个版本"),
	V2023_03("2023.03", "下一个版本"),
	V2023_04("2023.04", "下一个版本"),
	V2023_05("2023.05", "下一个版本"),
	V2023_06("2023.06", "下一个版本"),
	V2023_07("2023.07", "下一个版本"),
	V2023_08("2023.08", "下一个版本"),
	V2023_09("2023.09", "下一个版本"),
	V2023_10("2023.10", "下一个版本"),
	V2023_11("2023.11", "下一个版本"),
	V2023_12("2023.12", "下一个版本"),
	V2024_01("2024.01", "下一个版本"),
	V2024_02("2024.02", "下一个版本"),
	V2024_03("2024.03", "下一个版本"),
	V2024_04("2024.04", "下一个版本"),
	V2024_05("2024.05", "下一个版本"),
	V2024_06("2024.06", "下一个版本"),
	V2024_07("2024.07", "下一个版本"),
	V2024_08("2024.08", "下一个版本"),
	V2024_09("2024.09", "下一个版本"),
	V2024_10("2024.10", "下一个版本"),
	V2024_11("2024.11", "下一个版本"),
	V2024_12("2024.12", "下一个版本");

	/**
	 * code
	 */
	private final String code;
	/**
	 * 描述
	 */
	private final String info;

	private VersionEnum(String code, String info) {
		this.code = code;
		this.info = info;
	}

	public String getCode() {
		return this.code;
	}

	public String getInfo() {
		return this.info;
	}
}
