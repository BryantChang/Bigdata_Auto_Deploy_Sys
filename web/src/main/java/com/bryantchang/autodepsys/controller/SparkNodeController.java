package com.bryantchang.autodepsys.controller;

import com.bryantchang.autodepsys.bean.HadoopNode;
import com.bryantchang.autodepsys.bean.SparkNode;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.SparkNodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by bryantchang on 2016/12/2.
 */
@Controller
@SessionAttributes("sparknodelist")
public class SparkNodeController {
    @Resource
    SparkNodeService service;

    @RequestMapping("/admin/clustermanage/spark")
    public String SparkNodeList(ModelMap map) {
        ArrayList<SparkNode> list = service.getAllSparkNodes();
        map.put("sparknodelist", list);
//		System.out.println(list.size());
        return Constants.JSPBASE + "clustermanage/spark.jsp";
    }
}
