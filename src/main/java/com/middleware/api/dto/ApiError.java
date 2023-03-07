package com.middleware.api.dto;

public class ApiError {

    private String errorMessage;

    private String errorCode;

    private String request;

    private String requestType;

    private String customMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getCustomMessage() {
		return customMessage;
	}

	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}

	public ApiError(String errorMessage, String errorCode, String request, String requestType, String customMessage) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.request = request;
		this.requestType = requestType;
		this.customMessage = customMessage;
	}
	public ApiError (){
		super();
	}
	

}
