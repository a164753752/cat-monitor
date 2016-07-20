package com.oriental.finance.cat.monitor.biz.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述：业务处理基类
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/5 ProjectName:cat-monitor Version: 1.0
 */
@Data
@ToString
public class BaseBO implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 是否可用
     */
    private String enableFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createBy;

    /**
     * 创建人
     */
    private Date createAt;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateAt;

    /**
     * 当前页数
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

}
