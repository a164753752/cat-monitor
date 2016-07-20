package com.oriental.finance.cat.monitor.biz.manager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.manager.ScriptCommandConfigManager;
import com.oriental.finance.cat.monitor.common.enums.system.PageEnum;
import com.oriental.finance.cat.monitor.common.util.asserts.AssertResultUtils;
import com.oriental.finance.cat.monitor.biz.model.sys.ScriptCommandConfigBO;
import com.oriental.finance.cat.monitor.common.enums.system.SysOperatorEnum;
import com.oriental.finance.cat.monitor.common.enums.type.EnableFlagTypeEnum;
import com.oriental.finance.cat.monitor.common.util.BeanCopyUtils;
import com.oriental.finance.cat.monitor.common.util.DateUtils;
import com.oriental.finance.cat.monitor.dal.mapper.ScriptCommandConfigMapper;
import com.oriental.finance.cat.monitor.dal.model.ScriptCommandConfigDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述：脚本命令配置信息服务管理实现
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Slf4j
@Service
public class ScriptCommandConfigManagerImpl implements ScriptCommandConfigManager {

    /**
     * 脚本命令配置Mapper
     */
    @Autowired
    private ScriptCommandConfigMapper scriptCommandConfigMapper;

    /**
     * 添加脚本命令配置信息
     * @param scriptCommandConfigBO    脚本命令配置信息
     */
    public void addScriptCommandConfig(ScriptCommandConfigBO scriptCommandConfigBO){
        log.info("call addScriptCommandConfig scriptCommandConfigBO:{}",scriptCommandConfigBO);
        scriptCommandConfigBO.setCreateBy(SysOperatorEnum.SYSTEM.getCode());
        scriptCommandConfigBO.setUpdateBy(SysOperatorEnum.SYSTEM.getCode());
        scriptCommandConfigBO.setUpdateAt(DateUtils.getCurrentDate());
        scriptCommandConfigBO.setEnableFlag(EnableFlagTypeEnum.ENABLE.getType());
        ScriptCommandConfigDO scriptCommandConfigDO = BeanCopyUtils.copy(scriptCommandConfigBO, ScriptCommandConfigDO.class);
        int result = scriptCommandConfigMapper.insert(scriptCommandConfigDO);
        log.info("call addScriptCommandConfig result:{}",result);
        AssertResultUtils.checkUpdNum(result);
    }

    /**
     * 删除脚本命令配置信息
     * @param id    脚本命令配置id
     */
    @Override
    public void deleteScriptCommandConfig(String id) {
        log.info("call deleteScriptCommandConfig id:{}",id);

        //查询脚本命令配置信息
        ScriptCommandConfigDO scriptCommandConfigDO = scriptCommandConfigMapper.findByPrimaryKey(Integer.parseInt(id));
        log.info("call deleteScriptCommandConfig id:{},scriptCommandConfigDO:{}",id,scriptCommandConfigDO);
        AssertResultUtils.checkDBResult(null != scriptCommandConfigDO);

        //删除脚本命令配置信息
        int result = scriptCommandConfigMapper.deleteByPrimaryKey(Integer.parseInt(id));
        log.info("call deleteScriptCommandConfig result:{}",result);
        AssertResultUtils.checkUpdNum(result);
    }

    /**
     * 修改脚本命令配置信息
     * @param scriptCommandConfigBO    脚本命令配置信息
     */
    @Override
    public void updateScriptCommandConfig(ScriptCommandConfigBO scriptCommandConfigBO) {
        log.info("call updateScriptCommandConfig scriptCommandConfigBO:{}",scriptCommandConfigBO);

        //查询脚本命令配置信息
        ScriptCommandConfigDO scriptCommandConfigDO = scriptCommandConfigMapper.findByPrimaryKey(scriptCommandConfigBO.getId());
        log.info("call updateScriptCommandConfig scriptCommandConfigDO:{}",scriptCommandConfigDO);
        AssertResultUtils.checkDBResult(null != scriptCommandConfigDO);

        //更新脚本命令配置信息
        int result = scriptCommandConfigMapper.update(BeanCopyUtils.copy(scriptCommandConfigBO,ScriptCommandConfigDO.class));
        log.info("call updateScriptCommandConfig result:{}",result);
        AssertResultUtils.checkUpdNum(result);
    }

    /**
     * 根据条件查询脚本命令配置信息
     * @param scriptCommandConfigBO    脚本命令配置查询条件
     * @return List<ScriptCommandConfigBO>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ScriptCommandConfigBO> findByCondition(ScriptCommandConfigBO scriptCommandConfigBO){
        log.info("call findByConditionPaging scriptCommandConfigBO:{}",scriptCommandConfigBO);
        Map<String,String> condition = BeanCopyUtils.copy(scriptCommandConfigBO, Map.class);
        List<ScriptCommandConfigDO> scriptCommandConfigDOList = scriptCommandConfigMapper.findByCondition(condition);
        log.info("call findByConditionPaging scriptCommandConfigDOList.size:{}",scriptCommandConfigDOList.size());
        return BeanCopyUtils.copyList(scriptCommandConfigDOList,ScriptCommandConfigBO.class);
    }

    /**
     * 根据条件分页查询脚本命令配置信息
     * @param scriptCommandConfigBO    脚本命令配置信息
     * @return PageInfo<ScriptCommandConfigBO>
     */
    @SuppressWarnings("unchecked")
    @Override
    public PageInfo<ScriptCommandConfigBO> findByConditionByPaging(ScriptCommandConfigBO scriptCommandConfigBO) {
        log.info("call findByConditionPaging scriptCommandConfigBO:{}",scriptCommandConfigBO);
        Map<String,String> condition = BeanCopyUtils.copy(scriptCommandConfigBO, Map.class);
        if(null != scriptCommandConfigBO.getPageNum()){
            PageHelper.startPage(scriptCommandConfigBO.getPageNum(),scriptCommandConfigBO.getPageSize(),PageEnum.ORDER_BY_UPDATE_AT.getCode());
        }
        List<ScriptCommandConfigDO> scriptCommandConfigDOList = scriptCommandConfigMapper.findByCondition(condition);
        PageInfo result = new PageInfo<>(scriptCommandConfigDOList,Integer.parseInt(PageEnum.NAV_PAGE.getCode()));
        List<ScriptCommandConfigBO> scriptCommandConfigBOList = BeanCopyUtils.copyList(result.getList(),ScriptCommandConfigBO.class);
        result.setList(scriptCommandConfigBOList);
        log.info("call findByConditionPaging result:{}", result);
        return result;
    }

}
