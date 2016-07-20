package com.oriental.finance.cat.monitor.dal.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述：数据访问基类
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/5 ProjectName:cat-monitor Version: 1.0
 */
@Data
@ToString
public class BaseDO implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 是否可用 YES可用，NO不可用
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

}
