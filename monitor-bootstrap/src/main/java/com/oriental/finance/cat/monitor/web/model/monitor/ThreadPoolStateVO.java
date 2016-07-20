package com.oriental.finance.cat.monitor.web.model.monitor;

import com.oriental.finance.cat.monitor.web.model.BaseVO;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 描述：线程池状况BO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/14 ProjectName:cat-monitor Version: 1.0
 */
@Data
@ToString
public class ThreadPoolStateVO extends BaseVO {

    /**
     * 平台
     * @see com.oriental.finance.cat.monitor.common.enums.type.PlatformTypeEnum
     */
    private String platform;

    /**
     * 作用类型
     */
    private String type;

    /**
     * 记录日期
     */
    private Date recordDate;

    /**
     * 线程池中当前的线程数
     */
    private String poolSize;

    /**
     * 池中最小线程数,核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）
     */
    private String corePoolSize;

    /**
     * 线程池最大能容忍的线程数
     */
    private String maxPoolSize;

    /**
     * 线程池中曾经出现过的最大线程数
     */
    private String largestPoolSize;

    /**
     * 正在作业处理线程数
     */
    private String activeCount;

    /**
     * 任务数
     */
    private long taskCount;

    /**
     * 记录已经执行完毕的任务个数
     */
    private long completedTaskCount;

    /**
     * 线程池缓存队列大小
     */
    private String queueSize;

    /**
     * 当线程池中的线程数大于corePoolSize才起作用
     * 空闲线程存活时间(毫秒)
     */
    private long keepAliveTime;

    /**
     *  true 允许线程数不大于corePoolSize也会销毁线程
     */
    private String allowReleaseCodeIdle;

}
