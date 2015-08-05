<%@page language="java" import="java.util.*" %>
<%@page language="java" import="edu.nju.frs.model.*" %>
<%@page language="java" import="edu.nju.frs.util.*" %>
<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib uri="/WEB-INF/CheckSession.tld" prefix="MyTag" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>普通用户</title>
</head>
<body>
<MyTag:checkSession/>
<div class="container tabbable" style="margin-top:20px;">
<ul class="nav nav-tabs">
	<li><a href="#baseInfo" data-toggle="tab">我的基本信息</a></li>
    <li class="active"><a href="#joinProject" data-toggle="tab">我参与的项目</a></li>
    <li><a href="commonUserManageProject">我管理的项目</a></li>
    <li><a href="http://localhost:8080/FRS/user/welcome.jsp">注销账号</a></li>
  
</ul>

<div class="tab-content" style="width: 100%;">
	<div class="tab-pane" id="baseInfo">
		<div class="well" style="width: 100%;">
		<div style="margin:10px;">账号：<%=((User)request.getSession().getAttribute("user")).getUserId() %></div>
		<div style="margin:10px;">姓名：<%=((User)request.getSession().getAttribute("user")).getRealName() %></div>
		<div style="margin:10px;">银行卡号：<%=((User)request.getSession().getAttribute("user")).getBankCard().getBankNum() %></div>
		<div style="margin:10px;">银行余额：<%=((User)request.getSession().getAttribute("user")).getBankCard().getAmount() %></div>
		<div style="margin:10px;">银行卡收入：<%=((User)request.getSession().getAttribute("user")).getBankCard().getIncome() %></div>
		<div style="margin:10px;">银行支出：<%=((User)request.getSession().getAttribute("user")).getBankCard().getExpense() %></div>
		</div>
	</div>
	
<div class="tab-pane active" id="joinProject">
<table class="table table-striped table-bordered table-condensed">
	<thead><tr>
	<td width="136px">项目编号</td>
	<td width="136px">项目名称</td>
	<td width="136px">开始日期</td>
	<td width="136px">截止日期</td>
	<td width="136px">状态</td>
	<td width="136px">查看详情</td>
	</tr>
	</thead>
	<tbody>
<%
ArrayList<ProjectUser> allList = (ArrayList<ProjectUser>)request.getAttribute("allList");
if(allList==null)
	allList = new ArrayList<ProjectUser>();

  for(int i=0; i< allList.size(); i++){
%>
 <tr>
 <td><%=allList.get(i).getProject().getProjectId() %></td>
 <td><%=allList.get(i).getProject().getProjectName() %></td>
 <td><%=allList.get(i).getProject().getBeginDate() %></td>
 <td><%=allList.get(i).getProject().getEndDate() %></td>
 <td><%if(allList.get(i).getProject().getEndDate().before(java.sql.Date.valueOf(CommonHandle.getNowDate())))
		 out.print("已冻结");
 else if(allList.get(i).getProject().getBeginDate().after(java.sql.Date.valueOf(CommonHandle.getNowDate()))){
	 out.print("未开始");
 }else{
	 out.print("进行中");
 }
%></td>
<td><a href="joinViewProject?projectId=<%=allList.get(i).getProject().getProjectId() %>">more</a></td>
 </tr>
<%} %>
</tbody>
</table>
</div><!-- end joinProject -->

</div><!-- end tab-content-->
</div><!-- end container -->
</body>
</html>