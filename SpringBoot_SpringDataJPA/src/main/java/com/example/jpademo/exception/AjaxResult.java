package com.example.jpademo.exception;

import lombok.Data;

@Data
public class AjaxResult<T> {
    private Integer code = 1;
    private String msg = "成功";
    private T data;

    public AjaxResult() {
    }

    public AjaxResult(String msg) {
        this.msg = msg;
    }

    public AjaxResult(T data) {
        this.data = data;
    }

    public AjaxResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public AjaxResult(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }
}
