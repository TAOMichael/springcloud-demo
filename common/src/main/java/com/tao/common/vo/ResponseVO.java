package com.tao.common.vo;

import java.io.Serializable;

/**
 * 返回实体类
 * 
 * @author taoyinwu
 */
public class ResponseVO<T> implements Serializable {

    private static final long serialVersionUID = -5487452925663824040L;

    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String msg;

    /**
     * 返回结果
     */
    private T result;

    public ResponseVO() {

    }

    public ResponseVO(Integer code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public ResponseVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
