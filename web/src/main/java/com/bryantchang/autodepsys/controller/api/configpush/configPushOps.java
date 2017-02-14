package com.bryantchang.autodepsys.controller.api.configpush;

import com.bryantchang.autodepsys.common.Response;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.ConfigPushService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by bryantchang on 2016/12/24.
 */
@Controller
public class configPushOps {

    @Resource
    ConfigPushService service;

    @ResponseBody
    @RequestMapping(value = "/api/configpush", method = {RequestMethod.POST, RequestMethod.GET})
    public Response uploadFile(@RequestParam(value = "clustertype") String clusterType,
                               @RequestParam(value = "fileindex") String fileIndex,
                               @RequestParam(value = "host") String host) {
        String res = service.uploadFile(host, clusterType, fileIndex);
        Response response = null;
        if(res.equals("succ")) {
            response = new Response(Constants.SUCC, res);
        }else {
            response = new Response(Constants.IOERROR, res);
        }
        return response;
    }


}
