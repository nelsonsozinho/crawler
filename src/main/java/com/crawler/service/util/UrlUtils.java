package com.crawler.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtils {

	private static final String REGEX_DOMAIN = "(www.-)?[A-z]*(.com|.co.uk|.us|.org|.net|.mobi)";
	private static final String REGEX_URL_SPLIT = "(?:([^\\:]*)\\:\\/\\/)?(?:([^\\:\\@]*)"
			+ "(?:\\:([^\\@]*))?\\@)?(?:([^\\/\\:]*)\\.(?=[^\\.\\/\\:]*\\.[^\\.\\/\\:]*))"
			+ "?([^\\.\\/\\:]*)(?:\\.([^\\/\\.\\:]*))?(?:\\:([0-9]*))?(\\/[^\\?#]*"
			+ "(?=.*?\\/)\\/)?([^\\?#]*)?(?:\\?([^#]*))?(?:#(.*))?";
	
	private static Pattern patternDomain = Pattern.compile(REGEX_DOMAIN);
	private static Pattern patternSplit = Pattern.compile(REGEX_URL_SPLIT);
	
	
	public static String getDomain(String url) {
		Matcher matcher = patternDomain.matcher(url);
		String domain = null;
		while(matcher.find()) {
			domain = matcher.group();
		}
		
		if(domain.contains(".")) {
			String[] split = domain.split("\\.");
			domain = split[0];
		}
		
		return domain;
	}

	public static String getPath(String url) {
		Matcher matcher = patternSplit.matcher(url);
		StringBuffer buffer = new StringBuffer();
		while(matcher.find()) {
			int count = matcher.groupCount();
			for(int i=0;i<count;i++) {
				String found = matcher.group(i+1);
				if(found != null) {
					if(found.contains("/")) {
						buffer.append(found);
					} else if(found.contains(".")) {
						buffer.append(found);
					} else if(found.contains("?")) {
						buffer.append(found);
					} else if(found.contains("#")) {
						buffer.append(found);
					} else if(found.contains("+")) {
						buffer.append(found);
					} else if(found.contains("&")) {
						buffer.append(found);
					} else if(found.contains("=")) {
						buffer.append(found);
					}
				}
			}
		}
		return buffer.toString();
	}
	
}
