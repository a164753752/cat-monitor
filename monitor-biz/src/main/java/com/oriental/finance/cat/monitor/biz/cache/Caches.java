package com.oriental.finance.cat.monitor.biz.cache;

import com.oriental.finance.cat.monitor.biz.manager.ScriptCommandConfigManager;
import com.oriental.finance.cat.monitor.biz.model.sys.ScriptCommandConfigBO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：应用缓存
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/12 ProjectName:cat-monitor Version: 1.0
 */
@Component
@Slf4j

public class Caches {

    /**
     * 脚本命令配置Mapper
     */
    @Autowired
    private ScriptCommandConfigManager scriptCommandConfigManager;

    /**
     * 脚本命令配置缓存Map
     */
    @Getter
    private Map<String,ScriptCommandConfigBO> commandConfigCache = new HashMap<>();


    @PostConstruct
    public void init(){
        //脚本命令配置缓存
        initScriptCommandConfig();
    }

    /**
     * 脚本命令配置缓存
     */
    private void initScriptCommandConfig(){
        List<ScriptCommandConfigBO> scriptCommandConfigBOList = scriptCommandConfigManager.findByCondition(null);
        commandConfigCache.clear();
        for(ScriptCommandConfigBO scriptCommandConfigBO : scriptCommandConfigBOList){
            commandConfigCache.put(scriptCommandConfigBO.getCommandKey(),scriptCommandConfigBO);
        }
        log.info("call initScriptCommandConfig success!!!");
    }
}
