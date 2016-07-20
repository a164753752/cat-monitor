package com.oriental.finance.cat.monitor.work.check;

import com.oriental.finance.cat.monitor.common.exception.BizException;
import com.oriental.finance.cat.monitor.biz.model.sys.ScriptCommandConfigBO;
import com.oriental.finance.cat.monitor.common.enums.status.ConnexityCheckStatusEnum;
import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.common.enums.system.JobCodeEnum;
import com.oriental.finance.cat.monitor.common.util.CommandUtils;
import com.oriental.finance.cat.monitor.work.AdapterJobWorker;
import com.oriental.finance.cat.monitor.work.check.helper.ConnexityCheckHelper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：连通性检测
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/12 ProjectName:cat-monitor Version: 1.0
 */
@Service
@Slf4j
public class ConnexityCheckJobWorker extends AdapterJobWorker {

    /**
     * 连通性检测Helper
     */
    @Autowired
    private ConnexityCheckHelper connexityCheckHelper;


    /**
     * 检测命令Key
     * @return  true检测
     */
    @Override
    protected boolean ifCheckJobCommandKey() {
        return true;
    }

    @Override
    protected void doWorker(JobExecutionContext context) {
        log.info("call ConnexityCheckJobWorker doWorker context:{}", context);
        try {
            JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
            ScriptCommandConfigBO scriptCommandConfigBO = (ScriptCommandConfigBO) jobDataMap.get(JobCodeEnum.JOB_DATA_COMMAND_MAP.getKey());
            List<String> checkResult = CommandUtils.runShellReturnList(scriptCommandConfigBO.getCommand(),false);
            log.info("call ConnexityCheckJobWorker doWorker checkResult:{}",checkResult);
            //连通性检测数据落地
            ConnexityCheckStatusEnum connexityCheckStatus =  connexityCheckHelper.analyze(checkResult);
            connexityCheckHelper.handlerCheckResult(connexityCheckStatus,scriptCommandConfigBO.getCommand());
            //TODO  检测失败则报警
        } catch (Exception e) {
            log.error("call ConnexityCheckJobWorker doWorker Exception:{}",e);
            throw new BizException(BizCodeEnum.BIZ_CODE_500002);
        }
    }

}
