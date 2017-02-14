package com.bryantchang.autodepsys.controller;

import com.bryantchang.autodepsys.bean.SparkSettings;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.SparkSettingsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by bryantchang on 2016/12/8.
 */
@Controller
@SessionAttributes("sparkconfigfilelist")
public class SparkSettingController {
    @Resource
    SparkSettingsService service;
    @RequestMapping("/admin/configmanage/spark")
    public String sparkConfigFileList(ModelMap map) {
        Map<String, String> sparkFiles = Constants.sparkFiles;
        map.put("sparkconfigfilelist", sparkFiles);

        return Constants.JSPBASE + "configmanage/spark.jsp";
    }

}
