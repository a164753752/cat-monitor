package com.oriental.finance.cat.monitor.common.model;


import java.io.Serializable;

public class Response<T> implements Serializable {

    private static final long serialVersionUID = 8350327877975282483L;

    /**
     * 表示调用是否成功 ,如果为true,则可以调用getResult
     */
    private boolean success = false;

    /**
     * 获取调用返回值
     */
    private T result;

    /**
     * 业务状态码
     */
    private String responseCode;

    /**
     * 业务状态码描述
     */
    private String responseDesc;

    public Response() {
    }

    public Response(T result) {
        this.success = true;
        this.result = result;
    }

    public Response(String responseCode, String responseDesc) {
        this.success = false;
        this.result = null;
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
    }


    public Response(T result, String responseCode, String responseDesc) {
        this.success = true;
        this.result = result;
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", result=" + result +
                ", responseCode=" + responseCode +
                ", responseDesc=" + responseDesc +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        success = true;
        this.result = result;
    }

    public void setResponse(String errorCode, String errorDesc) {
        this.responseCode = errorCode;
        this.responseDesc = errorDesc;
    }

}
