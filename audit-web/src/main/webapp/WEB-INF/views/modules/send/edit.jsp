<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>用户添加页面</title>
    <%@ include file="/WEB-INF/views/commons/common.jsp" %>
    <script type="text/javascript" src="${basePath}/statics/modules/send/send_add.js"></script>
</head>
<body>
<sys:message msgContent="${message}"/>
<section class="content">
    <div class="box box-solid">
        <%@ include file="/WEB-INF/views/commons/message.jsp" %>

        <div class="box-body" style="display: block;">
            <form:form action="save" id="inputForm" method="post" class="form-horizontal ui-form ajaxForm">
                <form:hidden path="id"/>
                    <div class="form-group">
                        <label for="receiverEmail" class="col-sm-1 control-label">邮箱</label>
                        <div class="col-sm-2">
                            <form:input path="receiverEmail" value="${command.receiverEmail}" id="receiverEmail" cssClass="form-control required" placeholder="邮箱"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sendContent" class="col-sm-2 control-label">发送内容</label>
                        <div class="col-sm-1">
                            <form:textarea  path="sendContent" value="${command.sendContent}" id="sendContent" cssClass="form-control required" placeholder="发送内容"/>
                        </div>
                    </div>

                    <div class=" form-group" style="padding-left: 200px;">
                        <shiro:hasPermission name="sys:user:save">
                            <input id="btnSubmit" class="btn btn-primary" type="submit"
                                   value="保 存"/>&nbsp;
                        </shiro:hasPermission>
                       <%-- <a class="btn btn-default history"> 返 回 </a>--%>
                    </div>
            </form:form>
        </div>
    </div>
</section>
</body>
</html>
