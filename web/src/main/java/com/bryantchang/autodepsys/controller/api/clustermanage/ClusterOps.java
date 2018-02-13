package com.bryantchang.autodepsys.controller.api.clustermanage;


import com.bryantchang.autodepsys.bean.Cluster;
import com.bryantchang.autodepsys.common.Response;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.service.ClusterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author bryantchang
 */
@Controller
public class ClusterOps {
    @Resource
    ClusterService service;
    @ResponseBody
    @RequestMapping(value="/api/clustermanager/addcluster", method = {RequestMethod.POST, RequestMethod.GET})
    public Response addCluster(@RequestParam(value="clustername") String clustername,
                               @RequestParam(value="desc") String desc,
                               @RequestParam(value="infoid") String infoid){
        Cluster res = service.addCluster(clustername, desc, infoid);
        Response response = null;
        if (res != null) {
            return new Response(Constants.SUCC, "", res);
        }else {
            return new Response(Constants.ADDERR, "", null);
        }
    }
}


