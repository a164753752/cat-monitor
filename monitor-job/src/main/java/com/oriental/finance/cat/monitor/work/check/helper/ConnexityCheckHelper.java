package com.oriental.finance.cat.monitor.work.check.helper;

import com.oriental.finance.cat.monitor.biz.manager.ApplyConnexityCheckManager;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.ApplyConnexityCheckBO;
import com.oriental.finance.cat.monitor.common.enums.status.ConnexityCheckStatusEnum;
import com.oriental.finance.cat.monitor.common.enums.type.ConnexityCheckTypeEnum;
import com.oriental.finance.cat.monitor.common.enums.type.PlatformTypeEnum;
import com.oriental.finance.cat.monitor.common.enums.system.SysOperatorEnum;
import com.oriental.finance.cat.monitor.common.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述：连通性检测Helper
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/12 ProjectName:cat-monitor Version: 1.0
 */
@Component
@Slf4j
public class ConnexityCheckHelper {

    /**
     * 检测正常标识
     */
    private static final String SUCCESS = "SUCCESS";

    /**
     * 连通性检测管理服务
     */
    @Autowired
    private ApplyConnexityCheckManager applyConnexityCheckManager;

    /**
     * 解析检测结果
     * @param checkResult 检测结果
     * @return  true正常|false异常
     */
    public ConnexityCheckStatusEnum analyze(List<String> checkResult){
        String result = "";
        for(String temp : checkResult){
            result += temp;
        }
        return ConnexityCheckStatusEnum.resultMappingStatus(SUCCESS.equals(result));
    }

    /**
     * 处理检测结果
     * @param connexityCheckStatusEnum  检测状态
     * @param checkUrl    检测Url
     */
    public void handlerCheckResult(ConnexityCheckStatusEnum connexityCheckStatusEnum,String checkUrl){
        ApplyConnexityCheckBO applyConnexityCheckBO = new ApplyConnexityCheckBO();
        applyConnexityCheckBO.setApplyPlatform(PlatformTypeEnum.CIF.getCode());
        applyConnexityCheckBO.setCheckStatus(connexityCheckStatusEnum.getStatus());
        applyConnexityCheckBO.setCheckTime(DateUtils.getCurrentDate());
        applyConnexityCheckBO.setConnexityType(ConnexityCheckTypeEnum.HTTP.getType());
        applyConnexityCheckBO.setCheckUrl(checkUrl);
        applyConnexityCheckBO.setCreateBy(SysOperatorEnum.SYSTEM.getCode());
        applyConnexityCheckBO.setUpdateBy(SysOperatorEnum.SYSTEM.getCode());
        applyConnexityCheckManager.addApplyConnexityCheck(applyConnexityCheckBO);
    }

}
