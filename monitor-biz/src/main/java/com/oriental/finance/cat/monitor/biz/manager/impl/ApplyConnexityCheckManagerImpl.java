package com.oriental.finance.cat.monitor.biz.manager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.manager.ApplyConnexityCheckManager;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.ApplyConnexityCheckBO;
import com.oriental.finance.cat.monitor.common.enums.system.PageEnum;
import com.oriental.finance.cat.monitor.common.util.BeanCopyUtils;
import com.oriental.finance.cat.monitor.common.util.asserts.AssertResultUtils;
import com.oriental.finance.cat.monitor.dal.mapper.ApplyConnexityCheckMapper;
import com.oriental.finance.cat.monitor.dal.model.ApplyConnexityCheckDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述：应用连通性检测管理服务实现
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/12 ProjectName:cat-monitor Version: 1.0
 */
@Service
@Slf4j
public class ApplyConnexityCheckManagerImpl implements ApplyConnexityCheckManager {

    /**
     * 连通性检测Mapper
     */
    @Autowired
    private ApplyConnexityCheckMapper applyConnexityCheckMapper;

    /**
     * 添加连通性检测信息
     * @param applyConnexityCheckBO    连通性检测信息
     */
    @Override
    public void addApplyConnexityCheck(ApplyConnexityCheckBO applyConnexityCheckBO) {
        log.info("call addApplyConnexityCheck applyConnexityCheckBO:{}",applyConnexityCheckBO);
        ApplyConnexityCheckDO applyConnexityCheckDO = BeanCopyUtils.copy(applyConnexityCheckBO, ApplyConnexityCheckDO.class);
        int result = applyConnexityCheckMapper.insert(applyConnexityCheckDO);
        log.info("call addApplyConnexityCheck result:{}",result);
        AssertResultUtils.checkUpdNum(result);
    }

    @Override
    public ApplyConnexityCheckBO findById(String id) {
        log.info("call findById id:{}",id);
        ApplyConnexityCheckDO applyConnexityCheckDO = applyConnexityCheckMapper.findByPrimaryKey(Integer.parseInt(id));
        log.info("call findById id:{},applyConnexityCheckDO:{}",id,applyConnexityCheckDO);
        AssertResultUtils.checkDBResult(null != applyConnexityCheckDO);
        return BeanCopyUtils.copy(applyConnexityCheckDO,ApplyConnexityCheckBO.class);
    }

    /**
     * 根据条件查询连通性检测信息
     * @param applyConnexityCheckBO    查询条件
     * @return PageInfo<ApplyConnexityCheckBO>
     */
    @SuppressWarnings("unchecked")
    @Override
    public PageInfo<ApplyConnexityCheckBO> findByConditionPaging(ApplyConnexityCheckBO applyConnexityCheckBO) {
        log.info("call findByConditionPaging applyConnexityCheckBO:{}",applyConnexityCheckBO);
        Map<String,String> condition = BeanCopyUtils.copy(applyConnexityCheckBO, Map.class);
        if(null != applyConnexityCheckBO.getPageNum()){
            PageHelper.startPage(applyConnexityCheckBO.getPageNum(),applyConnexityCheckBO.getPageSize(),PageEnum.ORDER_BY_UPDATE_AT.getCode());
        }
        List<ApplyConnexityCheckDO> applyConnexityCheckDOList = applyConnexityCheckMapper.findByCondition(condition);
        PageInfo result = new PageInfo<>(applyConnexityCheckDOList,Integer.parseInt(PageEnum.NAV_PAGE.getCode()));
        List<ApplyConnexityCheckBO> applyConnexityCheckBOList = BeanCopyUtils.copyList(result.getList(),ApplyConnexityCheckBO.class);
        result.setList(applyConnexityCheckBOList);
        log.info("call findByConditionPaging result:{}", result);
        return result;
    }
}
