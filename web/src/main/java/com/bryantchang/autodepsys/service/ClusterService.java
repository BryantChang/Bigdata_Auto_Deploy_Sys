package com.bryantchang.autodepsys.service;


import com.bryantchang.autodepsys.bean.Cluster;
import com.bryantchang.autodepsys.dao.ClusterDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class ClusterService {
    @Resource
    ClusterDao dao;

    public ArrayList<Cluster> getAllClustersByUid(String userId) {
        ArrayList<Cluster> list = dao.getAllClustersByUid(userId);
        return list;
    }
}
