<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>菜单列表页</title>
    <%@ include file="/WEB-INF/views/commons/common.jsp" %>
</head>
<body>
<sys:message msgContent="${message}"/>
<div class="box-body" style="display: block;">
    <div class="box-tools pull-right">
        <shiro:hasPermission name="sys:menu:add">
            <input class="btn btn-success " type="button"
                   value="添加" onclick="javascript:location.href='./add'"/>
        </shiro:hasPermission>

    <%--    <shiro:hasPermission name="sys:menu:save">
            <input id="btnSubmit" class="btn btn-warning" type="button"
                   value="保存排序" onclick="updateSort();"/>
        </shiro:hasPermission>--%>

    </div>


    <form id="listForm" method="post">
        <table id="treeTable" class="table table-striped table-bordered table-condensed" style="font-size: 13px;">
            <thead>
            <tr>
                <th width="20%">名称</th>
                <th width="20%">链接</th>
                <%--<th width="20%">排序</th>--%>
                <th width="20%">可见</th>
                <th width="20%">权限标识</th>
                <shiro:hasPermission name="sys:menu:edit">
                    <th width="20%">操作</th>
                </shiro:hasPermission>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${menuList}" var="menu">
                <tr id="${menu.id}"
                    pId="${menu.parentId}">
                    <td nowrap>
                        <shiro:hasPermission name="sys:menu:add">
                            <a href="${basePath}/admin/sys/menu/add?id=${menu.id}">${menu.menuName}</a>
                        </shiro:hasPermission>
                        <shiro:lacksPermission name="sys:menu:add">
                            ${menu.menuName}
                        </shiro:lacksPermission>
                    </td>

                    <td title="${menu.href}">${not empty menu.href && fn:length(menu.href)>40 ? fn:substring(menu.href,0,41).concat('...') : menu.href}</td>
        <%--            <td style="text-align: center;"><shiro:hasPermission
                            name="sys:menu:add">
                        <input type="hidden" name="ids" value="${menu.id}"/>
                        <input name="sorts" type="text" value="${menu.sortNumber}"
                               class="sort" style="width: 50px; margin: 0; padding: 0; text-align: center;">
                    </shiro:hasPermission> <shiro:lacksPermission name="sys:menu:add">
                        ${menu.sortNumber}
                    </shiro:lacksPermission></td>--%>
                    <td>${menu.isShow == 0 ?'显示':'隐藏'}</td>
                    <td title="${menu.permission}">${not empty menu.permission && fn:length(menu.permission)>20 ? fn:substring(menu.permission,0,21).concat('...') : menu.permission}</td>
                    <td nowrap>
                        <shiro:hasPermission name="sys:menu:add">
                            <a href="${basePath}/admin/sys/menu/add?id=${menu.id}">修改</a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="sys:menu:delete">
                            <a href="javascript:deleteMenu('${menu.id}');"
                               onclick="">删除</a>
                        </shiro:hasPermission>
                   <%--     <shiro:hasPermission name="sys:menu:add">
                        <a href="${basePath}/admin/sys/menu/add?parentId=${menu.id}">添加下级菜单</a>
                        </shiro:hasPermission>--%>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
</body>


<script type="text/javascript">
    $(document).ready(function () {
        $("#treeTable").treeTable({
            expandLevel: 2
        }).show();
    });
    function updateSort() {
        var flag = true;
        $.each($(".sort"), function () {
            if (isNaN($(this).val())) {
                flag = false
                return;
            }
        });
        if (flag) {
            $("#listForm").attr("action", "batch_update_sort");
            $("#listForm").submit();
        } else {
            top.layer.confirm('排序中只允许输入数字', {icon: 3, title: '提示'}, function (index) {
                top.layer.close(index);
            });
        }
    }
    function deleteMenu(id) {
        top.layer.confirm('确定删除该菜单及其子菜单?', {icon: 3, title: '提示'}, function (index) {
            top.layer.close(index);
            location.href = "./menu/menu_delete?id=" + id;
        });
    }
</script>
</html>



