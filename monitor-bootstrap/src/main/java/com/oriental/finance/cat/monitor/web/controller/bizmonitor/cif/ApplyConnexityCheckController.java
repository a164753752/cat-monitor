package com.oriental.finance.cat.monitor.web.controller.bizmonitor.cif;

import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.manager.ApplyConnexityCheckManager;
import com.oriental.finance.cat.monitor.biz.model.biz.cif.ApplyConnexityCheckBO;
import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.common.exception.BizException;
import com.oriental.finance.cat.monitor.common.model.Response;
import com.oriental.finance.cat.monitor.common.util.BeanCopyUtils;
import com.oriental.finance.cat.monitor.common.util.CommandUtils;
import com.oriental.finance.cat.monitor.web.model.monitor.ApplyConnexityCheckVO;
import com.oriental.finance.cat.monitor.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.oriental.finance.cat.monitor.common.enums.system.PageEnum.*;

import java.util.List;


/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/12 ProjectName:cat-monitor Version: 1.0
 */
@Controller
@RequestMapping("connexityCheck")
@Slf4j
public class ApplyConnexityCheckController extends BaseController {

    /**
     * 应用连通性检测管理服务
     */
    @Autowired
    private ApplyConnexityCheckManager applyConnexityCheckManager;

    /**
     * 查询所有服务状况信息
     * @param model 传值Model
     * @return  跳转页面
     */
    @RequestMapping("find")
    public String find(Model model,ApplyConnexityCheckVO applyConnexityCheckVO){
        log.info("call find applyConnexityCheckVO:{}", applyConnexityCheckVO);
        try {
            PageInfo<ApplyConnexityCheckBO> pageInfo = applyConnexityCheckManager.
                    findByConditionPaging(BeanCopyUtils.copy(applyConnexityCheckVO, ApplyConnexityCheckBO.class));
            model.addAttribute(RESP_PAGING.getCode(),pageInfo);
        }catch (BizException e){
            log.error("call find BizException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),e.getFullExceptionInfo());
        }catch (Exception e){
            log.error("call find Exception:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),BizCodeEnum.getFullBizInfo(BizCodeEnum.BIZ_CODE_500001));
        }
        model.addAttribute(RESP_TITLE_CONNEXITY_CHECK.getCode(),RESP_TITLE_CONNEXITY_CHECK.getDesc());
        model.addAttribute(RESP_URL_PATH_CONNEXITY_CHECK.getCode(), RESP_URL_PATH_CONNEXITY_CHECK.getDesc());
        return RESP_DIRECT_PAGE_CONNEXITY_CHECK.getCode();
    }

    /**
     * 手动检测
     * @param id    检测记录id
     * @return  检测结果
     */
    @RequestMapping("handCheck")
    @ResponseBody
    public Response<List<String>> check(String id){
        Response<List<String>> response = new Response<>();
        log.info("call check id:{}", id);
        ApplyConnexityCheckBO applyConnexityCheckBO = applyConnexityCheckManager.findById(id);
        try {
            List<String> checkResult = CommandUtils.runShellReturnList(applyConnexityCheckBO.getCheckUrl(), false);
            response.setResult(checkResult);
        }catch (BizException e) {
            log.error("call check BizException id:{},e:{}",id,e);
            response.setResponse(e.getBizCode(),e.getBizDesc());
        }catch (Exception e) {
            log.error("call check Exception id:{},e:{}",id,e);
            response.setResponse(BizCodeEnum.BIZ_CODE_500001.getBizCode(),BizCodeEnum.BIZ_CODE_500001.getBizDesc());
        }
        log.info("call check id:{},response:{}",id,response);
        return response;
    }
}
