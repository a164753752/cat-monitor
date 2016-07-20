package com.oriental.finance.cat.monitor.web.model.sys;

import com.oriental.finance.cat.monitor.web.model.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

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
public class ScriptCommandConfigVO extends BaseVO {

    /**
     * 命令的KEY，命名简称(英文命名)
     */
    @NotNull(message = "命令的KEY不能为空")
    private String commandKey;

    /**
     * 实际执行的命令
     */
    @NotNull(message = "命令不能为空")
    private String command;

}