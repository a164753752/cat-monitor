package com.oriental.finance.cat.monitor.biz.manager;

import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.ServiceStateBO;

import java.util.List;

/**
 * 描述：Service调用状况信息服务管理
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
public interface ServiceStateManager {

    /**
     * 添加服务状况信息
     * @param serviceStateBO    服务状况信息
     */
    public void addServiceState(ServiceStateBO serviceStateBO);

    /**
     * 服务调用结束后更新
     * @param serviceStateBO    服务状况信息
     */
    public void updateByServiceCallEnd(ServiceStateBO serviceStateBO);

    /**
     * 根据条件查询服务状况信息
     * @param serviceStateBO    服务状况查询条件
     * @return List<ServiceStateBO>
     */
    public List<ServiceStateBO> findByCondition(ServiceStateBO serviceStateBO);

    /**
     * 根据条件查询服务状况信息
     * @param serviceStateBO    服务状况查询条件
     * @return List<ServiceStateBO>
     */
    public PageInfo<ServiceStateBO> findByConditionPaging(ServiceStateBO serviceStateBO);

   /**
     * 查询总数
     * @return 总条数
     */
    public Integer findByCount();

}
