package com.bryantchang.autodepsys.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bryantchang.autodepsys.bean.User;

@Repository
public class UserDao extends BaseDAO{
	public User getUserByName(String username) {
		User user = null;
		Map conditions = new HashMap();
		conditions.put("username", username);
		
		ArrayList<User> list = new ArrayList<User>();
		list = this.getList(User.class, conditions);
		if(list.size() > 0) {
			user = list.get(0);
		}else {
			user = null;
		}
		return user;
	}
	
	public User addUser(User user) {
		boolean res = false;
		res = this.insert(user);
		if(res == true) {
			return user;
		}else {
			return null;
		}
		
	}
	
}


