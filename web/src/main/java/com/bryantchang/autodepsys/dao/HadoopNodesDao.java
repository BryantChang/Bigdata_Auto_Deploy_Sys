package com.bryantchang.autodepsys.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bryantchang.autodepsys.bean.HadoopNode;

@Repository
public class HadoopNodesDao extends BaseDAO {
	public ArrayList<HadoopNode> getAllHadoopNodes() {
		return this.getList(HadoopNode.class, null);
	}
	
	public ArrayList<HadoopNode> getAllDataNodes() {
		Map conditions = new HashMap();
		conditions.put("datanode", 1L);
		ArrayList list = new ArrayList<HadoopNode>();
		list = this.getList(HadoopNode.class, conditions);
		return list;
	}
	
	public ArrayList<HadoopNode> getAllNameNodes() {
		Map conditions = new HashMap();
		conditions.put("namenode", 1L);
		ArrayList list = new ArrayList<HadoopNode>();
		list = this.getList(HadoopNode.class, conditions);
		return list;
	}

	public ArrayList<HadoopNode> getAllResourceManagers() {
		Map conditions = new HashMap();
		conditions.put("resourcemanager", 1L);
		ArrayList list = new ArrayList<HadoopNode>();
		list = this.getList(HadoopNode.class, conditions);
		return list;
	}


	public ArrayList<HadoopNode> getAllNodeManagers() {
		Map conditions = new HashMap();
		conditions.put("nodemanager", 1L);
		ArrayList list = new ArrayList<HadoopNode>();
		list = this.getList(HadoopNode.class, conditions);
		return list;
	}

	public HadoopNode getHadoopNodeByHost(String host) {
		Map conditions = new HashMap();
		conditions.put("hostname", host);
		ArrayList<HadoopNode> list =  new ArrayList<HadoopNode>();
		list = this.getList(HadoopNode.class, conditions);
		HadoopNode node = list.get(0);
		return node;
	}

	public ArrayList<HadoopNode> getHadoopNodeListByRole(String roleName) {
		if(roleName.equals("all")) {
			return this.getList(HadoopNode.class, null);
		}else {
			Map conditions = new HashMap();
			conditions.put(roleName, 1L);
			ArrayList list = new ArrayList<HadoopNode>();
			list = this.getList(HadoopNode.class, conditions);
			return list;
		}

	}
	
	public HadoopNode addNode(HadoopNode node){
		boolean res = false;
		res = this.insert(node);
		if(res == true) {
			return node;
		}else {
			return null;
		}
	} 
	
	public HadoopNode updateNode(HadoopNode node) {
		boolean res = false;
		res = this.update(node);
		if(res == true) {
			return node;
		}else {
			return null;
		}
	}
	
	public boolean delNode(Long nodeId) {
		Map conditions = new HashMap();
		conditions.put("id", nodeId);
		boolean res = this.deleteData(HadoopNode.class, conditions);
		return res;
	}	
}
