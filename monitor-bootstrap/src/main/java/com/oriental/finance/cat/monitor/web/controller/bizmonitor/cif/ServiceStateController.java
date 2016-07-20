package com.oriental.finance.cat.monitor.web.controller.bizmonitor.cif;

import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.manager.ServiceStateManager;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.ServiceStateBO;
import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.common.exception.BizException;
import com.oriental.finance.cat.monitor.common.exception.DBException;
import com.oriental.finance.cat.monitor.common.util.BeanCopyUtils;
import com.oriental.finance.cat.monitor.web.controller.BaseController;
import com.oriental.finance.cat.monitor.web.model.monitor.ServiceStateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.oriental.finance.cat.monitor.common.enums.system.PageEnum.*;

/**
 * 描述：服务状况Controller
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Controller
@Slf4j
@RequestMapping("cif")
public class ServiceStateController extends BaseController {

    /**
     *  服务状况管理服务
     */
    @Autowired
    private ServiceStateManager serviceStateManager;

    /**
     * 查询所有服务状况信息
     * @param model 传值Model
     * @return  跳转页面
     */
    @RequestMapping("serviceState/find")
    public String find(Model model,ServiceStateVO serviceStateVO){
        log.info("call find serviceStateVO:{}", serviceStateVO);

        try {
            PageInfo<ServiceStateBO> pageInfo = serviceStateManager.
                    findByConditionPaging(BeanCopyUtils.copy(serviceStateVO, ServiceStateBO.class));
            model.addAttribute(RESP_PAGING.getCode(),pageInfo);
        }catch (DBException e){
            log.error("call find DBException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),e.getFullExceptionInfo());
        }catch (BizException e){
            log.error("call find BizException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),e.getFullExceptionInfo());
        }catch (Exception e){
            log.error("call find Exception:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(), BizCodeEnum.getFullBizInfo(BizCodeEnum.BIZ_CODE_500001));
        }

        model.addAttribute(RESP_TITLE_SERVICE_STATE.getCode(),RESP_TITLE_SERVICE_STATE.getDesc());
        model.addAttribute(RESP_URL_PATH_SERVICE_STATE.getCode(),RESP_URL_PATH_SERVICE_STATE.getDesc());
        return RESP_DIRECT_PAGE_SERVICE_STATE.getCode();
    }

}
