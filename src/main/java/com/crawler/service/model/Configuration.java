package com.crawler.service.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "configurations")
@AttributeOverride(name = "id", column = @Column(name = "ConfigurationId"))
public class Configuration extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "key_config")
	private String key;
	
	@Column(name = "value_result")
	private String value;
	
	private Boolean isActive;
	
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
}
