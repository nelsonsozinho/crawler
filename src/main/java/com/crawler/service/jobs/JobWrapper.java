package com.crawler.service.jobs;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawler.service.bo.ICrawlerStorageService;
import com.crawler.service.helper.ContentHelper;
import com.crawler.service.model.Configuration;
import com.crawler.service.model.Resource;
import com.crawler.service.model.ResourceType;
import com.crawler.service.model.Target;
import com.crawler.service.request.AbstractVisitor;
import com.crawler.service.request.PageVisitor;
import com.crawler.service.util.ConfigurationKey;


public class JobWrapper implements Job {

	private static final Logger log = LoggerFactory.getLogger(JobWrapper.class);
	
	private Target target;
	private String id;
	private String name;
	
	private AbstractVisitor visitor;
	private ContentHelper helper;
	private List<Configuration> configurations;
	private List<String> urlsVisited;
	private int delay;
	
	private ICrawlerStorageService storageService;
	
	/**
	 * To work it need a target an a set of configurations
	 * 
	 * @param target
	 * @param configurations
	 * @param name
	 * @param storageService
	 */
	public JobWrapper(Target target, List<Configuration> configurations, String name, ICrawlerStorageService storageService) {
		this.target = target;
		this.name = target.getDomain();
		this.visitor = new PageVisitor();
		this.configurations = configurations;
		this.storageService = storageService;
		
		prepareConfigurarions();
		prepare();
	}
	
	/**
	 * Without name
	 * 
	 * @param target
	 * @param configurations
	 * @param storageService
	 */
	public JobWrapper(Target target, List<Configuration> configurations, ICrawlerStorageService storageService) {
		this(target, configurations, Thread.currentThread().getName(), storageService);
	}
	
	/**
	 * Init crawler job for a specific domain
	 */
	public void crowling() {
		deepCrowling(helper);
	}
	
	private void prepareConfigurarions() {
		for(Configuration configuration : configurations) {
			if(configuration.getKey().equals(ConfigurationKey.DELAY.getValue())) {
				delay = Integer.parseInt(configuration.getValue());
				visitor.setTimeout(delay);
			}
		}
	}
	
	/**
	 * Start a initical path
	 */
	private void prepare() {
		String mainUrl = target.getMainUrl();
		this.urlsVisited = new ArrayList<String>();
		
		//home page document
		try {
			Document initialDocument = visitor.getHTMLDocumentContent(mainUrl);
			helper = ContentHelper.BuilderContentHelper.build(initialDocument);
		} catch(MalformedURLException ex) {
			log.error("Error on connect " + helper.getUrl(), ex);
		}  catch(Exception e) {
			log.error("Problems on execute url " + helper.getUrl(), e);
		}
	}
	
	/**
	 * Deepening url access
	 * 
	 * @param helper
	 */
	private void deepCrowling(ContentHelper helper) {
		log.info("Try to get urls from " + helper.getUrl());
		List<String> urls = helper.findUrls(target);
		
		int urlsSize = urls.size();
		while(urlsSize != 0) {
			for(String url : urls) {
				urlsSize--;
				try {
					List<Resource> resources = storageService.findResourceByURL(url);
					if(resources.isEmpty() && (!urlsVisited.contains(url))) {
						Document document = visitor.getHTMLDocumentContent(url);
						if(document != null) {
							urlsVisited.add(url);
							ContentHelper helperDeeped = ContentHelper.BuilderContentHelper.build(document);
							writeContent(helperDeeped);
							deepCrowling(helperDeeped);
						}
					}
				} catch (InterruptedException e) {
					log.error("Error on wait to access the url", e);
				} catch(MalformedURLException ex) {
					log.error("Error on connect " + helper.getUrl(), ex);
				} catch(Exception e) {
					log.error("Problems on execute url " + helper.getUrl(), e);
				}
			}
		}
	}
	
	private void writeContent(ContentHelper helper) {
		Resource resource = new Resource();
		resource.setTarget(target);
		resource.setText(helper.getHTML());
		resource.setTitle(helper.getTitle());
		resource.setType(ResourceType.TEXT);
		resource.setUrl(helper.getUrl());
		
		storageService.storeWebContent(resource);
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	
	@Override
	public void run() {
		crowling();
	}

	@Override
	public Target getTarget() {
		return this.target;
	}
	
	

}
