<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>用户添加页面</title>
    <%@ include file="/WEB-INF/views/commons/common.jsp" %>
</head>
<body>
<section class="content">
    <div class="box box-solid">
        <%@ include file="/WEB-INF/views/commons/message.jsp" %>
        <div class="box-body" style="display: block;">
            <form:form action="save" method="post" class="form-horizontal ui-form ajaxForm">
                <form:hidden path="id"/>
                    <div class="form-group">
                        <label for="userName" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-3">
                            <form:input path="userName" cssClass="form-control required" placeholder="用户名"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="realName" class="col-sm-2 control-label">真实姓名</label>
                        <div class="col-sm-3">
                            <form:input path="realName" cssClass="form-control required" placeholder="真实姓名"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">用户密码</label>
                        <div class="col-sm-3">
                            <form:password id="password" path="password" cssClass="form-control ${empty command.id ? required : ''}" minlength="6" maxlength="15" placeholder="用户密码"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="rePassword" class="col-sm-2 control-label">确认密码</label>
                        <div class="col-sm-3">
                            <form:password path="rePassword" cssClass="form-control ${empty command.id ? required : ''}" minlength="6" maxlength="15" equalTo="#password" placeholder="确认密码"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-3">
                            <input type="radio" name="gender" value="0" class="minimal form-control"  ${empty command.id || command.gender == 0 ? 'checked':''}/>男
                            <input type="radio" name="gender" value="1" class="minimal form-control"  ${command.gender == 1 ? 'checked':''}/>女
                            <input type="radio" name="gender" value="2" class="minimal form-control"  ${empty command.gender || command.gender == 2 ? 'checked':''}/>保密
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">手机号码</label>
                        <div class="col-sm-3">
                            <form:input path="mobile" cssClass="form-control" placeholder="手机号码"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否可用</label>
                        <div class="col-sm-3">
                            <input type="radio" class="minimal form-control" ${empty command.status || command.status == 0 ? 'checked':''} name="status" value="0"/>启用
                            <input type="radio" class="minimal form-control" name="status" value="1"  ${ command.status == 1 ? 'checked':''} />禁用
                        </div>
                        <span class="help-inline control-label">(是否启用该用户)</span>
                    </div>
                    <div class="box-footer form-group" style="padding-left: 200px;">
                        <shiro:hasPermission name="sys:user:save">
                            <input id="btnSubmit" class="btn btn-primary" type="submit"
                                   value="保 存"/>&nbsp;
                        </shiro:hasPermission>

                        <a class="btn btn-default history"> 返 回 </a>
                    </div>
            </form:form>
        </div>
    </div>
</section>

</body>
</html>
