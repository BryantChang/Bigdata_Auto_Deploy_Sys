package com.bryantchang.autodepsys.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bryantchang.autodepsys.bean.HadoopNode;
import com.bryantchang.autodepsys.dao.HadoopNodesDao;

@Service
public class HadoopNodeService {
	@Resource
	HadoopNodesDao dao;
	
	public HadoopNode addNode(String hostname, String ip, 
			String os, String sshport, String sshuser, String sshpass,
			String issudo, String namenode, String datanode, 
			String secondarynamenode, String jobtracker, String tasktracker,
			String isformatted) {
		Integer sshport_int = Integer.valueOf(sshport);
		Boolean issudo_long = Boolean.valueOf(issudo);
		Boolean namenode_long = Boolean.valueOf(namenode);
		Boolean datanode_long = Boolean.valueOf(datanode);
		Boolean secondarynamenode_long = Boolean.valueOf(secondarynamenode);
		Boolean jobtracker_long = Boolean.valueOf(jobtracker);
		Boolean tasktracker_long = Boolean.valueOf(tasktracker);
		Boolean isformatted_long = Boolean.valueOf(isformatted);
		Timestamp ctime = new Timestamp(new Date().getTime());
		Timestamp mtime = new Timestamp(new Date().getTime());
		HadoopNode node = new HadoopNode(0L, hostname, ip, os, sshport_int, sshuser, sshpass, 
				issudo_long, namenode_long, datanode_long, secondarynamenode_long, 
				jobtracker_long, tasktracker_long, isformatted_long, ctime);
		HadoopNode res_node = dao.addNode(node);
		
		return res_node;
	}
	
	
	public HadoopNode updateNode(String id, String hostname, String ip, 
			String os, String sshport, String sshuser, String sshpass,
			String issudo, String namenode, String datanode, 
			String secondarynamenode, String jobtracker, String tasktracker,
			String isformatted) {
		Long id_long = Long.valueOf(id);
		Integer sshport_int = Integer.valueOf(sshport);
		Boolean issudo_long = Boolean.valueOf(issudo);
		Boolean namenode_long = Boolean.valueOf(namenode);
		Boolean datanode_long = Boolean.valueOf(datanode);
		Boolean secondarynamenode_long = Boolean.valueOf(secondarynamenode);
		Boolean jobtracker_long = Boolean.valueOf(jobtracker);
		Boolean tasktracker_long = Boolean.valueOf(tasktracker);
		Boolean isformatted_long = Boolean.valueOf(isformatted);
		Timestamp ctime = new Timestamp(new Date().getTime());
		Timestamp mtime = new Timestamp(new Date().getTime());
		HadoopNode node = new HadoopNode(id_long, hostname, ip, os, sshport_int, sshuser, sshpass, 
				issudo_long, namenode_long, datanode_long, secondarynamenode_long, 
				jobtracker_long, tasktracker_long, isformatted_long, ctime);
		HadoopNode res_node = dao.updateNode(node);
		
		return res_node;
	}
	
	
	public boolean delNode(String id) {
		Long nodeId = Long.valueOf(id);
		boolean res = false;
		res = dao.delNode(nodeId);
		return res;
	}
	
	public ArrayList<HadoopNode> getAllHadoopNodes(){
		ArrayList<HadoopNode> list = dao.getHadoopNodeListByRole("all");
		return list;
	} 
}
