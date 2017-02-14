package bean;

import constants.Constants;

import java.io.Serializable;

/**
 * Created by bryantchang on 2016/12/27.
 * 响应信息类,用于回应HTTP请求
 */
public class Response{
    private Long code = Constants.SUCC;
    private String msg = "";

    public Response(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
