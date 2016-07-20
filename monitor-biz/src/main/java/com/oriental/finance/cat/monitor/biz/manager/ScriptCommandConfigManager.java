package com.oriental.finance.cat.monitor.biz.manager;

import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.model.sys.ScriptCommandConfigBO;

import java.util.List;

/**
 * 描述：脚本命令配置信息服务管理
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
public interface ScriptCommandConfigManager {

    /**
     * 添加脚本命令配置信息
     * @param scriptCommandConfigBO    脚本命令配置信息
     */
    public void addScriptCommandConfig(ScriptCommandConfigBO scriptCommandConfigBO);

    /**
     * 删除脚本命令配置信息
     * @param id    脚本命令配置id
     */
    public void deleteScriptCommandConfig(String id);

    /**
     * 修改脚本命令配置信息
     * @param scriptCommandConfigBO    脚本命令配置信息
     */
    public void updateScriptCommandConfig(ScriptCommandConfigBO scriptCommandConfigBO);

    /**
     * 根据条件查询脚本命令配置信息
     * @param scriptCommandConfigBO    脚本命令配置信息
     * @return List<ScriptCommandConfigBO>
     */
    public List<ScriptCommandConfigBO> findByCondition(ScriptCommandConfigBO scriptCommandConfigBO);

    /**
     * 根据条件分页查询脚本命令配置信息
     * @param scriptCommandConfigBO    脚本命令配置信息
     * @return PageInfo<ScriptCommandConfigBO>
     */
    public PageInfo<ScriptCommandConfigBO> findByConditionByPaging(ScriptCommandConfigBO scriptCommandConfigBO);
}
