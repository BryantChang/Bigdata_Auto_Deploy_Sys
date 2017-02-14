package main;

import common.PropertyParser;
import org.apache.commons.cli.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.servlet.GrizzlyWebContainerFactory;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;
import resource.NodeInfoResource;
import resource.NodeOpsResource;

import java.net.InetAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by bryantchang on 2016/12/27.
 */
public class App {

    public static void Usage() {
        System.out.println("usage of this application: ./App <-h/-f (property file)>");
    }

    public static void main(String[] args) {
        //parse usage
        CommandLineParser parser = new BasicParser();
        Options options = new Options();
        options.addOption("h", "help", false, "Print this usage information");
        options.addOption("f", "file", true, "File to save program output to");
        options.addOption("a", true, "ip address");
        CommandLine commandLine = null;
        try {
            commandLine = parser.parse( options, args );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String filePath = "";
        String ipAddress = "";

        if( commandLine.hasOption('h') ) {
            Usage();
            System.exit(-1);
        }
        if( commandLine.hasOption('f') ) {
            filePath = commandLine.getOptionValue('f');
        }
        if( commandLine.hasOption('a') ) {
            ipAddress = commandLine.getOptionValue('a');
        }
        if(filePath == null || filePath.equals("")) {
            Usage();
            System.exit(-1);
        }
        InetAddress ia = null;
        try {
            Properties properties = PropertyParser.parseProperties(filePath);
            String sparkHome = properties.getProperty("SPARK_HOME");
            String hadoopHome = properties.getProperty("HADOOP_HOME");
            String port = properties.getProperty("AGENT_PORT");
            String sparkMasterPort = properties.getProperty("SPARK_MASTER_PORT");
            if(port == null || port.equals("")) {
                port = "8080";
            }
            if(sparkHome != null) {
                System.setProperty("SPARK_HOME", sparkHome);
            }
            if(hadoopHome != null) {
                System.setProperty("HADOOP_HOME", hadoopHome);
            }
            if(sparkMasterPort == null || sparkMasterPort.equals("")) {
                sparkMasterPort = "7077";
                System.setProperty("SPARK_MASTER_PORT", sparkMasterPort);
            }
            final URI BASE_URI = URI.create("http://" + ipAddress + ":" + port + "/webapp/");
            Map<String, String> initParams = new HashMap<String, String>();
            initParams.put(ServerProperties.PROVIDER_PACKAGES, NodeInfoResource.class.getPackage().getName());
            initParams.put(ServerProperties.PROVIDER_PACKAGES, NodeOpsResource.class.getPackage().getName());
            final HttpServer server = GrizzlyWebContainerFactory.create(BASE_URI, ServletContainer.class, initParams);
            while (true){}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
