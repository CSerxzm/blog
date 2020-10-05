package com.xzm.bean;

import java.io.Serializable;

public class Tag implements Serializable {
    private Integer id;

    private String name;

    private Integer blogNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(Integer blogNum) {
        this.blogNum = blogNum;
    }
}