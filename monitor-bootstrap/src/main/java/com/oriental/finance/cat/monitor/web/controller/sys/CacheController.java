package com.oriental.finance.cat.monitor.web.controller.sys;

import com.oriental.finance.cat.monitor.biz.cache.Caches;
import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述：缓存Controller
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/13 ProjectName:cat-monitor Version: 1.0
 */
@Controller
@Slf4j
@RequestMapping("cache")
public class CacheController extends BaseController{

    /**
     * 缓存服务
     */
    @Autowired
    private Caches caches;

    /**
     * 加载缓存
     */
    @RequestMapping("load")
    public String load(Model model){
        log.info("call load cache");
        try {
            caches.init();
            model.addAttribute(BizCodeEnum.BIZ_CODE_200101.getBizCode(),BizCodeEnum.BIZ_CODE_200101.getBizDesc());
        }catch (Exception e){
            log.error("call load cache Exception:{}",e);
            model.addAttribute(BizCodeEnum.BIZ_CODE_500001.getBizCode(),BizCodeEnum.BIZ_CODE_500001.getBizDesc());
        }
        return "index";
    }

}
