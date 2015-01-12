package com.crawler.service.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository("genericDAO")
public class GenericDAO<T> implements IGenericDAO<T> {

	
	private EntityManager entityManager;
	
	
	public void insert(T entity) {
		this.entityManager.persist(entity);		
	}

	public void delete(Long id, Class classe) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaDelete<T> delete = builder.createCriteriaDelete(classe);
		Root root = delete.from(classe);
		delete.where(builder.lessThanOrEqualTo(root.get("id"), id));
		entityManager.createQuery(delete).executeUpdate();
	}

	public void update(T entity) {
		this.entityManager.merge(entity);
	}

	public T getById(Serializable id, Class<T> classe) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(classe);
		Root<T> root = query.from(classe);
		Predicate predicate = builder.equal(root.get("id"), id); 
		query.where(predicate);		
		T result = entityManager.createQuery(query).getSingleResult();
		return result;
	}

	public List<T> listAll(Class<T> classe) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(classe);
		Root<T> root = query.from(classe);
		CriteriaQuery<T> all = query.select(root);
		TypedQuery<T> allQuery = entityManager.createQuery(all);
		return allQuery.getResultList();
	}
	
	public List<T> listByParams(String hql, Map<String, Object> params) {
		Query query = entityManager.createQuery(hql);
		Set<String> keys = params.keySet();
		for(String key : keys) {
			Object value = params.get(key);
			query.setParameter(key, value);
		}
		List<T> result = query.getResultList();
		return result;
	}
	
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext()
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
