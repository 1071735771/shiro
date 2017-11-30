<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="alert_message"></div>
<c:if test="${not empty global_message}">
    <div class="alert alert-warning">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true" aria-label="关闭">
            <span class="fa fa-close" aria-hidden="true"></span>
        </button>

        <c:forEach var="item" items="${global_message}">
            <i class="icon-tip"></i>
            ${item.message}<br/>
        </c:forEach>
    </div>
</c:if>
<c:if test="${not empty global_success}">
    <div class="alert alert-success">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true" aria-label="关闭">
            <span class="fa fa-close" aria-hidden="true"></span>
        </button>

        <c:forEach var="item" items="${global_success}">
            <i class="icon-ok"></i>
            ${item.message}<br/>
        </c:forEach>
    </div>
</c:if>
<c:if test="${not empty global_error}">
    <div class="alert alert-danger">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true" aria-label="关闭">
            <span class="fa fa-close" aria-hidden="true"></span>
        </button>

        <c:forEach var="item" items="${global_error}">
            <i class="icon-remove"></i>
            ${item.message}<br/>
        </c:forEach>
    </div>
</c:if>
<script>
    //    if(top.$.cookie("successData")){
    //        var data =  JSON.parse(top.$.cookie("successData"));
    //        addMessage("success",data.data);
    //        top.$.removeCookie("successData");
    //    }
    function addMessage(type,message){
        $(".alert").remove();
        var html = "";
        var divCls = "";
        var icon = "";
        if(type == 'message'){
            divCls = "warning"
            icon = "icon-tip";
        }else if(type=='success'){
            divCls = "success";
            icon = "icon-ok";
        }else if(type == 'error'){
            divCls = "danger";
            icon = "icon-remove"
        }
        html = '<div class="alert alert-'+divCls+'">'
                +'<button type="button" class="close" data-dismiss="alert" aria-hidden="true" aria-label="关闭">'
                +'<span class="fa fa-close" aria-hidden="true"></span></button>'
                +'<i class="'+icon+'"></i>'
                +message+'<br/></div>';
        $("#alert_message").append(html);
    }
</script>