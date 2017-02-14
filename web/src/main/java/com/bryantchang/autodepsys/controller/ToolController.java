package com.bryantchang.autodepsys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bryantchang.autodepsys.common.RandomValidateCode;

@Controller
public class ToolController {
	
	@Resource
	RandomValidateCode code;
	@RequestMapping("/vcode")
	public void vcode(HttpServletRequest request, HttpServletResponse response) {
		code.getRandcode(request, response);
	}
	
	
	
	
}
