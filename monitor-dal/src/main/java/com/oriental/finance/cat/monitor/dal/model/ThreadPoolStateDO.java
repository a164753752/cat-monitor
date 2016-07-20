package com.oriental.finance.cat.monitor.dal.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 描述：线程池状况DO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/5 ProjectName:cat-monitor Version: 1.0
 */
@Data
@ToString
public class ThreadPoolStateDO extends BaseDO {

    private String platform;

    private String type;

    private Date recordDate;

    private Integer poolSize;

    private Integer corePoolSize;

    private Integer maxPoolSize;

    private Integer largestPoolSize;

    private Integer activeCount;

    private Long taskCount;

    private Long completedTaskCount;

    private Integer queueSize;

    private Long keepAliveTime;

    private String allowReleaseCodeIdle;

}