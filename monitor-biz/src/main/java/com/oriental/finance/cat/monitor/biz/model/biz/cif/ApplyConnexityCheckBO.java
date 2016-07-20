package com.oriental.finance.cat.monitor.biz.model.biz.cif;

import com.oriental.finance.cat.monitor.biz.model.BaseBO;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 描述：连通性检测BO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/5 ProjectName:cat-monitor Version: 1.0
 */
@Data
@ToString(callSuper = true)
public class ApplyConnexityCheckBO extends BaseBO {

    /**
     * 应用平台
     * @see com.oriental.finance.cat.monitor.common.enums.type.PlatformTypeEnum
     */
    private String applyPlatform;

    /**
     * 连通性类型
     * @see com.oriental.finance.cat.monitor.common.enums.type.ConnexityCheckTypeEnum
     */
    private String connexityType;

    /**
     * 检测地址
     */
    private String checkUrl;

    /**
     * 检测状态
     * @see com.oriental.finance.cat.monitor.common.enums.status.ConnexityCheckStatusEnum
     */
    private String checkStatus;

    /**
     * 检测时间
     */
    private Date checkTime;

}