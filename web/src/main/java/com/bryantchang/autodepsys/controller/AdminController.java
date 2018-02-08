package com.bryantchang.autodepsys.controller;

import com.bryantchang.autodepsys.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bryantchang.autodepsys.constant.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminController {
	@RequestMapping("/admin/index")
	public String toIndexPage(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie: cookies) {
			if("uid".equals(cookie.getName())) {
				map.put("userid", cookie.getValue());
				return Constants.JSPBASE + "main.jsp";
			}else{}
		}
		return Constants.JSPBASE + "login.jsp";

	} 
	
	
}
