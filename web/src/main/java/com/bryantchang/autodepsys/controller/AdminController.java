package com.bryantchang.autodepsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bryantchang.autodepsys.constant.Constants;

@Controller
public class AdminController {
	@RequestMapping("/admin/index")
	public String toIndexPage(){
		return Constants.JSPBASE + "main.jsp";
	} 
	
	
}
