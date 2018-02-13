package com.bryantchang.autodepsys.bean;

import java.sql.Timestamp;

public class Cluster {
    private Long id = null;
    private String cname = null;
    private String cdesc = null;
    private Long infoid = null;
    private Timestamp ctime = null;


    public Cluster() {
    }


    public Cluster(Long id, String cname, String cdesc, Long infoid, Timestamp ctime) {
        this.id = id;
        this.cname = cname;
        this.cdesc = cdesc;
        this.infoid = infoid;
        this.ctime = ctime;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
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
        return "Cluster{" +
                "id=" + id +
                ", cname='" + cname + '\'' +
                ", cdesc='" + cdesc + '\'' +
                ", infoid=" + infoid +
                ", ctime=" + ctime +
                '}';
    }
}
