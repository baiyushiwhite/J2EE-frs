<%@page language="java" import="java.util.*" %>
<%@page language="java" import="edu.nju.frs.model.*" %>
<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib uri="/WEB-INF/CheckSession.tld" prefix="MyTag" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="../css/jquery-ui-1.10.0.custom.css" />
<link rel="stylesheet" href="../css/jquery.ui.all.css">

<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/bootstrap-tab.js"></script>
<script type="text/javascript" src="../js/bootstrap-tooltip.js"></script>
<script type="text/javascript" src="../js/bootstrap-popover.js"></script>
<script type="text/javascript" src="../js/bootstrap-tab.js"></script>
<script type="text/javascript" src="../js/bootstrap-modal.js"></script>
<script type="text/javascript" src="../js/bootstrap-dropdown.js"></script>

<script type="text/javascript" src="../js/manager.js"></script>
<title>viewProject</title>
</head>
<body>
<MyTag:checkSession/>
<%
Project project = (Project)request.getAttribute("project");
%>
<div class="container tabbable" style="margin-top:20px;">
<div class="tabbable tabs-left">
  <ul class="nav nav-tabs">
      <li class="active"><a href="#baseInfo" data-toggle="tab">基本信息</a></li>
      <li class=""><a href="#userList" data-toggle="tab">项目成员</a></li>
      <li class=""><a href="#expenseList" data-toggle="tab">报销记录</a></li>    
  </ul>
  <div class="tab-content">
  	<div class="tab-pane active span10" id="baseInfo">
  		<div class="well">
  		<div style="margin-bottom:10px;"><strong>项目编号：<%=project.getProjectId() %></strong></div>
  		<div style="margin-bottom:10px;">项目名称：<%=project.getProjectName() %></div>
  		<div style="margin-bottom:10px;">项目描述：<%=project.getDescription() %></div>
  		<div style="margin-bottom:10px;">项目开始时间：<%=project.getBeginDate() %></div>
  		<div style="margin-bottom:10px;">项目结束时间：<%=project.getEndDate() %></div>
  		<div style="margin-bottom:10px;">项目主持人：<%=request.getAttribute("hostName") %></div>
  		<div style="margin-bottom:10px;">项目状态：<%=request.getAttribute("projectState") %></div>
  		</div>
  	</div>
  	<hr />
 
<div class="tab-pane active span10" id="userList">
<div class="well">

<strong>成员列表</strong>
<table>
<thead>
<tr>
<td>用户账号</td>
<td>用户姓名</td>
<td>是否为主持人</td>
<td>任务</td>
</tr>
</thead>
<tbody>
<%
  	ArrayList<ProjectUserInfo> userList = (ArrayList<ProjectUserInfo>)request.getAttribute("project_users");
  	  if(userList==null)
  		userList = new ArrayList<ProjectUserInfo>();
  	  for(int i=0;i<userList.size();i++){
  	%>
<tr>
<td><%=userList.get(i).getUserId() %></td>
<td><%=userList.get(i).getRealName() %></td>
<td><%if(userList.get(i).isHost())out.println("<i class=\"icon-star-empty\"></i>"); %></td>
<td><%=userList.get(i).getTask() %></td>
</tr>
<%} %>
</tbody>
</table>    
</div>
</div><!-- end userList -->
  	<hr/>
  	<div class="tab-pane active span10" id="expenseList">
  		<div class="well">
  		
  		</div>
  	</div><!-- end expense -->
  </div><!-- end tab-content -->
</div><!-- end tabs-left -->
</div><!-- end container -->
</body>
</html>