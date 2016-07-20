package com.oriental.finance.cat.monitor.web.model.monitor;

import com.oriental.finance.cat.monitor.web.model.BaseVO;
import lombok.Data;
import lombok.ToString;

/**
 * 描述：应用连通性VO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/12 ProjectName:cat-monitor Version: 1.0
 */
@Data
@ToString(callSuper = true)
public class ApplyConnexityCheckVO extends BaseVO {

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
    private String checkTime;

}
