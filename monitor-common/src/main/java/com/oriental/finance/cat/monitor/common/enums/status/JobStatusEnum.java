package com.oriental.finance.cat.monitor.common.enums.status;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 描述：状态枚举类
 * <p>
 * #是否是禁用状态
 * #是否是可用状态
 * </p>
 * User: lyd Date: 2016/7/5 ProjectName:cat-monitor Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum JobStatusEnum {

    /** 未加载 */
    UN_LOADING("UN_LOADING", "未加载"),

    /** 暂停 */
    PAUSED("PAUSED", "暂停"),

    /** 恢复 */
    RECOVER("RECOVER", "恢复"),

    /** 加载 */
    LOADING("LOADING", "加载"),

    /** 立即执行一次 */
    RUNNING("RUNNING", "立即执行一次"),

    /** 禁用 */
    FORBIDDEN("FORBIDDEN", "禁用"),

    ;

    /** 编码 */
    private final String code;

    /** 编码描述 */
    private final String desc;

    /**
     * 是否是禁用状态
     * @param jobStatus job状态
     * @return  true是|false否
     */
    public static boolean isDisable(String jobStatus){
        return FORBIDDEN.getCode().equals(jobStatus);
    }

    /**
     * 是否是可用状态
     * @param jobStatus job状态
     * @return  true是|false不是
     */
    public static boolean isUsable(String jobStatus){
        return !isDisable(jobStatus);
    }

    /**
     * 转换状态
     * @param status    指定状态
     * @return  JobStatusEnum
     */
    public static JobStatusEnum convertStatus(String status){
        for(JobStatusEnum jobStatusEnum : JobStatusEnum.values()){
            if(jobStatusEnum.getCode().equals(status)){
                return jobStatusEnum;
            }
        }
        return null;
    }

}
