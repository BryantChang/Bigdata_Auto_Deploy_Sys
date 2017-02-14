package com.bryantchang.autodepsys.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;

import org.springframework.stereotype.Service;

import com.bryantchang.autodepsys.bean.User;
import com.bryantchang.autodepsys.common.UidGenerator;
import com.bryantchang.autodepsys.dao.UserDao;

@Service
public class UserService {
	@Resource
	UserDao dao;

	public User doLogin(String username, String password) throws Exception {
		if (username == null || "".equals(username)) {
			throw new Exception("用户名不能为空");
		}
		if (password == null || "".equals(password)) {
			throw new Exception("密码不能为空");
		}

		User user = dao.getUserByName(username);

		if (user == null) {
			throw new Exception("用户名不存在");
		}
		if (!user.getPassword().equals(password)) {
			throw new Exception("密码错误");
		}
		return user;
	}

	public User doReg(String username, String password, String passConfirm, String email, String mobile, String role,
			HttpServletRequest request) throws Exception {
		if (username == null || "".equals(username)) {
			throw new Exception("用户名不能为空");
		} else if (password == null || "".equals(password)) {
			throw new Exception("密码不能为空");
		} else if (email == null || "".equals(email)) {
			throw new Exception("邮箱不能为空");
		} else if (mobile == null || "".equals(mobile)) {
			throw new Exception("电话不能为空");
		} else if (!passConfirm.equals(password)) {
			throw new Exception("两次输入的密码不一致");
		} else {
			Long role_long = Long.valueOf(role);
			Timestamp ctime = new Timestamp(new Date().getTime());
			Timestamp mtime = new Timestamp(new Date().getTime());
			User user = new User(0L, username, password, role_long, mobile, email, ctime);
			User resUser = dao.addUser(user);
			return resUser;
		}

	}
}
