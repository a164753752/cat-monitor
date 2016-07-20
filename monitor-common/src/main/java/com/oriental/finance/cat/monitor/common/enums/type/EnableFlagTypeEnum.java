package com.oriental.finance.cat.monitor.common.enums.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：可用标识类型
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/11 ProjectName:cat-monitor Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum EnableFlagTypeEnum {

    ENABLE("ENABLE","可用"),

    UNABLE("UNABLE","不可用"),
    ;

    private String type;

    private String desc;

}
