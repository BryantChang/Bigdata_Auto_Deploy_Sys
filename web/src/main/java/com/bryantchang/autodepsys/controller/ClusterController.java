package com.bryantchang.autodepsys.controller;


import com.bryantchang.autodepsys.bean.Cluster;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.ClusterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
@SessionAttributes("clusterlist")
public class ClusterController {
    @Resource
    ClusterService service;

    @RequestMapping("/admin/clustermanage/index")
    public String clusterList(@RequestParam String userid, ModelMap map) {
        ArrayList<Cluster> list = service.getAllClustersByUid(userid);
        return Constants.JSPBASE + "clustermanage/index.jsp";
    }
}
