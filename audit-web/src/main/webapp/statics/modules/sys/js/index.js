/**
 * Created by snow on 2016/6/14.
 */
//打开页签
var time;
function addTab(title, url) {
    // 屏幕适配
    var ClientH = document.documentElement.clientHeight;
    var offsetH = ClientH - 100;
    $('#tabs').tabs('close', title);
    var content = '<iframe scrolling="auto" frameborder="0" allowTransparency="true"  src="' + url + '" style="width:100%;min-height:' + offsetH + 'px;"></iframe>';
    $('#tabs').tabs('add', {
        title: title,
        content: content,
        closable: true
    });
    var currentTab = $("#tabs").tabs("getTab", title);
    currentTab.find('.panel .panel-body').css("height", offsetH + "px");
    //隐藏滚动条
    currentTab.find('.panel .panel-body').css('overflow', "hidden");
    //设置宽度
    currentTab.find('.panel .panel-body').css('width', $(".panel").width() + 'px');

}
$(document).ready(function () {
    //添加首页
    //addTab("首页", "../chart/index");
    //绑定tabs的右键菜单
    $("#tabs").tabs({
        onContextMenu: function (e, title) {
            e.preventDefault();
            $('#tabsMenu').menu('show', {
                left: e.pageX,
                top: e.pageY
            }).data("tabTitle", title);
        }
    });
//实例化menu的onClick事件
    $("#tabsMenu").menu({
        onClick: function (item) {
            closeTab(this, item.name);
        }
    });
});

//关闭所有
function closeTab(menu, type) {
    var curTabTitle = $(menu).data("tabTitle");
    var tabs = $("#tabs");
    if (type === "close") {
        tabs.tabs("close", curTabTitle);
        return;
    }
    if (type === "refresh") {
        $("#tabs").tabs("getTab", curTabTitle).find("iframe")[0].contentWindow.location.reload(true);
        return;
    }
    var allTabs = tabs.tabs("tabs");
    var closeTabsTitle = [];
    $.each(allTabs, function () {
        var opt = $(this).panel("options");
        if (opt.closable && type === "all") {
            closeTabsTitle.push(opt.title);
        }
    });
    for (var i = 0; i < closeTabsTitle.length; i++) {
        tabs.tabs("close", closeTabsTitle[i]);
    }
}

