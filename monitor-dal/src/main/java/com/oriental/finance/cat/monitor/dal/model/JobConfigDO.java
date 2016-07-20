package com.oriental.finance.cat.monitor.dal.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 描述：定时Job配置DO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/5 ProjectName:cat-monitor Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JobConfigDO extends BaseDO {

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务所属组
     */
    private String jobGroup;

    /**
     * 定时JOB类名或者bean名
     */
    private String jobClass;

    /**
     * 任务运行状态
     */
    private String status;

    /**
     * 任务运行时间表达式(运行规则)
     */
    private String jobCronExpress;

    /**
     * 任务执行次数,默认0
     */
    private Integer jobExecCount;

    /**
     * 失败允许重试次数
     */
    private Integer retryTimes;

    /**
     * 最后执行时间
     */
    private Date lastExecTime;

    /**
     * 下次执行时间
     */
    private Date nextExecTime;

    /**
     * 任务执行花费时间
     */
    private Date jobUsedTime;

    /**
     * 是否允许多机处理该任务：YES允许，默认NO不允许
     */
    private String isClusters;

    /**
     * 任务执行指定命令，可配置为日志搜索命令配置表的KEY
     */
    private String jobCommandKey;

}