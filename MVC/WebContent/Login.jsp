<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "mybean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">  
<head>  
    <meta charset="UTF-8">  
    <title>大连交通大学教务在线</title>
    <link rel="stylesheet" type="text/css" href="Login.css"/>  
</head>  
<body>  
    <div id="login">  
        <h1>DJTU教务登录</h1>  
        <form action="LoginServlet" method="post" >  
            <input type="text" required="required" placeholder="用户名" name="name"></input>  
            <input type="password" required="required" placeholder="密码" name="password"></input>  
            <input type="vafiation" required="required" placeholder="验证码" name="verifation"></input>  
            <button class="but" type="submit" value="Login" >登录</button>  
        </form>  
        <form action ="VerfiationServlet"method="post"> <button class="but1" type="submit" >获取验证码</button>  </form> 
    </div>  
</body>  
</html>  