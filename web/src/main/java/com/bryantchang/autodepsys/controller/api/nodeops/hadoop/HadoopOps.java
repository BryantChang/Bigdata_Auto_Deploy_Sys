package com.bryantchang.autodepsys.controller.api.nodeops.hadoop;

import com.bryantchang.autodepsys.common.Response;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.dao.HadoopNodesDao;
import com.bryantchang.autodepsys.service.HadoopNodeOpsService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by bryantchang on 2017/1/31.
 */
@Controller
public class HadoopOps {
    @Resource
    HadoopNodeOpsService service;


    @ResponseBody
    @RequestMapping(value="/api/nodeops/hadoop/hdfs/startcluster", method = {RequestMethod.POST, RequestMethod.GET})
    public Response startHDFSClusterResponse()  {
        String res = service.startHDFSCluster();
        Response response = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            response = mapper.readValue(res, Response.class);
        } catch (IOException e) {
            response = new Response(Constants.IOERROR, e.toString());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/api/nodeops/hadoop/hdfs/stopcluster", method = {RequestMethod.POST, RequestMethod.GET})
    public Response stopHDFSClusterResponse()  {
        String res = service.stopHDFSCluster();
        Response response = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            response = mapper.readValue(res, Response.class);
        } catch (IOException e) {
            response = new Response(Constants.IOERROR, "");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/api/nodeops/hadoop/yarn/startcluster", method = {RequestMethod.POST, RequestMethod.GET})
    public Response startYARNClusterResponse()  {
        String res = service.startYARNCluster();
        Response response = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            response = mapper.readValue(res, Response.class);
        } catch (IOException e) {
            response = new Response(Constants.IOERROR, "");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/api/nodeops/hadoop/yarn/stopcluster", method = {RequestMethod.POST, RequestMethod.GET})
    public Response stopYARNClusterResponse()  {
        String res = service.stopYARNCluster();
        Response response = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            response = mapper.readValue(res, Response.class);
        } catch (IOException e) {
            response = new Response(Constants.IOERROR, "");
        }
        return response;
    }


    @ResponseBody
    @RequestMapping(value="/api/nodeops/hadoop/checknode", method = {RequestMethod.POST, RequestMethod.GET})
    public Response checkNodeResponse(@RequestParam(value="type") String type,
                                      @RequestParam(value="ip") String ip)  {
        String res = service.checkNodeOnline(type, ip);
        Response response = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            response = mapper.readValue(res, Response.class);
        } catch (IOException e) {
            response = new Response(Constants.IOERROR, "");
        }
        return response;
    }


    @ResponseBody
    @RequestMapping(value="/api/nodeops/hadoop/ops", method = {RequestMethod.POST, RequestMethod.GET})
    public Response HadoopNodeOpsResponse(@RequestParam(value="clustertype") String clusterType,
                                      @RequestParam(value="ip") String ip,
                                      @RequestParam(value="nodetype") String nodeType,
                                      @RequestParam(value="opstype") String opsType)  {
        String res = service.hadoopNodeOps(ip, clusterType, nodeType, opsType);
        Response response = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            response = mapper.readValue(res, Response.class);
        } catch (IOException e) {
            response = new Response(Constants.IOERROR, "");
        }
        return response;
    }

}
