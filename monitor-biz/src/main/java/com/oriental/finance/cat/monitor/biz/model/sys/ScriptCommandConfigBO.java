package com.oriental.finance.cat.monitor.biz.model.sys;

import com.oriental.finance.cat.monitor.biz.model.BaseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 描述：日志搜索命令配置BO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/5 ProjectName:cat-monitor Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ScriptCommandConfigBO extends BaseBO {

    /**
     * 命令的KEY，命名简称(英文命名)
     */
    private String commandKey;

    /**
     * 实际执行的命令
     */
    private String command;

}