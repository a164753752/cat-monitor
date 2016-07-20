package com.oriental.finance.cat.monitor.test.dal;

import com.oriental.finance.cat.monitor.common.enums.status.JobStatusEnum;
import com.oriental.finance.cat.monitor.dal.mapper.JobConfigMapper;
import com.oriental.finance.cat.monitor.dal.model.JobConfigDO;
import com.oriental.finance.cat.monitor.test.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 描述：Job配置Mapper单元测试
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
public class JobConfigMapperTest extends BaseTest {

    @Autowired
    private JobConfigMapper jobConfigMapper;

    private int num;

    private JobConfigDO jobConfigDO;



    @Before
    public void before(){
        jobConfigDO = new JobConfigDO();
        jobConfigDO.setJobName("test");
        jobConfigDO.setJobGroup("testGroup");
        jobConfigDO.setJobClass("com.oriental.finance.cat.monitor.work.cif.ServiceLogTailJobWorker");
        jobConfigDO.setStatus(JobStatusEnum.LOADING.getCode());
        jobConfigDO.setRetryTimes(0);
        jobConfigDO.setEnableFlag("YES");
        jobConfigDO.setJobExecCount(0);
        jobConfigDO.setJobCronExpress("0/5 * * * * ?");
        jobConfigDO.setCreateBy("LYD");
        jobConfigDO.setRemark("测试JOB");
        jobConfigDO.setUpdateBy("LYD");
        jobConfigDO.setUpdateAt(new Date());
    }

    @Test
    public void test_addJobConfig(){
        num = jobConfigMapper.insert(jobConfigDO);
        Assert.assertEquals(num,1);
    }

    @Test
    public void test_findJobConfig(){
        List<JobConfigDO> result = jobConfigMapper.findByCondition(null);
        Assert.assertTrue(result.size()==1);
    }

    @Test
    public void test_deleteJobConfig(){
        num = jobConfigMapper.deleteByPrimaryKey(1);
        Assert.assertEquals(num, 1);
    }

    @After
    public void after(){
//        this.lock();
    }

}
