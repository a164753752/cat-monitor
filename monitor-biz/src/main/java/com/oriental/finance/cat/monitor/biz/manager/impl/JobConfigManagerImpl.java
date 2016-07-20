package com.oriental.finance.cat.monitor.biz.manager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.cache.Caches;
import com.oriental.finance.cat.monitor.biz.manager.JobConfigManager;
import com.oriental.finance.cat.monitor.biz.manager.ScriptCommandConfigManager;
import com.oriental.finance.cat.monitor.biz.manager.helper.JobConfigHelper;
import com.oriental.finance.cat.monitor.biz.model.sys.JobConfigBO;
import com.oriental.finance.cat.monitor.common.enums.status.JobStatusEnum;
import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.common.enums.system.PageEnum;
import com.oriental.finance.cat.monitor.common.enums.system.SysOperatorEnum;
import com.oriental.finance.cat.monitor.common.enums.type.EnableFlagTypeEnum;
import com.oriental.finance.cat.monitor.common.exception.BizException;
import com.oriental.finance.cat.monitor.common.util.BeanCopyUtils;
import com.oriental.finance.cat.monitor.common.util.DateUtils;
import com.oriental.finance.cat.monitor.common.util.asserts.AssertResultUtils;
import com.oriental.finance.cat.monitor.dal.mapper.JobConfigMapper;
import com.oriental.finance.cat.monitor.dal.model.JobConfigDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述：Job任务配置信息服务管理实现
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Slf4j
@Service
public class JobConfigManagerImpl implements JobConfigManager {

    /**
     * Job任务配置Mapper
     */
    @Autowired
    private JobConfigMapper jobConfigMapper;
    /**
     * job配置Helper
     */
    @Autowired
    private JobConfigHelper jobConfigHelper;


    /**
     * 添加Job任务配置信息
     * @param jobConfigBO    Job任务配置信息
     */
    public void addJob(JobConfigBO jobConfigBO) throws Exception {
        //job信息落地
        jobConfigHelper.checkCommandKeyExist(jobConfigBO);
        jobConfigBO.setStatus(JobStatusEnum.UN_LOADING.getCode());
        jobConfigBO.setCreateBy(SysOperatorEnum.SYSTEM.getCode());
        jobConfigBO.setUpdateBy(SysOperatorEnum.SYSTEM.getCode());
        jobConfigBO.setUpdateAt(DateUtils.getCurrentDate());
        jobConfigBO.setEnableFlag(EnableFlagTypeEnum.ENABLE.getType());
        JobConfigDO jobConfigDO = BeanCopyUtils.copy(jobConfigBO, JobConfigDO.class);
        int result = jobConfigMapper.insert(jobConfigDO);
        log.error("call addJob result:{}",result);
        AssertResultUtils.checkUpdNum(result);
    }

    /**
     * 根据id删除Job任务配置信息及任务
     * @param id    主键
     */
    @Override
    public void unloadJob(String id) throws Exception {
        jobConfigHelper.unloadJob(id);
    }

    /**
     * 更新Job任务配置信息
     * @param jobConfigBO    Job任务配置信息
     */
    @Override
    public void refurbishJob(JobConfigBO jobConfigBO) throws Exception {
        log.info("call refurbishJob jobConfigBO:{}",jobConfigBO);
        jobConfigHelper.checkCommandKeyExist(jobConfigBO);
        jobConfigHelper.refurbishJob(jobConfigBO);
    }

    /**
     * 根据条件分页查询Job任务配置信息
     * @param jobConfigBO    Job任务配置查询条件
     * @return PageInfo<JobConfigBO>
     */
    @SuppressWarnings("unchecked")
    @Override
    public PageInfo<JobConfigBO> findByConditionPaging(JobConfigBO jobConfigBO) {
        log.info("call findByConditionPaging jobConfigBO:{}", jobConfigBO);
        Map<String,String> condition = BeanCopyUtils.copy(jobConfigBO, Map.class);
        if(null != jobConfigBO.getPageNum()){
            PageHelper.startPage(jobConfigBO.getPageNum(), jobConfigBO.getPageSize(),PageEnum.ORDER_BY_UPDATE_AT.getCode());
        }
        List<JobConfigDO> jobConfigDOList = jobConfigMapper.findByCondition(condition);
        PageInfo result = new PageInfo<>(jobConfigDOList,Integer.parseInt(PageEnum.NAV_PAGE.getCode()));
        List<JobConfigBO> jobConfigBOList = BeanCopyUtils.copyList(result.getList(),JobConfigBO.class);
        result.setList(jobConfigBOList);
        log.info("call findByConditionPaging result:{}", result);
        return result;
    }

    /**
     * 根据条件查询Job任务配置信息
     * @param jobConfigBO    Job任务配置查询条件
     * @return List<JobConfigBO>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<JobConfigBO> findByCondition(JobConfigBO jobConfigBO) {
        log.error("call findByCondition jobConfigBO:{}", jobConfigBO);
        List<JobConfigBO> jobConfigBOList = null;
        try {
            Map<String,String> condition = BeanCopyUtils.copy(jobConfigBO, Map.class);
            List<JobConfigDO> jobConfigDOList = jobConfigMapper.findByCondition(condition);
            jobConfigBOList = BeanCopyUtils.copyList(jobConfigDOList,JobConfigBO.class);
            for(JobConfigBO bean : jobConfigBOList) {
                bean.setSimpleJobClass(Class.forName(bean.getJobClass()).getSimpleName());
            }
            log.error("call findByCondition jobConfigBOList.size:{}", jobConfigBOList.size());
        } catch (Exception e) {
            log.error("call JobConfigManagerImpl Exception:{}",e);
        }
        return jobConfigBOList;
    }

}
