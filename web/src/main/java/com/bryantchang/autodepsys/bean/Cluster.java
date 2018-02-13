package com.bryantchang.autodepsys.bean;

import java.sql.Timestamp;

public class Cluster {
    private Integer id = null;
    private String cname = null;
    private String cdesc = null;
    private Long infoid = null;
    private Timestamp ctime = null;


    public Cluster() {
    }

    public Cluster(Integer id, String cname, String cdesc, Long infoid, Timestamp ctime) {
        this.id = id;
        this.cname = cname;
        this.cdesc = cdesc;
        this.infoid = infoid;
        this.ctime = ctime;
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
        return "id=" + this.id + ", cname=" + this.cname + ", cdesc=" + this.cdesc + ", infoid=" + this.infoid;
    }
}
