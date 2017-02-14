package com.bryantchang.autodepsys.service;

import com.bryantchang.autodepsys.dao.HadoopNodeOpsDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by bryantchang on 2017/1/31.
 */
@Service
public class HadoopNodeOpsService {
    @Resource
    HadoopNodeOpsDao dao;

    public String startHDFSCluster() {
        return dao.startHDFSCluster();
    }

    public String stopHDFSCluster() {
        return dao.stopHDFSCluster();
    }

    public String startYARNCluster() {
        return dao.startYARNCluster();
    }

    public String stopYARNCluster() {
        return dao.stopYARNCluster();
    }

    public String checkNodeOnline(String type, String ip) {
        return dao.checkNodeOnline(type, ip);
    }

    public String hadoopNodeOps(String ip, String clusterType, String nodeType, String opsType) {
        return dao.hadoopNodeOps(ip, clusterType, nodeType, opsType);
    }
}
