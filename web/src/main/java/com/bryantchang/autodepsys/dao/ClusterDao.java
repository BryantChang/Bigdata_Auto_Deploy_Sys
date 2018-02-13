package com.bryantchang.autodepsys.dao;

import com.bryantchang.autodepsys.bean.Cluster;
import com.bryantchang.autodepsys.bean.ClusterInfo;
import com.bryantchang.autodepsys.bean.UserCluster;
import com.bryantchang.autodepsys.common.BaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ClusterDao extends BaseDAO{
    /**
     * getAllClusterIdsByUid 根据用户id获取所有的ClusterId
     * @param userId
     * @return ArrayList<Long> -- 所有的clusterid
     */
    private ArrayList<Long> getAllClusterIdsByUid(String userId) {
        ArrayList<Long> clusterIds = new ArrayList<Long>();
        Connection connection = BaseConnection.getConnection();
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        String sql = "select clusterid from usercluster where userid = " + Long.valueOf(userId);
        try {
            pStatement = connection.prepareStatement(sql);
            rSet = pStatement.executeQuery();
            while(rSet.next()) {
                clusterIds.add(rSet.getLong("clusterid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clusterIds;
    }


    /**
     * getAllClustersByUid 根据用户Id获取所有的Cluster
     * @param userId
     * @return ArrayList<Cluster> 所有的Cluster
     */
    public ArrayList<Cluster> getAllClustersByUid(String userId) {
        ArrayList<Long> clusterIds = getAllClusterIdsByUid(userId);
        String idStr = "";
        for (int i = 0; i < clusterIds.size(); i++) {
            idStr += clusterIds.get(i) + ",";
        }
        idStr = idStr.substring(0, idStr.length()-1);
        Map conditions = new HashMap();
        conditions.put("id", idStr);
        ArrayList<Cluster> clusterList = new ArrayList<Cluster>();
        clusterList = getList(Cluster.class, conditions);
        return clusterList;
    }


    public ArrayList<ClusterInfo> getClusterInfoList() {
        ArrayList<ClusterInfo> list = new ArrayList<ClusterInfo>();
        list = getList(ClusterInfo.class, null);
        return list;
    }

    public Cluster addCluster(Cluster cluster) {
        long primaryKey = 0;
        primaryKey = this.insert(cluster);
        if(primaryKey != 0) {
            cluster.setId(primaryKey);
            return cluster;
        }else {
            return null;
        }
    }

    public UserCluster addUserCluster(UserCluster userCluster) {
        long pk = 0;
        pk = this.insert(userCluster);
        if(pk != 0) {
            userCluster.setId(pk);
            return userCluster;
        }else {
            return null;
        }
    }

//
//    public static void main(String[] args) {
//        String userId = "2";
//        ArrayList<Cluster> clusterList = new ClusterDao().getAllClustersByUid(userId);
////        ArrayList<Long> clusterIds = new ClusterDao().getAllClusterIdsByUid(userId);
//        for (int i = 0; i < clusterList.size(); i++) {
//            System.out.println(clusterList.get(i).getCname());
//        }
////        ArrayList<ClusterInfo> list = new ClusterDao().getClusterInfoList();
////        for (int i = 0; i < list.size(); i++) {
////            System.out.println(list.get(i).getName());
////        }
//    }
}
