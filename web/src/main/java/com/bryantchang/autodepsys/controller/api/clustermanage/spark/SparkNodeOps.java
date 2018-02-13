package com.bryantchang.autodepsys.controller.api.clustermanage.spark;


import com.bryantchang.autodepsys.bean.HadoopNode;
import com.bryantchang.autodepsys.bean.SparkNode;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.SparkNodeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by bryantchang on 2016/12/2.
 */
@Controller
public class SparkNodeOps {

    @Resource
    SparkNodeService service;

    @ResponseBody
    @RequestMapping(value="/api/clustermanager/spark/addnode", method = {RequestMethod.POST, RequestMethod.GET})
    public Response getResponseAdd(@RequestParam(value="hostname") String hostname,
                                   @RequestParam(value="ip") String ip,
                                   @RequestParam(value="os") String os,
                                   @RequestParam(value="sshuser") String sshuser,
                                   @RequestParam(value="sshpass") String sshpass,
                                   @RequestParam(value="sshport") String sshport,
                                   @RequestParam(value="master") String master,
                                   @RequestParam(value="slave") String slave) {
        SparkNode res = service.addNode(hostname, ip, os, sshport, sshuser, sshpass, "0", master, slave);
        Response response = null;
        if(res != null) {
            response = new Response(Constants.SUCC, "", res);
        }else {
            response = new Response(Constants.ADDERR, "添加失败", res);
        }
        return response;
    }


    @ResponseBody
    @RequestMapping(value="/api/clustermanager/spark/updatenode", method = {RequestMethod.POST, RequestMethod.GET})
    public Response getResponseUpdate(@RequestParam(value="id") String id,
                                      @RequestParam(value="hostname") String hostname,
                                      @RequestParam(value="ip") String ip,
                                      @RequestParam(value="os") String os,
                                      @RequestParam(value="sshuser") String sshuser,
                                      @RequestParam(value="sshpass") String sshpass,
                                      @RequestParam(value="sshport") String sshport,
                                      @RequestParam(value="master") String master,
                                      @RequestParam(value="slave") String slave) {

        SparkNode res = service.updateNode(id, hostname, ip, os, sshport, sshuser, sshpass, "0", master, slave);
        Response response = null;
        if(res != null) {
            response = new Response(Constants.SUCC, "", res);
        }else {
            response = new Response(Constants.UPDATEERR, "添加失败", res);
        }
        return response;
    }


    @ResponseBody
    @RequestMapping(value="/api/clustermanager/spark/delnode", method = {RequestMethod.POST, RequestMethod.GET})
    public Response getResponseDel(@RequestParam(value="id") String id) {
        boolean res = service.delNode(id);
        Response response = null;
        if(res == true) {
            response = new Response(Constants.SUCC, "", null);
        }else {
            response = new Response(Constants.DELERR, "", null);
        }
        return response;
    }

    class Response {
        private long code = 0;
        private String msg = "";
        private SparkNode data = null;
        public Response(){}
        public Response(long code, String msg, SparkNode data) {
            super();
            this.code = code;
            this.msg = msg;
            this.data = data;
        }
        public long getCode() {
            return code;
        }

        public void setCode(long code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public SparkNode getData() {
            return data;
        }

        public void setData(SparkNode data) {
            this.data = data;
        }

    }
}
