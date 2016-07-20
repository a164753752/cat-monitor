package com.oriental.finance.cat.monitor.test.dal;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.dal.mapper.ServiceStateMapper;
import com.oriental.finance.cat.monitor.dal.model.ServiceStateDO;
import com.oriental.finance.cat.monitor.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Slf4j
public class ServiceStateMapperTest extends BaseTest {

    @Autowired
    private ServiceStateMapper serviceStateMapper;

    private int num;

    @Test
    public void test_addServiceState(){

    }

    @Test
    public void test_findServiceState(){
        PageHelper.startPage(1, Integer.parseInt("10"));
        List<ServiceStateDO> result = serviceStateMapper.findByCondition(null);
        PageInfo<ServiceStateDO> pageInfo = new PageInfo<>(result);
        log.info("pageInfo:{}",pageInfo);
        log.info("list:{}",pageInfo.getList());
    }


}
