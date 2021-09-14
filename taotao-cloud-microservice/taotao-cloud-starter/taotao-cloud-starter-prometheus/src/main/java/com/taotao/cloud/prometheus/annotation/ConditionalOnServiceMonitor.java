package com.taotao.cloud.prometheus.annotation;

import com.taotao.cloud.prometheus.contition.OnServiceMonitorNoticeCondition;
import com.taotao.cloud.prometheus.contition.PrometheusEnabledCondition;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional({ PrometheusEnabledCondition.class, OnServiceMonitorNoticeCondition.class })
public @interface ConditionalOnServiceMonitor {

}