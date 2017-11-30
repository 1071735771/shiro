<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>角色添加页面</title>
  <%@ include file="/WEB-INF/views/commons/common.jsp" %>
</head>
<body>
  <div style="margin-left: 5%">
    <div class="col-md-6 box-body">

        <form:form action="role_save" method="post" class="form-horizontal ui-form ajaxForm">
          <input type="hidden" id="id" name="id" value="${ empty role.id ? 0 :role.id}"/>
            <div class="row form-group">
              <div class="col-sm-6">
              <label for="roleName" class="col-sm-4 control-label" style="width: 25%" >角色名称:</label>
                <input type="text" class="required form-control" style="width: 45%;display: inline;" id="roleName" name="roleName" maxlength="90"  value="${role.roleName}">
              </div>
              </div>
            <div class="row form-group">
              <div class="col-sm-8">
                <label for="permIds" class="col-sm-4 control-label" style="width: 25%" >功能权限:</label>
                <sys:treeselect  id="permIds" name="permIds" value="${selPermIds}" labelName="${selPermNames}"
                                labelValue="${selPermNames}"	title="功能权限"
                                url="/admin/sys/menu/ajax_power_menutree" cssClass="form-control" cssStyle="width:45%;display: inline;" checked="true"/>
              </div>
            </div>

            <div class="row form-group">
              <div class="col-sm-6">
              <label  class="col-sm-4 control-label" style="width: 25%">是否可用:</label>
                <input type="radio"  class="minimal  {required:true} " ${empty role.isShow || role.isShow == 1 ? 'checked':''}  name="isShow" id="isShow" value="1"   />启用
                <input type="radio"  class="minimal "  name="isShow" id="isShow1" value="0"   ${empty role.isShow || role.isShow == 0 ? 'checked':''}  }/>禁用
              </div>
              </div>

            <div class="row form-group">
              <div class="col-sm-6" >
              <label for="remark" class="col-sm-4 control-label" style="width: 25%;">备注:</label>
                <textarea  id="remark" style="width:50%;display: inline;"  name="remark"  cols="40" maxlength="100" rows="7">${role.remark}</textarea>
              </div>
              </div>
            <div class="row form-group" style="text-align: center;">
              <shiro:hasPermission name="sys:role:save">
                <input id="btnSubmit" class="btn btn-primary"  type="submit"
                       value="保存" />&nbsp;
              </shiro:hasPermission>
            </div>
        </form:form>
      </div>
  </div>
</body>

</html>
