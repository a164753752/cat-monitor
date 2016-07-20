package com.oriental.finance.cat.monitor.biz.component.job;

import com.oriental.finance.cat.monitor.biz.manager.JobConfigManager;
import com.oriental.finance.cat.monitor.biz.manager.QuartzManager;
import com.oriental.finance.cat.monitor.biz.model.sys.JobConfigBO;
import com.oriental.finance.cat.monitor.common.enums.status.JobStatusEnum;
import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Component
@Slf4j
public class JobDispatcher {

    @Autowired
    private QuartzManager quartzManager;

    @Autowired
    private JobConfigManager jobConfigManager;

    @PostConstruct
    private void loadingJobRun() throws Exception {
        log.info("call JobDispatcher loadingJobRun start");
        List<JobConfigBO> jobConfigBOList = jobConfigManager.findByCondition(null);
        log.info("call JobDispatcher loadingJobRun jobConfigBOList.size:{}",jobConfigBOList.size());
        for(JobConfigBO jobConfigBO : jobConfigBOList){
            dispatchJob(jobConfigBO);
        }
        log.info("call JobDispatcher loadingJobRun end");
    }

    /**
     * 分配Job
     * @param jobConfigBO   Job配置信息
     */
    public void dispatchJob(JobConfigBO jobConfigBO) throws Exception {
        log.info("call JobDispatcher dispatchJob jobConfigBO:{}", jobConfigBO);
        JobStatusEnum jobStatusEnum = JobStatusEnum.convertStatus(jobConfigBO.getStatus());
        switch (jobStatusEnum) {
            /** 未加载 **/
            case UN_LOADING:
                log.info("call JobDispatcher dispatchJob unload");
                quartzManager.removeJob(jobConfigBO);
                break;
            /** 暂停 */
            case PAUSED:
                log.info("call JobDispatcher dispatchJob pauseJob");
                quartzManager.pauseJob(jobConfigBO);
                break;
            /** 恢复 */
            case RECOVER:
                log.info("call JobDispatcher dispatchJob resumeJob");
                quartzManager.resumeJob(jobConfigBO);
                break;
            /** 已加载 */
            case LOADING:
                log.info("call JobDispatcher dispatchJob runJob");
                quartzManager.runJob(jobConfigBO);
                break;
            /** 立即执行一次 */
            case RUNNING:
                log.info("call JobDispatcher dispatchJob atOnceRunJob");
                quartzManager.atOnceRunJob(jobConfigBO);
                break;
            /** 禁用 */
            case FORBIDDEN:
                log.info("call JobDispatcher dispatchJob removeJob");
                quartzManager.removeJob(jobConfigBO);
                break;
            default:
                throw new BizException(BizCodeEnum.BIZ_CODE_403003);
        }
    }

}
