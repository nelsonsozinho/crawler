package com.crawler.service.model;

public enum ResourceType {

	IMAGE(1), 
	VIDEO(2), 
	TEXT(3), 
	UNDESCRIBE(4);
	
	private int type;
	
	ResourceType(int type){
		this.type = type;
	}
	
	public int getValue() {
		return type;
	}
}
