package com.demo.spring.exception;


public class CustomException {
	
	private String message;
	private int statusCode;
	private String code;
	
	public CustomException(String message, int statusCode, String code) {
		this.message = message;
		this.statusCode = statusCode;
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public static class BadRequest {
		
		public static CustomException getResponse(String message) {
			return new CustomException(message, 400, "Bad Request");
		}
	}
}
