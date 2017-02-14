package com.bryantchang.autodepsys.controller;

import com.bryantchang.autodepsys.bean.HadoopNode;
import com.bryantchang.autodepsys.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bryantchang on 2016/12/3.
 * 配置管理
 */
@Controller
@SessionAttributes("hadoopconfigfilelist")
public class HadoopSettingController {


    @RequestMapping("/admin/configmanage/hadoop")
    public String hadoopConfigFileList(ModelMap map) {
        Map<String, String> hadoopFiles = Constants.hadoopFiles;
        map.put("hadoopconfigfilelist", hadoopFiles);

        return Constants.JSPBASE + "configmanage/hadoop.jsp";
    }



}
