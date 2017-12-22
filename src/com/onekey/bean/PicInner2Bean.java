package com.onekey.bean;


public class PicInner2Bean {
	String title;
	String picUrl;
	String description;
	String ctime;
	String url;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "PicInner2Bean [title=" + title + ", picUrl=" + picUrl
				+ ", description=" + description + ", ctime=" + ctime
				+ ", url=" + url + "]";
	}
}
