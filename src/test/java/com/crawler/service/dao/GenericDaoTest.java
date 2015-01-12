package com.crawler.service.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.crawler.service.model.Resource;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class GenericDaoTest {

	@Autowired
	private IGenericDAO<Resource> dao;
	
	@Before
	public void createAResource() {
		Resource resource = new Resource();
		resource.setText("test element");
		resource.setTitle("test title");
		resource.setUrl("test");
		
		dao.insert(resource);
	}
	
	@Test
	public void insertTest() {
		List<Resource> list = dao.listAll(Resource.class);
		assertNotNull(list);
	}
	
	@Test
	public void updateTest() {
		Resource resource = dao.listAll(Resource.class).get(0);
		resource.setUrl("www");
		dao.update(resource);
		
		assertNotNull(resource);
		assertEquals(resource.getUrl(), "www");
	}
	
	@Test
	public void listAll() {
		List<Resource> list = dao.listAll(Resource.class);
		assertNotNull(list);
	}
	
	@Test
	public void getByIdTest() {
		Resource resource = dao.listAll(Resource.class).get(0);
		Long id = resource.getId();
		Resource result = dao.getById(id, Resource.class);
		assertNotNull(result);
		assertEquals(result, resource);
	}
	
	@Test
	public void listByParamsTest() {
		Map<String,Object> params = new HashMap<String, Object>();
		String hql = "from Resource as resource where url = :url";
		params.put("url", "test");
		List<Resource> list = dao.listByParams(hql, params);
		assertNotNull(list);
	}
	
}
