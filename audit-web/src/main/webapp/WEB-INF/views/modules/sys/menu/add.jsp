<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>系统菜单添加页面</title>
  <%@ include file="/WEB-INF/views/commons/common.jsp" %>
</head>
<body>
<sys:message msgContent="${message}"/>
    <div class="box-body" style="display: block;">
        <form id="inputForm" action="${basePath}/admin/sys/menu/menu_save" method="post"  class="form-horizontal">
          <input type="hidden" id="id" name="id" value="${ empty menu.id ? 0 :menu.id}"/>
          <div class="box-body">
            <div class="form-group">
              <label for="parentId" class="col-sm-2 control-label">上级菜单</label>
              <div class="col-sm-4">
                <sys:treeselect id="parentId" name="parentId" value="${parentMenu.id}" labelName="parent.name"
                                 labelValue="${parentMenu.menuName}"	title="菜单"
                                 url="${basePath}/admin/sys/menu/ajax_menu_tree" extId="${menu.id}" cssStyle="width:70%"  cssClass="form-control" allowClear="true" />
              </div>
              <span class=" control-label">(父级菜单)</span>
            </div>

            <div class="form-group">
              <label for="menuName" class="col-sm-2 control-label">菜单名称</label>
              <div class="col-sm-4">
                <input type="text" style="width: 70%" class="form-control required" id="menuName" name="menuName"  maxlength="20" placeholder="菜单名称"  value="${menu.menuName}">
              </div>
            </div>

            <div class="form-group">
              <label for="href" class="col-sm-2 control-label">链接</label>
              <div class="col-sm-4">
                <input type="text" style="width: 70%" class="form-control" id="href" name="href" placeholder="链接"  maxlength="150"  value="${menu.href}">
              </div>
              <span class="help-inline control-label">(点击菜单跳转的页面)</span>
            </div>

            <div class="form-group">
              <label for="menuIcon" class="col-sm-2 control-label">图标样式</label>
              <div class="col-sm-4">
                <input type="text" style="width: 70%" class="form-control" id="menuIcon" name="menuIcon" placeholder="图标样式"  maxlength="100"  value="${menu.menuIcon}">
              </div>
              <span class="help-inline control-label">(菜单的图标样式或者是图标名称)<a href="${basePath}/statics/modules/sys/img/menu-icons.png" target="_Blank">  查看</a></span>
            </div>

            <div class="form-group">
              <label for="sortNumber" class="col-sm-2 control-label">排序</label>
              <div class="col-sm-4">
                <input type="text" style="width: 70%" class="form-control required digits" id="sortNumber" name="sortNumber" placeholder="排序"  maxlength="7" value="${menu.sortNumber}" onchange="checkObj(this)">
              </div>
              <span class="help-inline control-label">(菜单排序号)</span>
            </div>

            <div class="form-group">
              <label  class="col-sm-2 control-label">是否可见</label>
              <div class="col-sm-4">
                <input type="radio"  class="minimal form-control {required:true} " ${empty menu.isShow || menu.isShow == 0 ? 'checked':''}  name="isShow" id="isShow" value="0"   />显示
                <input type="radio"  class="minimal form-control"  name="isShow" id="isShow1" value="1"   ${ menu.isShow == 1 ? 'checked':''}  }/>隐藏
              </div>
              <span class="help-inline control-label">(菜单是否显示在菜单栏)</span>
            </div>

            <div class="form-group">
              <label for="permission" class="col-sm-2 control-label">权限标识</label>
              <div class="col-sm-4">
                <input type="text" style="width: 70%" class="form-control" id="permission" name="permission"  maxlength="100" placeholder="权限标识"  value="${menu.permission}" >
              </div>
              <span class="help-inline control-label">(菜单的访问权限。采用shiro的权限标示格式)</span>
            </div>

            <div class="form-group">
              <label for="remark" class="col-sm-2 control-label">备注</label>
              <div class="col-sm-4">
                <textarea type="te" maxlength="100"  class="form-control" id="remark" name="remark" placeholder="备注"  cols="25" rows="7">${menu.remark}</textarea>
              </div>
            </div>
          <div class="box-footer form-group" style="padding-left: 200px;">
            <shiro:hasPermission name="sys:menu:save">
              <input id="btnSubmit" class="btn btn-primary"  type="submit"
                     value="保 存" />&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn btn-cancle" type="button" value="返 回"
                   onclick="history.go(-1)" />
          </div>
          </div>
        </form>
      </div>
</body>
<script type="text/javascript">
  function checkVal(){
    var menuName = document.getElementById("menuName");
    var href = document.getElementById("href");
    var sortNumber = document.getElementById("sortNumber");
    var permission = document.getElementById("permission");
    alert(menuName.value);
    //checkObj(sortNumber);
    if(menuName.value.length<1 ||href.value.length<1 ||sortNumber.value.length<1 ||permission.value.length<1
    || menuName =="" || href =="" || sortNumber=="" || permission=="") {
     //noSubmit();
    }
  }
    var submitBtn = document.getElementById("btnSubmit");
    submitBtn.onclick = function (event) {
      var menuName = document.getElementById("menuName");
      var href = document.getElementById("href");
      var sortNumber = document.getElementById("sortNumber");
      var permission = document.getElementById("permission");
      alert(menuName.value);
      //checkObj(sortNumber);
      if(menuName.value.length<1 ||href.value.length<1 ||sortNumber.value.length<1 ||permission.value.length<1
              || menuName =="" || href =="" || sortNumber=="" || permission=="") {
        return false;
      }
    };

  function checkObj(obj){
    var pattern = /^[0-9]*$/;
    if(!pattern.test(obj.value)){
      alert("排序字段必须为数字");
      document.getElementById("sortNumber").value="";
    }
  }
</script>
</html>
