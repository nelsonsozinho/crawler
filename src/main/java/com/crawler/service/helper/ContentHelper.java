package com.crawler.service.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawler.service.model.Target;

public class ContentHelper {

	private Document document;
	
	private Pattern patter = Pattern.compile("((?:https?\\:\\/\\/|www\\.)(?:[-a-z0-9]+\\.)*[-a-z0-9]+.*)");
	private Pattern mailPatter = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
	
	private ContentHelper(Document document) {
		this.document = document;
	}
	
	public String getHTML() {
		String out = "";
		out = document.html();
		return out;
	}
	
	public List<String> findUrls() {
		List<String> urls = new ArrayList<String>();
		Elements aElement = document.select("a");
		for(Element e : aElement) {
			String url = e.attr("href");
			Matcher matcher = patter.matcher(url);
			if(matcher.find())
				urls.add(url);
		}
		
		return urls;
	}
	
	public List<String> findUrls(Target domain) {
		List<String> urls = new ArrayList<String>();
		Elements aElement = document.select("a");
		for(Element e : aElement) {
			String url = e.attr("href");
			if(!urls.contains(url)) {
				if(isURL(url) && !isOutOfDomain(url, domain.getDomain(), domain.getMainUrl())) {
					urls.add(url);
				} else if(!isEmail(url)) {
					if(!url.isEmpty() && !isOutOfDomain(url, domain.getDomain(), domain.getMainUrl())) {
						urls.add(domain.getMainUrl() + "/" + url);
					}
				} else if(hasLinkWithHash(url) && !isOutOfDomain(url, domain.getDomain(), domain.getMainUrl())) {
					urls.add(domain.getMainUrl() + "/" + url);
				} else if(urlWithoutHTTP(url) && !isOutOfDomain(url, domain.getDomain(), domain.getMainUrl())) {
					url = "http://"+url;
				}
			}
		}
		
		return urls;
	}
	
	/**
	 * Check if the url has http protocol
	 * @param url
	 * @return
	 */
	private boolean urlWithoutHTTP(String url) {
		if(url.toLowerCase().contains("http://") || url.toLowerCase().contains("https://")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check if has a link with hashtag
	 * @param url
	 * @return
	 */
	private boolean hasLinkWithHash(String url) {
		if(url.contains("#")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check if the urls is out of domain
	 * 
	 * @param url
	 * @param domain
	 * @param mainUrl
	 * @return
	 */
	private boolean isOutOfDomain(String url, String domain, String mainUrl) {
		if(url.contains(domain)) {
			mainUrl =  mainUrl.replace("http://www.", "");
			if(url.contains(mainUrl)) {
				return false;
			} 
			
		}
		return true;
	}
	
	/**
	 * If an url is valid
	 * 
	 * @param url
	 * @return
	 */
	private boolean isURL(String url) {
		Matcher matcher = patter.matcher(url);
		if(matcher.find()) {
			return true;
		}
		return false;
	}
	
	/**
	 * If the url is an email
	 * 
	 * @param email
	 * @return
	 */
	private boolean isEmail(String email) {
		if(email.toLowerCase().contains("mailto:")) {
			return true;
		} else {
			Matcher matcher = patter.matcher(email);
			if(matcher.find()) {
				return true;
			}
		}
		return false;
	}
	
	public String getTitle() {
		Elements element = this.document.getElementsByTag("title");
		return element.toString();
	}
	
	public String getUrl() {
		return this.document.baseUri();
	}
	
	public static final class BuilderContentHelper
	{
		public static ContentHelper build(Document document) {
			return new ContentHelper(document);
		}
	}

}
