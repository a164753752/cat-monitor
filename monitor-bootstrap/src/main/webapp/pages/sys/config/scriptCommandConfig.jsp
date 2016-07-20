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

<form class="form-search" role="form" action="${pageContext.request.contextPath}/scriptCommandConfig/find" method="post">

    <div class="page-content">

        <div class="row cat-search-condition">
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="命令KEY" class="nav-search-input"
                           name="commandKey" value="${queryMap['commandKey']}"/>
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
            <div class="col-xs-3 cat-search-condition-col" align="center">
                <span class="input-icon">
                    <input type="text" placeholder="脚本命令" class="nav-search-input"
                           name="command" value="${queryMap['command']}"/>
                    <i class="icon-search nav-search-icon"></i>
                </span>
            </div>
        </div>


        <div class="table-responsive">
            <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th class="center">
                        <label>
                            <input type="checkbox" class="ace" />
                            <span class="lbl"></span>
                        </label>
                    </th>
                    <th>ID</th>
                    <th>命令Key</th>
                    <th>脚本命令</th>
                    <th>记录人</th>
                    <th>记录时间</th>
                    <th>更新人</th>
                    <th>更新时间</th>
                    <th style="text-align: center">
                        <button class="btn btn-xs btn-info" data-toggle="modal"
                                data-target="#scriptCommandConfigModal" title="新增">
                            <i class="icon-zoom-in bigger-120"></i>
                        </button>
                    </th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="scriptCommandConfig" items="${pageInfo.list}" varStatus="status">
                <tr id="tr_scriptCommandConfig_${scriptCommandConfig.id}">
                    <td class="center">
                        <label>
                            <input type="checkbox" value="${scriptCommandConfig.id}" class="ace" />
                            <span class="lbl"></span>
                        </label>
                        <input type="hidden" id="id_${status.index}" value="${scriptCommandConfig.id}"/>
                    </td>

                    <td>${scriptCommandConfig.id}</td>
                    <td id="commandKey_${status.index}">${scriptCommandConfig.commandKey}</td>
                    <td id="command_${status.index}" title="${scriptCommandConfig.command}">${scriptCommandConfig.command}</td>
                    <td>${scriptCommandConfig.createBy}</td>
                    <td><fmt:formatDate value="${scriptCommandConfig.createAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${scriptCommandConfig.updateBy}</td>
                    <td><fmt:formatDate value="${scriptCommandConfig.updateAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                            <button type="button" class="btn btn-xs btn-info" title="修改" data-toggle="modal"
                                    data-target="#scriptCommandConfigModal" onclick="scriptCommandConfigModule.modify('${status.index}')">
                                <i class="icon-edit bigger-120"></i>
                            </button>
                            <button type="button" class="btn btn-xs btn-danger" title="删除"
                                    data-toggle="modal" data-target="#infoModal" onclick="scriptCommandConfigModule.showDelete('${scriptCommandConfig.id}','${title}')">
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

<!--scriptCommandConfigForm-->
<form id="scriptCommandConfigForm" action="${pageContext.request.contextPath}/scriptCommandConfig/add" method="post">
    <!-- 信息 -->
    <div class="modal fade" id="scriptCommandConfigModal" tabindex="-1" role="dialog"
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
                    <div class="row" align="center">
                        <div class="col-xs-6">
                            <span class="input-icon">
                                <input type="text" placeholder="命令Key" name="commandKey" id="scriptCommandConfig_commandKey"/>
                                <i class="icon-leaf blue"></i>
                            </span>
                        </div>
                        <div class="col-xs-6">
                            <span class="input-icon">
                                <input type="text" placeholder="脚本命令" name="command" id="scriptCommandConfig_command"/>
                                <i class="icon-leaf blue"></i>
                            </span>
                        </div>
                    </div>
                    <hr />

                </div><!--modal-body-->
                <div class="modal-footer">
                    <input type="hidden" name="id" id="scriptCommandConfig_id"/>
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
</form><!--scriptCommandConfigForm-->

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
                        data-dismiss="modal" onclick="scriptCommandConfigModule.deleteScriptCommandConfig()">
                    确定
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div><!--infoModal-->

</body>
</html>
