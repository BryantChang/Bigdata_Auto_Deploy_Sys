package com.bryantchang.autodepsys.controller;

import com.bryantchang.autodepsys.bean.HadoopNode;
import com.bryantchang.autodepsys.bean.SparkNode;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.HadoopNodeService;
import com.bryantchang.autodepsys.service.SparkNodeOpsService;
import com.bryantchang.autodepsys.service.SparkNodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by bryantchang on 2017/1/31.
 */
@Controller
@SessionAttributes(types = {ArrayList.class, String.class},
        value = {"sparknodelist", "masterip"}
)
public class SparkNodeOpsController {
    @Resource
    SparkNodeService service;


    @RequestMapping("/admin/nodeops/spark")
    public String sparkNodeList(ModelMap map) {
        ArrayList<SparkNode> list = service.getAllSparkNodes();
        map.put("sparknodelist", list);
        String masterIp = service.getSparkNodeByRole("master").get(0).getIp();
        map.put("masterip", masterIp);
        return Constants.JSPBASE + "nodeops/spark.jsp";
    }
}
