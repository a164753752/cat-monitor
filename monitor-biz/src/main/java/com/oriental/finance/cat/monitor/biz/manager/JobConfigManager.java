package com.oriental.finance.cat.monitor.biz.manager;

import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.model.sys.JobConfigBO;

import java.util.List;

/**
 * 描述：Job任务配置信息服务管理
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
public interface JobConfigManager {

    /**
     * 添加Job任务配置信息
     * @param jobConfigBO    Job任务配置信息
     */
    public void addJob(JobConfigBO jobConfigBO) throws Exception;

    /**
     * 根据id删除Job任务配置信息
     * @param id    主键
     */
    public void unloadJob(String id) throws Exception;

    /**
     * 更新Job任务配置信息
     * @param jobConfigBO    Job任务配置信息
     */
    public void refurbishJob(JobConfigBO jobConfigBO) throws Exception;

    /**
     * 根据条件查询Job任务配置信息
     * @param jobConfigBO    Job任务配置查询条件
     * @return List<JobConfigBO>
     */
    public List<JobConfigBO> findByCondition(JobConfigBO jobConfigBO);

    /**
     * 根据条件分页查询Job任务配置信息
     * @param jobConfigBO    Job任务配置查询条件
     * @return PageInfo<JobConfigBO>
     */
    public PageInfo<JobConfigBO> findByConditionPaging(JobConfigBO jobConfigBO);

}
