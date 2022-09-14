package com.taotao.cloud.sensitive.sensitive.sensitive.annotation;


import com.taotao.cloud.sensitive.sensitive.sensitive.annotation.metadata.SensitiveStrategy;
import com.taotao.cloud.sensitive.sensitive.sensitive.api.impl.SensitiveStrategyBuiltIn;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模拟用户使用禁止使用的 SensitiveStrategyBuiltIn 来声明策略
 */
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveStrategy(SensitiveStrategyBuiltIn.class)
public @interface SensitiveErrorSystemBuiltIn {
}
