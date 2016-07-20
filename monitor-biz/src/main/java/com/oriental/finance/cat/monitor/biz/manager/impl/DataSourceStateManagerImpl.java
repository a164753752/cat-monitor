package com.oriental.finance.cat.monitor.biz.manager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.manager.DataSourceStateManager;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.DataSourceStateBO;
import com.oriental.finance.cat.monitor.common.enums.system.PageEnum;
import com.oriental.finance.cat.monitor.common.enums.system.SysOperatorEnum;
import com.oriental.finance.cat.monitor.common.util.BeanCopyUtils;
import com.oriental.finance.cat.monitor.common.util.asserts.AssertResultUtils;
import com.oriental.finance.cat.monitor.dal.mapper.DataSourceStateMapper;
import com.oriental.finance.cat.monitor.dal.model.DataSourceStateDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述：数据源使用状况信息服务管理实现
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/15 ProjectName:cat-monitor Version: 1.0
 */
@Service
@Slf4j
public class DataSourceStateManagerImpl implements DataSourceStateManager {

    /**
     * 数据源状况Mapper
     */
    @Autowired
    private DataSourceStateMapper dataSourceStateMapper;

    /**
     * 添加数据源状况信息
     * @param dataSourceStateBO    数据源状况信息
     */
    @Override
    public void addDataSourceState(DataSourceStateBO dataSourceStateBO) {
        log.info("call addDataSourceState:{}",dataSourceStateBO);
        dataSourceStateBO.setCreateBy(SysOperatorEnum.SYSTEM.getCode());
        dataSourceStateBO.setUpdateBy(SysOperatorEnum.SYSTEM.getCode());
        DataSourceStateDO dataSourceStateDO = BeanCopyUtils.copy(dataSourceStateBO,DataSourceStateDO.class);
        int result = dataSourceStateMapper.insert(dataSourceStateDO);
        log.info("call addDataSourceState result:{}", result);
        AssertResultUtils.checkUpdNum(result);
    }

    /**
     * 根据条件查询数据源状况信息
     * @param dataSourceStateBO    数据源状况查询条件
     * @return List<DataSourceStateBO>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DataSourceStateBO> findByCondition(DataSourceStateBO dataSourceStateBO) {
        log.info("call findByCondition:{}",dataSourceStateBO);
        Map<String,String> condition = BeanCopyUtils.copy(dataSourceStateBO,Map.class);
        List<DataSourceStateDO> dataSourceStateDOList = dataSourceStateMapper.findByCondition(condition);
        log.info("call findByCondition dataSourceStateDOList.size:{}",dataSourceStateDOList.size());
        return BeanCopyUtils.copyList(dataSourceStateDOList, DataSourceStateBO.class);
    }

    /**
     * 根据条件查询数据源状况信息
     * @param dataSourceStateBO    数据源状况查询条件
     * @return List<DataSourceStateBO>
     */
    @SuppressWarnings("unchecked")
    @Override
    public PageInfo<DataSourceStateBO> findByConditionPaging(DataSourceStateBO dataSourceStateBO) {
        log.info("call findByConditionPaging:{}",dataSourceStateBO);
        Map<String,String> condition = BeanCopyUtils.copy(dataSourceStateBO,Map.class);
        if(null != dataSourceStateBO.getPageNum()){
            PageHelper.startPage(dataSourceStateBO.getPageNum(),dataSourceStateBO.getPageSize(),PageEnum.ORDER_BY_UPDATE_AT.getCode());
        }
        List<DataSourceStateDO> dataSourceStateDOList = dataSourceStateMapper.findByCondition(condition);
        PageInfo result = new PageInfo(dataSourceStateDOList,Integer.parseInt(PageEnum.NAV_PAGE.getCode()));
        List<DataSourceStateBO> dataSourceStateBOList = BeanCopyUtils.copyList(dataSourceStateDOList,DataSourceStateBO.class);
        result.setList(dataSourceStateBOList);
        log.info("call findByConditionPaging result:{}",result);
        return result;
    }
}
