<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>用户管理页面</title>
    <%@ include file="/WEB-INF/views/commons/common.jsp" %>
    <script src="${basePath}/statics/commons/js/mydatagrid.js"></script>
</head>
<body style="overflow-y:hidden">
<section class="content">
    <div class="box box-solid mydatagrid">
        <%@ include file="/WEB-INF/views/commons/message.jsp" %>
        <div class="box-body" style="display: block;align:center">
            <form:form class="form-horizontal searchForm" action="user_list" commandName="searchForm">
                <div class="form-group">
                    <label for="LIKE_userName" class="col-sm-1 control-label">用户名</label>
                    <div class="col-sm-3">
                        <form:input cssClass="form-control" path="LIKE_userName"  placeholder="用户名" />
                    </div>
                    <button type="submit" class="btn btn-info pull-left">查询</button>
                    <div class="col-sm-2 box-tools pull-right">
                        <shiro:hasPermission name="sys:user:edit">
                            <a class="btn btn-primary" url="edit"
                               onclick="editInfo(this)">添加</a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="portals:instance:delete">
                            <a class="btn btn-danger" url="user_delete" onclick="delInfo(this)">删除</a>
                        </shiro:hasPermission>
                    </div>
                </div>
            </form:form>
        </div>
        <div class="easyui-datagrid" id="dataGrid"></div>
    </div>
</section>
</body>
<script>
    loadGrid();
    function loadGrid() {
        $(".mydatagrid").MyDataGrid({
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'userName', title: '用户名', width: '10%', align: 'center', sortable: true},
                {field: 'realName', title: '真实姓名', width: '10%', align: 'center', sortable: true},
                {
                    field: 'status', title: '是否可用', width: '15%', align: 'center', sortable: true,
                    formatter: function (value, row, index) {
                        return value == '0' ? '启用' : '禁用';
                    }
                },
                {
                    field: 'createDate', title: '创建时间', width: '20%', align: 'center', sortable: true,
                    formatter: function (value, rec, index) {
                        return formatDate(new Date(value));
                    }
                },
                {
                    field: 'modifyDate', title: '修改时间', width: '20%', align: 'center', sortable: true,
                    formatter: function (value, rec, index) {
                        return formatDate(new Date(value));
                    }
                },
                {
                    field: 'id', width: '10%', title: '操作', align: 'center', formatter: function (value, row, index) {
                    return '<a url="edit?id=' + value + '" onclick="editInfo(this)">编辑</a>';
                }
                }
            ]]
        });
    }
    function editInfo(element){
        var href = $(element).attr("url");
        layer.open({
            type: 2,
            title: '编辑用户信息',
            shadeClose: true,
            area: ['80%','90%'],
            content: href,
            cancel: function (index) {
                layer.close(index);
            },
            end: function (index) {
                //刷新父页面列表
                layer.close(index);
                loadGrid();
            }
        });
        return false;
    }
</script>
</html>
