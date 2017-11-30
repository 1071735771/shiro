<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>告警规则添加</title>
    <%@ include file="/WEB-INF/views/commons/common.jsp" %>
    <script type="text/javascript" src="${basePath}/statics/modules/send/send_add.js"></script>
</head>
<body>
<section class="content">
    <div class="box box-solid">
        <%@ include file="/WEB-INF/views/commons/message.jsp" %>
        <div class="box-body" style="display: block;">
            <form:form action="save" method="post" class="form-horizontal ui-form ajaxForm">
                    <input type="hidden" id="id" name="id" value="${ empty command.id  ? null :command.id }"/>
                    <div class="form-group">
                        <label for="intervalTime" class="col-sm-3 control-label">两地登录的时间差</label>
                        <div class="col-sm-5">
                            <form:input path="intervalTime" value="${ empty command.intervalTime ? '' :command.intervalTime}" id="intervalTime" cssClass="form-control required" placeholder="两地登录的时间差（单位：时）"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="errorTryNumber" class="col-sm-3 control-label">错误尝试次数</label>
                        <div class="col-sm-5">
                            <form:input  path="errorTryNumber" value="${ empty command.errorTryNumber ? '' :command.errorTryNumber}" id="errorTryNumber" cssClass="form-control required" placeholder="错误尝试次数（单位：次）"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="commonNumber" class="col-sm-3 control-label">设置常用地址次数</label>
                        <div class="col-sm-5">
                            <form:input  path="commonNumber" value="${ empty command.commonNumber ? '' :command.commonNumber}" id="commonNumber" cssClass="form-control required" placeholder="设置常用地址次数（单位：时）"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sendEmail" class="col-sm-3 control-label">告警邮箱地址</label>
                        <div class="col-sm-5">
                            <form:textarea  path="sendEmail" value="${command.sendEmail}"  id="sendEmail" cssClass="form-control required" placeholder="多个地址之间用“；”隔开"/>
                        </div>
                    </div>

                    <div class=" form-group" style="padding-left: 200px;">
                        <shiro:hasPermission name="sys:user:save">
                            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="checkReg();"/>&nbsp;
                        </shiro:hasPermission>
                    </div>
            </form:form>
        </div>
    </div>
</section>
</body>
<script type="text/javascript">
    function checkReg(){
        var obj = document.getElementById("sendEmail");
        var mail_arr =obj.value.split(";");
        for(var i=0;i<mail_arr.length;i++){
            if(!/([a-zA-Z]|[0-9]|_)+@([a-zA-Z]|[0-9]|_)+.com/.test(mail_arr[i])) {
                alert('邮箱格式错误');
                obj.value = "";
                return false;
            }
            return true;
        }
    }
</script>
</html>
