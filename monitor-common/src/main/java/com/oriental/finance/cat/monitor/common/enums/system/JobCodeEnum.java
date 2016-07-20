package com.oriental.finance.cat.monitor.common.enums.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum JobCodeEnum {

    /**
     * CAT监控任务数据
     */
    JOB_DATA_CONFIG_MAP("CAT.MONITOR.JOB.DATA.CONFIG", "CAT监控任务数据"),

    /**
     * Job脚本命令信息
     */
    JOB_DATA_COMMAND_MAP("CAT.MONITOR.JOB.DATA.COMMAND", "CAT监控任务数据"),

    ;

    private final String key;

    private final String desc;

}
