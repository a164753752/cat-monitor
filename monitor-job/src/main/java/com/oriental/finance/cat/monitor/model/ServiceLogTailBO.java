package com.oriental.finance.cat.monitor.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 描述：服务状况日志信息BO
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/11 ProjectName:cat-monitor Version: 1.0
 */
@Data
@ToString
public class ServiceLogTailBO implements Serializable {

    /**
     * 日志日期
     */
    private String logDate;
    /**
     * 日期线程信息
     */
    private String logThread;
    /**
     * 日志级别
     */
    private String logLevel;
    /**
     * 服务路径
     */
    private String logServicePath;
    /**
     * 监控流水号
     */
    private String monitorId;
    /**
     * 业务日志信息
     */
    private LogInfo logInfo = new LogInfo();

    /**
     * 解析日志信息
     * @param line  日志信息
     */
    public void analyze(String line){
        //两部分(logback+业务日志)
        String[] splitLog = line.split("oooo");
        String[] logback = splitLog[0].split("#");
        this.setLogDate(logback[0]);
        this.setLogThread(logback[1]);
        this.setLogLevel(logback[2]);
        this.setLogServicePath(logback[3]);
        this.setMonitorId(logback[4]);
        //业务信息
        this.getLogInfo().analyze(splitLog[1]);
    }


    /**
     * 业务日志信息
     */
    @Data
    @ToString
    public class LogInfo{
        /**
         * 服务平台
         */
        private String servicePlatform;
        /**
         * 服务起始标识   true起|false终
         */
        private boolean originFlag;
        /**
         * 业务关键字前缀信息
         */
        private String logInfoPrefix;
        /**
         * 业务接口
         */
        private String serviceInterface;
        /**
         * 业务方法
         */
        private String serviceMethod;
        /**
         * 服务状态
         */
        private boolean serviceStatus;
        /**
         * 服务响应码
         */
        private String serviceRespCode;
        /**
         * 服务响应描述
         */
        private String serviceRespDesc;

        /**
         * 解析业务日志信息
         * @param logInfo   业务日志信息
         */
        private void analyze(String logInfo){
            String[] splitLogInfo = logInfo.split("-");
            this.logInfoPrefix = splitLogInfo[0].trim();
            this.originFlag = logInfoPrefix.startsWith("startCall");
            if(originFlag){
                this.servicePlatform = splitLogInfo[1];
                this.serviceInterface = splitLogInfo[2];
                this.serviceMethod = splitLogInfo[3];
            }else{
                this.serviceStatus = Boolean.parseBoolean(splitLogInfo[1]);
                if(!this.serviceStatus){
                    this.serviceRespCode = splitLogInfo[2];
                    this.serviceRespDesc = splitLogInfo[3];
                }
            }
        }
    }

}

