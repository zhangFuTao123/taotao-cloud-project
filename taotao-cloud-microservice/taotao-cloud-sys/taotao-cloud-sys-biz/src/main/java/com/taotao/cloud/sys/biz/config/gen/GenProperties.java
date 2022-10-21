package com.taotao.cloud.sys.biz.config.gen;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 代码生成相关配置
 *
 * @author shuigedeng
 * @version 2022.09
 * @since 2022-09-22 09:41:54
 */
@Component
@ConfigurationProperties(prefix = "sys.gen")
@EnableConfigurationProperties({GenProperties.class})
public class GenProperties {
	/**
	 * 作者
	 */
	public static String author;

	/**
	 * 生成包路径
	 */
	public static String packageName;

	/**
	 * 自动去除表前缀，默认是false
	 */
	public static boolean autoRemovePre;

	/**
	 * 表前缀(类名不会包含表前缀)
	 */
	public static String tablePrefix;

	public static String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		GenProperties.author = author;
	}

	public static String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		GenProperties.packageName = packageName;
	}

	public static boolean getAutoRemovePre() {
		return autoRemovePre;
	}

	public void setAutoRemovePre(boolean autoRemovePre) {
		GenProperties.autoRemovePre = autoRemovePre;
	}

	public static String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		GenProperties.tablePrefix = tablePrefix;
	}
}
