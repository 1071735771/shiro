//显示提示框
function showMsg(msg,timeout,type){
    timeout=timeout==null||timeout==undefined?3*1000:timeout;
    type=type==null||type==undefined?1:type;
    layer.msg(msg,{
        icon: type,
        time: timeout
    });
}

// 显示加载框
function loading(msg){
    var index = layer.load(0);
    return index;
}
//关闭提示框
function closeTip(index){
    layer.close(index);
}

/**
 * 关闭所有的提示框
 * @param type; 提示框类型:loading,dialog,
 */
function closeAllTips(type) {
    layer.closeAll(type);
}


// 警告对话框
function alertx(msg,type){
    type=type==undefined||type==null?1:type;
    layer.alert(msg, {icon: type});
}

// 确认对话框
function confirmx(msg, href){
    console.log(href);
    layer.confirm(msg, {icon: 3, title:'提示'}, function(index){
        href();
        layer.close(index);
    });
}

//获取URL地址参数
function getQueryString(name, url) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    if (!url || url == "") {
        url = window.location.search;
    } else {
        url = url.substring(url.indexOf("?"));
    }
    r = url.substr(1).match(reg)
    if (r != null) return unescape(r[2]);
    return null;
}

/**
 * 格式化日期输出
 * @param now
 * @param format
 * @returns {string}
 */
function formatDate(dateObj, format) {

    var year = dateObj.getFullYear();
    var month = ((dateObj.getMonth() + 1) >= 10 ? (dateObj.getMonth() + 1) : "0" + (dateObj.getMonth() + 1));
    var date = ((dateObj.getDate() < 10 ? "0" + dateObj.getDate() : dateObj.getDate()));
    var hour = ((dateObj.getHours() < 10 ? "0" + dateObj.getHours() : dateObj.getHours()));
    var minute = ((dateObj.getMinutes() < 10 ? "0" + dateObj.getMinutes() : dateObj.getMinutes()));
    var second = ((dateObj.getSeconds() < 10 ? "0" + dateObj.getSeconds() : dateObj.getSeconds()));
    if (format == undefined || format == "" || format == "yyyy-MM-dd HH:mm:ss") {
        return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
    } else if (format == "yyyy-MM-dd") {
        return year + "-" + month + "-" + date;
    } else if (format == "HH:mm:ss") {
        return hour + ":" + minute + ":" + second;
    }
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}

$.fn.uiSerializeForm = function () {
    var o = {};
    var a = this.serializeArray();
    jQuery.each(a, function (index, li) {
        if (o[li.name]) {
            o[li.name] = o[li.name] + "," + li.value;
        } else {
            o[li.name] = li.value;
        }
    });
    return o;
};

$.fn.uiDeSerializeForm = function (obj) {
    var a = this.serializeArray();
    $.each(a,function(index,li){
        $("[name='"+li.name+"']").val(eval("obj."+li.name));
    })
};

function bindAll() {

    //绑定UIForm
    $(".ui-form").each(function (idx, item) {
        $(item).UIForm();
    });

    //关闭iframe弹窗
    $(".cancel-layer").on("click",function(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
        return false;
    })

    //返回
    $(".history").on("click", function () {
        history.back();
        return false;
    });

    //icheck
    $('.i-checks').iCheck({
        checkboxClass: 'icheckbox_minimal-green',
        radioClass: 'iradio_minimal-green'
    });
}

/*表单后加提示图标*/
function showTipMsgs(){
    appendClass("fa fa-info-circle");
}

function appendClass(fa){
    if($(".data-tip-msg")){
        $(".data-tip-msg").after(" <i class='"+fa+" changeHand '></i>");
        $(".data-tip-msg").each(function () {
            var msg = $(this).attr("i-data-message");
            $(this).next().bind("click",function(){
                showTipMsg(msg);
            })
        })
    }
}

jQuery(function () {
    bindAll();
    basePath = $("#basePath").val();
});