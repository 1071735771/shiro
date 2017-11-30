(function ($) {
    // 屏幕适配
    var ClientH = document.documentElement.clientHeight;
    var offsetH = ClientH - 130;
    var defaults = {
        width: '100%',
        idField: 'id',
        height: offsetH,
        nowrap: false,
        striped: true,
        border: true,
        loadMsg: "数据加载中，请稍后...",
        collapsible: false,//是否可折叠的
        pagination: true,//分页控件
        rownumbers: true,//行号
        fitColumns: true,
        // singleSelect:true,//单选
        remoteSort:false, //默认本地排序
        method: 'post',
        pageSize: 10,////每页显示的记录条数，默认为10
        pageList: [10, 20, 50, 100]//可以设置每页记录条数的列表
    };

    var MyDataGrid = function (element, options) {
        this.$this = $(element);
        //下面两个核心对象分别是搜索框,表格
        this.searchForm = $(".searchForm", this.$this);
        this.url = this.searchForm.attr("action") == undefined ? window.location.href : this.searchForm.attr("action");
        this.datagrid = $(".easyui-datagrid", this.$this);
        //页大小,页数,查询参数
        this.request = $.extend({rows: 10, page: 1}, this.searchForm.uiSerializeForm());
        options = $.extend({url: this.url, queryParams: this.request}, options);
        //绑定搜索事件
        this.searchForm.submit($.proxy(function () {
            this.request.page = 1;
            this.reloadDataGrid(options);
            return false;
        }, this));
        this.datagrid.datagrid(options);
    }

    MyDataGrid.prototype.reloadDataGrid = function () {
        this.request = $.extend(this.request, this.searchForm.uiSerializeForm());
        this.datagrid.datagrid('load', this.request);
    }

    MyDataGrid.prototype.getSelections = function(){
        return this.datagrid.datagrid('getSelections');
    }

    $.fn.MyDataGrid = function (options) {
        var opts = $.extend(defaults, options);
        return new MyDataGrid(this, opts);
    };

})(jQuery);