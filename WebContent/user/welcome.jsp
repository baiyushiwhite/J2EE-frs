<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
      <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css"/>

<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>welcome to frs!</title>
</head>
<body>
<div class="container tabbable" style="margin-top:20px;">
<div class="well">
<s:form action="login" method="post" namespace="/frs">
<div>账号：<input name="userId"/></div>
<div>密码：<input type="password" name="password"/></div>
<input class="btn-primary" style="width:80px;margin-top:20px;" type="submit" value="确定"/>
</s:form>
</div>
</div>
</body>
</html>