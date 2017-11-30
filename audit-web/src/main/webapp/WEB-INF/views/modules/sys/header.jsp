<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!-- header begin-->
<header class="main-header">
  <!-- Logo -->
  <a href="index" class="logo">
    <!-- mini logo for sidebar mini 50x50 pixels -->
    <span class="logo-mini"><b><i class="fa fa-amazon"></i></b></span>
    <!-- logo for regular state and mobile devices -->
    <span class="logo-lg"><b>大数据业务运营管理</b></span>
  </a>

  <!-- Header Navbar -->
  <nav class="navbar navbar-static-top" role="navigation">
    <!-- Sidebar toggle button-->
    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
      <span class="sr-only">Toggle navigation</span>
    </a>
    <!-- Navbar Right Menu -->
    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <!-- User Account Menu -->
        <li class="dropdown">
          <!-- Menu Toggle Button -->
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <span class="hidden-xs"><i class="fa fa-user"></i>&nbsp;&nbsp;${loginUser.userName}</span>
          </a>
          <ul class="pull-right dropdown-menu dropdown-close">
            <!-- Menu Footer-->
            <li>
                <a href="${basePath}/admin/logout" class="btn"><i class="fa fa-sign-out fa-fw"></i>退出登录</a>
            </li>
          </ul>
        </li>

      </ul>
    </div>
  </nav>
</header>

