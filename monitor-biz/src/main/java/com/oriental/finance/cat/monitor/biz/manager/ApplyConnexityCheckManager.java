package com.oriental.finance.cat.monitor.biz.manager;

import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.ApplyConnexityCheckBO;

/**
 * 描述：应用连通性检测管理服务
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/12 ProjectName:cat-monitor Version: 1.0
 */
public interface ApplyConnexityCheckManager {

    /**
     * 添加连通性检测信息
     * @param applyConnexityCheckBO    连通性检测信息
     */
    public void addApplyConnexityCheck(ApplyConnexityCheckBO applyConnexityCheckBO);

    /**
     * 根据id查询连通性检测信息
     * @param id    查询条件
     * @return ApplyConnexityCheckBO
     */
    public ApplyConnexityCheckBO findById(String id);

    /**
     * 根据条件查询连通性检测信息
     * @param applyConnexityCheckBO    查询条件
     * @return PageInfo<ApplyConnexityCheckBO>
     */
    public PageInfo<ApplyConnexityCheckBO> findByConditionPaging(ApplyConnexityCheckBO applyConnexityCheckBO);

}
