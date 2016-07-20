package com.oriental.finance.cat.monitor.dal.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 描述：日志搜索命令配置DO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/5 ProjectName:cat-monitor Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ScriptCommandConfigDO extends BaseDO {

    /**
     * 命令的KEY，命名简称(英文命名)
     */
    private String commandKey;

    /**
     * 实际执行的命令
     */
    private String command;

}