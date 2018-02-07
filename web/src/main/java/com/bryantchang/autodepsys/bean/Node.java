package com.bryantchang.autodepsys.bean;

import java.sql.Timestamp;

public class Node {
    private Long id = null;
    private String hostname = null;
    private String ip = null;
    private String os = null;
    private Integer sshport = null;
    private String sshuser = null;
    private String sshpass = null;
    private Timestamp ctime = null;


    public Node() {
    }

    public Node(Long id, String hostname, String ip, String os, Integer sshport, String sshuser, String sshpass, Timestamp ctime) {
        this.id = id;
        this.hostname = hostname;
        this.ip = ip;
        this.os = os;
        this.sshport = sshport;
        this.sshuser = sshuser;
        this.sshpass = sshpass;
        this.ctime = ctime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Integer getSshport() {
        return sshport;
    }

    public void setSshport(Integer sshport) {
        this.sshport = sshport;
    }

    public String getSshuser() {
        return sshuser;
    }

    public void setSshuser(String sshuser) {
        this.sshuser = sshuser;
    }

    public String getSshpass() {
        return sshpass;
    }

    public void setSshpass(String sshpass) {
        this.sshpass = sshpass;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }
}
