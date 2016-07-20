package com.oriental.finance.cat.monitor.dal.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 描述：服务状况DO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/5 ProjectName:cat-monitor Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ServiceStateDO extends BaseDO {

    /**
     * 服务记录日期：YYYYMMDD
     */
    private Date serviceDate;

    /**
     *  服务类型：LOGIN登录，OPEN开户，AGREE_APPLY协议申请，AGREE_SIGN协议签约，AGREE_UNSIGN协议解约，PWD_AUTH密码鉴权，
     */
    private String serviceType;

    /**
     *  服务路径(接口地址)
     */
    private String servicePath;

    /**
     *  该服务所在的平台：CIF客户平台
     */
    private String servicePlatform;

    /**
     *  服务日志ID
     */
    private String traceLogId;

    /**
     *  服务开始执行时间(精确到毫秒):YYYYMMDDhhmmsss
     */
    private Date startExecuteTime;

    /**
     *  服务执行结束时间(毫秒):YYYYMMDDhhmmsss
     */
    private Date endExecuteTime;

    /**
     * 服务具体执行时间(毫秒)
     */
    private long executeTime;

    /**
     * 服务状态：INIT请求，SUCCESS成功，FAILD失败
     */
    private String serviceStatus;

    /**
     * 服务响应码
     */
    private String serviceRespCode;

    /**
     * 服务响应描述
     */
    private String serviceRespDesc;

    /**
     * 服务关联号，如：协议申请和协议签约可以通过此关联起来
     */
    private String relationNo;

}