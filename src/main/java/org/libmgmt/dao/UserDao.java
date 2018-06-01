package org.libmgmt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.libmgmt.model.Book;
import org.libmgmt.model.User;
import org.libmgmt.utility.QueryBuilderUtility;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	EntityManagerFactory entityManagerFactory;

	@PersistenceContext
	EntityManager entityManager;

	public void setEntityManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	/**
	 * Adds the user
	 * 
	 * @param user
	 * @return
	 */
	public User addUser(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		// entityManager.flush();
		return user;
	}

	/**
	 * Updates the user
	 * 
	 * @param user
	 * @return
	 */
	public User updateUser(User user) {
		entityManager.getTransaction().begin();
		user = entityManager.merge(user);
		entityManager.getTransaction().commit();
		return user;
	}

	/**
	 * Deletes the user
	 * 
	 * @param userId
	 * @return
	 */
	public Integer deleteUser(Integer userId) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(User.class, userId));
		entityManager.getTransaction().commit();
		return userId;
	}

	/**
	 * Returns the user-details for given user-id
	 * 
	 * @param userId
	 * @return
	 */
	public User getUser(Integer userId) {
		return entityManager.find(User.class, userId);
	}

	/**
	 * Returns the list of users
	 * 
	 * @return
	 */
	public List<User> listUsers() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		query.from(User.class);
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
	public List<User> searchUser(String propertyName, String value) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> root = query.from(User.class);
		Predicate predicate = cb.like(cb.lower(QueryBuilderUtility.<User>getPropertyPath(root, propertyName)),
				"%" + value.toLowerCase() + "%");
		query.where(predicate);
		return entityManager.createQuery(query).getResultList();
	}

}
