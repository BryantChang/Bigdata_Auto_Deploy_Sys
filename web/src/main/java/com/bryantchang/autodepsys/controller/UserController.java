package com.bryantchang.autodepsys.controller;

import java.net.URL;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

import com.bryantchang.autodepsys.bean.User;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.UserService;

@Controller
@SessionAttributes("user")
public class UserController {
	@Resource
	UserService service;

	// http://localhost:8080/autodeploymentsys/login
	@RequestMapping("login")
	public String toLoginPage() {
		return Constants.JSPBASE + "login.jsp";
	}

	@RequestMapping("reg")
	public String toRegPage() {
		return Constants.JSPBASE + "reg.jsp";
	}

	// doLogin的action
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(@RequestParam String username, @RequestParam String password, @RequestParam String vcode,
			HttpServletRequest request, HttpServletResponse response, ModelMap map, RedirectAttributes model) {
		try {
			String sessionCode = (String) request.getSession().getAttribute(Constants.RANDOM_CODE_KEY);
			if (vcode.toLowerCase().equals(sessionCode.toLowerCase())) {
				User user = service.doLogin(username, password);
				map.put("user", user);
                Cookie cookie = new Cookie("uid", user.getId()+"");
                cookie.setMaxAge(5 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);
			} else {
				request.setAttribute("error", "验证码错误");
				return Constants.JSPBASE + "login.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			return Constants.JSPBASE + "login.jsp";
		}
		return "redirect:/admin/index";
	}

	// doLogout的action
	@RequestMapping("doLogout")
	public String doLogout(SessionStatus status) {
		status.setComplete();
		return "redirect:/admin/index";
	}

	@RequestMapping(value = "/doReg", method = RequestMethod.POST)
	public String doReg(@RequestParam(value="username") String username, 
			@RequestParam(value="password") String password, 
			@RequestParam(value="passconfirm") String passConfirm, 
			@RequestParam(value="mobile") String mobile,
			@RequestParam(value="email") String email,
			@RequestParam(value="role") String role,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			User user = service.doReg(username, password, passConfirm, email, mobile, role, request);
			if(user != null) {
				return Constants.JSPBASE + "login.jsp";
			}else {
				request.setAttribute("error", "注册失败");
				return Constants.JSPBASE + "reg.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			return Constants.JSPBASE + "reg.jsp";
		}
	}
	
	
}
