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
<script type="text/javascript" src="../js/manager.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>财务审核人员</title>
</head>
<body>
<div class="container tabbable">
<ul class="nav nav-tabs">
	<li class="active"><a href="#baseInfo" data-toggle="tab">我的基本信息</a></li>
    <li><a href="#waitFEList" data-toggle="tab">待审批报销</a></li>
	<li><a href="http://localhost:8080/FRS/user/welcome.jsp">注销账号</a></li>
  </ul>
<div class="tab-content" style="width: 100%;">
	<div class="tab-pane span10 active" id="baseInfo">
		<div class="well" style="width: 100%;">
		<div style="margin:10px;">账号：<%=((User)request.getSession().getAttribute("user")).getUserId() %></div>
		<div style="margin:10px;">姓名：<%=((User)request.getSession().getAttribute("user")).getRealName() %></div>
		<div style="margin:10px;">银行卡号：<%=((User)request.getSession().getAttribute("user")).getBankCard().getBankNum() %></div>
		</div>
	</div>
<div class="tab-pane span10" id="waitFEList">

<%
if(request.getAttribute("info")!=null){
%>
<div class="alert alert-success" style="margin-top:20px;width:100%;" onClick="hide_me(this)">
<%=request.getAttribute("info") %>
</div>
<%}%>
<%
if(request.getAttribute("error")!=null){
%>
<div class="alert alert-error" style="margin-top:20px;" onClick="hide_me(this)">
<%=request.getAttribute("error") %>
</div>
<%}%>

  	<%
  	ArrayList<ExpenseClaim> waitFEList = (ArrayList<ExpenseClaim>)request.getAttribute("waitFEList");
  	  if(waitFEList==null)
  		waitFEList = new ArrayList<ExpenseClaim>();
  	  for(int i=0;i<waitFEList.size();i++){
  	%>
<div class="well">
  		<div style="margin-bottom:10px;"><strong>报销编号：<%=waitFEList.get(i).getExpenseId() %></strong></div>
  		<div style="margin-bottom:10px;">报销名称：<%=waitFEList.get(i).getExpenseName() %></div>
  		<div style="margin-bottom:10px;">报销描述：<%=waitFEList.get(i).getExpenseDescription() %></div>
  		<div style="margin-bottom:10px;">报销者姓名：<%=waitFEList.get(i).getUser().getRealName() %></div>
  		<div style="margin-bottom:10px;">报销者编号：<%=waitFEList.get(i).getUser().getUserId() %></div>
  		<div style="margin-bottom:10px;">报销金额：<%=waitFEList.get(i).getExpense() %></div>
  		<div style="margin-bottom:10px;">审核状态：
  		<%
  		switch(waitFEList.get(i).getApproveState()){
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
  		<form action="updateExpenseClaim" method="post">
  		<input type="hidden" name="expenseClaimId" value="<%=waitFEList.get(i).getExpenseId()%>">
  		
  		<input type="radio" name="approve" value="3">同意
		<input type="radio" name="approve" value="0">不同意
  		<div style="margin-top:10px;">审核说明：<textarea name="comment"  cols="60" rows="5"><%=waitFEList.get(i).getComment() %></textarea></div>
  		
		<input class="btn-primary" style="width:80px;" type="submit" value="提交"/>
  		</form>
</div>
<%} %>
  	</div>
  	
  
</div><!-- end content -->

</div><!-- end container -->
</body>
</html>