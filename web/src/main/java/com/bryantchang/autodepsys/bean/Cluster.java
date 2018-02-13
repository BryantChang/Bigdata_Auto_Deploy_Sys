package com.bryantchang.autodepsys.bean;

import java.sql.Timestamp;

public class Cluster {
    private Integer id = null;
    private String cname = null;
    private String des = null;
    private Long infoid = null;
    private Timestamp ctime = null;


    public Cluster() {
    }

    public Cluster(Integer id, String cname, String des, Long infoid, Timestamp ctime) {
        this.id = id;
        this.cname = cname;
        this.des = des;
        this.infoid = infoid;
        this.ctime = ctime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Long getInfoid() {
        return infoid;
    }

    public void setInfoid(Long infoid) {
        this.infoid = infoid;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }


    @Override
    public String toString() {
        return "id=" + this.id + ", cname=" + this.cname + ", des=" + this.des + ", infoid=" + this.infoid;
    }
}
