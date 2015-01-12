package com.crawler.service.request;

import java.net.MalformedURLException;

import org.jsoup.nodes.Document;

public abstract class AbstractVisitor {
	
	private Integer timeout;
	
	public abstract Document getHTMLDocumentContent(String url) throws MalformedURLException, Exception;
	
	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		if(timeout == 0) {
			this.timeout = 1000;
		}
		else {
			this.timeout = timeout;
		}
	}
	
}
