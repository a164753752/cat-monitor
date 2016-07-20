<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
    String basePath = request.getContextPath();
%>
<!-- basic styles -->
<link href="${pageContext.request.contextPath}/front_frame/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/front_frame/assets/css/font-awesome.min.css" />

<!--[if IE 7]>
<link rel="stylesheet" href="${pageContext.request.contextPath}/front_frame/assets/css/font-awesome-ie7.min.css" />
<![endif]-->

<!-- page specific plugin styles -->

<!-- fonts -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/front_frame/google/css/family.css" />

<!-- ace styles -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/front_frame/assets/css/ace.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/front_frame/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/front_frame/assets/css/ace-skins.min.css" />

<!--[if lte IE 8]>
<link rel="stylesheet" href="${pageContext.request.contextPath}/front_frame/assets/css/ace-ie.min.css" />
<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->

<script src="${pageContext.request.contextPath}/front_frame/assets/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/front_frame/assets/js/html5shiv.js"></script>
<script src="${pageContext.request.contextPath}/front_frame/assets/js/respond.min.js"></script>
<![endif]-->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="${pageContext.request.contextPath}/front_frame/google/js/jquery.min-2.0.3.js"></script>
<!-- <![endif]-->

<!--[if IE]>
<script src="${pageContext.request.contextPath}/front_frame/google/js/jquery.min-1.10.2.js"></script>
<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
    window.jQuery || document.write("<script src='${pageContext.request.contextPath}/front_frame/assets/js/jquery-2.0.3.min.js'>"+"<"+"script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${pageContext.request.contextPath}/front_frame/assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
</script>
<![endif]-->

<script type="text/javascript">
    if("ontouchend" in document) document.write("<script src='${pageContext.request.contextPath}/front_frame/assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
</script>
<script src="${pageContext.request.contextPath}/front_frame/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/front_frame/assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/front_frame/assets/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/front_frame/assets/js/jquery.dataTables.bootstrap.js"></script>

<!--[if lte IE 8]>
<script src="${pageContext.request.contextPath}/front_frame/assets/js/excanvas.min.js"></script>
<![endif]-->

<script src="${pageContext.request.contextPath}/front_frame/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/front_frame/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${pageContext.request.contextPath}/front_frame/assets/js/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/front_frame/assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="${pageContext.request.contextPath}/front_frame/assets/js/jquery.sparkline.min.js"></script>
<script src="${pageContext.request.contextPath}/front_frame/assets/js/flot/jquery.flot.min.js"></script>
<script src="${pageContext.request.contextPath}/front_frame/assets/js/flot/jquery.flot.pie.min.js"></script>
<script src="${pageContext.request.contextPath}/front_frame/assets/js/flot/jquery.flot.resize.min.js"></script>

<!-- ace scripts -->

<script src="${pageContext.request.contextPath}/front_frame/assets/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/front_frame/assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->

<!--  iframe  -->
<script type="text/javascript" language="javascript">
    function iFrameHeight() {
        var pageFrame = "pageFrame";
        var ifm = document.getElementById(pageFrame);
        var subWeb = ifm.contentDocument;
//        subWeb.body.style.backgroundColor = "#fff";
        if(ifm != null && subWeb != null) {
            ifm.height = subWeb.body.scrollHeight;
        }
    }

    var contextPath = '${pageContext.request.contextPath}';

</script>
