package dao;

import common.PropertyParser;
import constants.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by bryantchang on 2016/12/27.
 * 节点操作业务逻辑
 */
public class NodeOpsDao {


    private static String sparkSbinHome = System.getProperty("SPARK_HOME") + Constants.SPARK_SBIN_HOME;
    private static String hadoopSbinHome = System.getProperty("HADOOP_HOME") + Constants.HADOOP_SBIN_HOME;
    private static String hadoopHDFSDaemonScriptPath = hadoopSbinHome + "/hadoop-daemon.sh";
    private static String hadoopYARNDaemonScriptPath = hadoopSbinHome + "/yarn-daemon.sh";
    //公共私有方法--执行linux命令
    private String execLinuxCommands(String cmd) {
        String res = null;
        String []cmds = {"/bin/sh", "-c", cmd};
        try {
            Process pro = Runtime.getRuntime().exec(cmds);
            pro.waitFor();
            InputStream in = pro.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            String line = null;
            StringBuffer buffer = new StringBuffer();
            while ((line = read.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            res = buffer.toString();
            System.out.println(res);
        }catch(Exception e) {
            res = "error";
            return res;
        }
        return res;
    }

    //执行shell脚本
    private String execLinuxScripts(String scriptFile, String[] args) {
        System.out.println("sh" + scriptFile);
        String res = null;
        ArrayList<String> cmdList = new ArrayList<String>();
        cmdList.add("sh");
        cmdList.add(scriptFile);
        String[] cmds = null;
        if(args != null) {
            for (int i = 0; i < args.length; i++) {
                cmdList.add(args[i]);
            }
        }
        int size = cmdList.size();
        cmds = (String[])cmdList.toArray(new String[size]);
        for (int i = 0; i < cmds.length; i++) {
            System.out.println(cmds[i]);
        }
        try {
            Process pro = Runtime.getRuntime().exec(cmds);
            pro.waitFor();
            InputStream in = pro.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            String line = null;
            StringBuffer buffer = new StringBuffer();
            while ((line = read.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            res = buffer.toString();
            System.out.println(res);
        }catch(Exception e) {
            res = "error";
            return res;
        }
        return res;
    }

    //查询节点是否启动的通用方法
    public String check(String nodeType) {
        String cmd = "ps aux | grep " + nodeType + " | grep -v grep | awk \'{print $2}\'";
        System.out.println(cmd);
        return this.execLinuxCommands(cmd);
    }

    //Spark 节点操作
    public String startSparkMaster() {
        String startMasterScriptPath = sparkSbinHome + "/start-master.sh";
        return this.execLinuxScripts(startMasterScriptPath, null);
    }

    public String stopSparkMaster() {
        String stopMasterScriptPath = sparkSbinHome + "/stop-master.sh";
        return this.execLinuxScripts(stopMasterScriptPath, null);
    }

    public String startSparkSlave(String masterHost, String masterPort) {
        String startSlaveScriptPath = sparkSbinHome + "/start-slave.sh";
        String [] args = {"spark://" + masterHost + ":" + masterPort};
        return this.execLinuxScripts(startSlaveScriptPath, args);
    }

    public String stopSparkSlave() {
        String stopSlaveScriptPath = sparkSbinHome + "/stop-slave.sh";
        return this.execLinuxScripts(stopSlaveScriptPath, null);
    }

    public String startSparkCluster() {
        String startClusterScriptPath = sparkSbinHome + "/start-all.sh";
        return this.execLinuxScripts(startClusterScriptPath, null);
    }

    public String stopSparkCluster() {
        String stopClusterScriptPath = sparkSbinHome + "/stop-all.sh";
        return this.execLinuxScripts(stopClusterScriptPath, null);
    }

    //Hadoop 节点操作
    public String hadoopHDFSNodeOps(String nodeType, String opsType) {
        String[] args = {opsType, nodeType};
        return this.execLinuxScripts(hadoopHDFSDaemonScriptPath, args);
    }

    public String hadoopYARNNodeOps(String nodeType, String opsType) {
        String[] args = {opsType, nodeType};
        return this.execLinuxScripts(hadoopYARNDaemonScriptPath, args);
    }

    public String startHDFSCluster() {
        String startHDFSScriptPath = hadoopSbinHome + "/start-dfs.sh";
        return this.execLinuxScripts(startHDFSScriptPath, null);
    }

    public String stopHDFSCluster() {
        String stopHDFSScriptPath = hadoopSbinHome + "/stop-dfs.sh";
        return this.execLinuxScripts(stopHDFSScriptPath, null);
    }

    public String startYARNCluster() {
        String startYARNScriptPath = hadoopSbinHome + "/start-yarn.sh";
        return this.execLinuxScripts(startYARNScriptPath, null);
    }

    public String stopYARNCluster() {
        String stopYARNScriptPath = hadoopSbinHome + "/stop-yarn.sh";
        return this.execLinuxScripts(stopYARNScriptPath, null);
    }




}
