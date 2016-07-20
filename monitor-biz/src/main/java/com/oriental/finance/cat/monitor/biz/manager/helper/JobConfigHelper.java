package com.oriental.finance.cat.monitor.biz.manager.helper;

import com.oriental.finance.cat.monitor.biz.cache.Caches;
import com.oriental.finance.cat.monitor.biz.component.job.JobDispatcher;
import com.oriental.finance.cat.monitor.biz.manager.QuartzManager;
import com.oriental.finance.cat.monitor.biz.model.sys.JobConfigBO;
import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.common.exception.BizException;
import com.oriental.finance.cat.monitor.common.util.BeanCopyUtils;
import com.oriental.finance.cat.monitor.common.util.asserts.AssertResultUtils;
import com.oriental.finance.cat.monitor.dal.mapper.JobConfigMapper;
import com.oriental.finance.cat.monitor.dal.model.JobConfigDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：job配置helper
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/16 ProjectName:cat-monitor Version: 1.0
 */
@Service
@Slf4j
public class JobConfigHelper {

    /**
     * job配置Mapper
     */
    @Autowired
    private JobConfigMapper jobConfigMapper;
    /**
     * 定时任务管理服务
     */
    @Autowired
    private QuartzManager quartzManager;
    /**
     * job配置服务
     */
    @Autowired
    private JobDispatcher jobDispatcher;
    /**
     * 缓存服务
     */
    @Autowired
    private Caches caches;


    /**
     * 刷新作业
     * @param jobConfigBO   job配置信息
     */
    public void refurbishJob(JobConfigBO jobConfigBO) throws Exception {
        //查询job配置信息
        JobConfigDO jobConfigDO = jobConfigMapper.findByPrimaryKey(jobConfigBO.getId());
        log.info("call refurbishJob jobConfigDO:{}",jobConfigDO);
        AssertResultUtils.checkDBResult(null != jobConfigDO);

        //配置Job
        jobDispatcher.dispatchJob(jobConfigBO);

        //更新job配置信息
        jobConfigDO = BeanCopyUtils.copy(jobConfigBO,JobConfigDO.class);
        int result = jobConfigMapper.update(jobConfigDO);
        log.error("call refurbishJob result:{}",result);
        AssertResultUtils.checkUpdNum(result);
    }

    /**
     * 卸载作业
     * @param id   job配置id
     */
    public void unloadJob(String id) throws Exception {
        log.info("call unloadJob id:{}",id);

        //查询job配置信息
        JobConfigDO jobConfigDO = jobConfigMapper.findByPrimaryKey(Integer.parseInt(id));
        log.info("call unloadJob jobConfigDO:{}",jobConfigDO);
        AssertResultUtils.checkDBResult(null != jobConfigDO);

        //卸载Job
        quartzManager.removeJob(BeanCopyUtils.copy(jobConfigDO,JobConfigBO.class));

        //删除Job信息
        int result = jobConfigMapper.deleteByPrimaryKey(Integer.parseInt(id));
        log.error("call unloadJob result:{}",result);
        AssertResultUtils.checkUpdNum(result);
    }

    /**
     * 校验脚本名命令key是否存在
     * @param jobConfigBO   job配置
     */
    public void checkCommandKeyExist(JobConfigBO jobConfigBO){
        if(StringUtils.isNotBlank(jobConfigBO.getJobCommandKey())){
            if(!caches.getCommandConfigCache().containsKey(jobConfigBO.getJobCommandKey())){
                throw new BizException(BizCodeEnum.BIZ_CODE_403004);
            }
        }
    }

}
