package com.crawler.service.filter.robots;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.crawler.service.filter.AccessFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RobotsAccesFilter implements AccessFilter {

    private static final Logger log = LoggerFactory.getLogger(RobotsAccesFilter.class);

	private RobotsWrapper wrapper;
	
	public RobotsAccesFilter(RobotsWrapper wrapper) {
		this.wrapper = wrapper;
	}

	@Override
	public boolean urlCanVisit(String url) throws MalformedURLException {
        URL urlObject = new URL(url);
		String domain = urlObject.getHost();
		String path = urlObject.getPath();
		List<String> disallow = wrapper.getDisallow();

		return !isDisallow(path, disallow);
	}
	
	private boolean isDisallow(String path, List<String> disallow) {
		for(String rule : disallow) {
            if(rule.contains("*")) {
                if(rule.toLowerCase().contains(path.toLowerCase())) {
                    log.info("Prepare to desallow orders: " + rule);
                    return true;
                }
            } else {
                if(rule.toLowerCase().contains(path.toLowerCase())) {
                    log.info("Prepare to desallow orders: " + rule);
                    return true;
                }
            }
		}
		
		return false;
	}

}
