package com.oriental.finance.cat.monitor.common.enums.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：服务状况状态枚举
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/11 ProjectName:cat-monitor Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum ServiceStateStatusEnum {

    /**
     * 初始化
     */
    INIT("PAUSED", "初始化"),
    /**
     * 成功
     */
    SUCCESS("SUCCESS", "成功"),
    /**
     * 失败
     */
    FAIL("FAIL", "失败"),

    ;

    private String status;

    private String desc;

    /**
     * 是否是初始状态
     * @param status    指定状态
     * @return  true是|false否
     */
    public static boolean isInit(String status){
        return INIT.getStatus().equals(status);
    }

    /**
     * 是否不是初始状态
     * @param status    指定状态
     * @return  true不是|false是
     */
    public static boolean isNotInit(String status){
        return !isInit(status);
    }

}
