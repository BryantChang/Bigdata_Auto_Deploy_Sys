package com.bryantchang.autodepsys.service;


import com.bryantchang.autodepsys.bean.Cluster;
import com.bryantchang.autodepsys.dao.ClusterDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Service
public class ClusterService {
    private static Logger logger = Logger.getLogger(ClusterService.class);
    @Resource
    ClusterDao dao;

    public ArrayList<Cluster> getAllClustersByUid(String userId) {
        ArrayList<Cluster> list = dao.getAllClustersByUid(userId);
        return list;
    }


    public Cluster addCluster(String clustername, String desc, String infoidStr){
        String cname = clustername;
        Long infoid = Long.valueOf(infoidStr);
        Timestamp ctime = new Timestamp(new Date().getTime());
        Cluster cluster = new Cluster(0, cname, desc, infoid, ctime);
        logger.info(cluster);
        Cluster res = dao.addCluster(cluster);
        return res;
    }
}
