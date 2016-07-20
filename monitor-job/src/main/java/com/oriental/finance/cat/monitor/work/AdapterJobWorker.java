package com.oriental.finance.cat.monitor.work;

import com.oriental.finance.cat.monitor.work.cif.helper.JobWorkerHelper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述：JobWorker适配
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Slf4j
@Component
public class AdapterJobWorker extends AbstractJobWorker{

    /**
     * 定时作业Helper
     */
    @Autowired
    private JobWorkerHelper jobWorkerHelper;

    @Override
    protected void beforeWorker(JobExecutionContext context) {
        if(ifCheckJobCommandKey()){
            jobWorkerHelper.checkCommandKey(context);
        }
    }

    @Override
    protected void doWorker(JobExecutionContext context) {
    }

    @Override
    protected void afterWorker(JobExecutionContext context) {
    }

    /**
     * 是否检查Job的脚本命令key
     * @return  true是|false否
     */
    protected boolean ifCheckJobCommandKey(){
        return false;
    }
}
