package com.cmdc.interfaces.dto;

import lombok.Data;

@Data
//封装返回结果的类
public class JsonResult<T> {

    private int code;
    private String msg;
    private T data;


    public JsonResult() {
        this.code = 200;
        this.msg = "ok!";
    }


    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public JsonResult(T data) {
        this.data = data;
        this.code = 200;
        this.msg = "ok!";
    }


    public JsonResult(T data, String msg) {
        this.data = data;
        this.code = 200;
        this.msg = msg;
    }

}