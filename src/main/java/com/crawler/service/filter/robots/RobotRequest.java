package com.crawler.service.filter.robots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.crawler.service.request.ObjectRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Get the robots.txt through a http GET request include the content in RobotsWrapper 
 *
 */
public class RobotRequest implements ObjectRequest {

	private static final Logger log = LoggerFactory.getLogger(RobotRequest.class);
	
	/**
	 * Get the robots.txt from url
	 */
	@Override
	public RobotsWrapper requestElement(String url) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);
		HttpContext context = new BasicHttpContext();
		List<String> elements = null;
		
		try {
			HttpResponse response = client.execute(get, context);
			HttpEntity entity = response.getEntity();
			if(entity != null) {
				
				InputStream stream = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
				String str = null;
				elements = new ArrayList<String>();
				if(stream != null) {
					while((str = reader.readLine()) != null) {
						if(str != null || str.length() > 0) {
							elements.add(str);
						}
					}
				}
			}
		} catch (ClientProtocolException e) {
			log.error("Error on process the protocol, e");
			elements = new ArrayList<String>();
		} catch (IOException e) {
			log.error("Erro on request the objet from url " + url, e);
			elements = new ArrayList<String>();
		}
		
		return convertToWrapper(elements, url);
	}
	
	private RobotsWrapper convertToWrapper(List<String> elements, String url) {
		List<String> disallow = new ArrayList<String>();
		List<String> map = new ArrayList<String>();
		for(String line : elements) {
			if(line != null && line.length() > 0) {
				if(line.toLowerCase().contains("disallow")) {
					disallow.add(line);
				} else if(line.toLowerCase().contains("sitemap")) {
					map.add(line);
				}
			}
		}
		RobotsWrapper wrapper = new RobotsWrapper();
		wrapper.setDisallow(disallow);
		wrapper.setSitemap(map);
		wrapper.setDomain(url);
		return wrapper;
	}
}
