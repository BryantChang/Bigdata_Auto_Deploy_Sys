package com.bryantchang.autodepsys.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bryantchang.autodepsys.bean.HadoopNode;
import com.bryantchang.autodepsys.bean.SparkNode;
import com.bryantchang.autodepsys.dao.SparkNodeDao;

@Service
public class SparkNodeService {
    @Resource
    SparkNodeDao dao;

    public boolean delNode(String id) {
        Long nodeId = Long.valueOf(id);
        boolean res = false;
        res = dao.delNode(nodeId);
        return res;
    }

    public ArrayList<SparkNode> getAllSparkNodes() {
        ArrayList<SparkNode> list = dao.getAllSparkNodes();
        return list;
    }

    public ArrayList<SparkNode> getSparkNodeByRole(String roleName) {
        ArrayList<SparkNode> list = dao.getSparkNodeByRole(roleName);
        return list;
    }

    public SparkNode addNode(String hostname, String ip,
                             String os, String sshport, String sshuser, String sshpass,
                             String issudo, String master, String slave) {
        Integer sshport_int = Integer.valueOf(sshport);
        Boolean issudo_bool = Boolean.valueOf(issudo);
        Boolean master_bool = Boolean.valueOf(master);
        Boolean slave_bool = Boolean.valueOf(slave);
        Timestamp ctime = new Timestamp(new Date().getTime());
        Timestamp mtime = new Timestamp(new Date().getTime());
        SparkNode node = new SparkNode(0L, hostname, ip, os,
                sshport_int, sshuser, sshpass, issudo_bool, master_bool, slave_bool,
                ctime);
        SparkNode res_node = dao.addNode(node);
        return res_node;
    }

    public SparkNode updateNode(String id, String hostname, String ip,
                             String os, String sshport, String sshuser, String sshpass,
                             String issudo, String master, String slave) {
        Long id_long = Long.valueOf(id);
        Integer sshport_int = Integer.valueOf(sshport);
        Boolean issudo_bool = Boolean.valueOf(issudo);
        Boolean master_bool = Boolean.valueOf(master);
        Boolean slave_bool = Boolean.valueOf(slave);
        Timestamp ctime = new Timestamp(new Date().getTime());
        Timestamp mtime = new Timestamp(new Date().getTime());
        SparkNode node = new SparkNode(id_long, hostname, ip, os,
                sshport_int, sshuser, sshpass, issudo_bool, master_bool, slave_bool,
                ctime);
        SparkNode res_node = dao.updateNode(node);
        return res_node;
    }


}
