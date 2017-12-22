package com.onekey.bean;

import java.util.List;


public class PicInnerBean {
	List<PicInner2Bean> newslist;
	String msg;
	int code;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<PicInner2Bean> getNewslist() {
		return newslist;
	}

	public void setNewslist(List<PicInner2Bean> newslist) {
		this.newslist = newslist;
	}

	@Override
	public String toString() {
		return "PicInnerBean [newslist=" + newslist + ", msg=" + msg
				+ ", code=" + code + "]";
	}
}
