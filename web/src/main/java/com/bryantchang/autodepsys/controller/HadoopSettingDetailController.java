package com.bryantchang.autodepsys.controller;

import com.bryantchang.autodepsys.bean.HadoopSettings;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.HadoopSettingsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by bryantchang on 2016/12/18.
 */
@Controller
@SessionAttributes(types = {ArrayList.class, String.class},
        value = {"hadoop_setting_list", "filename"})
public class HadoopSettingDetailController {
    @Resource
    HadoopSettingsService service;

    @RequestMapping("/admin/configmanage/hadoop/coresite")
    public String HadoopCoresiteList(ModelMap map) {
        ArrayList<HadoopSettings> settings = service.getHadoopSettingByFileName("core-site.xml");
        map.put("hadoop_setting_list", settings);
        map.put("filename", "core-site.xml");
        return Constants.JSPBASE + "configmanage/detail/hadoopcoresite.jsp";
    }

    @RequestMapping("/admin/configmanage/hadoop/mapredsite")
    public String HadoopMapredList(ModelMap map) {
        ArrayList<HadoopSettings> settings = service.getHadoopSettingByFileName("mapred-site.xml");
        map.put("hadoop_setting_list", settings);
        map.put("filename", "mapred-site.xml");
        return Constants.JSPBASE + "configmanage/detail/hadoopmapredsite.jsp";
    }

    @RequestMapping("/admin/configmanage/hadoop/hdfssite")
    public String HadoopHdfssiteList(ModelMap map) {
        ArrayList<HadoopSettings> settings = service.getHadoopSettingByFileName("hdfs-site.xml");
        map.put("hadoop_setting_list", settings);
        map.put("filename", "hdfs-site.xml");
        return Constants.JSPBASE + "configmanage/detail/hadoophdfssite.jsp";
    }

    @RequestMapping("/admin/configmanage/hadoop/yarnsite")
    public String HadoopYarnsiteList(ModelMap map) {
        ArrayList<HadoopSettings> settings = service.getHadoopSettingByFileName("yarn-site.xml");
        map.put("hadoop_setting_list", settings);
        map.put("filename", "yarn-site.xml");
        return Constants.JSPBASE + "configmanage/detail/hadoopyarnsite.jsp";
    }

}
