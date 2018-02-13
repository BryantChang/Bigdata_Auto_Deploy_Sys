package com.bryantchang.autodepsys.controller.api.configmanage.spark;

import com.bryantchang.autodepsys.bean.SparkSettings;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.controller.api.clustermanage.AbstarctConfigOps;
import com.bryantchang.autodepsys.service.ConfigFileService;
import com.bryantchang.autodepsys.service.SparkSettingsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bryantchang on 2016/12/8.
 */
@Controller
public class SparkConfigOps extends AbstarctConfigOps{
    @Resource
    ConfigFileService service;
    @Resource
    SparkSettingsService sparkDefaultService;

    @ResponseBody
    @RequestMapping(value="/api/configmanage/spark/downloadconfig", method = {RequestMethod.POST, RequestMethod.GET})
    public Response getResponseDownload(@RequestParam(value="clustertype") String clusterType,
                                        @RequestParam(value="fileindex") String fileindex, HttpServletRequest request,
                                        HttpServletResponse response) {
        String filename = (String) Constants.sparkFiles.get(fileindex);
        this.downloadFile(filename, clusterType, request, response);
        return null;
    }

    @ResponseBody
     @RequestMapping(value="/api/configmanage/spark/generateconfig", method = {RequestMethod.POST, RequestMethod.GET})
     public Response getResponseGenerate(@RequestParam(value="fileindex") String fileindex) {
        String filename = (String)Constants.sparkFiles.get(fileindex);
        Long res = Constants.SUCC;
        if(filename.equals("slaves")) {
            res = service.generateSparkSlaveFile();
        }else if(filename.equals("spark-defaults.conf")) {
            res = service.generateSparkDefaultFile();
        }
        return new Response(res, "", null);
    }

    @ResponseBody
    @RequestMapping(value="/api/configmanage/spark/addfromcurfile", method = {RequestMethod.POST, RequestMethod.GET})
    public Response addSettingFromCurFile(@RequestParam(value="fileindex") String fileindex) {
        String filename = (String)Constants.sparkFiles.get(fileindex);
        Long res = Constants.SUCC;
        res = service.addSparkSettingFromCurFile();
        return new Response(res, "", null);
    }

    @ResponseBody
    @RequestMapping(value="/api/configmanage/spark/updatedefault", method = {RequestMethod.POST, RequestMethod.GET})
    public Response getResponseUpdateDefault(
            @RequestParam(value="id") String id,
            @RequestParam(value="name") String name,
            @RequestParam(value="value") String value,
            @RequestParam(value="description") String description,
            @RequestParam(value="filename") String filename) {
        SparkSettings res = sparkDefaultService.updateSparkSetting(id,
                 name ,value, description, filename);
        Response response = null;
        if(res != null) {
            response = new Response(Constants.SUCC, "", res);
        }else {
            response = new Response(Constants.UPDATEERR, "", res);
        }
        return response;
    }


    @ResponseBody
    @RequestMapping(value="/api/configmanage/spark/adddefault", method = {RequestMethod.POST, RequestMethod.GET})
    public Response getResponseAddDefault(
            @RequestParam(value="name") String name,
            @RequestParam(value="value") String value,
            @RequestParam(value="description") String description,
            @RequestParam(value="filename") String filename) {
        SparkSettings res = sparkDefaultService.addSparkSettings(name, value,
                description, filename);
        Response response = null;
        if(res != null) {
            response = new Response(Constants.SUCC, "", res);
        }else {
            response = new Response(Constants.ADDERR, "", res);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/api/configmanage/spark/deldefault", method = {RequestMethod.POST, RequestMethod.GET})
    public Response getResponseDelDefault(
            @RequestParam(value = "id") String id) {
        Boolean res = sparkDefaultService.delSetting(id);
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
        private SparkSettings settings = null;

        public Response() {
        }

        public Response(long code, String msg, SparkSettings settings) {
            this.code = code;
            this.msg = msg;
            this.settings = settings;
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

        public SparkSettings getSettings() {
            return settings;
        }

        public void setSettings(SparkSettings settings) {
            this.settings = settings;
        }
    }
}
