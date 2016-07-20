package com.oriental.finance.cat.external.anotation;

import com.oriental.finance.cat.external.enums.MonitorItems;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述：监控项注解
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/11 ProjectName:cat-monitor Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Monitor {

    public MonitorItems items();

}
