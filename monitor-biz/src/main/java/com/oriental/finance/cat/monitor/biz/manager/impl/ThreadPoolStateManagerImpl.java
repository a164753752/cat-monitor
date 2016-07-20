package com.oriental.finance.cat.monitor.biz.manager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.manager.ThreadPoolStateManager;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.ThreadPoolStateBO;
import com.oriental.finance.cat.monitor.common.enums.system.PageEnum;
import com.oriental.finance.cat.monitor.common.enums.system.SysOperatorEnum;
import com.oriental.finance.cat.monitor.common.util.BeanCopyUtils;
import com.oriental.finance.cat.monitor.common.util.asserts.AssertResultUtils;
import com.oriental.finance.cat.monitor.dal.mapper.ThreadPoolStateMapper;
import com.oriental.finance.cat.monitor.dal.model.ThreadPoolStateDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述：连接池使用状况信息服务管理实现
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/15 ProjectName:cat-monitor Version: 1.0
 */
@Service
@Slf4j
public class ThreadPoolStateManagerImpl implements ThreadPoolStateManager {

    /**
     * 连接池状况Mapper
     */
    @Autowired
    private ThreadPoolStateMapper threadPoolStateMapper;

    /**
     * 添加连接池状况信息
     * @param threadPoolStateBO    连接池状况信息
     */
    @Override
    public void addThreadPoolState(ThreadPoolStateBO threadPoolStateBO) {
        log.info("call addThreadPoolState:{}",threadPoolStateBO);
        threadPoolStateBO.setCreateBy(SysOperatorEnum.SYSTEM.getCode());
        threadPoolStateBO.setUpdateBy(SysOperatorEnum.SYSTEM.getCode());
        ThreadPoolStateDO dataSourceStateDO = BeanCopyUtils.copy(threadPoolStateBO,ThreadPoolStateDO.class);
        dataSourceStateDO.setAllowReleaseCodeIdle(threadPoolStateBO.getAllowReleaseCodeIdle());
        int result = threadPoolStateMapper.insert(dataSourceStateDO);
        log.info("call addThreadPoolState result:{}", result);
        AssertResultUtils.checkUpdNum(result);
    }

    /**
     * 根据条件查询连接池状况信息
     * @param threadPoolStateBO    连接池状况查询条件
     * @return List<ThreadPoolStateBO>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ThreadPoolStateBO> findByCondition(ThreadPoolStateBO threadPoolStateBO) {
        log.info("call findByCondition:{}",threadPoolStateBO);
        Map<String,String> condition = BeanCopyUtils.copy(threadPoolStateBO,Map.class);
        List<ThreadPoolStateDO> threadPoolStateDOList = threadPoolStateMapper.findByCondition(condition);
        log.info("call findByCondition threadPoolStateDOList.size:{}", threadPoolStateDOList.size());
        return BeanCopyUtils.copyList(threadPoolStateDOList, ThreadPoolStateBO.class);
    }

    /**
     * 根据条件查询连接池状况信息
     * @param threadPoolStateBO    连接池状况查询条件
     * @return List<ThreadPoolStateBO>
     */
    @SuppressWarnings("unchecked")
    @Override
    public PageInfo<ThreadPoolStateBO> findByConditionPaging(ThreadPoolStateBO threadPoolStateBO) {
        log.info("call findByConditionPaging:{}",threadPoolStateBO);
        Map<String,String> condition = BeanCopyUtils.copy(threadPoolStateBO,Map.class);
        if(null != threadPoolStateBO.getPageNum()){
            PageHelper.startPage(threadPoolStateBO.getPageNum(),threadPoolStateBO.getPageSize(),PageEnum.ORDER_BY_UPDATE_AT.getCode());
        }
        List<ThreadPoolStateDO> threadPoolStateDOList = threadPoolStateMapper.findByCondition(condition);
        PageInfo result = new PageInfo(threadPoolStateDOList,Integer.parseInt(PageEnum.NAV_PAGE.getCode()));
        List<ThreadPoolStateBO> threadPoolStateBOList = BeanCopyUtils.copyList(threadPoolStateDOList,ThreadPoolStateBO.class);
        result.setList(threadPoolStateBOList);
        log.info("call findByConditionPaging result:{}",result);
        return result;
    }
}
