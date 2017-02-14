package service;

import dao.NodeOpsDao;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by bryantchang on 2016/12/27.
 */
public class NodeOpsService {
    public String startSparkMaster() {
        return new NodeOpsDao().startSparkMaster();
    }

    public String stopSparkMaster() {
        return new NodeOpsDao().stopSparkMaster();
    }

    public String startSparkSlave(String masterHost, String masterPort) {
        return new NodeOpsDao().startSparkSlave(masterHost, masterPort);
    }

    public String stopSparkSlave() {
        return new NodeOpsDao().stopSparkSlave();
    }

    public String startSparkCluster() {
        return new NodeOpsDao().startSparkCluster();
    }

    public String stopSparkCluster() {
        return new NodeOpsDao().stopSparkCluster();
    }

    public String check(String nodeType) {
        return new NodeOpsDao().check(nodeType);
    }


    public String hadoopHDFSNodeOps(String nodeType, String opsType) {
        return new NodeOpsDao().hadoopHDFSNodeOps(nodeType, opsType);
    }

    public String startHDFSCluster() {
        return new NodeOpsDao().startHDFSCluster();
    }

    public String stopHDFSCluster() {
        return new NodeOpsDao().stopHDFSCluster();
    }

    public String hadoopYARNNodeOps(String nodeType, String opsType) {
        return new NodeOpsDao().hadoopYARNNodeOps(nodeType, opsType);
    }

    public String startYARNCluster() {
        return new NodeOpsDao().startYARNCluster();
    }

    public String stopYARNCluster() {
        return new NodeOpsDao().stopYARNCluster();
    }

}
