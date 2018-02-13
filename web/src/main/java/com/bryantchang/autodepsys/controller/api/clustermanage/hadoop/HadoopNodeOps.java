package com.bryantchang.autodepsys.controller.api.clustermanage.hadoop;

import javax.annotation.Resource;

import com.bryantchang.autodepsys.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bryantchang.autodepsys.bean.HadoopNode;
import com.bryantchang.autodepsys.service.HadoopNodeService;
/**
 * Created by bryantchang on 2017/2/1.
 * hadoop 节点管理接口
 * /api/clustermanager/hadoop
 */
@Controller
public class HadoopNodeOps {
	@Resource
	HadoopNodeService service;

	//添加Hadoop节点
	@ResponseBody
	@RequestMapping(value="/api/clustermanager/hadoop/addnode", method = {RequestMethod.POST, RequestMethod.GET})
	public Response getResponseAdd(@RequestParam(value="hostname") String hostname,
			 @RequestParam(value="ip") String ip, 
			 @RequestParam(value="os") String os,
			 @RequestParam(value="sshuser") String sshuser,
			 @RequestParam(value="sshpass") String sshpass,
			 @RequestParam(value="sshport") String sshport,
			 @RequestParam(value="namenode") String namenode,
			 @RequestParam(value="datanode") String datanode,
			 @RequestParam(value="secondarynamenode") String secondarynamenode,
			 @RequestParam(value="resourcemanager") String resourcemanager,
			 @RequestParam(value="nodemanager") String nodemanager) {
		HadoopNode res = service.addNode(hostname, ip, os, sshport, sshuser, sshpass, 
				"0", namenode, datanode, secondarynamenode, resourcemanager, nodemanager, "1");
		
		Response response = null;
		if(res != null) {
			response = new Response(Constants.SUCC, "", res);
		}else {
			response = new Response(Constants.ADDERR, "添加失败", res);
		}
		return response;
	}
	
	

	//更新Hadoop节点
	@ResponseBody
	@RequestMapping(value="/api/clustermanager/hadoop/updatenode", method = {RequestMethod.POST, RequestMethod.GET})
	public Response getResponseUpdate(@RequestParam(value="id") String id,
			 @RequestParam(value="hostname") String hostname,
			 @RequestParam(value="ip") String ip, 
			 @RequestParam(value="os") String os,
			 @RequestParam(value="sshuser") String sshuser,
			 @RequestParam(value="sshpass") String sshpass,
			 @RequestParam(value="sshport") String sshport,
			 @RequestParam(value="namenode") String namenode,
			 @RequestParam(value="datanode") String datanode,
			 @RequestParam(value="secondarynamenode") String secondarynamenode,
			 @RequestParam(value="resourcemanager") String resourcemanager,
			 @RequestParam(value="nodemanager") String nodemanager) {
		HadoopNode res = service.updateNode(id, hostname, ip, os, sshport, sshuser, sshpass, 
				"0", namenode, datanode, secondarynamenode, resourcemanager, nodemanager, "1");
		
		Response response = null;
		if(res != null) {
			response = new Response(Constants.SUCC, "", res);
		}else {
			response = new Response(Constants.UPDATEERR, "添加失败", res);
		}
		return response;
	}

	//删除Hadoop节点
	@ResponseBody
	@RequestMapping(value="/api/clustermanager/hadoop/delnode", method = {RequestMethod.POST, RequestMethod.GET})
	public Response getResponseDel(@RequestParam(value="id") String id) {
		boolean res = service.delNode(id);
		Response response = null;
		if(res == true) {
			response = new Response(Constants.SUCC, "", null);
		}else {
			response = new Response(Constants.DELERR, "", null);
		}
		return response;
	}
	
	
	class Response {
		private long code = 0;
		private String msg = "";
		private HadoopNode data = null;
		public Response(){}
		public Response(long code, String msg, HadoopNode data) {
			super();
			this.code = code;
			this.msg = msg;
			this.data = data;
		}
		public long getCode() {
			return code;
		}

		public void setCode(long code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public HadoopNode getData() {
			return data;
		}

		public void setData(HadoopNode data) {
			this.data = data;
		}

	}
}
