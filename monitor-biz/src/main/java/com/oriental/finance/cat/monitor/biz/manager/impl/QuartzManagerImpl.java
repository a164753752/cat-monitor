package com.oriental.finance.cat.monitor.biz.manager.impl;

import com.oriental.finance.cat.monitor.biz.manager.QuartzManager;
import com.oriental.finance.cat.monitor.biz.model.sys.JobConfigBO;
import com.oriental.finance.cat.monitor.common.enums.system.JobCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 描述：Quartz服务管理实现
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Slf4j
@Service
public class QuartzManagerImpl implements QuartzManager {

    /**
     * Spring Scheduler
     */
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 1、运行/加入计划任务
     *
     * @param jobConfigBO                            JOB基本信息参数
     * @throws org.quartz.SchedulerException         调度异常
     * @throws java.text.ParseException			     解析异常
     * @throws ClassNotFoundException				 类不存在异常
     */
    @Override
    public void runJob(JobConfigBO jobConfigBO) throws SchedulerException, ParseException, ClassNotFoundException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobConfigBO.getJobName(), jobConfigBO.getJobGroup());
        CronTrigger trigger = (CronTrigger) schedulerFactoryBean.getScheduler().getTrigger(triggerKey);
        if (null == trigger) {
            addJob(jobConfigBO);
            return;
        }
        if (!trigger.getCronExpression().equals(jobConfigBO.getJobCronExpress())) {
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobConfigBO.getJobCronExpress());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder).build();
            JobDetail jobDetail = schedulerFactoryBean.getScheduler().getJobDetail(trigger.getJobKey());
            jobDetail.getJobDataMap().put(JobCodeEnum.JOB_DATA_CONFIG_MAP.getKey(), jobConfigBO);
            //按新的trigger重新设置job执行,以当前时间为基准来调整下一次触发的时间
            schedulerFactoryBean.getScheduler().rescheduleJob(triggerKey, trigger);
        }
    }

    /**
     * 2、加入计划任务
     *
     * @param jobConfigBO                            JOB基本信息参数
     * @throws SchedulerException					 调度异常
     * @throws java.text.ParseException				 解析异常
     * @throws ClassNotFoundException				 类不存在异常
     */
    @Override
    public void addJob(JobConfigBO jobConfigBO) throws SchedulerException, ParseException, ClassNotFoundException {
        Class beanClass = Class.forName(jobConfigBO.getJobClass());
        JobDetail jobDetail = JobBuilder.newJob(beanClass).withIdentity(jobConfigBO.getJobName(), jobConfigBO.getJobGroup()).build();
        jobDetail.getJobDataMap().put(JobCodeEnum.JOB_DATA_CONFIG_MAP.getKey(), jobConfigBO);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobConfigBO.getJobCronExpress());
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobConfigBO.getJobName(), jobConfigBO.getJobGroup())
                .withSchedule(scheduleBuilder).build();
        schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
    }

    /**
     * 3、任务暂停
     *
     * @param jobConfigBO                            JOB基本信息参数
     * @throws SchedulerException					 调度异常
     */
    @Override
    public void pauseJob(JobConfigBO jobConfigBO) throws SchedulerException, ParseException, ClassNotFoundException {
        if (!isExistsJob(jobConfigBO)){
            addJob(jobConfigBO);
        }
        if (scheduleJob(jobConfigBO).equals(jobConfigBO.getStatus())) {
            return;
        }
        JobKey jobKey = JobKey.jobKey(jobConfigBO.getJobName(), jobConfigBO.getJobGroup());
        schedulerFactoryBean.getScheduler().pauseJob(jobKey);
    }

    /**
     * 4、任务恢复/继续
     *
     * @param jobConfigBO                            JOB基本信息参数
     * @throws SchedulerException					 调度异常
     */
    @Override
    public void resumeJob(JobConfigBO jobConfigBO) throws SchedulerException, ParseException, ClassNotFoundException {
        if (!isExistsJob(jobConfigBO)){
            addJob(jobConfigBO);
        }
        JobKey jobKey = JobKey.jobKey(jobConfigBO.getJobName(), jobConfigBO.getJobGroup());
        schedulerFactoryBean.getScheduler().resumeJob(jobKey);
    }

    /**
     * 5、任务移除
     *
     * @param jobConfigBO                            JOB基本信息参数
     * @return									     TRUE/FALSE
     * @throws SchedulerException					 调度异常
     */
    @Override
    public boolean removeJob(JobConfigBO jobConfigBO) throws SchedulerException, ParseException, ClassNotFoundException {
        if (!isExistsJob(jobConfigBO)){
            return true;
        }
        JobKey jobKey = JobKey.jobKey(jobConfigBO.getJobName(), jobConfigBO.getJobGroup());
        return schedulerFactoryBean.getScheduler().deleteJob(jobKey);
    }

    /**
     * 6、立即执行任务，只会运行一次
     *
     * @param jobConfigBO                        JOB基本信息参数
     * @throws SchedulerException			     调度异常
     */
    @Override
    public void atOnceRunJob(JobConfigBO jobConfigBO) throws SchedulerException, ParseException, ClassNotFoundException {
        if (!isExistsJob(jobConfigBO)){
            addJob(jobConfigBO);
        }

        JobKey jobKey = new JobKey(jobConfigBO.getJobName(),jobConfigBO.getJobGroup());
        JobDetail jobDetail = schedulerFactoryBean.getScheduler().getJobDetail(jobKey);
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put(JobCodeEnum.JOB_DATA_CONFIG_MAP.getKey(), jobConfigBO);

        schedulerFactoryBean.getScheduler().triggerJob(jobKey, jobDataMap);

    }

    /**
     * 7、计划中的任务
     *
     * @throws SchedulerException				调度异常
     * @return 									计划中的任务列表
     */
    @Override
    public List<JobConfigBO> scheduleJob() throws SchedulerException {
        List<JobConfigBO> jobConfigBOList = new ArrayList<>();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = schedulerFactoryBean.getScheduler().getJobKeys(matcher);
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = schedulerFactoryBean.getScheduler().getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                JobConfigBO jobConfigBO = new JobConfigBO();
                jobConfigBO.setJobName(jobKey.getName());
                jobConfigBO.setJobGroup(jobKey.getGroup());
                jobConfigBO.setRemark("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = schedulerFactoryBean.getScheduler().getTriggerState(trigger.getKey());
                jobConfigBO.setStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    jobConfigBO.setJobCronExpress(cronExpression);
                }
                jobConfigBOList.add(jobConfigBO);
            }
        }
        return jobConfigBOList;
    }

    /**
     * 8、运行中的任务
     *
     * @throws SchedulerException				调度异常
     * @return 									运行中的任务列表
     */
    @Override
    public List<JobConfigBO> runningJob() throws SchedulerException {
        List<JobExecutionContext> executingJobs = schedulerFactoryBean.getScheduler().getCurrentlyExecutingJobs();
        List<JobConfigBO> jobConfigBOList = new ArrayList<>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            JobConfigBO jobConfigBO = new JobConfigBO();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            jobConfigBO.setJobName(jobKey.getName());
            jobConfigBO.setJobGroup(jobKey.getGroup());
            jobConfigBO.setRemark("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = schedulerFactoryBean.getScheduler().getTriggerState(trigger.getKey());
            jobConfigBO.setStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                jobConfigBO.setJobCronExpress(cronExpression);
            }
            jobConfigBOList.add(jobConfigBO);
        }
        return jobConfigBOList;
    }

    /**
     * 9、根据任务名、组名查询任务状态
     * @param jobConfigBO							JOB基本信息参数
     * @return										任务状态
     * @throws SchedulerException					运行中的任务列表
     */
    @Override
    public String scheduleJob(JobConfigBO jobConfigBO) throws SchedulerException {
        String status = null;
        TriggerKey triggerKey = TriggerKey.triggerKey(jobConfigBO.getJobName(), jobConfigBO.getJobGroup());
        CronTrigger trigger = (CronTrigger) schedulerFactoryBean.getScheduler().getTrigger(triggerKey);
        if (null != trigger) {
            Trigger.TriggerState triggerState = schedulerFactoryBean.getScheduler().getTriggerState(trigger.getKey());
            status = triggerState.name();
        }
        return status;
    }

    /**
     * 任务是否存在
     *
     * @param jobConfigBO							JOB基本信息参数
     * @return										Ture/False
     * @throws SchedulerException					调度异常
     */
    private boolean isExistsJob(JobConfigBO jobConfigBO) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobConfigBO.getJobName(), jobConfigBO.getJobGroup());
        CronTrigger trigger = (CronTrigger) schedulerFactoryBean.getScheduler().getTrigger(triggerKey);
        return !(null == trigger);
    }

    /**
     * 10、重新加载定时任务
     *
     * @param jobConfigBO                           JOB基本信息参数
     */
    public void reloadJob(JobConfigBO jobConfigBO) throws SchedulerException, ParseException, ClassNotFoundException {
        if (this.removeJob(jobConfigBO)) {
            addJob(jobConfigBO);
        }
    }

}
