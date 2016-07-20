package com.oriental.finance.cat.monitor.common.enums.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：连通性检测类型
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/12 ProjectName:cat-monitor Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum ConnexityCheckTypeEnum {

    /**
     * HTTP方式
     */
    HTTP("HTTP","HTTP方式"),

    /**
     * DUBBO方式
     */
    DUBBO("DUBBO","DUBBO方式"),

    /**
     * SOCKET方式
     */
    SOCKET("SOCKET","SOCKET方式"),

    /**
     * PING方式
     */
    PING("PING","PING方式"),

    ;

    private String type;

    private String desc;

}
