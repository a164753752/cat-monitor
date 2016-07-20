package com.oriental.finance.cat.monitor.common.enums.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：系统操作信息枚举
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/11 ProjectName:cat-monitor Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum SysOperatorEnum {

    SYSTEM("CAT-MONITOR","CAT监控系统"),

    ;

    private final String code;

    private final String desc;
}
