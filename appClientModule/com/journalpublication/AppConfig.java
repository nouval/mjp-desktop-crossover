package com.journalpublication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.journalpublication.model.Journal;

public class AppConfig {
	
	private PropertiesConfiguration config = null;
	private static AppConfig instance = null;
	
	private AppConfig() {
		try {
			config = new PropertiesConfiguration("config.properties");
			config.setAutoSave(true);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static AppConfig getInstance() {
		if (instance == null) {
			instance = new AppConfig();
		}
		
		return instance;
	}
	
	public String getApiUrl() {
		return this.getProperty("apiUrl");
	}
	
	public void setApiUrl(String apiUrl) {
		this.setProperty("apiUrl", apiUrl);
	}
	
	public String getApiKey() {
		return this.getProperty("apiKey");
	}
	
	public void setApiKey(String apiKey) {
		this.setProperty("apiKey", apiKey);
	}

	private void setProperty(String key, String value) {
		if (config != null) {
			config.setProperty(key, value);
		}
	}

	private String getProperty(String key) {
		if (config != null) {
			return (String)config.getProperty(key);
		}
		
		return null;
	}

	public void setUsername(String username) {
		this.setProperty("username", username);
	}

	public String getUsername() {
		return this.getProperty("username");
	}
	
	public void setToken(String token) {
		this.setProperty("token", token);
	}
	
	public String getToken() {
		return this.getProperty("token");
	}

	public void setExpiry(Date expiry) {
		this.setProperty("expiry", Long.toString(expiry.getTime()));
	}
	
	public ArrayList<Journal> getListOfSubscribedJournals() {
		
		ArrayList<Journal> journals = new ArrayList<Journal>(); 

		try {
			
			ObjectMapper mapper = new ObjectMapper();
			ArrayList<Journal> parserdJournals = mapper.readValue(this.getProperty("subscribedJournals"), 
					mapper.getTypeFactory().constructCollectionType(List.class, Journal.class)); 
			journals.addAll(parserdJournals);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return journals;
	}
	
	public void setListOfSubscribedJournals(java.util.ArrayList<Journal> journals) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(journals);
			this.setProperty("subscribedJournals", jsonInString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Date getExpiry() {
		try {
			long time = Long.parseLong(this.getProperty("expiry"));
			return new Date(time);
		} catch (Exception e) {
			return new Date(0);
		}
	}
}
