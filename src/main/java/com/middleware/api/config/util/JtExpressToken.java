package com.middleware.api.config.util;


public class JtExpressToken{
	private String token;
	private String cookie;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public JtExpressToken(String token, String cookie) {
		super();
		this.token = token;
		this.cookie = cookie;
	}
	public JtExpressToken() {
		super();			
	}
	
}
