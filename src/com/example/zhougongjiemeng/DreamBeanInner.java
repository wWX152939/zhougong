package com.example.zhougongjiemeng;

import java.util.List;


public class DreamBeanInner {

	private String id;
	private String title;
	private String des;
	private List<String> list;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	@Override
	public String toString() {
		return "DreamBeanInner [id=" + id + ", title=" + title + ", des=" + des
				+ ", list=" + list + "]";
	}
	
}
