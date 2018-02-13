package com.bryantchang.autodepsys.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bryantchang.autodepsys.common.HttpRequest;
import org.springframework.stereotype.Repository;
import com.bryantchang.autodepsys.bean.SparkNode;

@Repository
public class SparkNodeDao extends BaseDAO {
	public SparkNode addNode(SparkNode node) {
		long pk = 0;
		pk = this.insert(node);
		if(pk != 0) {
			node.setId(pk);
			return node;
		}else {
			return null;
		}
	}
	
	public SparkNode updateNode(SparkNode node) {
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
		boolean res = this.deleteData(SparkNode.class, conditions);
		return res;
	}
	
	public ArrayList<SparkNode> getAllSparkNodes() {
		return this.getList(SparkNode.class, null);
	}


	public ArrayList<SparkNode> getSparkNodeByRole(String roleName) {
		if(roleName.equals("all")) {
			return this.getAllSparkNodes();
		}else {
			Map conditions = new HashMap();
			conditions.put(roleName, 1L);
			ArrayList list = new ArrayList<SparkNode>();
			list = this.getList(SparkNode.class, conditions);
			return list;
		}
	}

	public SparkNode getSparkNodeByHost(String host) {
		Map conditions = new HashMap();
		conditions.put("hostname", host);
		ArrayList<SparkNode> list = new ArrayList<SparkNode>();
		list = this.getList(SparkNode.class, conditions);
		SparkNode node = list.get(0);
		return node;
	}


}
