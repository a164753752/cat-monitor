package com.oriental.finance.cat.monitor.common.enums.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：是否标识枚举
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/11 ProjectName:cat-monitor Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum YesOrNoEnum {

    YES("YES","是"),

    NO("NO","否"),

    ;

    private final String code;

    private final String desc;
}
