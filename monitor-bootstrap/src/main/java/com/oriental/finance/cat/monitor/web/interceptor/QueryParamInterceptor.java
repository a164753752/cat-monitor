package com.oriental.finance.cat.monitor.web.interceptor;

import com.oriental.finance.cat.monitor.common.enums.system.PageEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：查询参数拦截器
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Slf4j
public class QueryParamInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(PageEnum.RESP_QUERY_PARAM.getCode(), getQueryParam(request));
        request.setAttribute(PageEnum.RESP_QUERY_MAP.getCode(),getQueryMap(request));
        log.debug("call preHandle queryParam:{},queryMap:{}",
                request.getAttribute(PageEnum.RESP_QUERY_PARAM.getCode()),
                request.getAttribute(PageEnum.RESP_QUERY_MAP.getCode()));
        return super.preHandle(request, response, handler);
    }

    /**
     * 获取查询参数map 数组方式
     * @return  Map
     */
    private Map<String,String[]> getQueryMaps(HttpServletRequest request){
        Map<String,String[]> queryMaps = new HashMap<>();
        for(Map.Entry<String,String[]> entry : request.getParameterMap().entrySet()){
            queryMaps.put(entry.getKey(),entry.getValue());
        }
        return queryMaps;
    }

    /**
     * 获取查询参数map
     * @return  Map
     */
    private Map<String,String> getQueryMap(HttpServletRequest request){
        Map<String,String> queryMap = new HashMap<>();
        for(Map.Entry<String,String[]> entry : request.getParameterMap().entrySet()){
            queryMap.put(entry.getKey(),entry.getValue()[0]);
        }
        return queryMap;
    }

    /**
     * 获取查询参数
     * @return  查询参数
     */
    private String getQueryParam(HttpServletRequest request){
        Enumeration params = request.getParameterNames();
        String queryParam = "";
        while (params.hasMoreElements()){
            String param = params.nextElement()+"";
            queryParam += "&"+param+"="+request.getParameter(param);
        }
        return queryParam;
    }
}
