package com.oriental.finance.cat.monitor.biz.component.job;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;


/**
 * 描述：Job工厂，重写了spring job 工厂，解决job无法使用自动注入问题
 * <p>
 * #是否是禁用状态
 * #是否是可用状态
 * </p>
 * User: lyd Date: 2016/7/5 ProjectName:cat-monitor Version: 1.0
 */
@Component
public class JobFactory extends AdaptableJobFactory {

    /**
     * The one of Spring BeanFactory
     */
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    /**
     * 1、JOB 注入
	 *
     * @param bundle 		TriggerFiredBundle
     * @return Object 		Autowire Bean
     * @throws Exception	异常
     */
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }

}
