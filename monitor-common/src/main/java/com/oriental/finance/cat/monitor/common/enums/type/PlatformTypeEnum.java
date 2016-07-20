package com.oriental.finance.cat.monitor.common.enums.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：平台枚举
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/11 ProjectName:cat-monitor Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum PlatformTypeEnum {

    /**
     * 客户信息系统
     */
    CIF("CIF","客户信息系统"),

    /**
     * 监控系统
     */
    MONITOR("MONITOR","监控系统")

    ;

    private String code;

    private String desc;

}
