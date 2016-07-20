<%--
  Created by IntelliJ IDEA.
  User: lyd
  Date: 2016/7/18
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="pages/layout/script.jsp"/>
<div class="error-container">
    <div class="well">
        <h1 class="grey lighter smaller">
            <span class="blue bigger-125">
                <i class="icon-sitemap"></i>
                404
            </span>
            Page Not Found
        </h1>

        <hr/>
        <h3 class="lighter smaller">找了好久都没找到!</h3>

        <div>
            <form class="form-search">
                <span class="input-icon align-middle">
                    <i class="icon-search"></i>

                    <input type="text" class="search-query"
                           placeholder="search..."/>
                </span>
                <button class="btn btn-primary" onclick="return false;">Go!</button>
            </form>

            <div class="space"></div>
            <h4 class="smaller">尝试下下面的选择:</h4>

            <ul class="list-unstyled spaced inline bigger-110 margin-15">
                <li>
                    <i class="icon-hand-right blue"></i>
                    重新检查下访问的URL地址
                </li>

                <li>
                    <i class="icon-hand-right blue"></i>
                    休息一会!
                </li>

                <li>
                    <i class="icon-hand-right blue"></i>
                    再休息一会!
                </li>
            </ul>
        </div>

        <hr/>
        <div class="space"></div>

        <div class="center">
            <a href="#" class="btn btn-primary">
                <i class="icon-dashboard"></i>
                首页
            </a>
        </div>
    </div>
</div>