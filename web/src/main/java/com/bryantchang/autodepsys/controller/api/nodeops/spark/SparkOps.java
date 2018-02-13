package com.bryantchang.autodepsys.controller.api.nodeops.spark;

import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.SparkNodeOpsService;
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
public class SparkOps {
    @Resource
    SparkNodeOpsService service;

    @ResponseBody
    @RequestMapping(value="/api/nodeops/spark/startcluster", method = {RequestMethod.POST, RequestMethod.GET})
    public Response startSparkClusterResponse()  {
        String res = service.startSparkCluster();
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
    @RequestMapping(value="/api/nodeops/spark/stopcluster", method = {RequestMethod.POST, RequestMethod.GET})
    public Response stopSparkClusterResponse()  {
        String res = service.stopSparkCluster();
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
    @RequestMapping(value="/api/nodeops/spark/checknode", method = {RequestMethod.POST, RequestMethod.GET})
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
    @RequestMapping(value="/api/nodeops/spark/ops", method = {RequestMethod.POST, RequestMethod.GET})
    public Response sparkOpsResponse(@RequestParam(value="ip") String ip,
                                      @RequestParam(value="nodetype") String nodeType,
                                      @RequestParam(value="opstype") String opsType,
                                     @RequestParam(value="masterip") String masterIp)  {
        String res = service.sparkNodeOps(ip, nodeType, opsType, masterIp);
        Response response = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            response = mapper.readValue(res, Response.class);
        } catch (IOException e) {
            response = new Response(Constants.IOERROR, e.toString());
        }
        return response;
    }



}
