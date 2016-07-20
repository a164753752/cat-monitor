package com.oriental.finance.cat.monitor.work.cif;

import com.oriental.finance.cat.monitor.biz.model.sys.ScriptCommandConfigBO;
import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.common.enums.system.JobCodeEnum;
import com.oriental.finance.cat.monitor.common.exception.BizException;
import com.oriental.finance.cat.monitor.common.util.CommandUtils;
import com.oriental.finance.cat.monitor.model.ServiceLogTailBO;
import com.oriental.finance.cat.monitor.work.AdapterJobWorker;
import com.oriental.finance.cat.monitor.work.cif.helper.ServiceLogTailJobHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * 描述：服务调用日志tail监控Worker
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Component
@Slf4j
public class ServiceLogTailJobWorker extends AdapterJobWorker {

    /**
     * 服务状况日志监控任务helper
     */
    @Autowired
    private ServiceLogTailJobHelper serviceLogTailJobHelper;

    private volatile static boolean runFlag = false;


    /**
     * 检测命令Key
     * @return  true检测
     */
    @Override
    protected boolean ifCheckJobCommandKey() {
        return true;
    }

    @Override
    protected synchronized void doWorker(JobExecutionContext context) {
        log.info("call ServiceLogTailJobWorker doWorker context:{},runFlag:{}", context,runFlag);
        if(runFlag){
            return;
        }
        runFlag = true;
        try {
            JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
            ScriptCommandConfigBO scriptCommandConfigBO = (ScriptCommandConfigBO) jobDataMap.get(JobCodeEnum.JOB_DATA_COMMAND_MAP.getKey());
            Process process = CommandUtils.runShell(scriptCommandConfigBO.getCommand());
            //开启新线程处理
            ServiceLogTailThread.buildInstance(process, serviceLogTailJobHelper).start();
        } catch (Exception e) {
            log.error("call ServiceLogTailJobWorker doWorker Exception:{}",e);
            throw new BizException(BizCodeEnum.BIZ_CODE_500002);
        }
    }

    /**
     * 服务日志收集处理
     */
    private static class ServiceLogTailThread extends Thread {

        /**
         * 脚本处理
         */
        private ServiceLogTailJobHelper serviceLogTailJobHelper;
        /**
         * 脚本处理
         */
        private Process process;
        /**
         * 输入流
         */
        private InputStreamReader inputStreamReader;
        /**
         * 缓冲输入流
         */
        private BufferedReader bufferedReader;

        public ServiceLogTailThread(Process process,ServiceLogTailJobHelper serviceLogTailJobHelper){
            this.setName("ServiceLogTailThread-");
            this.process = process;
            this.serviceLogTailJobHelper = serviceLogTailJobHelper;
            this.inputStreamReader = new InputStreamReader(this.process.getInputStream());
            this.bufferedReader = new BufferedReader(inputStreamReader);
        }

        /**
         * 生成实例
         * @param process   脚本处理
         */
        private static ServiceLogTailThread buildInstance(Process process, ServiceLogTailJobHelper serviceLogTailJobHelper){
            return new ServiceLogTailThread(process,serviceLogTailJobHelper);
        }

        /**
         * 实时获取服务状况信息处理
         */
        @Override
        public void run() {
            try {
                String line;
                while( null != (line = bufferedReader.readLine()) && StringUtils.isNotBlank(line) ){
                    log.info("call ServiceLogTailThread.run line:{}",line);
                    if(!line.contains(ServiceLogTailJobHelper.KEY_WORD)){
                        continue;
                    }
                    try{
                        //解析日志
                        ServiceLogTailBO serviceLogTailBO = serviceLogTailJobHelper.analyzeLog(line);
                        //处理日志
                        serviceLogTailJobHelper.handlerServiceLog(serviceLogTailBO);
                    }catch (Exception e){
                        log.error("call ServiceLogTailThread.analyzeLog Exception:{}",e);
                        TimeUnit.SECONDS.sleep(5000);
                    }
                }
                log.info("call ServiceLogTailThread.run end");
            }catch (Exception e){
                log.error("call ServiceLogTailThread.run Exception:{}",e);
            }finally {
                try {
                    CommandUtils.close(inputStreamReader,bufferedReader,process);
                } catch (IOException e) {
                    log.error("call ServiceLogTailThread.close Exception:{}",e);
                }
            }
        }
    }

    public static void main(String[] args) {
        String line1 = "2016-07-13 13:36:32,280#[DubboServerHandler-192.168.201.10:20080-thread-200]#[INFO ]#[c.o.f.c.p.f.ServiceMonitorFilter.invoke:32]#[20160713133632fb528faa-6580-44b5-8070-472a2329d91b] oooo startCall.ServiceMonitorFilter-CIF-com.oriental.finance.cif.product.facade.CustomerMobileFacade-binding";
        String line2 = "2016-07-13 13:36:32,280#[DubboServerHandler-192.168.201.10:20080-thread-200]#[INFO ]#[c.o.f.c.p.s.AbstractCustomerService.beforeValidate:27]#[20160713133632fb528faa-6580-44b5-8070-472a2329d91b] oooo 请求参数：baseReqDto =BindingCustomerMobileReqDto(operatorNo=510000000731, mobile=18616783429, bindingServiceType=SMS)";
        String line3 = "2016-07-13 13:36:32,285#[DubboServerHandler-192.168.201.10:20080-thread-200]#[ERROR]#[c.o.f.c.p.s.CustomerMobileService.binding:49]#[20160713133632fb528faa-6580-44b5-8070-472a2329d91b] oooo call product-service.CustomerMobileService.binding err,se=操作员不存在！！";
        String line4 = "2016-07-13 13:36:32,286#[DubboServerHandler-192.168.201.10:20080-thread-200]#[INFO ]#[c.o.f.c.p.f.ServiceMonitorFilter.invoke:35]#[20160713133632fb528faa-6580-44b5-8070-472a2329d91b] oooo endCall.ServiceMonitorFilter-false-400008-操作员不存在！！";
        System.out.println(line1.contains(ServiceLogTailJobHelper.KEY_WORD));
        System.out.println(line2.contains(ServiceLogTailJobHelper.KEY_WORD));
        System.out.println(line3.contains(ServiceLogTailJobHelper.KEY_WORD));
        System.out.println(line4.contains(ServiceLogTailJobHelper.KEY_WORD));

    }

}
