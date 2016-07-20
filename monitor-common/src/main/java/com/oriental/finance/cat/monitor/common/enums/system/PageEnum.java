package com.oriental.finance.cat.monitor.common.enums.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：业面信息枚举
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/17 ProjectName:cat-monitor Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum PageEnum {

    /**
     * 分页信息
     */

    PAGE_NUM("1","默认起始页"),

    NAV_PAGE("5","导航页大小"),

    PAGE_SIZE("5","每页大小"),

    ORDER_BY_UPDATE_AT("UPDATE_AT DESC","最后更新时间降序"),

    /**
     * 请求响应信息
     */

    //分页查询
    RESP_PAGING("pageInfo","分页查询"),
    RESP_QUERY_PARAM("queryParam","查询参数"),
    RESP_QUERY_MAP("queryMap","查询参数Map"),

    //响应结果
    RESP_RESULT("result","响应结果"),
    RESP_ERROR_RESULT("errorResult","异常响应结果"),

    //title
    RESP_TITLE_JOB("title","Job配置"),
    RESP_TITLE_SCRIPT_COMMAND("title","脚本命令配置"),
    RESP_TITLE_CONNEXITY_CHECK("title","应用连通性检测信息"),
    RESP_TITLE_SERVICE_STATE("title","服务状况信息"),
    RESP_TITLE_DATA_SOURCE_STATE("title","数据源信息"),
    RESP_TITLE_THREAD_POOL_STATE("title","服务线程池信息"),

    //分页urlPath
    RESP_URL_PATH_JOB("urlPath","jobConfig"),
    RESP_URL_PATH_SCRIPT_COMMAND("urlPath","scriptCommandConfig"),
    RESP_URL_PATH_CONNEXITY_CHECK("urlPath","connexityCheck"),
    RESP_URL_PATH_SERVICE_STATE("urlPath","cif/serviceState"),
    RESP_URL_PATH_DATA_SOURCE_STATE("urlPath","cif/dataSource"),
    RESP_URL_PATH_THREAD_POOL_STATE("urlPath","cif/providerThread"),

    //页面跳转路径信息
    RESP_DIRECT_PAGE_JOB("pages/sys/config/jobConfig","job配置信息页面"),
    RESP_DIRECT_PAGE_SCRIPT_COMMAND("pages/sys/config/scriptCommandConfig","脚本命令配置信息页面"),
    RESP_DIRECT_PAGE_CONNEXITY_CHECK("pages/monitor/check/connexityCheck","连通性检测页面"),
    RESP_DIRECT_PAGE_SERVICE_STATE("pages/monitor/cif/serviceState","服务状况信息页面"),
    RESP_DIRECT_PAGE_DATA_SOURCE_STATE("pages/monitor/cif/dataSourceState","数据源状况页面"),
    RESP_DIRECT_PAGE_THREAD_POOL_STATE("pages/monitor/cif/providerThreadState","线程池状况页面"),

    ;

    private final String code;

    private final String desc;

}
