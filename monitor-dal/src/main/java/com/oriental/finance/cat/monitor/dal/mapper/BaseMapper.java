package com.oriental.finance.cat.monitor.dal.mapper;

import com.oriental.finance.cat.monitor.dal.model.BaseDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 描述：Mapper基类
 * <p>
 * #新增数据
 * #更新数据
 * #删除数据by主键
 * #查询数据by主键
 * #查询数据by条件
 * </p>
 * User: lyd Date: 2016/7/5 ProjectName:cat-monitor Version: 1.0
 */
public interface BaseMapper<M extends BaseDO> {

    /**
     * 新增数据
     *
     * @param baseModel 插入的对象
     * @return 插入的条数
     */
    int insert(M baseModel);

    /**
     * 更新数据
     *
     * @param baseModel 更新的对象
     * @return 更新的条数
     */
    int update(M baseModel);

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 更新的条数
     */
    int deleteByPrimaryKey(@Param("id") Integer id);

    /**
     * 查询数据by主键
     *
     * @param id 主键
     * @return T 数据对象
     */
    M findByPrimaryKey(@Param("id") Integer id);

    /**
     * 查询数据by条件
     *
     * @param condition 查询条件
     * @return List<M> 数据对象
     */
    List<M> findByCondition(Map<String,String> condition);

    /**
     * 计算总数
     * @return 总条数
     */
    Integer count();
}
