<%--
  Created by IntelliJ IDEA.
  User: lyd
  Date: 2016/7/12
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/pages/layout/script.jsp"/>
    <title></title>
</head>
<body marginheight="580px" style="background-image: url('${pageContext.request.contextPath}/img/bg.png')">

<div class="page-content">
    <div class="page-header">
        <div class="alert alert-block alert-success">
            <button type="button" class="close" data-dismiss="alert">
                <i class="icon-remove"></i>
            </button>
            <i class="icon-ok green"></i>
            欢迎使用
            <strong class="green">
               CAT-MONITOR监控系统
                <small>(v1.0.0)</small>
            </strong>
        </div>
    </div>
    <!-- /.page-header -->

    <div class="row">
        <div class="col-xs-6">
            <div class="widget-box">
                <div class="widget-header widget-header-flat widget-header-small">
                    <h5>
                        <i class="icon-signal"></i>
                        访问来源
                    </h5>

                    <div class="widget-toolbar no-border">
                        <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown"
                                style="margin-top:5px">
                            本周
                            <i class="icon-angle-down icon-on-right bigger-110"></i>
                        </button>

                        <ul class="dropdown-menu pull-right dropdown-125 dropdown-lighter dropdown-caret">
                            <li class="active">
                                <a href="#" class="blue">
                                    <i class="icon-caret-right bigger-110">&nbsp;</i>
                                    本周
                                </a>
                            </li>

                            <li>
                                <a href="#">
                                    <i class="icon-caret-right bigger-110 invisible">&nbsp;</i>
                                    上周
                                </a>
                            </li>

                            <li>
                                <a href="#">
                                    <i class="icon-caret-right bigger-110 invisible">&nbsp;</i>
                                    本月
                                </a>
                            </li>

                            <li>
                                <a href="#">
                                    <i class="icon-caret-right bigger-110 invisible">&nbsp;</i>
                                    上月
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="widget-body">
                    <div class="widget-main">
                        <div id="piechart-placeholder"></div>

                        <div class="hr hr8 hr-double"></div>

                        <div class="clearfix">
                            <div class="grid3">
                                <span class="grey">
                                    开户
                                </span>
                                <h4 class="bigger pull-right">1,050</h4>
                            </div>
                            <div class="grid3">
                                <span class="grey">
                                    银行卡签约申请
                                </span>
                                <h4 class="bigger pull-right">1,050</h4>
                            </div>
                            <div class="grid3">
                                <span class="grey">
                                    银行卡签约
                                </span>
                                <h4 class="bigger pull-right">1,050</h4>
                            </div>
                        </div>
                    </div><!-- /widget-main -->
                </div><!-- /widget-body -->
            </div><!-- /widget-box -->
        </div>
        <div class="col-xs-6">

        </div>
    </div>

</div>


</body>
</html>
