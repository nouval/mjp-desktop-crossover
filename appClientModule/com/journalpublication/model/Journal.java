package com.journalpublication.model;

public class Journal {

	private String subject;
	private String filename;
	private String id;
	private String tags;
//	private String version;
	private byte[] content;
//	private String userId;

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getSubject() {
		return this.subject;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getFilename() {
		return this.filename;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public String getTags() {
		return this.tags;
	}
	
	public byte[] getContent() {
		return this.content;
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return this.getSubject();
	}	
}
