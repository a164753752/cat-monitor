package com.oriental.finance.cat.monitor.biz.manager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.manager.ServiceStateManager;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.ServiceStateBO;
import com.oriental.finance.cat.monitor.common.enums.system.PageEnum;
import com.oriental.finance.cat.monitor.common.enums.system.SysOperatorEnum;
import com.oriental.finance.cat.monitor.common.enums.type.EnableFlagTypeEnum;
import com.oriental.finance.cat.monitor.common.util.BeanCopyUtils;
import com.oriental.finance.cat.monitor.common.util.DateUtils;
import com.oriental.finance.cat.monitor.common.util.asserts.AssertResultUtils;
import com.oriental.finance.cat.monitor.dal.mapper.ServiceStateMapper;
import com.oriental.finance.cat.monitor.dal.model.ServiceStateDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述：Service调用状况信息服务管理实现
 * <p>
 * #添加服务状况信息
 * #服务调用结束后更新
 * #根据条件查询服务状况信息
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Slf4j
@Service
public class ServiceStateManagerImpl implements ServiceStateManager {

    /**
     * Service调用状况Mapper
     */
    @Autowired
    private ServiceStateMapper serviceStateMapper;

    /**
     * 添加服务状况信息
     * @param serviceStateBO    服务状况信息
     */
    public void addServiceState(ServiceStateBO serviceStateBO){
        log.info("call addServiceState serviceStateBO:{}",serviceStateBO);
        serviceStateBO.setCreateBy(SysOperatorEnum.SYSTEM.getCode());
        serviceStateBO.setUpdateBy(SysOperatorEnum.SYSTEM.getCode());
        serviceStateBO.setUpdateAt(DateUtils.getCurrentDate());
        serviceStateBO.setEnableFlag(EnableFlagTypeEnum.ENABLE.getType());
        ServiceStateDO serviceStateDO = BeanCopyUtils.copy(serviceStateBO, ServiceStateDO.class);
        int result = serviceStateMapper.insert(serviceStateDO);
        log.info("call addServiceState result:{}",result);
        AssertResultUtils.checkUpdNum(result);
    }

    /**
     * 服务调用结束后更新
     * @param serviceStateBO    服务状况信息
     */
    @Override
    public void updateByServiceCallEnd(ServiceStateBO serviceStateBO) {
        log.info("call updateByServiceCallEnd serviceStateBO:{}",serviceStateBO);
        ServiceStateDO serviceStateDO = BeanCopyUtils.copy(serviceStateBO, ServiceStateDO.class);
        int result = serviceStateMapper.update(serviceStateDO);
        log.info("call updateByServiceCallEnd result:{}",result);
        AssertResultUtils.checkUpdNum(result);
    }

    /**
     * 根据条件查询服务状况信息
     * @param serviceStateBO    服务状况查询条件
     * @return List<ServiceStateBO>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ServiceStateBO> findByCondition(ServiceStateBO serviceStateBO) {
        log.info("call findByConditionPaging serviceStateBO:{}",serviceStateBO);
        Map<String,String> condition = BeanCopyUtils.copy(serviceStateBO, Map.class);
        List<ServiceStateDO> serviceStateDOList = serviceStateMapper.findByCondition(condition);
        log.info("call findByConditionPaging serviceStateDOList:{}",serviceStateDOList);
        return BeanCopyUtils.copyList(serviceStateDOList,ServiceStateBO.class);
    }

    /**
     * 根据条件查询服务状况信息
     * @param serviceStateBO    服务状况查询条件
     * @return PageInfo<ServiceStateBO>
     */
    @SuppressWarnings("unchecked")
    @Override
    public PageInfo<ServiceStateBO> findByConditionPaging(ServiceStateBO serviceStateBO){
        log.info("call findByConditionPaging serviceStateBO:{}",serviceStateBO);
        Map<String,String> condition = BeanCopyUtils.copy(serviceStateBO, Map.class);
        if(null != serviceStateBO.getPageNum()){
            PageHelper.startPage(serviceStateBO.getPageNum(),serviceStateBO.getPageSize(),PageEnum.ORDER_BY_UPDATE_AT.getCode());
        }
        List<ServiceStateDO> serviceStateDOList = serviceStateMapper.findByCondition(condition);
        PageInfo result = new PageInfo<>(serviceStateDOList,Integer.parseInt(PageEnum.NAV_PAGE.getCode()));
        List<ServiceStateBO> serviceStateBOList = BeanCopyUtils.copyList(result.getList(),ServiceStateBO.class);
        for(ServiceStateBO bean : serviceStateBOList){
            String[] path = bean.getServicePath().split("\\.");
            bean.setSimpleServicePath(path[path.length-1]);
        }
        result.setList(serviceStateBOList);
        log.info("call findByConditionPaging result:{}", result);
        return result;
    }

    /**
     * 查询总数
     * @return 总条数
     */
    @Override
    public Integer findByCount() {
        log.info("call findByCount");
        Integer result = serviceStateMapper.count();
        log.info("call findByCount result:{}",result);
        return result;
    }

}
