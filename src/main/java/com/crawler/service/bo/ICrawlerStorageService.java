package com.crawler.service.bo;

import java.util.List;

import com.crawler.service.model.Resource;
import com.crawler.service.model.Target;

public interface ICrawlerStorageService {

	public void storeWebContent(List<Resource> resources);
	public void storeWebContent(Resource resource);
	public List<Resource> findResourceByURL(String URL);
	public List<Target> listActiveTargets();
	public void storeWebResourceWithTarget(List<Resource> resources, String url);
	public Target findTargetByUrl(String URL);
	
}
