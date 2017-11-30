<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>日志审查页面</title>
    <%@ include file="/WEB-INF/views/commons/common.jsp" %>

    <%--<link rel="stylesheet" href="${basePath}/statics/thirdparty/bootstrap/css/bootstrap.min.css">
    <!-- jQuery 2.2.0 -->
    <script src="${basePath}/statics/thirdparty/jquery/jquery.min.js"></script>
    <!-- Bootstrap 3.3.6 -->
    <script src="${basePath}/statics/thirdparty/bootstrap/js/bootstrap.min.js"></script>--%>
</head>
<body style="overflow-y:hidden">
<section class="content">
    <div class="box box-solid mydatagrid">
        <%@ include file="/WEB-INF/views/commons/message.jsp" %>
        <div class="box-body" style="display: block;align:center">
            <%--  <form:form class="form-horizontal searchForm" action="send_list" commandName="searchForm">

                <div class="col-sm-3 box-tools pull-right">
                     <shiro:hasPermission name="sys:user:edit">
                         <a class="btn btn-primary" url="edit" id="add"
                            onclick="add(this)">添加</a>
                     </shiro:hasPermission>

                     <shiro:hasPermission name="sys:user:edit">
                         <a class="btn btn-primary" url="edit"
                            onclick="edit(this)">修改</a>
                     </shiro:hasPermission>

                     <shiro:hasPermission name="portals:instance:delete">
                         <a class="btn btn-danger" url="delete" onclick="del(this)">删除</a>
                     </shiro:hasPermission>

                 </div>
            </form:form>--%>
        </div>
        <div class="easyui-datagrid" id="dataGrid"></div>
    </div>
</section>
</body>
<script>
    var rowHeight = 32;
    //loadGrid();
    loadGrid2();
    function loadGrid() {
        $(".mydatagrid").MyDataGrid({
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'userName', title: '用户名', width: '20%', align: 'center', sortable: true},
                {field: 'receiverEmail', title: '告警地址', width: '20%', align: 'center', sortable: true},
                {field: 'sendContent', title: '发送内容', width: '35%', align: 'center', sortable: true},
                { field: 'sendWay', title: '告警方式', width: '20%', align: 'center', sortable: true,
                    formatter: function (value, row, index) {
                        return value == '0' ? '短信' : '邮件';
                    }
                }
            ]]
        });
    }

    function add(element){
        var href = $(element).attr("url");
        layer.open({
            type: 2,
            title: '添加告警信息',
            shadeClose: true,
            area: ['60%','60%'],
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


    function edit(element){
        var href = $(element).attr("url");
        var ids = [];
        var rows = $('#dataGrid').datagrid('getSelections');
        for(var i=0; i<rows.length; i++){
            ids.push(rows[i].id);
        }
        if(ids.length == 0 || ids.length >1){
            alertx("只能选择一项",0);
            return ;
        }
        layer.open({
            type: 2,
            title: '添加告警信息',
            shadeClose: true,
            area: ['50%','60%'],
            content: href+"?id="+ids,
            cancel: function (index) {

                layer.close(index);
            },
            end: function (index) {
                //刷新父页面列表
                layer.close(index);

                loadGrid();
            },
            yes: function(index){

                layer.close(index);
            }
        });
        return false;
    }

    function  del(){
        var ids = [];
        var rows = $('#dataGrid').datagrid('getSelections');
        for(var i=0; i<rows.length; i++){
            ids.push(rows[i].id);
        }
        if(ids.length == 0){
            alertx("请至少选择一项",0);
            return ;
        }
        var idStr = ids.join(",");
        layer.confirm('确定禁用所选数据吗?', {icon: 3, title:'提示'}, function(index) {
            layer.close(index);
            $.post('delete',{"idStr":idStr},function(data){
                if(data == 0){
                    alertx('删除成功',1);
                    loadGrid();
                }else{
                    alertx('删除失败',2);
                    loadGrid();
                }
            });
        });
    }

    //加载表格datagrid
    function loadGrid2() {
        var clientH = document.documentElement.clientHeight;
        var offsetH = clientH - 77;
        var pageSize = 10;
        rowHeight = (offsetH - 55) / pageSize;
        //加载数据
        $('#dataGrid').datagrid({
            width: '100%',
            height: offsetH,
            idField: 'id',
            nowrap: false,
            striped: true,
            border: true,
            loadMsg: "数据加载中，请稍后...",
            collapsible: false,//是否可折叠的
            fit: false,//自动大小
            pagination: true,//分页控件
            rownumbers: true,//行号
            fitcolumns: true,
            url: 'send_pub',
            method: 'get',
            columns: [[
                { field: '0',title: '操作ID',width: '15%', align: 'center', sortable: true},
                { field: '1',title: '操作模块',width: '15%', align: 'center', sortable: true},
                { field: '2',title: '操作类型',width: '15%',align: 'center',sortable: true},
                { field: '3',title: '操作人名称',width: '15%',align: 'center',sortable: true},
                { field: '4',title: '操作时间',width: '15%',align: 'center',sortable: true,formatter:function (value,row,index) {
                    return formatDate(new Date(value));
                }},
                { field: '5',title: '操作信息',width: '15%',align: 'center',sortable: true},
                { field: '6',title: '操作结果',width: '15%',align: 'center',sortable: true},
                { field: '7',title: '登录IP',width: '15%',align: 'center',sortable: true},
                { field: '8',title: '操作日期 ',width: '15%',align: 'center',sortable: true,formatter:function (value,row,index) {
                    return formatDate(new Date(value));
                }}
            ]],
            //列排序时触发方法
            onSortColumn: function (sort, order) {
                doSearch(sort, order);
            },
            /* onLoadSuccess: function (data) {
             var dataGrid = $("#dataGrid");
             autoHeight(dataGrid, rowHeight);
             }*/
        });

        //设置分页控件
        var p = $('#dataGrid').datagrid('getPager');
        $(p).pagination({
            pageSize: 10,////每页显示的记录条数，默认为101
            pageList: [10],//可以设置每页记录条数的列表
            beforePageText: '第',//页数文本框前显示的汉字
            afterPageText: '页    共 {pages} 页',
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
        });
    }

    //参数查询
    function doSearch(sortName, sortValue) {
        var loginIP=$("#login_ip").val();
        var actionUser=$("#action_user").val();
        var startTime=$("#startTime").datebox("getValue");
        var endTime=$("#endTime").datebox("getValue");
        var type=  $("#type").find("option:selected").val();
        $("#dataGrid").datagrid('reload', {
            loginIP:loginIP,
            actionUser:actionUser,
            startTime:startTime,
            endTime:endTime,
            type:type,
            sortName: sortName,
            sortValue: sortValue,
        });
        $('#dataGrid').datagrid('clearSelections');
    }


</script>
</html>
