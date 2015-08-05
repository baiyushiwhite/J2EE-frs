<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
      <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>新建项目</title>
	<link rel="stylesheet" href="../css/jquery.ui.all.css">
	<script src="../js/jquery-1.9.1.js"></script>
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
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="manageUser">用户管理</a></li>
    <li><a href="manageProject">项目管理</a></li>
</ul>
<s:form action="createProject" namespace="/frs" method="post">
<div>
<s:textfield name="projectName" label="项目名称"/>
<s:textarea name="projectDescription" label="项目描述"/>
<s:textfield name="hostId" label="项目负责人"/>
<s:textfield name="expense" label="项目经费预算/人民币"/>
</div>

<div>
<label for="from">开始日期</label>
<input type="text" id="from" name="from"/>
<label for="to">截止日期</label>
<input type="text" id="to" name="to"/>
</div>

<s:submit value="提交"/>
<s:reset/>
</s:form>
</body>
</html>
