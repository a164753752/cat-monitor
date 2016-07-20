<%@ page import="com.oriental.finance.cat.monitor.common.enums.status.JobStatusEnum" %>
<%@ page import="com.oriental.finance.cat.monitor.web.util.SpringContext" %>
<%@ page import="com.oriental.finance.cat.monitor.biz.cache.Caches" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.oriental.finance.cat.monitor.biz.model.sys.ScriptCommandConfigBO" %>
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
    <script src="${pageContext.request.contextPath}/js/sys.js"></script>
</head>
<body style="background-color: #ffffff;height:666px">


<jsp:include page="/pages/layout/search.jsp"/>

<form class="form-search" role="form" action="${pageContext.request.contextPath}/jobConfig/find" method="post">

    <div class="page-content">

        <div class="row cat-search-condition">
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="调用日期" class="nav-search-input"
                           id="jobName" name="jobName" value="${queryMap['jobName']}"/>
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="任务所属组" class="nav-search-input"
                           id="jobGroup" name="jobGroup" value="${queryMap['jobGroup']}"/>
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="任务类名" class="nav-search-input"
                           id="jobClass" name="jobClass" value="${queryMap['jobClass']}"/>
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <label>
                    <span class="lbl" >任务状态:</span>
                    <select name="jobStatus">
                        <option value="">所有</option>
                        <c:forEach var="jobStatus" items="<%=JobStatusEnum.values()%>">
                            <option value="${jobStatus.code}">${jobStatus.desc}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <label>
                    <span class="lbl">指定命令:</span>
                    <select name="jobCommandKey">
                        <option value="">无</option>
                        <%
                            Caches caches = SpringContext.getBean("caches", Caches.class);
                            for(Map.Entry<String, ScriptCommandConfigBO> entry : caches.getCommandConfigCache().entrySet()){
                        %>
                        <option value="<%=entry.getKey()%>"><%=entry.getKey()%></option>
                        <%
                            }
                        %>
                    </select>
                </label>
            </div>

        </div>


        <div class="table-responsive">
            <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th class="center">
                        详情
                    </th>
                    <th>ID</th>
                    <th>名称</th>
                    <th>所属组</th>
                    <th>实现类</th>
                    <th>运行规则</th>
                    <th>状态</th>
                    <th>最后执行时间</th>
                    <th>下次执行时间</th>
                    <th>执行时间</th>
                    <th>指定命令</th>
                    <th style="text-align: center">
                        <button type="button" class="btn btn-xs btn-info" data-toggle="modal"
                                data-target="#jobConfigModal" title="新增" onclick="jobConfigModule.add()">
                            <i class="icon-zoom-in bigger-120"></i>
                        </button>
                    </th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="jobConfig" items="${pageInfo.list}" varStatus="status">
                <tr id="tr_jobConfig_${jobConfig.id}">
                    <td class="center">
                        <button type="button" class="btn btn-xs btn-warning" title="详细信息">
                            <i class="icon-eye-open bigger-100"></i>
                        </button>
                        <input type="hidden" id="id_${status.index}" value="${jobConfig.id}"/>
                        <input type="hidden" id="retryTimes_${status.index}" value="${jobConfig.retryTimes}"/>
                        <input type="hidden" id="isClusters_${status.index}" value="${jobConfig.isClusters}"/>
                    </td>
                    <td id="id_${status.index}">${jobConfig.id}</td>
                    <td id="jobName_${status.index}">${jobConfig.jobName}</td>
                    <td id="jobGroup_${status.index}">${jobConfig.jobGroup}</td>
                    <td id="jobClass_${status.index}" title="${jobConfig.jobClass}">${jobConfig.simpleJobClass}</td>
                    <td id="jobCronExpress_${status.index}">${jobConfig.jobCronExpress}</td>
                    <c:forEach var="jobStatus" items="<%=JobStatusEnum.values()%>">
                        <c:if test="${jobStatus.code==jobConfig.status}">
                            <td>
                                ${jobStatus.desc}
                                <input type="hidden" id="jobStatus_${status.index}" value="${jobStatus.code}"/>
                            </td>
                        </c:if>
                    </c:forEach>
                    <td id="lastExecTime_${status.index}"><fmt:formatDate value="${jobConfig.lastExecTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td id="nextExecTime_${status.index}"><fmt:formatDate value="${jobConfig.nextExecTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td id="jobUsedTime_${status.index}"><fmt:formatDate value="${jobConfig.jobUsedTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td id="jobCommandKey_${status.index}">${jobConfig.jobCommandKey}</td>
                    <td>
                        <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                            <button type="button" class="btn btn-xs btn-info" title="修改"
                                    data-toggle="modal" data-target="#jobConfigModal" onclick="jobConfigModule.modify('${status.index}')">
                                <i class="icon-edit bigger-120"></i>
                            </button>
                            <button type="button" class="btn btn-xs btn-danger"  title="删除"
                                    data-toggle="modal" data-target="#infoModal" onclick="jobConfigModule.showDelete('${jobConfig.id}','${title}')">
                                <i class="icon-trash bigger-120"></i>
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

<form id="jobConfigForm" action="${pageContext.request.contextPath}/jobConfig/add" method="post">
    <!-- 信息 -->
    <div class="modal fade" id="jobConfigModal" tabindex="-1" role="dialog"
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
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-6" align="left">
                            <div class="row">
                                <div class="col-xs-6" style="float:none">任务名称</div>
                                <div class="col-xs-6">
                                    <span class="input-icon">
                                        <input type="text" placeholder="任务名称" name="jobName"
                                               id="jobConfig_jobName" readonly/>
                                        <i class="icon-leaf blue"></i>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="row">
                                <div class="col-xs-6" style="float:none">任务所属组</div>
                                <div class="col-xs-6">
                                    <span class="input-icon">
                                        <input type="text" placeholder="任务所属组" name="jobGroup"
                                               id="jobConfig_jobGroup" readonly/>
                                        <i class="icon-leaf blue"></i>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top:5px">
                        <div class="col-xs-6" align="left">
                            <div class="row">
                                <div class="col-xs-6" style="float:none">任务类名</div>
                                <div class="col-xs-6">
                                    <span class="input-icon">
                                        <input type="text" placeholder="任务类名" name="jobClass"
                                               id="jobConfig_jobClass" readonly/>
                                        <i class="icon-leaf blue"></i>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6" align="left">
                            <div class="row">
                                <div class="col-xs-4" style="float:none">运行规则</div>
                                <div class="col-xs-8">
                                    <span class="input-icon">
                                        <input type="text" placeholder="运行规则" style="width:120px"
                                        id="jobConfig_jobCronExpress" name="jobCronExpress" readonly/>
                                        <i class="icon-leaf blue"></i>
                                    </span>
                                    <button type="button" class="btn btn-xs btn-info" data-toggle="modal"
                                            data-target="#quartzModal" title="新增规则" style="margin-bottom:5px"
                                            onclick="quartzClass.showQuartzExpression()">
                                        <i class="icon-zoom-in bigger-180"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr />

                    <div class="row">
                        <div class="col-xs-6" align="left">
                            <div class="row">
                                <div class="col-xs-4" style="float:none">运行状态</div>
                                <div class="col-xs-8">
                                    <i class="icon-leaf blue bigger-150"></i>
                                    <span class="input-icon">
                                        <select name="status" id="jobConfig_jobStatus">
                                            <c:forEach var="jobStatus" items="<%=JobStatusEnum.values()%>">
                                                <option value="${jobStatus.code}">${jobStatus.desc}</option>
                                            </c:forEach>
                                        </select>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6" align="left">
                            <div class="row">
                                <div class="col-xs-4" style="float:none">指定命令</div>
                                <div class="col-xs-8">
                                    <i class="icon-leaf blue bigger-150"></i>
                                    <span class="input-icon">
                                        <select name="jobCommandKey" id="jobConfig_jobCommandKey">
                                            <option value="">无</option>
                                            <%
                                                for(Map.Entry<String, ScriptCommandConfigBO> entry : caches.getCommandConfigCache().entrySet()){
                                            %>
                                            <option value="<%=entry.getKey()%>"><%=entry.getKey()%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top:5px">
                        <div class="col-xs-6" align="left">
                            <div class="row">
                                <div class="col-xs-6" style="float:none">失败允许重试次数</div>
                                <div class="col-xs-6">
                                    <i class="icon-leaf blue bigger-150"></i>
                                    <span class="input-icon">
                                        <select name="retryTimes" id="jobConfig_retryTimes">
                                            <option value="0">0</option>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                            <option value="6">6</option>
                                        </select>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6" align="left">
                            <div class="row">
                                <div class="col-xs-6" style="float:none">是否允许多机处理</div>
                                <div class="col-xs-6">
                                    <i class="icon-leaf blue bigger-150"></i>
                                    <span class="input-icon">
                                        <select name="isClusters" id="jobConfig_isClusters">
                                            <option value="NO">否</option>
                                            <option value="YES">是</option>
                                            <option value="YxES">x</option>
                                        </select>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr />

                </div><!--modal-body-->
                <div class="modal-footer">
                    <input type="hidden" name="id" id="jobConfig_id"/>
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="submit" class="btn btn-primary">
                        确定
                    </button>
                </div><!--modal-footer-->
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</form><!--   addForm  -->

<!--quartzModal-->
<div class="modal fade" id="quartzModal" tabindex="-1" role="dialog"
     aria-labelledby="quartzModalLabel" aria-hidden="true">
    <div class="modal-dialog"  style="width:900px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="quartzModalLabel">
                    Job运行规则
                </h4>
            </div>
            <div class="modal-body" >
                <div id="quartzModalBody">
                    <h3 class="header smaller lighter grey">
                        <i class="icon-spinner icon-spin orange bigger-125"></i>
                        插件加载中...
                    </h3>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="quartzClass.quartzExpression()">
                    确定
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div><!--quartzModal-->

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

                </h4>
            </div>
            <div class="modal-body" >
                <div id="infoModalBody">

                </div>
            </div>
            <div class="modal-footer" id="infoModalFoot">
                <input type="hidden"/>
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary"
                        data-dismiss="modal" onclick="jobConfigModule.deleteJobConfig()">
                    确定
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div><!--infoModal-->


</body>
</html>
