package com.example.smartcity05.bean;

import java.util.List;

public class NewsListBean {

    private int total;
    private int code;
    private String msg;

    private List<NewsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewsBean> getRows() {
        return rows;
    }

    public void setRows(List<NewsBean> rows) {
        this.rows = rows;
    }

}
