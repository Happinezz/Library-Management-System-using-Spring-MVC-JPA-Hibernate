package org.libmgmt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.libmgmt.utility.QueryBuilderUtility;

public abstract class ModelDao<T> {
	private Class<T> clazz;

	@PersistenceContext
	EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ModelDao() {
		this.clazz = getClassName();
	}

	public abstract Class<T> getClassName();

	/**
	 * Creates the object
	 * 
	 * @param object
	 * @return
	 */
	public T create(T object) {
		entityManager.persist(object);
		return object;
	}

	/**
	 * Updates the object
	 * 
	 * @param object
	 * @return
	 */
	public T update(T object) {
		object = entityManager.merge(object);
		return object;
	}

	/**
	 * Deletes the object
	 * 
	 * @param objectId
	 * @return
	 */
	public Integer delete(Integer objectId) {
		entityManager.remove(entityManager.find(clazz, objectId));
		return objectId;
	}

	/**
	 * Returns the details for given id
	 * 
	 * @param id
	 * @return
	 */
	public T read(Integer id) {
		return entityManager.find(clazz, id);
	}

	/**
	 * Returns the list of objects
	 * 
	 * @return
	 */
	public List<T> list() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(clazz);
		query.from(clazz);
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * Searches the book for given propertyName having given value and returns
	 * the matching list
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> search(String propertyName, String value) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(clazz);
		Root<T> root = query.from(clazz);
		Predicate predicate = cb.like(cb.lower(QueryBuilderUtility.<T>getPropertyPath(root, propertyName)),
				"%" + value.toLowerCase() + "%");
		query.where(predicate);
		return entityManager.createQuery(query).getResultList();
	}

	public List<T> executeQuery(CriteriaQuery<T> query) {
		return entityManager.createQuery(query).getResultList();
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}
}
