<%@page language="java" import="java.util.ArrayList" %>
<%@page language="java" import="edu.nju.frs.model.*" %>
<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib uri="/WEB-INF/CheckSession.tld" prefix="MyTag" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>财务主管</title>
</head>
<body>
<MyTag:checkSession/>
你好！<%=request.getAttribute("realName")%>
</body>
</html>