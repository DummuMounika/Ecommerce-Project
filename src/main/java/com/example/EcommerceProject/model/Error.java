package com.example.EcommerceProject.model;

public class Error {
	
	private String errorMsg;
	private String status;
	
	public String getError() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Error(String errorMsg, String status) {
		super();
		this.errorMsg = errorMsg;
		this.status = status;
	}
	
	public Error() {
		super();
	}
	
	
	
	
	

}
