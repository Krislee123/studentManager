<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
欢迎进入学生管理系统
<!--action请求后台的ul路径-->
<form method="post" action="login.do">
用户名：<input name="username"><br>
密码：<input name="password"><br> 
<input type="submit" value="提交">
<!-- <input type="submit" value="注册"> -->
</form>
还没有注册，请立即<a href="register.jsp">注册</a>
</body>
</html> 