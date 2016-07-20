package com.oriental.finance.cat.monitor.biz.manager;

import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.ThreadPoolStateBO;

import java.util.List;

/**
 * 描述：线程池使用状况信息服务管理
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
public interface ThreadPoolStateManager {

    /**
     * 添加线程池状况信息
     * @param threadPoolStateBO    线程池状况信息
     */
    public void addThreadPoolState(ThreadPoolStateBO threadPoolStateBO);

    /**
     * 根据条件查询线程池状况信息
     * @param threadPoolStateBO    线程池状况查询条件
     * @return List<ThreadPoolStateBO>
     */
    public List<ThreadPoolStateBO> findByCondition(ThreadPoolStateBO threadPoolStateBO);

    /**
     * 根据条件查询线程池状况信息
     * @param threadPoolStateBO    线程池状况查询条件
     * @return List<ThreadPoolStateBO>
     */
    public PageInfo<ThreadPoolStateBO> findByConditionPaging(ThreadPoolStateBO threadPoolStateBO);

}
