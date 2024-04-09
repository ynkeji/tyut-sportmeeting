package com.tyut.sport.entity;

public class DataResult {
    private int status;   //响应状态码
    private String msg;   //响应信息
    private Object data;  //响应数据
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
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

    public String toString() {
        return "DataResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
    }
}
