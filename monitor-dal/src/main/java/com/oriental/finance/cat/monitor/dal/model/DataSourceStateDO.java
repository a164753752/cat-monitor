package com.oriental.finance.cat.monitor.dal.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 描述：数据源状况DO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/5 ProjectName:cat-monitor Version: 1.0
 */
@Data
@ToString
public class DataSourceStateDO extends BaseDO {

    private String platform;

    private Date recordDate;

    private String userName;

    private String url;

    private String driverClassName;

    private String autoCommit;

    private Integer numActive;

    private Integer numIdle;

    private Integer initialSize;

    private Integer maxSize;

    private Integer minIdle;

    private Integer maxIdle;

    private Long maxWait;

    private Long idleAliveTime;

    private String testOnBorrow;

    private Long idleReleaseTime;

}