<%@page language="java" import="java.util.*" %>
<%@page language="java" import="edu.nju.frs.model.*" %>
<%@page language="java" import="edu.nju.frs.util.*" %>
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
<div class="container tabbable" style="margin-top:20px;">
<ul class="nav nav-tabs">
    <li><a href="commonUserJoinProject">我参与的项目</a></li>
    <li><a href="commonUserManageProject">我管理的项目</a></li>
    <li><a href="http://localhost:8080/FRS/user/welcome.jsp">注销账号</a></li>
  
</ul>

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

<%
Project project = (Project)request.getAttribute("project");
%>
<div id="update_success" class="alert alert-success" style="margin-top:20px;display: none;" onclick="hide_me(this)"></div>
<div id="update_fail" class="alert alert-error" style="margin-top:20px;display: none;" onclick="hide_me(this)"></div>

<div class="tabbable tabs-left">
  <ul class="nav nav-tabs">
      <li class="active"><a href="#baseInfo" data-toggle="tab">基本信息</a></li>
      <li class=""><a href="#userList" data-toggle="tab">项目成员</a></li>
      <li class=""><a href="#expenseList" data-toggle="tab">报销记录</a></li> 
      <%
      	if(request.getAttribute("waitHostList")!=null){
      %>   
      <li class=""><a href="#waiHostList" data-toggle="tab">待审批报销</a></li>
      <%} %>
      <%
      	if(request.getAttribute("waitFEList")!=null){
      %>   
      <li class=""><a href="#waitFEList" data-toggle="tab">待审批报销</a></li>
      <%} %>
  </ul>
  <div class="tab-content">
  	<div class="tab-pane active span10" id="baseInfo">
  		<div class="well">
  		<div style="margin-bottom:10px;"><strong>项目编号：<%=project.getProjectId() %></strong></div>
  		<div style="margin-bottom:10px;">项目名称：<%=project.getProjectName() %></div>
  		<div style="margin-bottom:10px;">项目描述：<%=project.getDescription() %></div>
  		<div style="margin-bottom:10px;">项目开始时间：<%=project.getBeginDate() %></div>
  		<div style="margin-bottom:10px;">项目结束时间：<%=project.getEndDate() %></div>
  		<div style="margin-bottom:10px;">项目主持人账号：<%=project.getHostId() %></div>
  		<div style="margin-bottom:10px;">项目主持人姓名：<%=project.getHostName() %></div>
  		<div style="margin-bottom:10px;">项目状态：
<%if(project.getEndDate().before(java.sql.Date.valueOf(CommonHandle.getNowDate())))
		 out.print("已冻结");
 else if(project.getBeginDate().after(java.sql.Date.valueOf(CommonHandle.getNowDate()))){
	 out.print("未开始");
 }else{
	 out.print("进行中");
 }
%></div>
  		</div>
  	</div><!-- end baseInfo -->
  	
  	<div class="tab-pane span10" id="userList">
  	<table class="table table-striped table-bordered table-condensed">
  	<thead><tr>
	<td width="70px">成员编号</td>
	<td width="70px">姓名</td>
	<td width="540px">任务</td>
	<td width="70px;">编辑任务</td>
	</tr>
	</thead>
	<tbody id="newUserList">
  	<%
  	ArrayList<ProjectUser> userList = (ArrayList<ProjectUser>)request.getAttribute("userList");
  	  if(userList==null)
  		userList = new ArrayList<ProjectUser>();
  	  for(int i=0;i<userList.size();i++){
  		  if(!userList.get(i).isHost()){
  	%>
<tr>
<td><%=userList.get(i).getUser().getUserId() %></td>
<td><%=userList.get(i).getUser().getRealName() %></td>
<td><%=userList.get(i).getTask() %></td>
<td>
<a href="javascript:void(0)" onclick="on_edit_task_click(this)"><i class="icon-pencil"></i>&nbsp;&nbsp;&nbsp;edit</a>
</td>
</tr>
<%}} %>
</tbody>
</table>   

<!-- begin edit users -->

<div style="margin-bottom:10px;">
<a class="btn btn-primary" style="width:100px;" onClick="showUserList(this)">选择用户</a></div>
<div id="users">
<div id="chooseUser" style="text-align:left;margin-top:30px;width:25%;display:none;float:left;">
<div><!-- begin hashmap -->
<form action="editProject" method="post">
<input id="hiddenProjecId" type="hidden" name="projectId" value="<%=project.getProjectId()%>"/>
<%!public boolean isInList(ArrayList<ProjectUser> ul, User user){
	for(int k = 0; k < ul.size(); k++){
		if(ul.get(k).getUser().getUserId().equals(user.getUserId())){
			return true;
		}
	}
	return false;
}
%>
<%
			ArrayList<User> allUser = (ArrayList<User>)request.getAttribute("allUser");
			if(allUser!=null){
				for(int j = 0; j < allUser.size(); j++){
						if(project.getHostId().equals(allUser.get(j).getUserId())){
					%>
					
<% }else if(isInList(userList, allUser.get(j))){
%>
<div style="font:16;">
<input type="checkbox" checked name="selectedUser" title="<%=allUser.get(j).getRealName() %>" value="<%=allUser.get(j).getUserId() %>" onClick="updateSelectedUser()"/>&nbsp;&nbsp;姓名：<%=allUser.get(j).getRealName() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账号：<%=allUser.get(j).getUserId()%></div>
<%}else{%>
<div style="font:16;">
<input type="checkbox" name="selectedUser" title="<%=allUser.get(j).getRealName() %>" value="<%=allUser.get(j).getUserId() %>" onClick="updateSelectedUser()"/>&nbsp;&nbsp;姓名：<%=allUser.get(j).getRealName() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账号：<%=allUser.get(j).getUserId()%></div>
<% 
}}}%>
<input class="btn-primary" style="width:80px;margin-top:30px;" type="submit" value="提交"/>
</form>
</div><!-- end hashmap -->
</div><!-- end chooseUser -->

</div><!-- end users -->
<p style="clear:both;"></p>
<!-- end edit users -->

</div><!-- end userList -->
  	<div class="tab-pane span10" id="expenseList">
  	<%
  	ArrayList<ExpenseClaim> expenseList = (ArrayList<ExpenseClaim>)request.getAttribute("expenseList");
  	  if(expenseList==null)
  		expenseList = new ArrayList<ExpenseClaim>();
  	  for(int i=0;i<expenseList.size();i++){
  	%>
<div class="well">
  		<div style="margin-bottom:10px;"><strong>报销编号：<%=expenseList.get(i).getExpenseId() %></strong></div>
  		<div style="margin-bottom:10px;">报销名称：<%=expenseList.get(i).getExpenseName() %></div>
  		<div style="margin-bottom:10px;">报销描述：<%=expenseList.get(i).getExpenseDescription() %></div>
  		<div style="margin-bottom:10px;">报销者姓名：<%=expenseList.get(i).getUser().getRealName() %></div>
  		<div style="margin-bottom:10px;">报销者编号：<%=expenseList.get(i).getUser().getUserId() %></div>
  		<div style="margin-bottom:10px;">报销金额：<%=expenseList.get(i).getExpense() %></div>
  		<div style="margin-bottom:10px;">审核状态：
  		<%
  		switch(expenseList.get(i).getApproveState()){
  			case WaitHostPass:
  				out.print("等待主持人审批");
  				break;
  			case WaitFEPass:
  				out.print("等待财务审核人员审批");
  				break;
  			case FENotPass:
  				out.print("审核未通过");
  				break;
  			case Finish:
  				out.print("已报销");
  				break;
  			default:
  				out.print("未知");
  				break;
  		}
  		%></div>
  		<div style="margin-bottom:10px;">审核说明：<%=expenseList.get(i).getComment() %></div>
</div>

<%} %>
  	</div><!-- end expense -->
  	
  	<div class="tab-pane span10" id="waiHostList">
  	<%
  	ArrayList<ExpenseClaim> waitHostList = (ArrayList<ExpenseClaim>)request.getAttribute("waitHostList");
  	  if(waitHostList==null)
  		waitHostList = new ArrayList<ExpenseClaim>();
  	  for(int i=0;i<waitHostList.size();i++){
  	%>
<div class="well">
  		<div style="margin-bottom:10px;"><strong>报销编号：<%=waitHostList.get(i).getExpenseId() %></strong></div>
  		<div style="margin-bottom:10px;">报销名称：<%=waitHostList.get(i).getExpenseName() %></div>
  		<div style="margin-bottom:10px;">报销描述：<%=waitHostList.get(i).getExpenseDescription() %></div>
  		<div style="margin-bottom:10px;">报销者姓名：<%=waitHostList.get(i).getUser().getRealName() %></div>
  		<div style="margin-bottom:10px;">报销者编号：<%=waitHostList.get(i).getUser().getUserId() %></div>
  		<div style="margin-bottom:10px;">报销金额：<%=waitHostList.get(i).getExpense() %></div>
  		<div style="margin-bottom:10px;">审核状态：
  		<%
  		switch(waitHostList.get(i).getApproveState()){
  			case WaitHostPass:
  				out.print("等待主持人审批");
  				break;
  			case WaitFEPass:
  				out.print("等待财务审核人员审批");
  				break;
  			case FENotPass:
  				out.print("审核未通过");
  				break;
  			case Finish:
  				out.print("已报销");
  				break;
  			default:
  				out.print("未知");
  				break;
  		}
  		%></div>
  		<form action="http://localhost:8080/FRS/frs/updateExpenseClaim" method="post">
  		<input type="hidden" name="expenseClaimId" value="<%=waitHostList.get(i).getExpenseId()%>"/>
  		<input type="hidden" name="projectId" value="<%=project.getProjectId()%>"/>
  		<input type="radio" name="approve" value="1"/>同意
		<input type="radio" name="approve" value="2"/>不同意
  		<div style="margin-top:20px;">审核说明：<textarea name="comment"  cols="60" rows="5"></textarea></div>
  		
		<input class="btn-primary" style="width:80px;" type="submit" value="提交"/>
  		</form>
</div>
<%} %>
  	</div><!-- end waitHost -->
  	
  </div><!-- end tab-content -->
</div><!-- end tabs-left -->
</div><!-- end container -->
</body>
</html>