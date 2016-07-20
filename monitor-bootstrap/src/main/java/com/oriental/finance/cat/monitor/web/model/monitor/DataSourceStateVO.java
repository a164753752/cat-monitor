package com.oriental.finance.cat.monitor.web.model.monitor;

import com.oriental.finance.cat.monitor.web.model.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述：数据源信息VO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/18 ProjectName:cat-monitor Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSourceStateVO extends BaseVO {

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
    private String numActive;

    /**
     * 当前空闲连接数
     */
    private String numIdle;

    /**
     * 初始化时创建的连接数
     */
    private String initialSize;

    /**
     * 活动连接的最大数量
     */
    private String maxSize;

    /**
     *  最小空闲连接数
     */
    private String minIdle;

    /**
     *  最大空闲连接数
     */
    private String maxIdle;

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
