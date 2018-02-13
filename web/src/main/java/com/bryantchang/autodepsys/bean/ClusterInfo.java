package com.bryantchang.autodepsys.bean;

public class ClusterInfo {
    private Long id = null;
    private String name = null;

    public ClusterInfo() {
    }

    public ClusterInfo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
