package com.crawler.service.jobs;

import java.util.List;

import com.crawler.service.model.Configuration;


public class JobProcess implements Runnable {

	private Job job;
	private List<Configuration> configurations;
	
	public JobProcess(Job job, List<Configuration> configurations) {
		this.job = job;
		this.configurations = configurations;
		prepare();
	}
	
	public void prepare() {
		
	}
	
	@Override
	public void run() {
		job.run();
	}
	
	public String getTargetName() {
		return job.getName();
	}

}
