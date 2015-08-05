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

<div class="container tabbable">
<ul class="nav nav-tabs">
    <li><a href="manageUser">用户管理</a></li>
    <li><a href="manageProject">项目管理</a></li>
</ul>
<%Project project = (Project)request.getAttribute("project"); %>
<div id ="new_project_panel" style="background-color: whiteSmoke;text-align:center;margin-top:20px;padding:30px 40px;">
<form action="updateProject" method="post">
<input type="hidden" name="projectId" value="<%=project.getProjectId()%>"/>
<div>项目名称：<input name="projectName" value="<%=project.getProjectName()%>"/></div>
<div>项目描述：<textarea name="description" cols="60" rows="5"><%=project.getDescription()%></textarea></div>
<div>项目经费预算/人民币：<input name="expense" value="<%=project.getExpense()%>"/></div>
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
<input type="text" id="from" name="from"  value="<%=project.getBeginDate()%>"/></div>
<div>截止日期：
<input type="text" id="to" name="to" value="<%=project.getEndDate()%>"/></div>
</div>

<input class="btn-primary" style="width:80px;" type="submit" value="提交"/>
</form>
</div>
</div>
</body>
</html>