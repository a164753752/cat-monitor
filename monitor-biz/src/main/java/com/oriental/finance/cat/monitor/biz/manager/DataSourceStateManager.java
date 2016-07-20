package com.oriental.finance.cat.monitor.biz.manager;

import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.DataSourceStateBO;

import java.util.List;

/**
 * 描述：数据源使用状况信息服务管理
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
public interface DataSourceStateManager {

    /**
     * 添加数据源状况信息
     * @param dataSourceStateBO    数据源状况信息
     */
    public void addDataSourceState(DataSourceStateBO dataSourceStateBO);

    /**
     * 根据条件查询数据源状况信息
     * @param dataSourceStateBO    数据源状况查询条件
     * @return List<DataSourceStateBO>
     */
    public List<DataSourceStateBO> findByCondition(DataSourceStateBO dataSourceStateBO);

    /**
     * 根据条件查询数据源状况信息
     * @param dataSourceStateBO    数据源状况查询条件
     * @return List<DataSourceStateBO>
     */
    public PageInfo<DataSourceStateBO> findByConditionPaging(DataSourceStateBO dataSourceStateBO);

}
