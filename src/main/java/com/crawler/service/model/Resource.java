package com.crawler.service.model;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "resources")
@AttributeOverride(name = "id", column = @Column(name = "ResourceId"))
public class Resource extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(columnDefinition="text", length=999)
	private String url;
	
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "title")
	private String title;
		
	@Type(type = "org.hibernate.type.TextType") 
	@Column(name = "html_content")
	private String text;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "category_type")
	private ResourceType type;
	
	@ManyToOne()
	@JoinColumn(name = "TargetId")
	private Target target;
		
	
	
	public String getUrl() {
		return url;
	}
	
	public ResourceType getType() {
		return type;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getText() {
		return text;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setType(ResourceType type) {
		this.type = type;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

}
