package com.oriental.finance.cat.monitor.common.enums.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：连通性检测状态枚举
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/12 ProjectName:cat-monitor Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum ConnexityCheckStatusEnum {

    NORMAL("NORMAL","正常"),

    TIMEOUT("TIMEOUT","超时"),

    ABNORMAL("ABNORMAL","异常"),
    ;

    private String status;

    private String desc;

    /**
     * 结果映射检测状态
     * @param result    结果
     * @return  检测状态
     */
    public static ConnexityCheckStatusEnum resultMappingStatus(boolean result){
        if(result){
            return NORMAL;
        }
        return ABNORMAL;
    }

}
