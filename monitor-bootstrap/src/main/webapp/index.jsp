<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>CAT-MONITOR【控制台】</title>
    <meta name="description" content="CAT-MONITOR" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <jsp:include page="pages/layout/script.jsp"/>

</head>

<body>

<!--  顶部  -->
<jsp:include page="pages/layout/top.jsp"/>

<div class="main-container" id="main-container" >
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner" id="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <!--  菜单栏  -->
        <jsp:include page="pages/layout/left.jsp"/>

        <div class="main-content" id="main-content">

            <div class="page-content" style="padding:1px" id="page-content">

                <iframe name="pageFrame" id="pageFrame" frameborder="0" width="100%" height="100%"
                        scrolling="yes" onload="iFrameHeight()" src="${pageContext.request.contextPath}/pages/dashboard.jsp">

                    <!--  实际内容处理页面  -->
                </iframe>
            </div>

        </div><!-- /.main-content -->

        <!--  setting页面  -->
        <jsp:include page="pages/layout/setting.jsp"/>

    </div><!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->



</body>
</html>


