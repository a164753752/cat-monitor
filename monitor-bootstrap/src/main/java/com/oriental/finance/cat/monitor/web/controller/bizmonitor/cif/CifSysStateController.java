package com.oriental.finance.cat.monitor.web.controller.bizmonitor.cif;

import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.external.cif.CifSysStateService;
import com.oriental.finance.cat.monitor.biz.manager.DataSourceStateManager;
import com.oriental.finance.cat.monitor.biz.manager.ThreadPoolStateManager;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.DataSourceStateBO;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.ThreadPoolStateBO;
import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.common.exception.BizException;
import com.oriental.finance.cat.monitor.common.exception.DBException;
import com.oriental.finance.cat.monitor.common.model.Response;
import com.oriental.finance.cat.monitor.common.util.BeanCopyUtils;
import com.oriental.finance.cat.monitor.web.controller.BaseController;
import com.oriental.finance.cat.monitor.web.model.monitor.DataSourceStateVO;
import com.oriental.finance.cat.monitor.web.model.monitor.ThreadPoolStateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.oriental.finance.cat.monitor.common.enums.system.PageEnum.*;

/**
 * 描述：应用状况Controller
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/14 ProjectName:cat-monitor Version: 1.0
 */
@Controller
@RequestMapping("cif")
@Slf4j
public class CifSysStateController extends BaseController {

    /**
     * cif应用状况服务
     */
    @Autowired
    private CifSysStateService cifSysStateService;
    /**
     * 数据源信息状况管理服务
     */
    @Autowired
    private DataSourceStateManager dataSourceStateManager;
    /**
     * 线程池信息状况管理服务
     */
    @Autowired
    private ThreadPoolStateManager threadPoolStateManager;

    /**
     * 查询应用数据源信息
     * @param model 传值Model
     * @return  跳转页面
     */
    @RequestMapping("dataSource/find")
    public String findDataSource(Model model,DataSourceStateVO dataSourceStateVO){
        log.info("call CifSysStateController.findDataSource dataSourceStateVO:{}",dataSourceStateVO);

        try {
            PageInfo<DataSourceStateBO> pageInfo = dataSourceStateManager.
                    findByConditionPaging(BeanCopyUtils.copy(dataSourceStateVO,DataSourceStateBO.class));
            model.addAttribute(RESP_PAGING.getCode(),pageInfo);
        }catch (DBException e){
            log.error("call find DBException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),e.getFullExceptionInfo());
        }catch (BizException e){
            log.error("call find BizException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),e.getFullExceptionInfo());
        }catch (Exception e){
            log.error("call find Exception:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),BizCodeEnum.getFullBizInfo(BizCodeEnum.BIZ_CODE_500001));
        }

        model.addAttribute(RESP_TITLE_DATA_SOURCE_STATE.getCode(),RESP_TITLE_DATA_SOURCE_STATE.getDesc());
        model.addAttribute(RESP_URL_PATH_DATA_SOURCE_STATE.getCode(), RESP_URL_PATH_DATA_SOURCE_STATE.getDesc());
        return RESP_DIRECT_PAGE_DATA_SOURCE_STATE.getCode();
    }

    /**
     * 获取应用数据源状况
     * @return  Response<DataSourceStateBO>
     */
    @RequestMapping("dataSource/get")
    @ResponseBody
    public Response<DataSourceStateBO> getDataSource(){
        log.info("call CifSysStateController.getDataSource");
        Response<DataSourceStateBO> response = new Response<>();
        try {
            response.setResult(cifSysStateService.getDataSourceState());
        }catch (Exception e){
            log.error("call CifSysStateController.dataSource Exception:{}",e);
            response.setResponse(BizCodeEnum.BIZ_CODE_500101.getBizCode(),BizCodeEnum.BIZ_CODE_500101.getBizDesc());
        }
        log.info("call CifSysStateController.dataSource response:{}",response);
        return response;
    }

    /**
     * 查询应用dubbo服务线程信息
     * @param model 传值Model
     * @return  跳转页面
     */
    @RequestMapping("providerThread/find")
    public String findProviderThread(Model model,ThreadPoolStateVO threadPoolStateVO){
        log.info("call CifSysStateController.findProviderThread threadPoolStateVO:{}",threadPoolStateVO);

        try {
            PageInfo<ThreadPoolStateBO> pageInfo = threadPoolStateManager.
                    findByConditionPaging(BeanCopyUtils.copy(threadPoolStateVO,ThreadPoolStateBO.class));
            model.addAttribute(RESP_PAGING.getCode(),pageInfo);
        }catch (DBException e){
            log.error("call find DBException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),e.getFullExceptionInfo());
        }catch (BizException e){
            log.error("call find BizException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),e.getFullExceptionInfo());
        }catch (Exception e){
            log.error("call find Exception:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),BizCodeEnum.getFullBizInfo(BizCodeEnum.BIZ_CODE_500001));
        }

        model.addAttribute(RESP_TITLE_THREAD_POOL_STATE.getCode(),RESP_TITLE_THREAD_POOL_STATE.getDesc());
        model.addAttribute(RESP_URL_PATH_THREAD_POOL_STATE.getCode(),RESP_URL_PATH_THREAD_POOL_STATE.getDesc());
        return RESP_DIRECT_PAGE_THREAD_POOL_STATE.getCode();
    }


    /**
     * 获取应用dubbo服务线程状况
     * @return  Response<DataSourceStateBO>
     */
    @RequestMapping("providerThread/get")
    @ResponseBody
    public Response<ThreadPoolStateBO> getProviderThread(){
        log.info("call CifSysStateController.getProviderThread");
        Response<ThreadPoolStateBO> response = new Response<>();
        try {
            response.setResult(cifSysStateService.getProviderThreadState());
        }catch (BizException e){
            log.error("call CifSysStateController.providerThread BizException:{}",e);
            response.setResponse(e.getBizCode(),e.getBizDesc());
        }catch (Exception e){
            log.error("call CifSysStateController.providerThread Exception:{}",e);
            response.setResponse(BizCodeEnum.BIZ_CODE_500001.getBizCode(),BizCodeEnum.BIZ_CODE_500001.getBizDesc());
        }
        log.info("call CifSysStateController.providerThread response:{}",response);
        return response;
    }

}
