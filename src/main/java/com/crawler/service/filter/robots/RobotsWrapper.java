package com.crawler.service.filter.robots;

import java.util.List;

/**
 * 
 * Wrapper to agroup the sitemaps and disallow rules 
 *
 */
public class RobotsWrapper {

	private String domain;
	private List<String> disallow;
	private List<String> sitemap;
	
	
	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public List<String> getDisallow() {
		return disallow;
	}

	public void setDisallow(List<String> disallow) {
		this.disallow = disallow;
	}
	
	public List<String> getSitemap() {
		return sitemap;
	}
	
	public void setSitemap(List<String> sitemap) {
		this.sitemap = sitemap;
	}
	
}
