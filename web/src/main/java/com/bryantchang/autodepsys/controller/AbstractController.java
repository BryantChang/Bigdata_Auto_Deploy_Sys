package com.bryantchang.autodepsys.controller;

import com.bryantchang.autodepsys.constant.Constants;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AbstractController {
    public String checkUser(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String res = "";
        for (Cookie cookie: cookies) {
            if ("uid".equals(cookie.getName())) {
                res = cookie.getValue();
            } else {
            }
        }
        return res;
    }
}
