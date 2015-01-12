package com.crawler.service.jobs;

import static org.junit.Assert.*;

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

import com.crawler.service.bo.ICrawlerStorageService;
import com.crawler.service.dao.IGenericDAO;
import com.crawler.service.model.Configuration;
import com.crawler.service.model.Target;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class JobWrapperTest {
	
	@Autowired
	private ICrawlerStorageService cs;
	
	@Autowired
	private IGenericDAO<Target> targetDao;
	
	@Autowired
	private IGenericDAO<Configuration> configurationDao;

	
	@Before
	public void configue() {
		Target target = new Target();
		target.setActive(true);
		target.setDomain("test");
		target.setInputDate(new Date());
		target.setMainUrl("http://www.test.com");
		target.setTitle("test title");
		targetDao.insert(target);
		
		Configuration config = new Configuration();
		config.setKey("test_key");
		config.setValue("test_value");
		configurationDao.insert(config);
	}
	
	@Test
	public void testJobWrapperPrepare() {
		Target target = cs.findTargetByUrl("http://www.test.com");
		List<Configuration> configurations = configurationDao.listAll(Configuration.class); 	
		
		assertNotNull(target);
		assertNotNull(configurations);
	}
	
}
