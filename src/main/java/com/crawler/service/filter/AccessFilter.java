package com.crawler.service.filter;

import java.net.MalformedURLException;

/**
 * Interface used to filter the visit way by the rules from robots.txt
 */
public interface AccessFilter {

	public boolean urlCanVisit(String url) throws MalformedURLException;
	
}
