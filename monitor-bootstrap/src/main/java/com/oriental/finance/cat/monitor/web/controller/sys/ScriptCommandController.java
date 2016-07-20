package com.oriental.finance.cat.monitor.web.controller.sys;

import com.github.pagehelper.PageInfo;
import com.oriental.finance.cat.monitor.biz.manager.ScriptCommandConfigManager;
import com.oriental.finance.cat.monitor.biz.model.sys.ScriptCommandConfigBO;
import com.oriental.finance.cat.monitor.common.compoent.ValidateFactory;
import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.common.exception.BizException;
import com.oriental.finance.cat.monitor.common.exception.DBException;
import com.oriental.finance.cat.monitor.common.model.Response;
import com.oriental.finance.cat.monitor.common.util.BeanCopyUtils;
import com.oriental.finance.cat.monitor.common.util.CommandUtils;
import com.oriental.finance.cat.monitor.web.model.sys.ScriptCommandConfigVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.oriental.finance.cat.monitor.common.enums.system.PageEnum.*;

import java.util.List;

/**
 * 描述：脚本命令Controller
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/4 ProjectName:cat-monitor Version: 1.0
 */
@Controller
@RequestMapping("scriptCommandConfig")
@Slf4j
public class ScriptCommandController {

    /**
     * 脚本命令配置管理服务
     */
    @Autowired
    private ScriptCommandConfigManager scriptCommandConfigManager;


    /**
     * 处理shell命令
     * @param command   shell命令
     * @return  处理结果
     */
    @RequestMapping("shell")
    @ResponseBody
    public Response<List<String>> executeShell(@RequestParam("command") String command){
        log.info("call execShell command:{}",command);
        Response<List<String>> response;
        try {
            List<String> runResult = CommandUtils.runShellReturnList(command,false);
            log.info("call execShell runResult:{}",runResult);
            response = new Response<>(runResult);
        } catch (Exception e) {
            log.error("call execShell Exception:{}",e);
            response = new Response<>(e.getLocalizedMessage(),e.getMessage());
        }
        return response;
    }

    /**
     * 查询所有脚本命令配置信息
     * @param model 传值Model
     * @return  跳转页面
     */
    @RequestMapping("find")
    public String find(Model model,ScriptCommandConfigVO scriptCommandConfigVO){
        log.info("call find scriptCommandConfigBO:{}", scriptCommandConfigVO);
        try {
            PageInfo<ScriptCommandConfigBO> pageInfo  = scriptCommandConfigManager.
                    findByConditionByPaging(BeanCopyUtils.copy(scriptCommandConfigVO,ScriptCommandConfigBO.class));
            model.addAttribute(RESP_PAGING.getCode(),pageInfo);
        }catch (DBException e){
            log.error("call add DBException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(), e.getFullExceptionInfo());
        }catch (BizException e){
            log.error("call add BizException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),e.getFullExceptionInfo());
        }catch (Exception e){
            log.error("call add Exception:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),BizCodeEnum.getFullBizInfo(BizCodeEnum.BIZ_CODE_500001));
        }
        model.addAttribute(RESP_TITLE_SCRIPT_COMMAND.getCode(),RESP_TITLE_SCRIPT_COMMAND.getDesc());
        model.addAttribute(RESP_URL_PATH_SCRIPT_COMMAND.getCode(),RESP_URL_PATH_SCRIPT_COMMAND.getDesc());
        return RESP_DIRECT_PAGE_SCRIPT_COMMAND.getCode();
    }

    /**
     * 添加脚本命令配置信息
     * @param model 传值Model
     * @return  查询页面
     */
    @RequestMapping("add")
    public String add(Model model,ScriptCommandConfigVO scriptCommandConfigVO) {
        log.info("call add scriptCommandConfigVO:{}", scriptCommandConfigVO);

        try {
            ValidateFactory.validateModel(scriptCommandConfigVO);
            scriptCommandConfigManager.addScriptCommandConfig(BeanCopyUtils.copy(scriptCommandConfigVO, ScriptCommandConfigBO.class));
            model.addAttribute(RESP_RESULT.getCode(),BizCodeEnum.BIZ_CODE_200102.getBizDesc());
        }catch (DBException e){
            log.error("call add DBException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(), e.getFullExceptionInfo());
        }catch (BizException e){
            log.error("call add BizException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),e.getFullExceptionInfo());
        }catch (Exception e){
            log.error("call add Exception:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),BizCodeEnum.getFullBizInfo(BizCodeEnum.BIZ_CODE_500001));
        }

        return find(model,scriptCommandConfigVO);
    }

    /**
     * 删除脚本命令配置信息
     * @param id    脚本命令id
     * @return  Response<Boolean>
     */
    @RequestMapping("delete")
    @ResponseBody
    public Response<Boolean> delete(String id) {
        log.info("call delete id:{}", id);
        Response<Boolean> response = new Response<>();
        try {
            Validate.notEmpty(id);
            scriptCommandConfigManager.deleteScriptCommandConfig(id);
            response.setResult(true);
        }catch (DBException e){
            log.error("call delete id:{},DBException:{}",id,e);
            response.setResponse(e.getBizCode(),e.getBizDesc());
        }catch (BizException e){
            log.error("call delete id:{},BizException:{}",id,e);
            response.setResponse(e.getBizCode(),e.getBizDesc());
        }catch (IllegalArgumentException e){
            log.error("call delete id:{},IllegalArgumentException:{}",id,e);
            response.setResponse(BizCodeEnum.BIZ_CODE_500001.getBizCode(),BizCodeEnum.BIZ_CODE_500001.getBizDesc());
        }catch (Exception e){
            log.error("call delete id:{},Exception:{}",id,e);
            response.setResponse(BizCodeEnum.BIZ_CODE_500001.getBizCode(),BizCodeEnum.BIZ_CODE_500001.getBizDesc());
        }
        log.info("call delete id:{},response:{}", id,response);
        return response;
    }

    /**
     * 修改脚本命令配置信息
     * @param model 传值Model
     * @return  查询页面
     */
    @RequestMapping("update")
    public String update(Model model,ScriptCommandConfigVO scriptCommandConfigVO) {
        log.info("call update scriptCommandConfigVO:{}", scriptCommandConfigVO);

        try {
            ValidateFactory.validateModel(scriptCommandConfigVO);
            scriptCommandConfigManager.updateScriptCommandConfig(BeanCopyUtils.copy(scriptCommandConfigVO, ScriptCommandConfigBO.class));
            model.addAttribute(RESP_RESULT.getCode(),BizCodeEnum.BIZ_CODE_200103.getBizDesc());
        }catch (DBException e){
            log.error("call update DBException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(), e.getFullExceptionInfo());
        }catch (BizException e){
            log.error("call update BizException:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),e.getFullExceptionInfo());
        }catch (Exception e){
            log.error("call update Exception:{}",e);
            model.addAttribute(RESP_ERROR_RESULT.getCode(),BizCodeEnum.getFullBizInfo(BizCodeEnum.BIZ_CODE_500001));
        }

        return find(model,scriptCommandConfigVO);
    }
}
