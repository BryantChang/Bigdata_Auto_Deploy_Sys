package com.bryantchang.autodepsys.service;

import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.dao.ConfigPushDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by bryantchang on 2016/12/24.
 */
@Service
public class ConfigPushService {
    @Resource
    ConfigPushDao dao;
    public String uploadFile(String host, String clusterType, String fileIndex) {
        String res = null;
        String fileName = null;
        String filePath = null;
        if(clusterType.equals("hadoop")) {
            fileName = (String)Constants.hadoopFiles.get(fileIndex);
        }else if(clusterType.equals("spark")) {
            fileName = (String)Constants.sparkFiles.get(fileIndex);
        }
        filePath = Constants.ROOT_DIR +
                Constants.SYS_DIR + "/" +
                Constants.CONFIG_TPL + "/" + clusterType + "/" + fileName;
        res = dao.uploadFile(host, clusterType, filePath);
        return res;
    }

}
