
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

<form class="form-search" action="${pageContext.request.contextPath}/cif/providerThread/find" method="post">

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
                    <th>活动数</th>
                    <th>初始数</th>
                    <th>激活数</th>
                    <th>最大数</th>
                    <th>线程峰值数</th>
                    <th>现任务数</th>
                    <th>任务完成数</th>
                    <th>队列缓存数</th>
                    <th>记录时间</th>
                    <th style="text-align: center">
                        操作
                    </th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="providerThreadState" items="${pageInfo.list}" varStatus="status">
                <tr>
                    <td>
                        ${providerThreadState.id}
                    </td>
                    <td>${providerThreadState.poolSize}</td>
                    <td>${providerThreadState.corePoolSize}</td>
                    <td>${providerThreadState.activeCount}</td>
                    <td>${providerThreadState.maxPoolSize}</td>
                    <td>${providerThreadState.largestPoolSize}</td>
                    <td>${providerThreadState.taskCount}</td>
                    <td>${providerThreadState.completedTaskCount}</td>
                    <td>${providerThreadState.queueSize}</td>
                    <td><fmt:formatDate value="${providerThreadState.recordDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                            <button type="button" class="btn btn-xs btn-info" title="详细信息">
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
                <button type="button" class="btn btn-success cat-search-button" id="providerThreadState"
                        onclick="cifSysStateModule.getProviderThreadState()" data-toggle="modal" data-target="#infoModal">
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
                    <div class="modal-body" id="providerThreadModalBody">
                        <div class="row" >
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">当前的线程数</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="poolSize"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">最小线程数</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="corePoolSize"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" >
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">最大线程数</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="maxPoolSize"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">峰值线程数</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="largestPoolSize"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr/>

                        <div class="row" >
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">激活数</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="activeCount"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">任务数</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="taskCount"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" >
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">成功任务数</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="completedTaskCount"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6" align="left">
                                <div class="row">
                                    <div class="col-xs-6" style="float:none">缓存队列大小</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="queueSize"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr />

                        <div class="row" >
                            <div class="col-xs-6" align="left">
                                <div class="row" title="当线程池中的线程数大于corePoolSize才起作用">
                                    <div class="col-xs-6" style="float:none">空闲线程存活时间(毫秒)</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="keepAliveTime"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6" align="left">
                                <div class="row" title="当线程池中的线程数不大于corePoolSize也会销毁线程">
                                    <div class="col-xs-6" style="float:none">允许销毁空闲核心线程</div>
                                    <div class="col-xs-6">
                                        <span class="input-icon">
                                            <input type="text" disabled id="allowReleaseCodeIdle"/>
                                            <i class="icon-leaf blue"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr/>


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
