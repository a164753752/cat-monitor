package com.oriental.finance.cat.monitor.test.dal;

import com.oriental.finance.cat.monitor.dal.mapper.ScriptCommandConfigMapper;
import com.oriental.finance.cat.monitor.dal.model.ScriptCommandConfigDO;
import com.oriental.finance.cat.monitor.test.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述：脚本命令Mapper单元测试
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
public class ScriptCommandConfigMapperTest extends BaseTest{

    /**
     * 日志搜索命令配置Mapper
     */
    @Autowired
    private ScriptCommandConfigMapper scriptCommandConfigMapper;
    /**
     * 主键
     */
    private Integer id;

    @Before
    public void before(){
        id = 123456789;
    }

    /**
     * 根据主键获取LogSearchCommandConfigDO
     */
    @Test
    public void test_findByPrimaryKey(){
        ScriptCommandConfigDO logSearchCommandConfigDO =
                (ScriptCommandConfigDO) scriptCommandConfigMapper.findByPrimaryKey(id);
        Assert.assertNull(logSearchCommandConfigDO);
    }

}
