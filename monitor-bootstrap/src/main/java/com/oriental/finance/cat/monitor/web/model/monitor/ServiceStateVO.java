package com.oriental.finance.cat.monitor.web.model.monitor;

import com.oriental.finance.cat.monitor.web.model.BaseVO;
import lombok.Data;
import lombok.ToString;


/**
 * 描述：服务状况VO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/12 ProjectName:cat-monitor Version: 1.0
 */
@Data
@ToString(callSuper = true)
public class ServiceStateVO extends BaseVO {

    /**
     * 服务记录日期：YYYYMMDD
     */
    private String serviceDate;

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
     * 服务状态：INIT请求，SUCCESS成功，FAILD失败
     */
    private String serviceStatus;

    /**
     * 服务响应码
     */
    private String serviceRespCode;

    /**
     * 服务关联号，如：协议申请和协议签约可以通过此关联起来
     */
    private String relationNo;
}
