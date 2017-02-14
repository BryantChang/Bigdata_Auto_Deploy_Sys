package com.bryantchang.autodepsys.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bryantchang.autodepsys.bean.HadoopNode;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.HadoopNodeService;

@Controller
@SessionAttributes("hadoopnodelist")
public class HadoopNodeController {
	@Resource
	HadoopNodeService service;
	
	@RequestMapping("/admin/clustermanage/hadoop")
	public String hadoopNodeList(ModelMap map) {
		ArrayList<HadoopNode> list = service.getAllHadoopNodes();
		map.put("hadoopnodelist", list);
		return Constants.JSPBASE + "clustermanage/hadoop.jsp";
	} 
	
}
