package com.oriental.finance.cat.monitor.dal.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 描述：连通性检测DO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/5 ProjectName:cat-monitor Version: 1.0
 */
@Data
@ToString
public class ApplyConnexityCheckDO extends BaseDO {

    /**
     * 应用平台：CIF
     */
    private String applyPlatform;

    /**
     * 连通性类型
     */
    private String connexityType;

    /**
     * 检测地址
     */
    private String checkUrl;

    /**
     * 检测状态：SUCCESS,TIMEOUT,FAIL
     */
    private String checkStatus;

    /**
     * 检测时间
     */
    private Date checkTime;

}