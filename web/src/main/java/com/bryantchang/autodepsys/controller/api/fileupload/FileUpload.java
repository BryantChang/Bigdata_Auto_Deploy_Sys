package com.bryantchang.autodepsys.controller.api.fileupload;

import com.bryantchang.autodepsys.common.Response;
import com.bryantchang.autodepsys.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by bryantchang on 2017/2/1.
 * 文件上传API  /api/fileupload
 */
@Controller
public class FileUpload {
    @ResponseBody
    @RequestMapping("/fileupload")
    public Response fileUpload(@RequestParam("myfile") MultipartFile uploadFile, HttpServletRequest request) {
        String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/node_list/";
        String filename = uploadFile.getOriginalFilename();
        Response response = null;
        File dir = new File(uploadUrl);
        if(!dir.exists()) {
            dir.mkdir();
        }

        File targetFile = new File(uploadUrl + filename);

        if(!targetFile.exists()) {
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                response = new Response(Constants.IOERROR, e.toString());
                return response;
            }
        }

        try {
            uploadFile.transferTo(targetFile);
            response = new Response(Constants.SUCC, "upload to" + targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            response = new Response(Constants.IOERROR, e.toString());
            return response;
        }
        return response;

    }

}
