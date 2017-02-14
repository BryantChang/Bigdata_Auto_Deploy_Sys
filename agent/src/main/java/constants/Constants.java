package constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bryantchang on 2016/12/27.
 * 程序中的各种常量定义
 */
public class Constants {
    //codes
    public static final Long SUCC = 100000L;//succ
    public static final Long ERR = 100001L;//error

    public static final String HADOOP_SBIN_HOME = "/sbin";
    public static final String HADOOP_CONFIG_DIR = "/etc/hadoop";
    public static final String HADOOP_BIN_HOME = "/bin";

    public static final String SPARK_SBIN_HOME = "/sbin";
    public static final String SPARK_CONFIG_DIR = "/conf";
    public static final String SPARK_BIN_HOME = "/bin";


    public static final String SPARK_MASTER = "Master";
    public static final String SPARK_SLAVE = "Worker";

    public static final String HDFS_NAMENODE = "NameNode";
    public static final String HDFS_DATANODE = "DataNode";
    public static final String HDFS_SECONDARYNAMENODE = "SecondaryNameNode";

    public static final String YARN_RESOURCEMANAGER = "ResourceManager";
    public static final String YARN_NODEMANAGER = "NodeManager";

    //hadoop config map
    public static final Map nodeTypeMap = new HashMap<String, String>(){{
        put("master", "Master");
        put("slave", "Worker");
        put("namenode", "NameNode");
        put("datanode", "DataNode");
        put("secondarynamenode", "SecondaryNameNode");
        put("resourcemanager", "ResourceManager");
        put("nodemanager", "NodeManager");
    }};



}
