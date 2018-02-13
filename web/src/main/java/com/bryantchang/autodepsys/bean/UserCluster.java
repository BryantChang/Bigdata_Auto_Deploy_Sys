package com.bryantchang.autodepsys.bean;

import java.sql.Timestamp;

public class UserCluster {
    private Long id = null;
    private Long userid = null;
    private Long clusterid = null;
    private Timestamp ctime = null;

    public UserCluster() {
    }

    public UserCluster(Long id, Long userid, Long clusterid, Timestamp ctime) {
        this.id = id;
        this.userid = userid;
        this.clusterid = clusterid;
        this.ctime = ctime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getClusterid() {
        return clusterid;
    }

    public void setClusterid(Long clusterid) {
        this.clusterid = clusterid;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "UserCluster{" +
                "id=" + id +
                ", userid=" + userid +
                ", clusterid=" + clusterid +
                ", ctime=" + ctime +
                '}';
    }
}
