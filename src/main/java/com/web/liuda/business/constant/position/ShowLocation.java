package com.web.liuda.business.constant.position;

public class ShowLocation {
	
	private Integer status;
	private Result result;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "ShowLocation [status=" + status + ", result=" + result + "]";
	}

}
