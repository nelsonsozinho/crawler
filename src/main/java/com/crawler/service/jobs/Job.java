package com.crawler.service.jobs;

import com.crawler.service.model.Target;

/**
 * 
 * Interface that means a simple job. It's like a thread, keeps the work until link is end
 *
 */
public interface Job {
	
	/**
	 * Get the job name
	 * @return name
	 */
	public String getName();
	
	/**
	 * Get the job name
	 * 
	 * @return job name
	 */
	public String getId();
	
	/**
	 * tart a simple job.
	 */
	public void run();
	
	/**
	 * get the target 
	 * 
	 * @return
	 */
	public Target getTarget();

}
