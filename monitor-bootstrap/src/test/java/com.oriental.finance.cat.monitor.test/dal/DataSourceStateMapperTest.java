package com.oriental.finance.cat.monitor.test.dal;

import com.oriental.finance.cat.monitor.common.enums.type.PlatformTypeEnum;
import com.oriental.finance.cat.monitor.dal.mapper.DataSourceStateMapper;
import com.oriental.finance.cat.monitor.dal.model.DataSourceStateDO;
import com.oriental.finance.cat.monitor.test.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 描述：数据源状况Mapper单元测试
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
public class DataSourceStateMapperTest extends BaseTest {

    @Autowired
    private DataSourceStateMapper dataSourceStateMapper;

    private int num;

    private DataSourceStateDO dataSourceStateDO;


    @Before
    public void before(){
        dataSourceStateDO = new DataSourceStateDO();
        dataSourceStateDO.setRecordDate(new Date());
        dataSourceStateDO.setPlatform(PlatformTypeEnum.MONITOR.getCode());
        dataSourceStateDO.setAutoCommit("AUTO");
        dataSourceStateDO.setDriverClassName("com.mysql.driver");
        dataSourceStateDO.setIdleAliveTime(1000L);
        dataSourceStateDO.setIdleReleaseTime(5000L);
        dataSourceStateDO.setInitialSize(10);
        dataSourceStateDO.setMaxIdle(5);
        dataSourceStateDO.setMaxSize(20);
        dataSourceStateDO.setMaxWait(1000L);
        dataSourceStateDO.setMinIdle(2);
        dataSourceStateDO.setNumActive(2);
        dataSourceStateDO.setNumIdle(1);
        dataSourceStateDO.setTestOnBorrow("YES");
        dataSourceStateDO.setUrl("jdbc:mysql://127.0.0.1:3306/cat-monitor?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=PRC&useSSL=false");
        dataSourceStateDO.setUserName("cat");
        dataSourceStateDO.setCreateBy("LYD");
        dataSourceStateDO.setUpdateBy("LYD");
    }

    @Test
    public void test_addJobConfig(){
        num = dataSourceStateMapper.insert(dataSourceStateDO);
        Assert.assertEquals(num,1);
    }

    @Test
    public void test_findJobConfig(){
        List<DataSourceStateDO> result = dataSourceStateMapper.findByCondition(null);
        Assert.assertTrue(result.size()==1);
    }

}
