<%@ page import="com.oriental.finance.cat.monitor.common.enums.status.ConnexityCheckStatusEnum" %>
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
    <script src="${pageContext.request.contextPath}/js/cif.js"></script>
</head>
<body style="background-color: #ffffff;height:666px">


<jsp:include page="/pages/layout/search.jsp"/>

<form id="connexityForm" class="form-search" action="${pageContext.request.contextPath}/connexityCheck/find" method="post">

    <div class="page-content">

        <!--  搜索条件  -->
        <div class="row cat-search-condition">
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="应用平台" class="nav-search-input"
                           id="applyPlatform" name="applyPlatform" value="${queryMap['applyPlatform']}" />
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="连通性类型" class="nav-search-input"
                           id="connexityType" name="connexityType" value="${queryMap['connexityType']}" />
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <label>
                    <span class="lbl" >检测状态:</span>
                    <select name="checkStatus">
                        <option value="">所有</option>
                        <option value="<%=ConnexityCheckStatusEnum.NORMAL.getStatus()%>"><%=ConnexityCheckStatusEnum.NORMAL.getDesc()%></option>
                        <option value="<%=ConnexityCheckStatusEnum.ABNORMAL.getStatus()%>"><%=ConnexityCheckStatusEnum.ABNORMAL.getDesc()%></option>
                        <option value="<%=ConnexityCheckStatusEnum.TIMEOUT.getStatus()%>"><%=ConnexityCheckStatusEnum.TIMEOUT.getDesc()%></option>
                    </select>
                </label>
            </div>
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="检测时间" class="nav-search-input"
                           id="checkTime" name="checkTime" value="${queryMap['checkTime']}" />
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
        </div><!--  搜索条件  -->


        <div class="table-responsive">
            <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>应用平台</th>
                    <th>连通性类型</th>
                    <th>检测地址</th>
                    <th>检测时间</th>
                    <th>检测状态</th>
                    <th>
                        手动检测
                    </th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="connexityCheck" items="${pageInfo.list}">
                <tr>
                    <td>${connexityCheck.id}</td>
                    <td>${connexityCheck.applyPlatform}</td>
                    <td>${connexityCheck.connexityType}</td>
                    <td title="${connexityCheck.checkUrl}">${connexityCheck.checkUrl}</td>
                    <td><fmt:formatDate value="${connexityCheck.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <c:forEach var="checkStatus" items="<%=ConnexityCheckStatusEnum.values()%>">
                        <c:if test="${checkStatus.status==connexityCheck.checkStatus}">
                            <td>${checkStatus.desc}</td>
                        </c:if>
                    </c:forEach>
                    <td>
                        <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                            <button class="btn btn-xs btn-primary" title="手动检测" type="button"
                                    data-toggle="modal" data-target="#infoModal" onclick="cifSysStateModule.connexityCheck('${connexityCheck.id}')">
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

<!--infoModal-->
<div class="modal fade" id="infoModal" tabindex="-1" role="dialog"
     aria-labelledby="infoModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="infoModalLabel">
                    连通性检测
                </h4>
            </div>
            <div class="modal-body" >
                <div id="infoModalBody">

                </div>
            </div>
            <div class="modal-footer" id="infoModalFoot">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary"
                        data-dismiss="modal">
                    确定
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div><!--infoModal-->

</body>
</html>
