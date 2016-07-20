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
<body style="background-color: #ffffff;">

<form class="form-search" role="form" action="${pageContext.request.contextPath}/cif/dataSource/find" method="post">

    <jsp:include page="/pages/layout/search.jsp"/>

    <div class="page-content">

        <div class="row cat-search-condition">
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="应用平台" class="nav-search-input"
                           id="applyPlatform" name="applyPlatform" value="${queryMap['applyPlatform']}" />
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
        </div>


        <div class="table-responsive">
            <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>平台</th>
                    <th>用户名</th>
                    <th>活动数</th>
                    <th>空闲数</th>
                    <th>初始数</th>
                    <th>最大数</th>
                    <th>记录时间</th>
                    <th style="text-align: center">
                        操作
                    </th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="dataSourceState" items="${pageInfo.list}" varStatus="status">
                <tr>
                    <td>
                        ${dataSourceState.id}
                    </td>
                    <td>${dataSourceState.platform}</td>
                    <td>
                        ${dataSourceState.userName}
                    </td>
                    <td>${dataSourceState.numActive}</td>
                    <td>${dataSourceState.numIdle}</td>
                    <td>${dataSourceState.initialSize}</td>
                    <td>${dataSourceState.maxSize}</td>
                    <td><fmt:formatDate value="${dataSourceState.recordDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
                <button type="button" class="btn btn-success cat-search-button" id="dataSourceState"
                        onclick="cifSysStateModule.getDataSourceState()" data-toggle="modal" data-target="#infoModal">
                    <i class="icon-refresh red align-middle bigger-125"></i>
                    当前状况
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

        <!-- 信息 -->
        <div class="modal fade" id="infoModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close"
                                data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            ${title}
                        </h4>
                    </div><!--modal-header-->
                    <div class="modal-body" id="dataSourceModalBody">
                        <div class="row" >
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">用户名</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="userName"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">URL</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="url"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" >
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">DB驱动名</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="driverClassName"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">事物自动提交</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="autoCommit"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" >
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">当前活动连接数</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="numActive"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">当前空闲连接数</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="numIdle"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr />

                        <div class="row" >
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">
                                        初始化时创建的连接数
                                    </div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="initialSize"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">活动连接的最大数量</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="maxSize"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" >
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">最小空闲连接数</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="minIdle"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">最大空闲连接数</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="maxIdle"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" >
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">获取连接最大等待时间</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="maxWait"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">空闲连接存活时间(毫秒)</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="idleAliveTime"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr />

                        <div class="row" >
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">获取连接时进行连接测试</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="testOnBorrow"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">执行空闲连接回收器时间</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="idleReleaseTime"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr />

                    </div><!--modal-body-->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            关闭
                        </button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">
                            确定
                        </button>
                    </div><!--modal-footer-->
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>

</body>
</html>
