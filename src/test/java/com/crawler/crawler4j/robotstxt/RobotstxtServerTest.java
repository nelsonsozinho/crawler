package com.crawler.crawler4j.robotstxt;

import com.crawler.service.filter.AccessFilter;
import com.crawler.service.filter.robots.RobotRequest;
import com.crawler.service.filter.robots.RobotsAccesFilter;
import com.crawler.service.filter.robots.RobotsWrapper;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RobotstxtServerTest {

	@Test
	public void canNotAccessAnURLDisallowed() {
        RobotRequest request = new RobotRequest();
        String url = "http://www.dzone.com/robots.txt";
        String urlDisallow = "http://www.dzone.com/links/dzone.com-opensearch.xml";
        RobotsWrapper wrapper = request.requestElement(url);
        AccessFilter filter = new RobotsAccesFilter(wrapper);
        boolean canVisit = false;

        try {
            canVisit = filter.urlCanVisit(urlDisallow);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        assertFalse(canVisit);
    }

    @Test
    public void canAccessAnURLAllowed() {
        RobotRequest request = new RobotRequest();
        String url = "http://www.dzone.com/robots.txt";
        String urlDisallow = "http://www.dzone.com/links/index.html";
        RobotsWrapper wrapper = request.requestElement(url);
        AccessFilter filter = new RobotsAccesFilter(wrapper);
        boolean canVisit = false;

        try {
            canVisit = filter.urlCanVisit(urlDisallow);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        assertTrue(canVisit);
    }

    @Test
    public void canAccessAnURLDisallowedWithMysqlInstall() {
        RobotRequest request = new RobotRequest();
        String url = "http://www.dzone.com/robots.txt";
        String urlDisallow = "http://www.dzone.com/INSTALL.mysql.txt";
        RobotsWrapper wrapper = request.requestElement(url);
        AccessFilter filter = new RobotsAccesFilter(wrapper);
        boolean canVisit = false;

        try {
            canVisit = filter.urlCanVisit(urlDisallow);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        assertFalse(canVisit);
    }

    @Test
    public void canAccessAnURLDisallowedWithLinks() {
        RobotRequest request = new RobotRequest();
        String url = "http://www.dzone.com/robots.txt";
        String urlDisallow = "http://www.dzone.com/links/send";
        RobotsWrapper wrapper = request.requestElement(url);
        AccessFilter filter = new RobotsAccesFilter(wrapper);
        boolean canVisit = false;

        try {
            canVisit = filter.urlCanVisit(urlDisallow);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        assertFalse(canVisit);
    }

}