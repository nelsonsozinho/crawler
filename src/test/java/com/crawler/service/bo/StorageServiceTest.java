package com.crawler.service.bo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.crawler.service.dao.IGenericDAO;
import com.crawler.service.model.Resource;
import com.crawler.service.model.ResourceType;
import com.crawler.service.model.Target;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class StorageServiceTest {

	@Autowired
	private ICrawlerStorageService storageService;
	
	@Autowired
	private IGenericDAO<Resource> dao;
	
	
	private List<Resource> resources = new ArrayList<Resource>();
	private List<Target> targets = new ArrayList<Target>();
	
	
	@Before
	public void configure() {
		prepareResourceList();
	}
	
	private void prepareResourceList() {
		Target target = new Target();
		target.setActive(true);;
		target.setDomain("http://www.test.com");
		target.setInputDate(new Date());
		target.setMainUrl("www.test.com");
		target.setTitle("Test Target");
		
		Resource resource1 = new Resource();
		resource1.setText("test text");
		resource1.setTitle("title1");
		resource1.setType(ResourceType.IMAGE);
		resource1.setUrl("www");
		
		Resource resource2 = new Resource();
		resource2.setText("test text");
		resource2.setTitle("title1");
		resource2.setType(ResourceType.IMAGE);
		resource2.setUrl("www");
		
		Resource resource3 = new Resource();
		resource3.setText("test text");
		resource3.setTitle("title1");
		resource3.setType(ResourceType.IMAGE);
		resource3.setUrl("www");
		
		targets.add(target);
		
		resources.add(resource1);
		resources.add(resource2);
		resources.add(resource3);
	}
		
	@Test
	public void storeWebContentTest() {
		storageService.storeWebContent(resources);
		List<Resource> resourcesRequired = dao.listAll(Resource.class);
		assertNotNull(resourcesRequired);
		assertFalse(resourcesRequired.isEmpty());
	}
	
	@Test
	public void storeWebResourceWithTargetTest() {
		
	}
	
}
