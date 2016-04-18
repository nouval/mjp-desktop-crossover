package com.journalpublication.model;

import com.journalpublication.AppConfig;

/**
 * POJO holds credential
 * @author nouval
 *
 */
public class Credential {
	private String username;
	private String token;
	
	public Credential() {
		this(AppConfig.getInstance().getToken(), AppConfig.getInstance().getUsername());
	}
	
	public Credential(String username, String token) {
		this.token = username;
		this.username = token;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setToken (String token) {
		this.token = token;
	}
	
	public String getToken() {
		return this.token;
	}
}
