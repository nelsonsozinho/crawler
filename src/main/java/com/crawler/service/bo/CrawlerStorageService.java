package com.crawler.service.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crawler.service.dao.IGenericDAO;
import com.crawler.service.model.Resource;
import com.crawler.service.model.Target;

@Service("content_service")
@Transactional(readOnly = true)
public class CrawlerStorageService implements ICrawlerStorageService {

	private static final Logger log = LoggerFactory.getLogger(CrawlerStorageService.class);
	
	@Autowired
	private IGenericDAO<Resource> dao;
	
	@Autowired
	private IGenericDAO<Target> daoTarget;
	
	@Override
	@Transactional(readOnly = false)
	public void storeWebContent(List<Resource> resources) {
		for(Resource res : resources) {
			if(findResourceByURL(res.getUrl()).isEmpty()) { 
				dao.insert(res);
			}
		}
	}
	
	@Override
	public void storeWebResourceWithTarget(List<Resource> resources, String url) {
		Target target = findTargetByUrl(url);
		if(target != null) {
			for(Resource resource : resources) {
				resource.setTarget(target);
			}
		}
		this.storeWebContent(resources);
	}
	
	/**
	 * Get a target
	 * 
	 * @param URL
	 * @return
	 */
	@Override
	public Target findTargetByUrl(String URL) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("mainUrl", URL);
		List targets = dao.listByParams("from Target as target where target.mainUrl = :mainUrl", params);
		if(!targets.isEmpty()) { 
			Target target = (Target) targets.get(0);
			return target;
		}
		log.error("Taget with domain " + URL + " was not found");
		return null;
	}
	
	@Override
	public List<Resource> findResourceByURL(String URL) {
		String hql = "from Resource as resource where resource.url = :url";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("url", URL);
		List<Resource> resources = dao.listByParams(hql, params); 
		return resources;
	}

	@Override
	@Transactional(readOnly = false)
	public void storeWebContent(Resource resource) {
		if(findResourceByURL(resource.getUrl()).isEmpty()) { 
			dao.insert(resource);
		} else {
			log.debug("The URL " + resource.getUrl() + " already exist");
		}
	}

	@Override
	public List<Target> listActiveTargets() {
		String hql = "from Target as target where target.isActive = true";
		Map<String,Object> params = new HashMap<String, Object>();
		List<Target> targets = daoTarget.listByParams(hql, params);
		return targets;
	}

    private boolean resourceCanWrite(Resource resource) {
        List<Resource> resources = findResourceByURL(resource.getUrl());
        
        return true;
    }

}
