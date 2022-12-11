package com.example.smartcity05.bean;

import java.util.List;

public class LevelBean {
    private String servType;
    private List<ServBean> servBeanList;

    public LevelBean(String servType, List<ServBean> servBeanList) {
        this.servType = servType;
        this.servBeanList = servBeanList;
    }

    public String getServType() {
        return servType;
    }

    public void setServType(String servType) {
        this.servType = servType;
    }

    public List<ServBean> getServBeanList() {
        return servBeanList;
    }

    public void setServBeanList(List<ServBean> servBeanList) {
        this.servBeanList = servBeanList;
    }
}
