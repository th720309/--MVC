<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功</title>
</head>
<body>
<jsp:useBean id = "show" class = "mybean.Model" scope = "session" />
<jsp:setProperty property="*"  name="show"/>
<p>您好！ </p>
<p><jsp:getProperty name = "show" property = "name"/></p>
<p>欢迎登录DJTU综合教务</p>
<p>后续功能还未开发，敬请期待！</p>
</body>
</html>