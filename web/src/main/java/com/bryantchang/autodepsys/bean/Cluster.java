package com.bryantchang.autodepsys.bean;

import java.sql.Timestamp;

public class Cluster {
    private Integer id = null;
    private String cname = null;
    private String desc = null;
    private Long infoid = null;
    private Timestamp ctime = null;


    public Cluster() {
    }

    public Cluster(Integer id, String cname, String desc, Long infoid, Timestamp ctime) {
        this.id = id;
        this.cname = cname;
        this.desc = desc;
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
        return desc;
    }

    public void setDes(String des) {
        this.desc = des;
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
        return "id=" + this.id + ", cname=" + this.cname + ", des=" + this.desc + ", infoid=" + this.infoid;
    }
}
