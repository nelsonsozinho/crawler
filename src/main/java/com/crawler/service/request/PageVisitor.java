package com.crawler.service.request;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageVisitor extends AbstractVisitor {

	private static final Logger log = LoggerFactory.getLogger(PageVisitor.class);
	
	private Integer timeout;
	
	
	public PageVisitor() {
		this.timeout = 10000;
	}

	public PageVisitor(Integer timeout) throws Exception {
		if(timeout <= 0) throw new Exception("Timeout can't be ZERO or less");
		this.timeout = timeout;
	}
	
	public Document getHTMLDocumentContent(String url) throws MalformedURLException, Exception {
		Document doc = null;
		try {
			Connection conn = Jsoup.connect(url);	
			doc = conn.timeout(timeout).get();
		} catch(MalformedURLException e) {
			log.error("Mal formed url: " + url);
		} catch (IOException e) {
			log.error("Timeout connection exception " + url);
		} 
	
		return doc;
	}
	
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
