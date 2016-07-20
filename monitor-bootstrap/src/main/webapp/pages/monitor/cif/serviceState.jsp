<%@ page import="com.oriental.finance.cat.monitor.common.enums.status.ServiceStateStatusEnum" %>
<%--
  Created by IntelliJ IDEA.
  User: lyd
  Date: 2016/7/8
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="/pages/layout/script.jsp"/>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" />
</head>
<body style="background-color: #ffffff;">

<form class="form-search" role="form" action="${pageContext.request.contextPath}/cif/serviceState/find" method="post">

    <jsp:include page="/pages/layout/search.jsp"/>

    <div class="page-content">

        <div class="row cat-search-condition">
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="调用日期" class="nav-search-input"
                           id="serviceDate" name="serviceDate" value="${queryMap['serviceDate']}"/>
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="服务类型" class="nav-search-input"
                           id="serviceType" name="serviceType" value="${queryMap['serviceType']}"/>
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="服务平台" class="nav-search-input"
                           id="servicePlatform" name="servicePlatform" value="${queryMap['servicePlatform']}"/>
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="服务路径" class="nav-search-input"
                           id="servicePath" name="servicePath" value="${queryMap['servicePath']}"/>
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <label>
                    <span class="lbl">服务状态:</span>
                    <select name="serviceStatus">
                        <option value="">所有</option>
                        <option value="<%=ServiceStateStatusEnum.INIT.getStatus()%>"><%=ServiceStateStatusEnum.INIT.getDesc()%></option>
                        <option value="<%=ServiceStateStatusEnum.SUCCESS.getStatus()%>"><%=ServiceStateStatusEnum.SUCCESS.getDesc()%></option>
                        <option value="<%=ServiceStateStatusEnum.FAIL.getStatus()%>"><%=ServiceStateStatusEnum.FAIL.getDesc()%></option>
                    </select>
                </label>
            </div>
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="服务响应码" class="nav-search-input"
                           id="serviceRespCode" name="serviceRespCode" value="${queryMap['serviceRespCode']}"/>
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="服务关联号" class="nav-search-input"
                           id="relationNo" name="relationNo" value="${queryMap['relationNo']}"/>
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
        </div>


        <div class="table-responsive">
            <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>调用日期</th>
                    <th>服务类型</th>
                    <th>服务路径</th>
                    <th>服务平台</th>
                    <th>开始执行时间</th>
                    <th>执行结束时间</th>
                    <th>执行时间(毫秒)</th>
                    <th>状态</th>
                    <th>响应码</th>
                    <th>响应描述</th>
                    <th style="text-align: center">
                        操作
                    </th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="serviceState" items="${pageInfo.list}" varStatus="status">
                <tr>
                    <td>
                        <fmt:formatDate value="${serviceState.serviceDate}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>${serviceState.serviceType}</td>
                    <td title="${serviceState.servicePath}">${serviceState.simpleServicePath}</td>
                    <td>${serviceState.servicePlatform}</td>
                    <td><fmt:formatDate value="${serviceState.startExecuteTime}" pattern="yyyy-MM-dd HH:mm:ss,SSS"/></td>
                    <td><fmt:formatDate value="${serviceState.endExecuteTime}" pattern="yyyy-MM-dd HH:mm:ss,SSS"/></td>
                    <td>${serviceState.executeTime}</td>
                    <c:forEach var="serviceStateStatusEnum" items="<%=ServiceStateStatusEnum.values()%>">
                        <c:if test="${serviceStateStatusEnum.status==serviceState.serviceStatus}">
                            <td>${serviceStateStatusEnum.desc}</td>
                        </c:if>
                    </c:forEach>
                    <td>${serviceState.serviceRespCode}</td>
                    <td>${serviceState.serviceRespDesc}</td>
                    <td>
                        <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                            <button class="btn btn-xs btn-info" title="详细信息">
                                <i class="icon-eye-open bigger-120"></i>
                            </button>
                        </div>
                    </td>
                </tr>
                </c:forEach>

                </tbody>
            </table>
        </div><!-- /.table-responsive -->

        <div class="row cat-search-paging">
            <div class="col-xs-4">
                <button class="btn btn-primary cat-search-button">
                    <i class="icon-search align-middle bigger-125"></i>
                    搜索
                </button>
            </div>
            <div class="col-xs-2">
            </div>
            <div class="col-xs-6">
                <%@include file="/pages/layout/paging.jsp"%>
            </div>
        </div>

    </div>

</form><!--  form   -->


</body>
</html>
