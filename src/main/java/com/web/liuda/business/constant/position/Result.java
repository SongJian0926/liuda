package com.web.liuda.business.constant.position;

public class Result {
	
	private Location location;
	private Integer precise;
	private Integer confidence;
	private String level;
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Integer getPrecise() {
		return precise;
	}
	public void setPrecise(Integer precise) {
		this.precise = precise;
	}
	public Integer getConfidence() {
		return confidence;
	}
	public void setConfidence(Integer confidence) {
		this.confidence = confidence;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "Result [location=" + location + ", precise=" + precise
				+ ", confidence=" + confidence + ", level=" + level + "]";
	}
	

}
