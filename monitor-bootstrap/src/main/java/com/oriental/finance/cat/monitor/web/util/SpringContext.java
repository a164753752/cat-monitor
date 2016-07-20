package com.oriental.finance.cat.monitor.web.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 描述：spring上下文
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/12 ProjectName:cat-monitor Version: 1.0
 */
@Slf4j
@Component
public class SpringContext implements ApplicationContextAware{

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
        log.info("call setApplicationContext applicationContext:{}",SpringContext.applicationContext);
    }

    public static <T> T getBean(String bean,Class<T> clazz){
        return applicationContext.getBean(bean,clazz);
    }
}
