package com.crawler.service.model;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "targets")
@AttributeOverride(name = "id", column = @Column(name = "TargetId"))
public class Target extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String domain;
	private String mainUrl;
	private Date inputDate;
	
	@Column(name="IsActive")
	private boolean isActive;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "target")
	private List<Resource> resources;
	
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getInpuDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getMainUrl() {
		return mainUrl;
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}
	
}
