package com.oriental.finance.cat.monitor.test.biz;

import com.oriental.finance.cat.monitor.common.util.DateUtils;
import com.oriental.finance.cat.monitor.model.ServiceLogTailBO;
import com.oriental.finance.cat.monitor.work.cif.helper.ServiceLogTailJobHelper;
import com.oriental.finance.cat.monitor.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/11 ProjectName:cat-monitor Version: 1.0
 */
public class ServiceLogTailWorkerTest extends BaseTest {

    @Autowired
    private ServiceLogTailJobHelper serviceLogTailJobHelper;


    @Test
    public void test_serviceLogTail() throws InterruptedException {
        for(int i = 0;i<10;i++){
            String monitorId = DateUtils.getCurrent(DateUtils.fullPattern);
            String startLine = "2016-07-11 17:35:18,452#[DubboServerHandler-192.168.201.10:20080-thread-26]#[INFO ]#[o.a.z.ZooKeeper.<init>:438]#["+monitorId+"] oooo startCall.ServiceMonitorFilter-CIF-com.lyd.Test-monitor";
            String endLine = "2016-07-11 19:35:19,452#[DubboServerHandler-192.168.201.10:20080-thread-26]#[INFO ]#[o.a.z.ZooKeeper.<init>:438]#["+monitorId+"] oooo endCall.ServiceMonitorFilter-false-500-fail";

            ServiceLogTailBO serviceLogTailBO = serviceLogTailJobHelper.analyzeLog(startLine);
            serviceLogTailJobHelper.handlerServiceLog(serviceLogTailBO);
            serviceLogTailBO = serviceLogTailJobHelper.analyzeLog(endLine);
            serviceLogTailJobHelper.handlerServiceLog(serviceLogTailBO);
            TimeUnit.SECONDS.sleep(1);
        }
    }


}
