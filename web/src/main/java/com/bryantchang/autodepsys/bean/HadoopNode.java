package com.bryantchang.autodepsys.bean;

import java.sql.Timestamp;

/**
 * 
 * @author bryantchang
 * Hadoop节点实体类
 */

public class HadoopNode {
	private Long id = null;
	private String hostname = null;
	private String ip = null;
	private String os = null;
	private Integer sshport = null;
	private String sshuser = null;
	private String sshpass = null;
	private Boolean issudo = null;
	private Boolean namenode = null;
	private Boolean datanode = null;
	private Boolean secondarynamenode = null;
	private Boolean resourcemanager = null;
	private Boolean nodemanager = null;
	private Boolean isformatted = null;
	private Timestamp ctime = null;

	public HadoopNode() {

	}

	public HadoopNode(Long id, String hostname, String ip, String os, Integer sshport, String sshuser, String sshpass, Boolean issudo, Boolean namenode, Boolean datanode, Boolean secondarynamenode, Boolean resourcemanager, Boolean nodemanager, Boolean isformatted, Timestamp ctime) {
		this.id = id;
		this.hostname = hostname;
		this.ip = ip;
		this.os = os;
		this.sshport = sshport;
		this.sshuser = sshuser;
		this.sshpass = sshpass;
		this.issudo = issudo;
		this.namenode = namenode;
		this.datanode = datanode;
		this.secondarynamenode = secondarynamenode;
		this.resourcemanager = resourcemanager;
		this.nodemanager = nodemanager;
		this.isformatted = isformatted;
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

	public Boolean getIssudo() {
		return issudo;
	}

	public void setIssudo(Boolean issudo) {
		this.issudo = issudo;
	}

	public Boolean getNamenode() {
		return namenode;
	}

	public void setNamenode(Boolean namenode) {
		this.namenode = namenode;
	}

	public Boolean getDatanode() {
		return datanode;
	}

	public void setDatanode(Boolean datanode) {
		this.datanode = datanode;
	}

	public Boolean getSecondarynamenode() {
		return secondarynamenode;
	}

	public void setSecondarynamenode(Boolean secondarynamenode) {
		this.secondarynamenode = secondarynamenode;
	}

	public Boolean getResourcemanager() {
		return resourcemanager;
	}

	public void setResourcemanager(Boolean resourcemanager) {
		this.resourcemanager = resourcemanager;
	}

	public Boolean getNodemanager() {
		return nodemanager;
	}

	public void setNodemanager(Boolean nodemanager) {
		this.nodemanager = nodemanager;
	}

	public Boolean getIsformatted() {
		return isformatted;
	}

	public void setIsformatted(Boolean isformatted) {
		this.isformatted = isformatted;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "Nodes--" + id + "ip:" + ip;
	}

}
