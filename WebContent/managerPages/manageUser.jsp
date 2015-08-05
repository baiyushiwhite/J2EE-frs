<%@page language="java" import="java.util.ArrayList" %>
<%@page language="java" import="edu.nju.frs.model.*" %>
<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib uri="/WEB-INF/CheckSession.tld" prefix="MyTag" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="../css/jquery-ui-1.10.0.custom.css" />

<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/bootstrap-tab.js"></script>
<script type="text/javascript" src="../js/bootstrap-tooltip.js"></script>
<script type="text/javascript" src="../js/bootstrap-popover.js"></script>
<script type="text/javascript" src="../js/bootstrap-tab.js"></script>
<script type="text/javascript" src="../js/bootstrap-modal.js"></script>
<script type="text/javascript" src="../js/bootstrap-dropdown.js"></script>

<script type="text/javascript" src="../js/manager.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>系统管理员</title>
</head>
<body>
<MyTag:checkSession/>

<div class="container tabbable">
  <ul class="nav nav-tabs">
    <li class="active"><a href="manageUser" data-toggle="tab">用户管理</a></li>
    <li><a href="manageProject">项目管理</a></li>
    <li><a href="http://localhost:8080/FRS/user/welcome.jsp">注销账号</a></li>
  </ul>
  
  <div class="tab-content">
    <div class="tab-pane active" id="userManager">
      <!-- 用户管理 -->
      <a class = "btn" onclick="$('#new_user_panel').show('fast')">
  		<i class="icon-plus"></i>新建用户
  	  </a>
  	  <div class="well"  id ="new_user_panel" style="text-align:center;display:none;margin-top:20px;">
	  	<form action="register" method="post">
	  		<div>账号：<input id="userId" name="userId"  onblur="is_good_userId()"/><span id="userId_remind"></span></div>
			<div>密码：<input id="password" name="password" /><span id="password_remind"></span></div>
			<div>姓名：<input id="realName" name="realName" /><span id="realName_remind"></span></div>
			<div>银行账号：<input id="bankNum" name="bankNum" /><span id="bankNum_remind"></span></div>
		<div>
<select id="userType" name="userType">
  <option value ="CommonUser">普通用户</option>
  <option value ="ManagerUser">系统管理员</option>
  <option value="FinancialExaminers">财务审计人员</option>
  <option value="FinancialManager">财务经理</option>
</select>
</div>

<input class="btn-primary" onclick="registerUser()" style="width:80px;" type="button" value="提交"/>
</form>
<a class=" btn" href="#" onclick="$('#new_user_panel').hide('fast');">取消</a>
</div>
<div id="create_success" class="alert alert-success" style="display: none;" onclick="hide_me(this)"></div>
<div id="create_fail" class="alert alert-error" style="display: none;" onclick="hide_me(this)"></div>
<div>
<hr/>
<div id="update_success" class="alert alert-success" style="margin-top:20px;display: none;" onclick="hide_me(this)"></div>
<div id="update_fail" class="alert alert-error" style="margin-top:20px;display: none;" onclick="hide_me(this)"></div>
<div class="tabbable tabs-left">
  <ul class="nav nav-tabs">
      <li class="active"><a href="#allUser" data-toggle="tab"><i class="icon-list"></i>&nbsp;&nbsp;所有用户</a></li>
      <li class=""><a href="#commonUser" data-toggle="tab"><i class="icon-list"></i>&nbsp;&nbsp;普通用户</a></li>
      <li class=""><a href="#managerUser" data-toggle="tab"><i class="icon-list"></i>&nbsp;&nbsp;管理员用户</a></li>    
      <li class=""><a href="#feUser" data-toggle="tab"><i class="icon-list"></i>&nbsp;&nbsp;财务审核用户</a></li>
      <li class=""><a href="#fmUser" data-toggle="tab"><i class="icon-list"></i>&nbsp;&nbsp;财务主管用户</a></li>    
  </ul>
  <div class="tab-content">
          <div class="tab-pane active" id="allUser">
 <table class="table table-striped table-bordered table-condensed">
	<thead><tr>
	<td width="136px">账号</td>
	<td width="136px">姓名</td>
	<td width="136px">密码</td>
	<td width="136px">银行卡账号</td>
	<td width="136px">用户类型</td>
	<td width="136px">操作</td>
	</tr>
	</thead>
	<tbody>
<%
ArrayList<User> userList = (ArrayList<User>)request.getAttribute("userList");
if(userList==null)
	userList = new ArrayList<User>();
  for(int i=0;i<userList.size();i++){
%>
<tr>
<td><%=userList.get(i).getUserId() %></td>
<td><%=userList.get(i).getRealName() %></td>
<td><%=userList.get(i).getPassword() %></td>
<td><%=userList.get(i).getBankCard().getBankNum() %></td>
<td><%=userList.get(i).getUserType() %></td>
<td class="op_td">
<a href="javascript:void(0)" onclick="on_edit_click(this,1)"><i class="icon-pencil"></i>&nbsp;&nbsp;&nbsp;edit</a>
</td>
</tr>
<%} %>
</tbody>
</table>
</div>
<div class="tab-pane" id="commonUser">
<table class="table table-striped table-bordered table-condensed">
	<thead><tr>
	<td width="136px">账号</td>
	<td width="136px">姓名</td>
	<td width="136px">密码</td>
	<td width="136px">银行卡账号</td>
	<td width="136px">用户类型</td>
	<td width="136px">操作</td>
	</tr>
	</thead>
	<tbody>
<%
ArrayList<User> commonUserList = (ArrayList<User>)request.getAttribute("commonUserList");
  if(commonUserList==null)
	  commonUserList = new ArrayList<User>();
  for(int i=0;i<commonUserList.size();i++){
%>
<tr>
<td><%=commonUserList.get(i).getUserId() %></td>
<td><%=commonUserList.get(i).getRealName() %></td>
<td><%=commonUserList.get(i).getPassword() %></td>
<td><%=commonUserList.get(i).getBankCard().getBankNum() %></td>
<td><%=commonUserList.get(i).getUserType() %></td>
<td class="op_td">
<a href="javascript:void(0)" onclick="on_edit_click(this, 0)"><i class="icon-pencil"></i>&nbsp;&nbsp;&nbsp;edit</a>
</td>
</tr>
<%} %>
</tbody>
</table>            
          </div>
           <div class="tab-pane" id="managerUser">
<table class="table table-striped table-bordered table-condensed">
	<thead><tr>
	<td width="136px">账号</td>
	<td width="136px">姓名</td>
	<td width="136px">密码</td>
	<td width="136px">银行卡账号</td>
	<td width="136px">用户类型</td>
	<td width="136px">操作</td>
	</tr>
	</thead>
	<tbody>
<%
ArrayList<User> managerUserList = (ArrayList<User>)request.getAttribute("managerUserList");
if(managerUserList==null)
	managerUserList = new ArrayList<User>();  
for(int i=0;i<managerUserList.size();i++){
%>
<tr>
<td><%=managerUserList.get(i).getUserId() %></td>
<td><%=managerUserList.get(i).getRealName() %></td>
<td><%=managerUserList.get(i).getPassword() %></td>
<td><%=managerUserList.get(i).getBankCard().getBankNum() %></td>
<td><%=managerUserList.get(i).getUserType() %></td>
<td class="op_td">
<a href="javascript:void(0)" onclick="on_edit_click(this, 0)"><i class="icon-pencil"></i>&nbsp;&nbsp;&nbsp;edit</a>
</td>
</tr>
<%} %>
</tbody>
</table>              
          </div>
          <div class="tab-pane" id="feUser">
 <table class="table table-striped table-bordered table-condensed">
	<thead><tr>
	<td width="136px">账号</td>
	<td width="136px">姓名</td>
	<td width="136px">密码</td>
	<td width="136px">银行卡账号</td>
	<td width="136px">用户类型</td>
	<td width="136px">操作</td>
	</tr>
	</thead>
	<tbody>
<%
ArrayList<User> feUserList = (ArrayList<User>)request.getAttribute("feUserList");
if(feUserList==null)
	feUserList = new ArrayList<User>();  
for(int i=0;i<feUserList.size();i++){
%>
<tr>
<td><%=feUserList.get(i).getUserId() %></td>
<td><%=feUserList.get(i).getRealName() %></td>
<td><%=feUserList.get(i).getPassword() %></td>
<td><%=feUserList.get(i).getBankCard().getBankNum() %></td>
<td><%=feUserList.get(i).getUserType() %></td>
<td class="op_td">
<a href="javascript:void(0)" onclick="on_edit_click(this, 0)"><i class="icon-pencil"></i>&nbsp;&nbsp;&nbsp;edit</a>
</td>
</tr>
<%} %>
</tbody>
</table>            
          </div>
          <div class="tab-pane" id="fmUser">
 <table class="table table-striped table-bordered table-condensed">
	<thead><tr>
	<td width="136px">账号</td>
	<td width="136px">姓名</td>
	<td width="136px">密码</td>
	<td width="136px">银行卡账号</td>
	<td width="136px">用户类型</td>
	<td width="136px">操作</td>
	</tr>
	</thead>
	<tbody>
<%
ArrayList<User> fmUserList = (ArrayList<User>)request.getAttribute("fmUserList");
if(fmUserList==null)
	fmUserList = new ArrayList<User>();  
for(int i=0;i<fmUserList.size();i++){
%>
<tr>
<td><%=fmUserList.get(i).getUserId() %></td>
<td><%=fmUserList.get(i).getRealName() %></td>
<td><%=fmUserList.get(i).getPassword() %></td>
<td><%=fmUserList.get(i).getBankCard().getBankNum() %></td>
<td><%=fmUserList.get(i).getUserType() %></td>
<td class="op_td">
<a href="javascript:void(0)" onclick="on_edit_click(this, 0)"><i class="icon-pencil"></i>&nbsp;&nbsp;&nbsp;edit</a>
</td>
</tr>
<%} %>
</tbody>
</table>             
 </div>
</div>
</div><!-- end tabbable tabs-left -->
</div>
</div><!-- end userManager -->
</div>
</div>
</body>
</html>