package com.oriental.finance.cat.monitor.web.model.sys;

import com.oriental.finance.cat.monitor.web.model.BaseVO;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 描述：Job配置VO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/12 ProjectName:cat-monitor Version: 1.0
 */
@Data
@ToString(callSuper = true)
public class JobConfigVO extends BaseVO {

    /**
     * 任务名称
     */
    @NotBlank(message = "任务名称不能为空")
    private String jobName;

    /**
     * 任务所属组
     */
    @NotBlank(message = "任务所属组不能为空")
    private String jobGroup;

    /**
     * 定时JOB类名或者bean名
     */
    @NotBlank(message = "任务类名不能为空")
    private String jobClass;

    /**
     * 任务运行状态
     * @see com.oriental.finance.cat.monitor.common.enums.status.JobStatusEnum
     */
    private String status;

    /**
     * 任务运行时间表达式(运行规则)
     */
    @NotBlank(message = "任务运行规则不能为空")
//    @Pattern(regexp="",message = "任务运行规则格式错误")
    private String jobCronExpress;

    /**
     * 任务执行次数,默认0
     */
    private Integer jobExecCount;

    /**
     * 失败允许重试次数
     */
    @Pattern(regexp="0|1|2|3|4|5",message = "重试次数格式错误")
    private String retryTimes;

    /**
     * 最后执行时间
     */
    private String lastExecTime;

    /**
     * 下次执行时间
     */
    private String nextExecTime;

    /**
     * 任务执行花费时间
     */
    private String jobUsedTime;

    /**
     * 是否允许多机处理该任务：YES允许，默认NO不允许
     */
    @Pattern(regexp="YES|NO",message = "重试次数格式错误")
    private String isClusters;

    /**
     * 任务执行指定命令，可配置为日志搜索命令配置表的KEY
     */
    private String jobCommandKey;

}
