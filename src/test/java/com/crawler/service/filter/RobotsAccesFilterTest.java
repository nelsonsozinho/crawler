package com.crawler.service.filter;

import com.crawler.service.filter.robots.RobotsAccesFilter;
import org.junit.Test;

import com.crawler.service.request.ObjectRequest;
import com.crawler.service.filter.robots.RobotRequest;
import com.crawler.service.filter.robots.RobotsWrapper;

import java.net.MalformedURLException;

public class RobotsAccesFilterTest {

	@Test
	public void testUrlCanVisit() {
		String url = "http://www.bbc.com/robots.txt";
		ObjectRequest request = new RobotRequest();
		RobotsWrapper wrapper = (RobotsWrapper) request.requestElement(url);
		AccessFilter filter = new RobotsAccesFilter(wrapper);
        try {
            filter.urlCanVisit(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
	
}
