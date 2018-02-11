package com.bryantchang.autodepsys.controller;

import com.bryantchang.autodepsys.bean.Cluster;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.ClusterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class ClusterController extends AbstractController{
    @Resource
    ClusterService service;
    @RequestMapping("/admin/clustermanage/index")
    public String clusterIndex(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        String userId = "";
        String resPath = "";
        userId = this.checkUser(request, response);
        if("".equals(userId)) {
            resPath = "redirect:/";
        }else {
            ArrayList<Cluster> clusterList = service.getAllClustersByUid(userId);
            HashMap<Integer, String> infoMap = getClusterInfoMap();
            map.put("clusterlist", clusterList);
            map.put("clusterinfomap", infoMap);
            resPath = Constants.JSPBASE + "/clustermanage/index.jsp";
        }
        return resPath;
    }

}
