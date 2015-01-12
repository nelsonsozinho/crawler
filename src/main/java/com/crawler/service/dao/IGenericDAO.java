package com.crawler.service.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * GenericDao
 *
 * @param <T>
 */
public interface IGenericDAO<T> {

	public void insert(T entity);
	
	public void delete(Long id, Class classe);
	
	public void update(T entity);
	
	public T getById(Serializable id, Class<T> classe);
	
	public List<T> listAll(Class<T> classe);
	
	public List<T> listByParams(String hql, Map<String, Object> params);
	
}
