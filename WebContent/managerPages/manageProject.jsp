<%@page import="java.util.HashMap"%>
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


	<script src="../js/jquery.ui.core.js"></script>
	<script src="../js/jquery.ui.datepicker.js"></script>
	<link rel="stylesheet" href="../css/style.css">
	<script>
	$(function() {
		$( "#from" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 3,
			onClose: function( selectedDate ) {
				$( "#to" ).datepicker( "option", "minDate", selectedDate );
			}
		});
		$( "#to" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 3,
			onClose: function( selectedDate ) {
				$( "#from" ).datepicker( "option", "maxDate", selectedDate );
			}
		});
	});
	</script>

<script type="text/javascript" src="../js/manager.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>系统管理员</title>
</head>
<body>
<MyTag:checkSession/>

<div class="container tabbable">
  <ul class="nav nav-tabs">
    <li><a href="manageUser">用户管理</a></li>
    <li class="active"><a href="manageProject" data-toggle="tab">项目管理</a></li>
  	<li><a href="http://localhost:8080/FRS/user/welcome.jsp">注销账号</a></li>
  </ul>
  
  <div class="tab-content">
    <div class="tab-pane active" id="userManager">
      <a class = "btn" onClick="$('#new_project_panel').show('fast')">
  		<i class="icon-plus"></i>新建项目
  	  </a>
<div id ="new_project_panel" style="background-color: whiteSmoke;text-align:center;display:none;margin-top:20px;padding:30px 40px;">
<form action="createProject" method="post">
<div>项目名称：<input name="projectName"/></div>
<div>项目描述：<textarea name="description" cols="60" rows="5"></textarea></div>
<div>项目经费预算/人民币：<input name="expense"/></div>
<div>项目主持人：
<select name="hostId">
<%
			HashMap userMap = (HashMap)request.getAttribute("commonUsersInfo");
			if(userMap!=null){
				Iterator iter = userMap.entrySet().iterator(); 
				while (iter.hasNext()) { 
					Map.Entry entry = (Map.Entry) iter.next(); 
					String userId = (String)entry.getKey(); 
					String realName = (String)entry.getValue(); 
%>
<option value="<%=userId %>"><%=realName %></option>
<%}} %>
</select></div>
<div>
<div>开始日期：
<input type="text" id="from" name="from"/></div>
<div>截止日期：
<input type="text" id="to" name="to"/></div>
</div>

<input class="btn-primary" style="width:80px;" type="submit" value="提交"/>
</form>

<a class="btn" href="#" style="width:70px;" onClick="$('#new_project_panel').hide('fast');">取消</a>
</div>
<%
if(request.getAttribute("info")!=null){
%>
<div id="create_result" class="alert alert-success" style="margin-top:20px;" onClick="hide_me(this)">
<%=request.getAttribute("info") %>
</div>
<%}%>
<%
if(request.getAttribute("error")!=null){
%>
<div id="warning" class="alert alert-error" style="margin-top:20px;" onClick="hide_me(this)">
<%=request.getAttribute("error") %>
</div>
<%}%>
<div>
<hr/>

<div class="tabbable tabs-left">
  <ul class="nav nav-tabs">
      <li class="active"><a href="#allProject" data-toggle="tab">所有项目</a></li>
      <li class=""><a href="#notFinish" data-toggle="tab">未完成项目</a></li>
      <li class=""><a href="#finish" data-toggle="tab">已截至项目</a></li>    
  </ul>
  <div class="tab-content">
          <div class="tab-pane active" id="allProject">
            <table class="table table-striped table-bordered table-condensed">
	<thead><tr>
	<td width="136px">项目编号</td>
	<td width="136px">项目名称</td>
	<td width="136px">开始日期</td>
	<td width="136px">截止日期</td>
	<td width="136px">查看详情</td>
	</tr>
	</thead>
	<tbody>
<%

ArrayList<Project> allList = (ArrayList<Project>)request.getAttribute("allList");
if(allList==null)
	allList = new ArrayList<Project>();
  for(int i=0; i< allList.size(); i++){
%>
<tr>
<td><%=allList.get(i).getProjectId() %></td>
<td><%=allList.get(i).getProjectName()%></td>
<td><%=allList.get(i).getBeginDate() %></td>
<td><%=allList.get(i).getEndDate() %></td>
<td><a href="managerViewProject?projectId=<%=allList.get(i).getProjectId() %>">more</a></td>
</tr>
<%} %>
</tbody>
</table>
</div>
<div class="tab-pane" id="notFinish">
    <table class="table table-striped table-bordered table-condensed">
	<thead><tr>
	<td width="136px">项目编号</td>
	<td width="136px">项目名称</td>
	<td width="136px">开始日期</td>
	<td width="136px">截止日期</td>
	<td width="136px">查看详情</td>
	</tr>
	</thead>
	<tbody>
<%

ArrayList<Project> projectNotFinish = (ArrayList<Project>)request.getAttribute("projectNotFinish");
if(projectNotFinish==null)
	projectNotFinish = new ArrayList<Project>();
  for(int i=0; i< projectNotFinish.size(); i++){
%>
<tr>
<td><%=projectNotFinish.get(i).getProjectId() %></td>
<td><%=projectNotFinish.get(i).getProjectName()%></td>
<td><%=projectNotFinish.get(i).getBeginDate() %></td>
<td><%=projectNotFinish.get(i).getEndDate() %></td>
<td><a href="viewProject?projectId=<%=projectNotFinish.get(i).getProjectId() %>">more</a></td>
</tr>
<%} %>
</tbody>
</table>        
</div>
<div class="tab-pane" id="finish">
      <table class="table table-striped table-bordered table-condensed">
	<thead><tr>
	<td width="136px">项目编号</td>
	<td width="136px">项目名称</td>
	<td width="136px">开始日期</td>
	<td width="136px">截止日期</td>
	<td width="136px">查看详情</td>
	</tr>
	</thead>
	<tbody>
<%

ArrayList<Project> projectFinish = (ArrayList<Project>)request.getAttribute("projectFinish");
if(projectFinish==null)
	projectFinish = new ArrayList<Project>();
  for(int i=0; i< projectFinish.size(); i++){
%>
<tr>
<td><%=projectFinish.get(i).getProjectId() %></td>
<td><%=projectFinish.get(i).getProjectName()%></td>
<td><%=projectFinish.get(i).getBeginDate() %></td>
<td><%=projectFinish.get(i).getEndDate() %></td>
<td><a href="viewProject?projectId=<%=projectFinish.get(i).getProjectId() %>">more</a></td>
</tr>
<%} %>
</tbody>
</table>      
</div><!-- end finish -->     
</div><!-- end tab-content -->   
</div><!-- end tabs-left -->
</div><!-- end tab-content -->
</div><!-- end container -->
</div>
</div>
</body>
</html>