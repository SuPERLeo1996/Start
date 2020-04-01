package com.leo.hogwarts.entity.base;

/**
 * @ClassName ResultDTO
 * @Description
 * @Author Leo
 * @Date 2020/3/30 15:42
 */

public class ResultDTO<T> {

    private Integer code;
    private String msg;
    private T data;

    public ResultDTO(T data) {
        this.code = 200;
        this.data = data;
    }

    public ResultDTO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

