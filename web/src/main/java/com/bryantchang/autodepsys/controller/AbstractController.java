package com.bryantchang.autodepsys.controller;

import com.bryantchang.autodepsys.bean.ClusterInfo;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.dao.ClusterDao;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class AbstractController {
    @Resource
    ClusterDao dao;
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

    public HashMap<Integer, String> getClusterInfoMap() {
        ArrayList<ClusterInfo> list = new ArrayList<>();
        list = dao.getClusterInfoList();
        HashMap<Integer, String> infoMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            infoMap.put(list.get(i).getId(), list.get(i).getName());
        }
        return infoMap;
    }
}
