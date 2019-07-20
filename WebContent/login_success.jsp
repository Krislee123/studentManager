<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 导入对应的包 -->

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.datang.hrb.vo.*"%>
<!-- session.getAttribute返回的是Object类型需要强制转化为我们使用的类型 -->
<%
	String username = (String) session.getAttribute("username");
%>
<%
	List<User> userList = (ArrayList<User>) session.getAttribute("userList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- jsp中引用变量 需要在%后加上“=” -->
	登陆成功！欢迎<%=username%>
	 <br /> 所有用户信息
	<%
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
	%>
	<p>
		用户名：<%=user.getUsername()%>
		年龄：<%=user.getAge()%></p>
	<%
		}
	%>
 
</body>
</html>