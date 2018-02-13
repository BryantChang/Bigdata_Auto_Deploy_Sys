package com.bryantchang.autodepsys.common;

import com.bryantchang.autodepsys.constant.Constants;

/**
 * Created by bryantchang on 2017/1/30.
 * api统一返回格式
 */
public class Response {
    private Long code;
    private String msg;
    private Object data;

    public Response(Long code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Response() {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
