package com.crawler.service.util;

public enum ConfigurationKey {

	DELAY("REQUEST_WAIT");
	
	private String type;
	
	ConfigurationKey(String type){
		this.type = type;
	}
	
	public String getValue() {
		return type;
	}
	
}
