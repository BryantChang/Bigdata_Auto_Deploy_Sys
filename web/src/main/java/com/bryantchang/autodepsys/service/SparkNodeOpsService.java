package com.bryantchang.autodepsys.service;

import com.bryantchang.autodepsys.dao.SparkNodeOpsDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by bryantchang on 2017/1/30.
 */
@Service
public class SparkNodeOpsService {
    @Resource
    SparkNodeOpsDao dao;


    public String startSparkCluster() {
        return dao.startSparkCluster();
    }

    public String stopSparkCluster() {
        return dao.stopSparkCluster();
    }

    public String sparkNodeOps(String ip, String nodeType, String opsType, String masterIP) {
        return dao.sparkNodeOps(ip, nodeType, opsType, masterIP);
    }

    public String checkNodeOnline(String type, String ip) {
        return dao.checkNodeOnline(type, ip);
    }
}
