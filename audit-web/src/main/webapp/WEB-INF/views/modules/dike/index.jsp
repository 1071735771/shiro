<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>组件操作审计</title>
      <%@ include file="/WEB-INF/views/commons/common.jsp" %>
</head>
<body style="overflow-y:hidden">
<section class="content">
    <div class="box box-solid mydatagrid">
        <%@ include file="/WEB-INF/views/commons/message.jsp" %>
        <div id="cc" class="easyui-calendar"></div>
        <div class="box-body" style="display: block;align:center">
            <div id="search" style="padding:3px" class="pull-left">

                <label>场景类型:</label>
                <select id="location" onchange="downs();">
                    <option value="<%=basePath%>/admin/dike/index">1.平台操作行为</option>
                    <option value="<%=basePath%>/admin/dike/index_2">2.数据访问行为</option>
                    <option value="<%=basePath%>/admin/dike/index_3">3.任务调度行为</option>
                    <option value="<%=basePath%>/admin/dike/index_4">4.应用发布行为</option>
                    <option value="<%=basePath%>/admin/dike/index_5">5.接口调用行为</option>
                    <option value="<%=basePath%>/admin/dike/index_6">6.开放接口行为</option>
                    <option value="<%=basePath%>/admin/dike/index_7">7.数据同步行为</option>
                </select>

                <label>组件类型:</label>
                <select id="type" >
                    <option value="">请选择</option>
                    <option value="HDFS">HDFS</option>
                    <option value="Fortress">Fortress</option>
                    <option value="Hive">Hive</option>
                    <option value="Yarn">Yarn</option>
                </select>


              <%--  <label>ip:</label>
                <input type="text" id="login_ip" name="login_ip" style="padding:6px 12px;">--%>


                <label>操作人:</label>
                <input type="text" id="userName" name="userName" style="padding:6px 12px;">

                <label>开始时间:</label>
                <input class="easyui-datebox"  id="startTime" data-options="sharedCalendar:'#cc'">

                <label>结束时间:</label>
                <input class="easyui-datebox"  id="endTime" data-options="sharedCalendar:'#cc'">

                <button type="button" class="btn btn-primary" onclick="doSearch()">查询</button>
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

    //结束时间和开始时间跨度为三个月
    $("#startTime").datebox({
        onSelect : function(beginDate){
            alert("开始和结束的时间跨度不能超过7天");
            $('#endTime').datebox().datebox('calendar').calendar({
                validator: function(date){
                    return date - beginDate <  7 * 24 * 60 * 60 * 1000 && date > beginDate ;
                }
            });
        }
    });

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
            url: 'dike_list',
            method: 'post',
            columns: [[
                { field: '0',title: '操作人',width: '25%', align: 'center', sortable: false},
                { field: '1',title: '操作时间',width: '25%',align: 'center',sortable: false,formatter:function (value,row,index) {
                    return formatDate(new Date(value));
                }},
                { field: '2',title: '操作命令',width: '25%',align: 'center',sortable: false},
                { field: '3',title: '操作次数',width: '25%',align: 'center',sortable: false}
            ]],
            //列排序时触发方法
            onSortColumn: function (sort, order) {
                doSearch(sort, order);
            },
            onLoadSuccess: function (data) {
                var dataGrid = $("#dataGrid");
            },onBeforeLoad: function (param) {
                param.grant = "1";
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
        //var loginIP=$("#login_ip").val();
        var userName=$("#userName").val();
        var startTime=$("#startTime").datebox("getValue");
        var endTime=$("#endTime").datebox("getValue");
        var type=  $("#type").find("option:selected").val();
        $("#dataGrid").datagrid('reload', {
            grant:"1",
            userName:userName,
            startTime:startTime,
            endTime:endTime,
            type:type,
            sortName: sortName,
            sortValue: sortValue,
        });
        $('#dataGrid').datagrid('clearSelections');
    }

    function downs(){
        window.location = document.getElementById("location").value;
    }
</script>
</html>
