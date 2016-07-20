package com.oriental.finance.cat.monitor.web.controller.sys;

import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.manager.JobConfigManager;
import com.oriental.finance.cat.monitor.biz.model.sys.JobConfigBO;
import com.oriental.finance.cat.monitor.common.compoent.ValidateFactory;
import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.common.enums.system.PageEnum;
import com.oriental.finance.cat.monitor.common.exception.BizException;
import com.oriental.finance.cat.monitor.common.exception.DBException;
import com.oriental.finance.cat.monitor.common.model.Response;
import com.oriental.finance.cat.monitor.common.util.BeanCopyUtils;
import com.oriental.finance.cat.monitor.web.controller.BaseController;
import com.oriental.finance.cat.monitor.web.model.sys.JobConfigVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.oriental.finance.cat.monitor.common.enums.system.PageEnum.*;

/**
 * 描述：Job配置Controller
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Controller
@Slf4j
@RequestMapping("jobConfig")
public class JobConfigController extends BaseController {

    /**
     *  Job配置管理服务
     */
    @Autowired
    private JobConfigManager jobConfigManager;

    /**
     * 查询所有Job配置信息
     * @param model 传值Model
     * @return  跳转页面
     */
    @RequestMapping("find")
    public String find(Model model,JobConfigVO jobConfigVO){
        log.info("call find jobConfigVO:{}",jobConfigVO);
        try {
            PageInfo<JobConfigBO> pageInfo = jobConfigManager.
                    findByConditionPaging(BeanCopyUtils.copy(jobConfigVO, JobConfigBO.class));
            for(JobConfigBO jobConfigBO : pageInfo.getList()){
                jobConfigBO.setSimpleJobClass(Class.forName(jobConfigBO.getJobClass()).getSimpleName());
            }
            model.addAttribute(RESP_PAGING.getCode(),pageInfo);
        }catch (BizException e){
            log.error("call find BizException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),e.getFullExceptionInfo());
        }catch (Exception e){
            log.error("call find Exception:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),BizCodeEnum.getFullBizInfo(BizCodeEnum.BIZ_CODE_500001));
        }

        model.addAttribute(RESP_TITLE_JOB.getCode(),RESP_TITLE_JOB.getDesc());
        model.addAttribute(RESP_URL_PATH_JOB.getCode(),RESP_URL_PATH_JOB.getDesc());
        return RESP_DIRECT_PAGE_JOB.getCode();
    }

    /**
     * 添加Job配置信息
     * @param model 传值Model
     * @param jobConfigVO job配置信息
     * @return  查询页面
     */
    @RequestMapping("add")
    public String add(Model model,JobConfigVO jobConfigVO){
        try {
           log.info("call add jobConfigBO:{}", jobConfigVO);
            ValidateFactory.validateModel(jobConfigVO);
            jobConfigManager.addJob(BeanCopyUtils.copy(jobConfigVO, JobConfigBO.class));
            model.addAttribute(RESP_RESULT.getCode(),BizCodeEnum.BIZ_CODE_200102.getBizDesc());
        }catch (DBException e){
            log.error("call add DBException:{}",e);
            model.addAttribute(PageEnum.RESP_ERROR_RESULT.getCode(),e.getFullExceptionInfo());
        }catch (BizException e){
            log.error("call add BizException:{}",e);
            model.addAttribute(PageEnum.RESP_ERROR_RESULT.getCode(),e.getFullExceptionInfo());
        }catch (Exception e){
            log.error("call add Exception:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),BizCodeEnum.getFullBizInfo(BizCodeEnum.BIZ_CODE_500001));
        }
        return find(model,jobConfigVO);
    }

    /**
     * 删除Job配置信息
     * @param id 主键删除
     * @return  查询页面
     */
    @RequestMapping("delete")
    @ResponseBody
    public Response<Boolean> delete(String id){
        log.info("call delete id:{}", id);
        Response<Boolean> response = new Response<>();
        try {
            Validate.notEmpty(id);
            jobConfigManager.unloadJob(id);
            response.setResult(true);
        }catch (BizException e){
            log.error("call delete id:{},BizException:{}",id,e);
            response.setResponse(e.getBizCode(),e.getBizDesc());
        }catch (IllegalArgumentException e){
            log.error("call delete id:{},IllegalArgumentException:{}",id,e);
            response.setResponse(BizCodeEnum.BIZ_CODE_500101.getBizCode(),BizCodeEnum.BIZ_CODE_500101.getBizDesc());
        }catch (Exception e){
            log.error("call delete id:{},Exception:{}",id,e);
            response.setResponse(BizCodeEnum.BIZ_CODE_500101.getBizCode(),BizCodeEnum.BIZ_CODE_500101.getBizDesc());
        }
        return response;
    }

    /**
     * 更新Job配置信息
     * @param model 传值Model
     * @param jobConfigVO job配置信息
     * @return  查询页面
     */
    @RequestMapping("update")
    public String update(Model model,JobConfigVO jobConfigVO){
        log.info("call update jobConfigVO:{}", jobConfigVO);
        try {
            ValidateFactory.validateModel(jobConfigVO);
            jobConfigManager.refurbishJob(BeanCopyUtils.copy(jobConfigVO, JobConfigBO.class));
            model.addAttribute(RESP_RESULT.getCode(),BizCodeEnum.BIZ_CODE_200103.getBizDesc());
        }catch (BizException e){
            log.error("call update BizException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),e.getFullExceptionInfo());
        }catch (Exception e){
            log.error("call update Exception:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),BizCodeEnum.getFullBizInfo(BizCodeEnum.BIZ_CODE_500001));
        }
        return find(model,jobConfigVO);
    }

}
