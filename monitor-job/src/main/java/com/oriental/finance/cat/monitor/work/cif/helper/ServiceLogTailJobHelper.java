package com.oriental.finance.cat.monitor.work.cif.helper;

import com.oriental.finance.cat.monitor.biz.model.biz.cif.ServiceStateBO;
import com.oriental.finance.cat.monitor.common.enums.status.ServiceStateStatusEnum;
import com.oriental.finance.cat.monitor.biz.manager.ServiceStateManager;

import com.oriental.finance.cat.monitor.common.util.DateUtils;
import com.oriental.finance.cat.monitor.model.ServiceLogTailBO;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 描述：服务状况日志监控任务helper
 * <p>
 * #解析搜索日志
 * #处理服务状况日志信息
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Component
@Slf4j
public class ServiceLogTailJobHelper {

    /**
     * 服务状况日志搜索关键字
     */
    public static final String KEY_WORD = "CIFServiceMonitorFilter";

    /**
     * 服务状况管理服务
     */
    @Autowired
    private ServiceStateManager serviceStateManager;

    /**
     * 解析搜索日志
     * @param line  日志
     * @return  服务状况日志信息
     */
    public ServiceLogTailBO analyzeLog(String line){
        ServiceLogTailBO serviceLogTailBO = new ServiceLogTailBO();
        serviceLogTailBO.analyze(line);
        log.info("call analyzeLog serviceLogTailBO:{}",serviceLogTailBO);
        return serviceLogTailBO;
    }

    /**
     * 处理服务状况日志信息
     * @param serviceLogTailBO  服务状况日志信息Model
     */
    public void handlerServiceLog(ServiceLogTailBO serviceLogTailBO){
        ServiceStateBO serviceStateBO = new ServiceStateBO();
        serviceStateBO.setRelationNo(serviceLogTailBO.getMonitorId());
        serviceStateBO.setTraceLogId(serviceLogTailBO.getMonitorId());
        serviceStateBO.setServiceDate(DateUtils.parse(DateUtils.getCurrent(DateUtils.datePattern)));
        if(serviceLogTailBO.getLogInfo().isOriginFlag()){//服务开始调用信息落地
            serviceStateBO.setServiceStatus(ServiceStateStatusEnum.INIT.getStatus());
            serviceStateBO.setServicePath(serviceLogTailBO.getLogInfo().getServiceInterface());
            serviceStateBO.setServiceType(serviceLogTailBO.getLogInfo().getServiceMethod());
            serviceStateBO.setServicePlatform(serviceLogTailBO.getLogInfo().getServicePlatform());
            serviceStateBO.setStartExecuteTime(DateUtils.parse(serviceLogTailBO.getLogDate(),DateUtils.fullPatternSSS));
            serviceStateManager.addServiceState(serviceStateBO);
        }else{//服务结束调用信息更新
            List<ServiceStateBO> serviceStateBOList = serviceStateManager.findByCondition(serviceStateBO);
            log.info("call handlerServiceLog serviceStateBOList serviceStateBOList:{}", serviceStateBOList);
            if(CollectionUtils.isEmpty(serviceStateBOList)){
                return;
            }
            serviceStateBO = serviceStateBOList.get(0);
            if(ServiceStateStatusEnum.isNotInit(serviceStateBO.getServiceStatus())){
                return;
            }
            serviceStateBO.setEndExecuteTime(DateUtils.parse(serviceLogTailBO.getLogDate(), DateUtils.fullPatternSSS));
            serviceStateBO.setExecuteTime(DateUtils.computeDateByMS(serviceStateBO.getStartExecuteTime(), serviceStateBO.getEndExecuteTime()));
            serviceStateBO.setServiceRespCode(serviceLogTailBO.getLogInfo().getServiceRespCode());
            serviceStateBO.setServiceRespDesc(serviceLogTailBO.getLogInfo().getServiceRespDesc());
            serviceStateBO.setServiceStatus(serviceLogTailBO.getLogInfo().isServiceStatus()? ServiceStateStatusEnum.SUCCESS.getStatus(): ServiceStateStatusEnum.FAIL.getStatus());
            serviceStateManager.updateByServiceCallEnd(serviceStateBO);
        }
    }


    public static void main(String[] args) {
        DateTime start = DateTime.parse("2016-07-08 15:58:35,888", DateTimeFormat.forPattern(DateUtils.fullPatternSSS));
        DateTime end = DateTime.parse("2016-07-08 18:58:35,999",DateTimeFormat.forPattern(DateUtils.fullPatternSSS));
        Duration duration = new Duration(start,end);
        System.out.println(duration.getMillis());
    }

}
