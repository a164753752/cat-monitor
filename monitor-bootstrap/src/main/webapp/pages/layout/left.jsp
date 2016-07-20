<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
</head>
<body>

<div class="sidebar" id="sidebar">
    <script type="text/javascript">
        try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
    </script>

    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success" title="小按钮1">
                <i class="icon-signal"></i>
            </button>

            <button class="btn btn-info" title="小按钮2">
                <i class="icon-pencil"></i>
            </button>

            <button class="btn btn-warning" title="小按钮3">
                <i class="icon-group"></i>
            </button>

            <button class="btn btn-danger" title="小按钮4">
                <i class="icon-cogs"></i>
            </button>
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div><!-- #sidebar-shortcuts -->

    <ul class="nav nav-list">

        <li class="active" >
            <a href="${pageContext.request.contextPath}/pages/dashboard.jsp" target="pageFrame">
                <i class="icon-dashboard"></i>
                <span class="menu-text"> 仪表盘 </span>
            </a>
        </li>

        <!--  业务监控  -->
        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-desktop"></i>
                <span class="menu-text"> 业务监控 </span>
                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-double-angle-right"></i>
                        客户系统(CIF)
                        <b class="arrow icon-angle-down"></b>
                    </a>

                    <ul class="submenu">
                        <li>
                            <a href="${pageContext.request.contextPath}/cif/serviceState/find" target="pageFrame">
                                <i class="icon-leaf"></i>
                                服务状况
                            </a>
                        </li>

                        <li>
                            <a href="#" class="dropdown-toggle">
                                <i class="icon-double-angle-right"></i>
                                应用状况
                                <b class="arrow icon-angle-down"></b>
                            </a>

                            <ul class="submenu">
                                <li>
                                    <a href="${pageContext.request.contextPath}/cif/dataSource/find" target="pageFrame">
                                        <i class="icon-sitemap"></i>
                                        数据库连接池
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/cif/providerThread/find" target="pageFrame">
                                        <i class="icon-eye-open"></i>
                                        DUBBO线程池
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/connexityCheck/find" target="pageFrame">
                                        <i class="icon-exchange"></i>
                                        应用连通性
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </li><!--  业务监控  -->

        <!--  CAT系统配置  -->
        <li class="dropdown-toggle">
            <a href="#" class="dropdown-toggle">
                <i class="icon-legal"></i>
                <span class="menu-text"> CAT监控系统配置 </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu">
                <li>
                    <a href="${pageContext.request.contextPath}/druid/index.html"
                       class="dropdown-toggle" target="pageFrame">
                        <i class="icon-double-angle-right"></i>
                        应用状况(druid)
                    </a>
                </li>

                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-double-angle-right"></i>
                        系统配置
                        <b class="arrow icon-angle-down"></b>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="${pageContext.request.contextPath}/scriptCommandConfig/find"
                               class="dropdown-toggle" target="pageFrame">
                                <i class="icon-cog"></i>
                                脚本命令配置
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/jobConfig/find"
                               class="dropdown-toggle" target="pageFrame">
                                <i class="icon-cog"></i>
                                定时JOB配置
                            </a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/cache/load"
                       class="dropdown-toggle" target="_self">
                        <i class="icon-double-angle-right"></i>
                        缓存刷新
                    </a>
                </li>
            </ul>
        </li><!--  CAT系统配置  -->

    </ul><!-- /.nav-list -->

    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>

    <script type="text/javascript">
        try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
    </script>
</div>

</body>
</html>