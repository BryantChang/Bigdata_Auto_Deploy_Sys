package com.bryantchang.autodepsys.controller;

import com.bryantchang.autodepsys.bean.SparkSettings;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.SparkSettingsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by bryantchang on 2016/12/11.
 */
@Controller
@SessionAttributes("sparkdefaultsettinglist")
public class SparkSettingDetailController {
    @Resource
    SparkSettingsService service;

    @RequestMapping("/admin/configmanage/spark/sparkdefault")
    public String SparkDefaultList(ModelMap map) {
        ArrayList<SparkSettings> settings = service.getAllSparkSettings();
        map.put("sparkdefaultsettinglist", settings);
        return Constants.JSPBASE + "configmanage/detail/sparkdefault.jsp";
    }
}
