package com.bryantchang.autodepsys.controller.api.configmanage.hadoop;

import com.bryantchang.autodepsys.bean.HadoopNode;
import com.bryantchang.autodepsys.bean.HadoopSettings;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.controller.api.clustermanage.AbstarctConfigOps;
import com.bryantchang.autodepsys.service.ConfigFileService;
import com.bryantchang.autodepsys.service.HadoopSettingsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bryantchang on 2016/12/8.
 */
@Controller
public class HadoopConfigOps extends AbstarctConfigOps {
    @Resource
    ConfigFileService service;
    @Resource
    HadoopSettingsService hadoopSettingsService;

    @ResponseBody
    @RequestMapping(value = "/api/configmanage/hadoop/downloadconfig", method = {RequestMethod.POST, RequestMethod.GET})
    public Response getResponseDownload(@RequestParam(value = "clustertype") String clusterType,
                                        @RequestParam(value = "fileindex") String fileindex, HttpServletRequest request,
                                        HttpServletResponse response) {
        String filename = (String) Constants.hadoopFiles.get(fileindex);
        this.downloadFile(filename, clusterType, request, response);
        return null;
    }


    @ResponseBody
    @RequestMapping(value = "/api/configmanage/hadoop/generateconfig", method = {RequestMethod.POST, RequestMethod.GET})
    public Response getResponseGenerate(
            @RequestParam(value = "fileindex") String fileindex
    ) {
        String filename = (String) Constants.hadoopFiles.get(fileindex);
        Long res = Constants.SUCC;
        if (filename.equals("slaves")) {
            res = service.generateHadoopSlaveFile();
        }else {
            res = service.generateHadoopXmlConfigFile(fileindex);
        }
        return new Response(res, "", null);
    }

    @ResponseBody
    @RequestMapping(value = "/api/configmanage/hadoop/generatefromcurrentxml", method = {RequestMethod.POST, RequestMethod.GET})
    public Response addSettingFromCurrentXML(
            @RequestParam(value = "fileindex") String fileindex
    ) {
        String filename = (String) Constants.hadoopFiles.get(fileindex);
        Long res = Constants.SUCC;
        res = service.addHadoopSettingFromXML(fileindex);
        return new Response(res, "", null);
    }

    @ResponseBody
    @RequestMapping(value = "/api/configmanage/hadoop/updatexmlsetting", method = {RequestMethod.POST, RequestMethod.GET})
    public Response getResponseUpdateDefault(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "value") String value,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "filename") String filename) {
        HadoopSettings res = hadoopSettingsService.updateHadoopSetting(id,
                name, value, description, filename);
        Response response = null;
        if (res != null) {
            response = new Response(Constants.SUCC, "", res);
        } else {
            response = new Response(Constants.UPDATEERR, "", res);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/api/configmanage/hadoop/addxmlsetting", method = {RequestMethod.POST, RequestMethod.GET})
    public Response getResponseAddDefault(
            @RequestParam(value="name") String name,
            @RequestParam(value="value") String value,
            @RequestParam(value="description") String description,
            @RequestParam(value="filename") String filename) {
        HadoopSettings res = hadoopSettingsService.addHadoopSettings(name, value,
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
    @RequestMapping(value = "/api/configmanage/hadoop/delxmlsetting", method = {RequestMethod.POST, RequestMethod.GET})
    public Response getResponseDelDefault(
            @RequestParam(value = "id") String id) {
        Boolean res = hadoopSettingsService.delSetting(id);
        Response response = null;
        if(res == true) {
            response = new Response(Constants.SUCC, "", null);
        }else {
            response = new Response(Constants.DELERR, "", null);
        }
        return response;
    }

    static class Response {
        private long code = 0;
        private String msg = "";
        private HadoopSettings setting = null;

        public Response() {
        }

        public Response(long code, String msg, HadoopSettings setting) {
            this.code = code;
            this.setting = setting;
            this.msg = msg;
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

        public HadoopSettings getSetting() {
            return setting;
        }

        public void setSetting(HadoopSettings setting) {
            this.setting = setting;
        }
    }
}
