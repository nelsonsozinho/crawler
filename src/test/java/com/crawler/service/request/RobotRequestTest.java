package com.crawler.service.request;

import static org.junit.Assert.assertNotNull;

import com.crawler.service.filter.robots.RobotRequest;
import com.crawler.service.filter.robots.RobotsWrapper;
import org.junit.Test;


public class RobotRequestTest {

	@Test
	public void testSimpleRequest() {
		ObjectRequest request = new RobotRequest();
		RobotsWrapper result = (RobotsWrapper) request.requestElement("http://www.bbc.com/robots.txt");
		assertNotNull(result);
	}
	
}
