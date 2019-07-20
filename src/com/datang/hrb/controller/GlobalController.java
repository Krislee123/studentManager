package com.datang.hrb.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dataing.hrb.dao.ConnectionUtil;
import com.datang.hrb.service.LoginService;
import com.datang.hrb.service.lmpl.LoginServicelmpl;
import com.datang.hrb.vo.User;

@SuppressWarnings("serial")
public class GlobalController extends HttpServlet {
	private Map<String, String> userMap = new HashMap<String, String>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get");
		/*
		 * resp.setContentType("text/html;charset=UTF-8"); PrintWriter
		 * pw=resp.getWriter(); pw.write("恭喜访问后台"); pw.flush(); pw.close();
		 */
		resp.sendRedirect("ok.jsp");// 重定向
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		// System.out.println(req.getRequestURI());
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf("."));// action代表业务逻辑
		if (action.equals("register"))// 注册
		{
			/*
			 * userMap.put(username, password);
			 * resp.sendRedirect("register_success.jsp");//重定向
			 */
			Connection conn = ConnectionUtil.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement("insert into user(username,password) values(?,?)");
				ps.setString(1, username);
				ps.setString(2, password);
				int i = ps.executeUpdate();
				if (i == 1) {
					resp.sendRedirect("register_success.jsp");// 重定向
				} else {
					resp.sendRedirect("register_fail.jsp");// 重定向
				}
			} catch (SQLException e) {
				resp.sendRedirect("register_fail.jsp");
				e.printStackTrace();
			}
		} else if (action.equals("login"))// 登录
		{
			//使用user对用户名密码进行封装
			User user=new User();
			user.setUsername(username);
			user.setPassword(password);
			//调用service层处理登录逻辑
			LoginService loginService=new LoginServicelmpl();
			//对比返回值确定登录是否成功
			if(loginService.login(user)=="login_success.jsp") {
				HttpSession session=req.getSession();
				//使用session存储用户名
				session.setAttribute("username", username);
				List<User> userList=loginService.getUserList();
				session.setAttribute("userList", userList);
				resp.sendRedirect("login_success.jsp");
				
			}else {
				resp.sendRedirect("login_fail.jsp");
			}
			
			
			
			/*
			 * Connection conn=ConnectionUtil.getConnection(); PreparedStatement ps=null;
			 * //获取session对象 HttpSession session=req.getSession(); try {
			 * ps=conn.prepareStatement("select * from user where username=?");
			 * ps.setString(1, username); ResultSet rs=ps.executeQuery(); if (rs != null
			 * &&rs.next()) { String dbpwd=rs.getString(2); //密码一致 登陆成功
			 * if(password.equals(dbpwd)) { //在登陆成功后 使用session存储用户名，以供在页面上展示用户名
			 * session.setAttribute("username", username);
			 *//**
				 * 登陆成功后查询你想要的表里面的数据（这里就是以用户信息user表为例查询了当前表下的所有数据） 并使用泛型存储数据，使用session进行存储
				 *//*
					 * PreparedStatement ps_second=null; //查询所有数据
					 * ps_second=conn.prepareStatement("select * from user"); ResultSet
					 * rsList=ps_second.executeQuery(); //定义泛型，存储返回的数据 这里使用了User对数据进行封装 List<User>
					 * userList=new ArrayList<User>(); //遍历返回数据并将其存储到userList中 while(rsList.next())
					 * { //实例化User User user=new User(); //向user中赋值
					 * user.setUsername(rsList.getString(1)); user.setAge(rsList.getInt(3));
					 * //将user转储到userList userList.add(user); } //将userList转储到session中
					 * session.setAttribute("userList", userList);
					 * resp.sendRedirect("login_success.jsp"); }else {
					 * resp.sendRedirect("login_fail.jsp"); } } } catch (SQLException e) { // TODO
					 * Auto-generated catch block e.printStackTrace(); }
					 */
		}

	}
}
