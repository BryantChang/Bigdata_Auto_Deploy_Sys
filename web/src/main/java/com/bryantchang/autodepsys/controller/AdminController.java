package com.bryantchang.autodepsys.controller;

import com.bryantchang.autodepsys.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bryantchang.autodepsys.constant.Constants;

@Controller
public class AdminController {
	@RequestMapping("/admin/index")
	public String toIndexPage(@ModelAttribute("user") User user, ModelMap map){
		map.put("user", user);
		return Constants.JSPBASE + "main.jsp";
	} 
	
	
}
