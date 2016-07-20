package com.oriental.finance.cat.monitor.work.cif;

import com.oriental.finance.cat.monitor.biz.external.cif.CifSysStateService;
import com.oriental.finance.cat.monitor.biz.manager.ThreadPoolStateManager;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.ThreadPoolStateBO;
import com.oriental.finance.cat.monitor.work.AdapterJobWorker;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：线程池状况定时监控Worker
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/15 ProjectName:cat-monitor Version: 1.0
 */
@Service
@Slf4j
public class ThreadPoolStateJobWorker extends AdapterJobWorker {

    /**
     * CIF应用状况服务
     */
    @Autowired
    private CifSysStateService cifSysStateService;

    /**
     * 线程池状况管理服务
     */
    @Autowired
    private ThreadPoolStateManager threadPoolStateManager;


    @Override
    protected void doWorker(JobExecutionContext context) {
        log.info("call ThreadPoolStateJobWorker.doWorker context:{}",context);
        ThreadPoolStateBO providerThreadStateBO = cifSysStateService.getProviderThreadState();
        threadPoolStateManager.addThreadPoolState(providerThreadStateBO);
    }
}
