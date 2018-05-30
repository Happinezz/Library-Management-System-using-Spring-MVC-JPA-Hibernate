package org.libmgmt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.libmgmt.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

	EntityManagerFactory entityManagerFactory;

	@PersistenceContext
	EntityManager entityManager;

	public void setEntityManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public User addUser(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		return user;
	}

	public User updateUser(User user) {
		entityManager.getTransaction().begin();
		user = entityManager.merge(user);
		entityManager.getTransaction().commit();
		return user;
	}

	public Integer deleteUser(Integer userId) {
		entityManager.getTransaction().begin();
		entityManager.remove(userId);
		entityManager.getTransaction().commit();
		return userId;
	}

	public User getUser(Integer id) {
		return entityManager.find(User.class, id);
	}

	public List<User> listUsers() {
		return entityManager.createQuery("FROM " + User.class.getName()).getResultList();
	}

}
