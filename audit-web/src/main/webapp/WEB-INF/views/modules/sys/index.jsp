<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>大数据业务运营管理</title>
    <%@ include file="/WEB-INF/views/commons/common.jsp" %>
    <!--index -->
    <script src="${basePath}/statics/modules/sys/js/index.js"></script>
</head>
<body class="hold-transition skin-blue-light sidebar-mini" style="overflow:hidden;">
<div class="wrapper">

    <!-- 头部部分-->
    <jsp:include page="header.jsp"/>

    <!-- 左侧菜单部分-->
    <jsp:include page="left.jsp"/>

    <!-- 内容部分-->

    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="main easyui-tabs" id="tabs" data-options="tools:'#tab-tools'">
        </div>
    </div>
    <%--<!-- 底部部分-->--%>
    <%--<jsp:include page="footer.jsp"/>--%>

    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- tab页签右键菜单-->
<div id="tabsMenu" class="easyui-menu" style="width:120px;">
    <div name="refresh" data-options="iconCls:'icon-reload'">刷新</div>
    <div name="close"  data-options="iconCls:'icon-clear'" >关闭</div>
    <div name="all"  data-options="iconCls:'icon-no'">关闭所有</div>
</div>

</body>
</html>
