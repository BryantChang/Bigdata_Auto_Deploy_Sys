package com.bryantchang.autodepsys.dao;

import com.bryantchang.autodepsys.bean.HadoopNode;
import com.bryantchang.autodepsys.bean.SparkNode;
import com.bryantchang.autodepsys.common.PropertyParser;
import com.bryantchang.autodepsys.common.SftpUtils;
import com.bryantchang.autodepsys.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created by bryantchang on 2016/12/25.
 */
@Repository
public class ConfigPushDao {
    @Resource
    SparkNodeDao sparkDao;
    @Resource
    HadoopNodesDao hadoopDao;
    private static final Logger log = LoggerFactory.getLogger(ConfigPushDao.class);
    public String uploadFile(String host, String clusterType, String filePath) {
        Properties properties = PropertyParser.parseProperties();
        String res = null;
        String remoteDir = null;
        if(clusterType.equals("hadoop")) {
            HadoopNode node = hadoopDao.getHadoopNodeByHost(host);
            remoteDir = Constants.HADOOP_HOME + "/etc/hadoop";
            if(node == null) {
                res = "node not found";
                return res;
            }
            res = SftpUtils.getInstance().upload(node.getIp(), node.getSshport(), node.getSshuser(), node.getSshpass(), remoteDir, filePath);
        }else if(clusterType.equals("spark")) {
            SparkNode node = sparkDao.getSparkNodeByHost(host);
            remoteDir = Constants.SPARK_HOME + "/conf";
            if(node == null) {
                res = "node not found";
                return res;
            }
            res = SftpUtils.getInstance().upload(node.getIp(), node.getSshport(), node.getSshuser(), node.getSshpass(), remoteDir, filePath);
        }
        return res;
    }
}
