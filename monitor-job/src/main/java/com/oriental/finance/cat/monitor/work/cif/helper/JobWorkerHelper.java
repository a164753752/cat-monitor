package com.oriental.finance.cat.monitor.work.cif.helper;

import com.oriental.finance.cat.monitor.biz.cache.Caches;
import com.oriental.finance.cat.monitor.biz.model.sys.JobConfigBO;
import com.oriental.finance.cat.monitor.biz.model.sys.ScriptCommandConfigBO;
import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.common.enums.system.JobCodeEnum;
import com.oriental.finance.cat.monitor.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述：定时作业Helper
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/15 ProjectName:cat-monitor Version: 1.0
 */
@Component
@Slf4j
public class JobWorkerHelper {

    /**
     * 缓存服务
     */
    @Autowired
    private Caches caches;

    /**
     * 校验定时作业脚本命令
     * @param context   Job上下文
     */
    public void checkCommandKey(JobExecutionContext context){
        log.info("call ifCheckJobCommandKey context:{}", context);
        JobConfigBO jobConfigBO = (JobConfigBO) context.getJobDetail().getJobDataMap().get(JobCodeEnum.JOB_DATA_CONFIG_MAP.getKey());
        ScriptCommandConfigBO scriptCommandConfigBO = caches.getCommandConfigCache().get(jobConfigBO.getJobCommandKey());
        //脚本执行命令为空跳出
        if(StringUtils.isBlank(jobConfigBO.getJobCommandKey()) || null == scriptCommandConfigBO){
            throw new BizException(BizCodeEnum.BIZ_CODE_403002);
        }
        context.getJobDetail().getJobDataMap().put(JobCodeEnum.JOB_DATA_COMMAND_MAP.getKey(),scriptCommandConfigBO);
    }

}
