<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>告警规则</title>
    <%@ include file="/WEB-INF/views/commons/common.jsp" %>
</head>
<body style="overflow-y:hidden">
<section class="content">
    <div class="box box-solid mydatagrid">
        <%@ include file="/WEB-INF/views/commons/message.jsp" %>
        <div class="box-body" style="display: block;align:center">
            <div id="search" style="padding:3px" class="pull-left">

                <button type="button" class="btn btn-primary" style="margin-left: 981px" onclick="add()">添加</button>
                <button type="button" class="btn btn-primary" onclick="updateData()">修改</button>
                <button type="button" class="btn btn-danger"  onclick="batchDisable();">删除</button>
            </div>
        </div>
        <div class="easyui-datagrid" id="dataGrid"></div>
    </div>
</section>
</body>

<script>
    //页面加载
    loadGrid();
    var rowHeight = 32;
    //加载表格datagrid
    function loadGrid() {
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
            url: 'rule_list',
            method: 'post',
            columns: [[
                {field: 'ck', checkbox: true},
                { field: 'id',title: 'ID',width: '15%', align: 'center', sortable: true},
                { field: 'intervalTime',title: '两地登录的时间差',width: '15%', align: 'center', sortable: true},
                { field: 'errorTryNumber',title: '错误尝试次数',width: '15%',align: 'center',sortable: true},
                { field: 'commonNumber',title: '错误尝试次数',width: '15%',align: 'center',sortable: true},
                { field: 'sendEmail',title: '目标邮箱',width: '15%',align: 'center',sortable: true}
            ]],
            //列排序时触发方法
            onSortColumn: function (sort, order) {
                doSearch(sort, order);
            },
            onLoadSuccess: function (data) {
                var dataGrid = $("#dataGrid");
            }
        });

        //设置分页控件
        var p = $('#dataGrid').datagrid('getPager');
        $(p).pagination({
            pageSize: 10,////每页显示的记录条数，默认为10
            pageList: [10],//可以设置每页记录条数的列表
            beforePageText: '第',//页数文本框前显示的汉字
            afterPageText: '页    共 {pages} 页',
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
        });
    }

    //参数查询
    function doSearch(sortName, sortValue) {

        $("#dataGrid").datagrid('reload', {
        });
        $('#dataGrid').datagrid('clearSelections');
    }

    //删除
    function batchDisable(){
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
        layer.confirm('确定删除所选数据吗?', {icon: 3, title:'提示'}, function(index) {
            layer.close(index);
            $.post('delete',{"idStr":idStr},function(data){
                if(data == 0){
                    alertx('删除成功',1);
                    doSearch();
                }else{
                    alertx('删除失败',2);
                    doSearch();
                }
            });
        });
    }


    function add(){
        layer.open({
            type: 2,
            title: '添加告警规则页面',
            shadeClose: true,
            shade: 0.8,
            area: ['65%', '65%'],
            content: 'edit',
            cancel:function (index) {
                layer.close(index);
                doSearch();
            },
            end:function (index) {
                layer.close(index);
                doSearch();
            }
        });
    }


    function updateData(){
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
            title: '告警规则修改',
            shadeClose: true,
            shade: 0.8,
            area: ['40%', '65%'],
            content: "edit?id="+ids[0],
            cancel:function (index) {
                layer.close(index);
                doSearch();
            },
            end:function (index) {
                layer.close(index);
                doSearch();
            }
        })}

</script>
</html>
