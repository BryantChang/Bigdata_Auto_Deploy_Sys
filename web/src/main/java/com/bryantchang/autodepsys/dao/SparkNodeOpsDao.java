package com.bryantchang.autodepsys.dao;

import com.bryantchang.autodepsys.bean.SparkNode;
import com.bryantchang.autodepsys.common.HttpRequest;
import com.bryantchang.autodepsys.constant.Constants;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by bryantchang on 2017/1/30.
 */
@Repository
public class SparkNodeOpsDao {
    public String startSparkCluster() {
        ArrayList<SparkNode> list = new SparkNodeDao().getSparkNodeByRole("master");
        SparkNode master = list.get(0);
        String masterIp = master.getIp();
        String res = HttpRequest.sendGet(masterIp + ":" + Constants.AGENTPORT + "/webapp/nodeops/spark/startcluster", "");
        return res;
    }

    public String stopSparkCluster() {
        ArrayList<SparkNode> list = new SparkNodeDao().getSparkNodeByRole("master");
        SparkNode master = list.get(0);
        String masterIp = master.getIp();
        String res = HttpRequest.sendGet(masterIp + ":" + Constants.AGENTPORT + "/webapp/nodeops/spark/stopcluster", "");
        return res;
    }

    public String sparkNodeOps(String ip, String nodeType, String opsType, String masterIP) {
        String url = ip + ":" + Constants.AGENTPORT +"/webapp/nodeops/spark/" + opsType + nodeType;
        String res = HttpRequest.sendGet(url, "master=" + masterIP);
        return res;
    }

    public String checkNodeOnline(String type, String ip) {
        String res = HttpRequest.sendGet(ip + ":" + Constants.AGENTPORT + "/webapp/nodeops/checknode", "type=" + type);
        return res;
    }


}
