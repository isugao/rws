package com.gao.api.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;  // 显式声明序列化版本号（可选但推荐）
    private String id;
    private String name;
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
