<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="breadcrumbs" id="breadcrumbs" style="padding-top:8px;">

    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="${pageContext.request.contextPath}/index.jsp" target="_parent">首页</a>
        </li>
        <li class="active">${title}</li>
    </ul><!-- .breadcrumb -->

    <div class="nav-search" id="nav-search">
        <span class="input-icon">
            <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" />
            <i class="icon-search nav-search-icon"></i>
        </span>
    </div><!-- #nav-search -->

</div>

<c:if test="${result != null}">
    <div class="alert alert-info" align="center">
        <button type="button" class="close" data-dismiss="alert" style="float: left;">
            <i class="icon-remove"></i>
        </button>
        <strong>系统消息：</strong>
        ${result}
        <br />
    </div>
</c:if>

<c:if test="${errorResult != null}">
    <div class="alert alert-warning" align="center">
        <button type="button" class="close" data-dismiss="alert" style="float: left;">
            <i class="icon-remove"></i>
        </button>
        <strong>系统消息：</strong>
            ${errorResult}
        <br />
    </div>
</c:if>