package com.bryantchang.autodepsys.common;

import com.bryantchang.autodepsys.constant.Constants;

/**
 * Created by bryantchang on 2017/1/30.
 * api统一返回格式
 */
public class Response {
    private Long code;
    private String msg;

    public Response(Long code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
