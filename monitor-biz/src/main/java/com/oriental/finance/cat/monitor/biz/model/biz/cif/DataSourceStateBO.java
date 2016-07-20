package com.oriental.finance.cat.monitor.biz.model.biz.cif;

import com.oriental.finance.cat.monitor.biz.model.BaseBO;
import lombok.Data;
import lombok.ToString;

import java.util.Date;


/**
 * 描述：数据源状况BO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/14 ProjectName:cat-monitor Version: 1.0
 */
@Data
@ToString
public class DataSourceStateBO extends BaseBO {

    /**
     * 平台
     * @see com.oriental.finance.cat.monitor.common.enums.type.PlatformTypeEnum
     */
    private String platform;

    /**
     * 记录日期
     */
    private Date recordDate;

    /**
     * DB用户名
     */
    private String userName;

    /**
     * DB访问地址
     */
    private String url;

    /**
     * DB驱动名
     */
    private String driverClassName;

    /**
     * 是否自动提交
     */
    private String autoCommit;

    /**
     * 当前活动连接数
     */
    private int numActive;

    /**
     * 当前空闲连接数
     */
    private int numIdle;

    /**
     * 初始化时创建的连接数
     */
    private int initialSize;

    /**
     * 活动连接的最大数量
     */
    private int maxSize;

    /**
     *  最小空闲连接数
     */
    private int minIdle;

    /**
     *  最大空闲连接数
     */
    private int maxIdle;

    /**
     *  获取连接最大等待时间
     */
    private long maxWait;

    /**
     *  空闲连接存活时间(毫秒)
     */
    private long idleAliveTime;

    /**
     * 获取连接是否进行连接测试(true影响性能)
     */
    private String testOnBorrow;

    /**
     * 运行一次空闲连接回收器的时间
     */
    private long idleReleaseTime;

}
