package com.oriental.finance.cat.monitor.test.dal;

import com.oriental.finance.cat.monitor.common.enums.type.PlatformTypeEnum;
import com.oriental.finance.cat.monitor.dal.mapper.ThreadPoolStateMapper;
import com.oriental.finance.cat.monitor.dal.model.ThreadPoolStateDO;
import com.oriental.finance.cat.monitor.test.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 描述：线程池状况Mapper单元测试
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
public class ThreadPoolStateMapperTest extends BaseTest {

    @Autowired
    private ThreadPoolStateMapper threadPoolStateMapper;

    private int num;

    private ThreadPoolStateDO threadPoolStateDO;



    @Before
    public void before(){
        threadPoolStateDO = new ThreadPoolStateDO();
        threadPoolStateDO.setActiveCount(1);
        threadPoolStateDO.setAllowReleaseCodeIdle("YES");
        threadPoolStateDO.setCompletedTaskCount(2L);
        threadPoolStateDO.setCorePoolSize(1);
        threadPoolStateDO.setKeepAliveTime(1000L);
        threadPoolStateDO.setLargestPoolSize(11);
        threadPoolStateDO.setMaxPoolSize(10);
        threadPoolStateDO.setPoolSize(1);
        threadPoolStateDO.setQueueSize(0);
        threadPoolStateDO.setPlatform(PlatformTypeEnum.CIF.getCode());
        threadPoolStateDO.setType("SERVICE");
        threadPoolStateDO.setTaskCount(1L);
        threadPoolStateDO.setRecordDate(new Date());
        threadPoolStateDO.setCreateBy("LYD");
        threadPoolStateDO.setUpdateBy("LYD");
    }

    @Test
    public void test_addJobConfig(){
        num = threadPoolStateMapper.insert(threadPoolStateDO);
        Assert.assertEquals(num, 1);
    }

    @Test
    public void test_findJobConfig(){
        List<ThreadPoolStateDO> result = threadPoolStateMapper.findByCondition(null);
        Assert.assertTrue(result.size()==1);
    }

}
