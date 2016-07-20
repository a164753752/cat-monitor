package com.oriental.finance.cat.monitor.work;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 描述：抽象的JobWorker
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Slf4j
public abstract class AbstractJobWorker implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            beforeWorker(jobExecutionContext);

            doWorker(jobExecutionContext);

            afterWorker(jobExecutionContext);
        }catch (Exception e){
            log.error("call AbstractJobWorker execute Exception:{}",e);
        }finally {
            fail(jobExecutionContext);
        }
    }

    /**
     * 作业前
     * @param context   Job上下文
     */
    protected abstract void beforeWorker(JobExecutionContext context);

    /**
     * 实际作业
     * @param context   Job上下文
     */
    protected abstract void doWorker(JobExecutionContext context);

    /**
     * 作业后
     * @param context   Job上下文
     */
    protected abstract void afterWorker(JobExecutionContext context);

    /**
     * 作业失败处理   TODO
     * @param context   Job上下文
     */
    private void fail(JobExecutionContext context){
//        log.error("call AbstractJobWorker worker fail...");
    }
}
