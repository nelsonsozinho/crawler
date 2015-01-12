package com.crawler.service.main;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.crawler.service.bo.IConfigurationService;
import com.crawler.service.bo.ICrawlerStorageService;
import com.crawler.service.jobs.Job;
import com.crawler.service.jobs.JobProcess;
import com.crawler.service.jobs.JobWrapper;
import com.crawler.service.model.Configuration;
import com.crawler.service.model.Target;

@Component
public class MainStartted {

	private static final Logger log = LoggerFactory.getLogger(MainStartted.class);
	
	@Autowired
	private ICrawlerStorageService service;
	
	@Autowired
	private IConfigurationService configService; 
	
	
	public static void main(String...strings) {
		final ApplicationContext  ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		final MainStartted config = ctx.getBean(MainStartted.class);
		log.info("Applications is working");
		config.applicationContext();
	}
	
	public void applicationContext() {
		List<JobProcess> jobs = prepareJobs();
		for(JobProcess job : jobs) {
			Thread thread = new Thread(job);
			thread.setName(job.getTargetName());
			thread.start();
		}
	}
	
	private List<JobProcess> prepareJobs() {
		List<JobProcess> jobs = new ArrayList<JobProcess>();
		List<Target> targets = service.listActiveTargets();
		List<Configuration> configurations = configService.listActiveConfigurations();
		for(Target target : targets) {
			Job job = new JobWrapper(target, configurations, service);
			jobs.add(new JobProcess(job, configurations));
		}
		return jobs;
	}
	
}
