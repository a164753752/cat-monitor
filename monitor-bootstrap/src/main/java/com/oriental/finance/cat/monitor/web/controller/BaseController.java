package com.oriental.finance.cat.monitor.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/9 ProjectName:cat-monitor Version: 1.0
 */
@Slf4j
public class BaseController {

    /**
     * 设置session
     * @param key   key值
     * @param value value值
     */
    protected void setSessionV(String key,Object value){
        getSession().setAttribute(key,value);
    }

    /**
     * 获取session
     * @return  HttpSession
     */
    protected HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
            log.error("call getSession Exception:{}",e);
        }
        return session;
    }

    /**
     * 获取request
     * @return  HttpServletRequest
     */
    protected HttpServletRequest getRequest() {
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

}
