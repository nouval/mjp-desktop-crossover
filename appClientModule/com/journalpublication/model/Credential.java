package com.journalpublication.model;

import com.journalpublication.AppConfig;

public class Credential {
	private String username;
	private String token;
//	private Date expiry;
	
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
	
//	@JsonIgnore
//	public boolean isAuthenticated() {
//		
//		boolean tokenIsExpired = true;
//		
//		try {
//			// TODO: client should not have ablity to crack open token.
//			// best way to do this is utilizing service-poll and see if token still valid
//			Date expiry = Jwts.parser()
//				.setSigningKey(AppConfig.getInstance().getApiKey())
//				.parseClaimsJws(this.getToken())
//				.getBody()
//				.getExpiration();
//			
//			tokenIsExpired = expiry.before(Calendar.getInstance().getTime());
//			
//		} catch (Exception ex) {
//		}
//		
//		return !tokenIsExpired;
//	}
	
//	public Date getExpiry() {
//		return this.expiry;
//	}
	
//	public void setExpiry(Date expiry) {
//		this.expiry = expiry;
//	}
}
