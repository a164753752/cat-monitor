package com.oriental.finance.cat.monitor.biz.external.cif;

import com.oriental.finance.cat.monitor.biz.model.biz.cif.DataSourceStateBO;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.ThreadPoolStateBO;
import com.oriental.finance.cat.monitor.biz.util.asserts.AssertResponseUtils;
import com.oriental.finance.cat.monitor.common.enums.system.YesOrNoEnum;
import com.oriental.finance.cat.monitor.common.enums.type.PlatformTypeEnum;
import com.oriental.finance.cat.monitor.common.util.BeanCopyUtils;
import com.oriental.finance.cat.monitor.common.util.DateUtils;
import com.oriental.finance.cif.common.model.Response;
import com.oriental.finance.cif.product.bizModel.response.sys.DataSourceStateResDto;
import com.oriental.finance.cif.product.bizModel.response.sys.ProviderThreadStateResDto;
import com.oriental.finance.cif.product.facade.SysStateFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：CIF系统状况服务
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/14 ProjectName:cat-monitor Version: 1.0
 */
@Service
@Slf4j
public class CifSysStateService {

    /**
     * CIF系统状况dubbo服务
     */
    @Autowired
    private SysStateFacade sysStateFacade;

    /**
     * 获取数据源状况信息
     */
    public DataSourceStateBO getDataSourceState(){
        log.info("call SysStateService.getDataSourceState");
        Response<DataSourceStateResDto> response = sysStateFacade.dataSourceState();
        log.info("call SysStateService.getDataSourceState response:{}",response);
        AssertResponseUtils.checkResponse(response);
        DataSourceStateBO dataSourceStateBO = BeanCopyUtils.copy(response.getResult(),DataSourceStateBO.class);
        dataSourceStateBO.setPlatform(PlatformTypeEnum.CIF.getCode());
        dataSourceStateBO.setRecordDate(DateUtils.getCurrentDate());
        dataSourceStateBO.setTestOnBorrow(response.getResult().isTestOnBorrow()? YesOrNoEnum.YES.getCode():YesOrNoEnum.NO.getCode());
        dataSourceStateBO.setAutoCommit(response.getResult().isAutoCommit()? YesOrNoEnum.YES.getCode():YesOrNoEnum.NO.getCode());
        return dataSourceStateBO;
    }

    /**
     * 获取数据源状况信息
     */
    public ThreadPoolStateBO getProviderThreadState(){
        log.info("call SysStateService.getProviderThreadState");
        Response<ProviderThreadStateResDto> response = sysStateFacade.providerThreadState();
        log.info("call SysStateService.getProviderThreadState response:{}",response);
        AssertResponseUtils.checkResponse(response);
        ThreadPoolStateBO threadPoolStateBO = BeanCopyUtils.copy(response.getResult(),ThreadPoolStateBO.class);
        threadPoolStateBO.setPlatform(PlatformTypeEnum.CIF.getCode());
        threadPoolStateBO.setType("SERVICE");
        threadPoolStateBO.setRecordDate(DateUtils.getCurrentDate());
        threadPoolStateBO.setAllowReleaseCodeIdle(response.getResult().isAllowReleaseCodeIdle()?YesOrNoEnum.YES.getCode():YesOrNoEnum.NO.getCode());
        return threadPoolStateBO;
    }

}
