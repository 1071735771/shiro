<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>角色管理页面</title>
    <%@ include file="/WEB-INF/views/commons/common.jsp" %>

</head>
<body >
    <div class="box-body" style="display: block;">
        <%@ include file="/WEB-INF/views/commons/message.jsp" %>
      <div id="search" style="padding:3px" class="pull-left">
        <label>角色名称:</label>
        <input type="text" id="roleName" name="roleName" style="padding:6px 12px;" >
        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
      </div>

      <div class="box-tools pull-right">
     <shiro:hasPermission name="sys:role:add">
        <input  class="btn btn-success " type="button"
                value="添加" onclick="addRole();" />
     </shiro:hasPermission>

         <shiro:hasPermission name="sys:role:add">
        <input  class="btn btn-info " type="button"
                value="修改" onclick="updateRole();" />
         </shiro:hasPermission>

         <shiro:hasPermission name="sys:role:delete">
        <input id="btnSubmit" class="btn btn-danger" type="button"
               value="禁用" onclick="batchDelete();" />
     </shiro:hasPermission>
       </div>
    </div>
    <div class="easyui-datagrid" id="roleGrid" ></div>
</body>
<script>
    loadGrid();
    $("#roleName").addClear();
    $("#roleName").bind("keypress",function(event){
        if(event.keyCode == 13){
            search();
        }
    });

    var rowHeight = 32;
    //加载表格datagrid
    function loadGrid() {
        var url;
        var clientH = document.documentElement.clientHeight;
        var offsetH =clientH - 61;
        var pageSize = 10;
        rowHeight = (offsetH-55) / pageSize;

        //加载数据
        $('#roleGrid').datagrid({
            width:'100%',
            height:offsetH,
            idField: 'id',
            nowrap: false,
            striped: true,
            border: true,
            loadMsg: "数据加载中，请稍后...",
            collapsible:false,//是否可折叠的
            fit: false,//自动大小
            pagination:true,//分页控件
            rownumbers:true,//行号
            fitcolumns:true,
            url: 'ajax_role_data',
            method: 'post',
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'roleName', title: '角色名称',width:'20%',align:'center',sortable:true},
                {field: 'remark', title: '角色描述',width:'20%',align:'center',sortable:true},
                {field: 'isShow', title: '是否可用',width:'15%',align:'center',sortable:true
                    ,
                    formatter: function (value, row, index) {
                        return value == '0'?'禁用':'启用';
                    }},
                {field: 'createDate', title: '创建时间',width:'20%',align:'center',sortable:true
                    ,
                    formatter:function (value, rec, index) {
                        return formatDate(new Date(value));
                    }},
                {field: 'modifyDate', title: '修改时间',width:'20%',align:'center',sortable:true
                    ,
                    formatter:function (value, rec, index) {
                        return formatDate(new Date(value));
                    }}
            ]],
            //列排序时触发方法
            onSortColumn: function (sort, order) {
                search(sort,order);
            },
            onLoadSuccess: function(data){
                var dataGrid = $("#roleGrid");
            }

        });

        //设置分页控件
        var p = $('#roleGrid').datagrid('getPager');
        $(p).pagination({
            pageSize: 10,////每页显示的记录条数，默认为10
            pageList: [10],//可以设置每页记录条数的列表
            beforePageText: '第',//页数文本框前显示的汉字
            afterPageText: '页    共 {pages} 页',
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
        });
    }
    /**
     * 删除角色的方法
     */
    function batchDelete(){
        var ids = [];
        var rows = $('#roleGrid').datagrid('getSelections');
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
            $.post('role_delete',{"idStr":idStr},function(data){
                if(data == 0){
                    alertx('禁用成功',1);
                    search();
                }else{
                    alertx('禁用失败',2);
                    search();
                }
            });
        });

    }
    /**
     * 更新角色的方法
     */
    function updateRole(){
        var ids = [];
        var rows = $('#roleGrid').datagrid('getSelections');
        for(var i=0; i<rows.length; i++){
            ids.push(rows[i].id);
        }
        if(ids.length == 0 || ids.length >1){
            alertx("只能选择一项",0);
            return ;
        }
        layer.open({
            type: 2,
            title: '修改角色页面',
            shadeClose: true,
            shade: 0.8,
            area: ['40%', '70%'],
            content: 'role_add?id='+ids[0],
            cancel:function (index) {
                layer.close(index);
                search();
            },
            end:function (index) {
                layer.close(index);
                search();
            }
        });

    }

    /**
     * 添加角色
     */
    function addRole() {
        layer.open({
            type: 2,
            title: '添加角色页面',
            shadeClose: true,
            shade: 0.8,
            area: ['50%', '70%'],//宽高
            content: 'role_add',
            cancel:function (index) {
                layer.close(index);
                search();
            },
            end:function (index) {
                layer.close(index);
                search();
            }
        });

    }

    /**
     * 模糊查询方法
     */
    function search(sortName,sortValue) {
        $("#roleGrid").datagrid('load', {
            roleName: $("#roleName").val(),
            sortName:sortName,
            sortValue:sortValue,
        });
        $('#roleGrid').datagrid('clearSelections');

    }

</script>
</html>
