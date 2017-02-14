package com.bryantchang.autodepsys.dao;

import com.bryantchang.autodepsys.bean.HadoopNode;
import com.bryantchang.autodepsys.common.HttpRequest;
import com.bryantchang.autodepsys.constant.Constants;
import org.springframework.stereotype.Repository;

/**
 * Created by bryantchang on 2017/1/30.
 */
@Repository
public class HadoopNodeOpsDao {
    public String startHDFSCluster() {
        String namenodeIP = new HadoopNodesDao().getHadoopNodeListByRole("namenode").get(0).getIp();
        String res = HttpRequest.sendGet(namenodeIP + ":" + Constants.AGENTPORT + "/webapp/nodeops/hadoop/hdfs/startcluster", "");
        return res;
    }

    public String stopHDFSCluster() {
        String namenodeIP = new HadoopNodesDao().getHadoopNodeListByRole("namenode").get(0).getIp();
        String res = HttpRequest.sendGet(namenodeIP + ":" + Constants.AGENTPORT + "/webapp/nodeops/hadoop/hdfs/stopcluster", "");
        return res;
    }

    public String startYARNCluster() {
        String namenodeIP = new HadoopNodesDao().getHadoopNodeListByRole("resourcemanager").get(0).getIp();
        String res = HttpRequest.sendGet(namenodeIP + ":" + Constants.AGENTPORT + "/webapp/nodeops/hadoop/yarn/startcluster", "");
        return res;
    }

    public String stopYARNCluster() {
        String namenodeIP = new HadoopNodesDao().getHadoopNodeListByRole("resourcemanager").get(0).getIp();
        String res = HttpRequest.sendGet(namenodeIP + ":" + Constants.AGENTPORT + "/webapp/nodeops/hadoop/yarn/stopcluster", "");
        return res;
    }

    public String checkNodeOnline(String type, String ip) {
        String res = HttpRequest.sendGet(ip + ":" + Constants.AGENTPORT + "/webapp/nodeops/checknode", "type=" + type);
        return res;
    }

    public String hadoopNodeOps(String ip, String clusterType, String nodeType, String opsType) {
        String url = ip + ":" + Constants.AGENTPORT + "/webapp/nodeops/hadoop/" + clusterType + "/ops";
        String res = HttpRequest.sendGet(url, "nodetype=" + nodeType + "&opstype=" + opsType);
        return res;
    }
}
