package com.oriental.finance.cat.monitor.biz.manager;

import com.oriental.finance.cat.monitor.biz.model.sys.JobConfigBO;
import org.quartz.SchedulerException;

import java.text.ParseException;
import java.util.List;

/**
 * 描述：Quartz服务管理
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
public interface QuartzManager {

    /**
     * 1、运行/加入计划任务
     *
     * @param scheduleJob             				JOB基本信息参数
     * @throws org.quartz.SchedulerException                    调度异常信息
     */
    void runJob(JobConfigBO scheduleJob) throws SchedulerException,ParseException,
            ClassNotFoundException;

    /**
     * 2、加入计划任务
     *
     * @param scheduleJob             				JOB基本信息参数
     * @throws org.quartz.SchedulerException	  	调度异常信息
     */
    void addJob(JobConfigBO scheduleJob) throws SchedulerException,ParseException,
            ClassNotFoundException ;

    /**
     * 3、暂停任务
     *
     * @param scheduleJob             				JOB基本信息参数
     * @throws org.quartz.SchedulerException	  	调度异常信息
     */
    void pauseJob(JobConfigBO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException;

    /**
     * 4、恢复/继续任务
     *
     * @param scheduleJob             				JOB基本信息参数
     * @throws org.quartz.SchedulerException	  	调度异常信息
     */
    void resumeJob(JobConfigBO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException;

    /**
     * 5、删除任务
     *
     * @param scheduleJob             				JOB基本信息参数
     * @throws org.quartz.SchedulerException	  	调度异常信息
     */
    boolean removeJob(JobConfigBO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException;

    /**
     * 6、立即运行任务
     *
     * @param scheduleJob							JOB基本信息参数
     * @throws org.quartz.SchedulerException		调度异常信息
     */
    void atOnceRunJob(JobConfigBO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException;

    /**
     * 7、计划中的任务
     *
     * @throws SchedulerException					调度异常信息
     * @return 										计划中的任务列表
     */
    List<JobConfigBO> scheduleJob() throws SchedulerException;

    /**
     * 8、运行中的任务
     *
     * @throws SchedulerException					调度异常信息
     * @return 										运行中的任务列表
     */
    List<JobConfigBO> runningJob() throws SchedulerException;

    /**
     * 9、根据任务名、组名查询任务状态
     * @param scheduleJob							JOB基本信息参数
     * @return										任务状态
     * @throws SchedulerException					运行中的任务列表
     */
    String scheduleJob(JobConfigBO scheduleJob) throws SchedulerException;

    /**
     * 10、重新加载定时任务
     *
     * @param scheduleJob
     * @throws SchedulerException
     * @throws java.text.ParseException
     * @throws ClassNotFoundException
     */
    void reloadJob(JobConfigBO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException;


}
