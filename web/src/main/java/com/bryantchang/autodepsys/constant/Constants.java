package com.bryantchang.autodepsys.constant;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Constants {

    //mysql configurations
    public static final String MYSQL_URL = "jdbc:mysql://centos26:3306/autosys";
    public static final String MYSQL_USR = "root";
    public static final String MYSQL_PASS = "123456";

    //project configurations
    public static final String CATALINA_HOME =  "/home/bryantchang/allfiles/tools/apache-tomcat-8.5.8";
    public static final String ROOT_DIR = CATALINA_HOME + "/webapps/autodeploymentsys";
    public static final String RANDOM_CODE_KEY = "RANDOM_CODE_KEY";
    public static final String JSPBASE = "/WEB-INF/jsp/";
    public static final String SYS_DIR = "/sys";
    public static final String CONFIG_TPL = "config_templets";
    public static final String CURR_CONFIG = "current_config";
    public static final String BaseUrl = "http://192.168.1.105:8080/autodeploymentsys";
    public static final String HADOOP_HOME = "/home/bryantchang/allfiles/platforms/hadoop-2.7.0";
    public static final String SPARK_HOME = "/home/bryantchang/allfiles/platforms/spark-2.0.0-bin-hadoop2.7";


    //hadoop config file name
    public static final String HADOOPSLAVES = "slaves";
    public static final String CORESITE = "core-site.xml";
    public static final String HDFSSITE = "hdfs-site.xml";
    public static final String MAPREDSITE = "mapred-site.xml";
    public static final String YARNSITE = "yarn-site.xml";

    //api of Spark
    public static final String STARTMASTER = "/nodeops/spark/startmaster";
    public static final String STOPMASTER = "/nodeops/spark/stopmaster";
    public static final String STARTSLAVE = "/nodeops/spark/startslave";
    public static final String STOPSLAVE = "/nodeops/spark/stopslave";
    public static final String STARTSPARKCLUSTER = "/nodeops/spark/startall";
    public static final String STOPSPARKCLUSTER = "/nodeops/spark/stopall";


    //api of HDFS
    public static final String HDFSOPS = "/nodeops/hadoop/hdfs/ops";

    //api of YARN
    public static final String YARNOPS = "/nodeops/hadoop/yarn/ops";

    //api of check node status
    public static final String CHECKSTATUS = "/nodeops/checknode";

    public static final String AGENTPORT = "8090";

    //hadoop config map
    public static final Map hadoopFiles = new HashMap<String, String>(){{
        put("slaves", Constants.HADOOPSLAVES);
        put("coresite", Constants.CORESITE);
        put("hdfssite", Constants.HDFSSITE);
        put("mapredsite", Constants.MAPREDSITE);
        put("yarnsite", Constants.YARNSITE);
    }};

    //spark config file name
    public static final String SPARKSLAVES = "slaves";
    public static final String SPARKDEFAULT = "spark-defaults.conf";

    //spark config map
    public static final Map sparkFiles = new HashMap<String, String>(){{
        put("slaves", Constants.SPARKSLAVES);
        put("sparkdefault", Constants.SPARKDEFAULT);
    }};


    //For global succ is 100000L
    public static final Long SUCC = 100000L;
    //CURD ERR
    public static final Long ADDERR = 100001L;
    public static final Long UPDATEERR = 100002L;
    public static final Long DELERR = 100003L;

    //Not login
    public static final Long NOTLOGIN = 600001L;

    //file writer exceptions
    public static final Long IOERROR = 500001L;

    public static final Long FILENOTFOUND = 500002L;

    public static final Long KB = 1024L;







}
