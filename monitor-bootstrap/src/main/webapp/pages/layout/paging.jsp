<%--
  Created by IntelliJ IDEA.
  User: lyd
  Date: 2016/7/12
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <c:if test="${pageInfo.list != null }">
    <ul class="pagination">
        <li>
            <a href="${pageContext.request.contextPath}/${urlPath}/find?pageNum=${pageInfo.firstPage}${queryParam}">上列</a>
        </li>

        <li>
        <c:if test="${pageInfo.hasPreviousPage}">
            <a href="${pageContext.request.contextPath}/${urlPath}/find?pageNum=${pageInfo.prePage}${queryParam}">&laquo;</a>
        </c:if>
        </li>

        <c:forEach items="${pageInfo.navigatepageNums}" var="nav">
            <c:if test="${nav == pageInfo.pageNum}">
                <li class="active disabled">
                <a href="javascript:void(0);">
                        ${nav}
                </a>
            </c:if>
            <c:if test="${nav != pageInfo.pageNum}">
                <li>
                <a href="${pageContext.request.contextPath}/${urlPath}/find?pageNum=${nav}${queryParam}">
                        ${nav}
                </a>
            </c:if>
            </li>
        </c:forEach>
        <li>
        <c:if test="${pageInfo.hasNextPage}">
            <a href="${pageContext.request.contextPath}/${urlPath}/find?pageNum=${pageInfo.nextPage}${queryParam}">&raquo;</a>
        </c:if>
        </li>

        <li>
        <a href="${pageContext.request.contextPath}/${urlPath}/find?pageNum=${pageInfo.lastPage}${queryParam}">下列</a>
        </li>

        <li class="active">
            <span>总 : [ ${pageInfo.total} ] 条 , 共 : [ ${pageInfo.pages} ] 页</span>
        </li>
    </ul>
    </c:if>
</body>
</html>
