package com.example.smartcity05.bean;

public class ServBean {
    private int _id;
    private int type;
    private String servName;
    private int servImg;

    public ServBean() {
    }

    public ServBean(int type, String servName, int servImg) {
        this.type = type;
        this.servName = servName;
        this.servImg = servImg;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getServName() {
        return servName;
    }

    public void setServName(String servName) {
        this.servName = servName;
    }

    public int getServImg() {
        return servImg;
    }

    public void setServImg(int servImg) {
        this.servImg = servImg;
    }
}
