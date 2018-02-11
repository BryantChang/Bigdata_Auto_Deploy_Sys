package com.bryantchang.autodepsys.bean;

public class ClusterInfo {
    private Integer id = null;
    private String name = null;

    public ClusterInfo() {
    }

    public ClusterInfo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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
        this.name = name;
    }

}
