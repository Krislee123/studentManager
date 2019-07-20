package com.datang.hrb.service.lmpl;

import java.util.List;

import com.dataing.hrb.dao.UserDao;
import com.datang.hrb.service.LoginService;
import com.datang.hrb.vo.User;

public class LoginServicelmpl implements LoginService {
	UserDao userDao=new UserDao();
	@Override
	//实现接口内定义的login方法
	public String login(User user) {
		// 从数据库中查找数据（用户名对应的密码）进行比较确认是否登录
		//这里需要从数据库中查找  就需要使用dao层
		//调用dao层UserDao 根据输入的用户名获取对应的密码
	//	UserDao userDao=new UserDao();
		String password=userDao.getUsersByUsername(user.getUsername());
		//对比密码
		if(password!=null&&password.equals(user.getPassword())) {
			return "login_success.jsp";
		}else {
			return "login_fail.jsp";
		}
		
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return userDao.getUserList();
	}

}
