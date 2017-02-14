package com.bryantchang.autodepsys.controller;

import com.bryantchang.autodepsys.bean.HadoopNode;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.HadoopNodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by bryantchang on 2017/1/26.
 */
@Controller
@SessionAttributes("hadoopnodelist")
public class HadoopNodeOpsController {
    @Resource
    HadoopNodeService service;

    @RequestMapping("/admin/nodeops/hadoop")
    public String hadoopNodeList(ModelMap map) {
        ArrayList<HadoopNode> list = service.getAllHadoopNodes();
        map.put("hadoopnodelist", list);
        return Constants.JSPBASE + "nodeops/hadoop.jsp";
    }

}
