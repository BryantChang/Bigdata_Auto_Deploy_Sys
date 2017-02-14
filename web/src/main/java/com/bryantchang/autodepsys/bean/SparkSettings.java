package com.bryantchang.autodepsys.bean;

public class SparkSettings {
	private Long id = null;
	private String name = null;
	private String value = null;
	private String description = null;
	private String filename = null;
	public SparkSettings() {}
	public SparkSettings(Long id, String name, String value, String description, String filename) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.description = description;
		this.filename = filename;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
