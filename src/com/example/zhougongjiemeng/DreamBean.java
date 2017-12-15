package com.example.zhougongjiemeng;

import java.util.List;


public class DreamBean {

	private String reason;
	private List<DreamBeanInner> result;
	private int error_code;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public List<DreamBeanInner> getResult() {
		return result;
	}
	public void setResult(List<DreamBeanInner> result) {
		this.result = result;
	}
	public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	@Override
	public String toString() {
		return "DreamBean [reason=" + reason + ", result=" + result
				+ ", error_code=" + error_code + "]";
	}
	
}
