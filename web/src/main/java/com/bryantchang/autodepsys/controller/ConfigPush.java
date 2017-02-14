package com.bryantchang.autodepsys.controller;

import com.bryantchang.autodepsys.bean.HadoopNode;
import com.bryantchang.autodepsys.bean.SparkNode;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.HadoopNodeService;
import com.bryantchang.autodepsys.service.SparkNodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by bryantchang on 2016/12/24.
 */
@Controller
@SessionAttributes(types = {ArrayList.class, String.class, String.class},
        value = {"nodelist", "fileindex", "allnodes"}
)
public class ConfigPush {
    @Resource
    HadoopNodeService hadoopService;
    @Resource
    SparkNodeService sparkService;

    @RequestMapping("/admin/configmanage/configpush")
    public String getNodeList(
            @RequestParam(value = "clustertype") String clustertype,
            @RequestParam(value = "fileindex") String fileindex,
            ModelMap map) {
        String resJsp = null;
        if(clustertype.equals("hadoop")) {
            String allNodes = "";
            ArrayList<HadoopNode> list = hadoopService.getAllHadoopNodes();
            for (int i = 0; i < list.size(); i++) {
                allNodes += list.get(i).getHostname() + ",";
            }
            allNodes = allNodes.substring(0, allNodes.length() - 1);
            map.put("nodelist", list);
            map.put("fileindex", fileindex);
            map.put("allnodes", allNodes);
            resJsp = Constants.JSPBASE + "configpush/hadoop.jsp";
        }else if(clustertype.equals("spark")){
            String allNodes = "";
            ArrayList<SparkNode> list = sparkService.getAllSparkNodes();
            for (int i = 0; i < list.size(); i++) {
                allNodes += list.get(i).getHostname() + ",";
            }
            allNodes = allNodes.substring(0, allNodes.length() - 1);
            map.put("nodelist", list);
            map.put("fileindex", fileindex);
            map.put("allnodes", allNodes);
            resJsp = Constants.JSPBASE + "configpush/spark.jsp";
        }
        return resJsp;
    }
}
