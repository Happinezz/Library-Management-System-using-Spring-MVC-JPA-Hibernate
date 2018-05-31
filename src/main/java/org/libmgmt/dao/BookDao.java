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
import org.libmgmt.utility.QueryBuilderUtility;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BookDao {

	EntityManagerFactory entityManagerFactory;

	@PersistenceContext
	EntityManager entityManager;

	public void setEntityManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Transactional
	public Book addBook(Book book) {
		entityManager.getTransaction().begin();
		entityManager.persist(book);
		entityManager.getTransaction().commit();
		// entityManager.flush();
		return book;
	}

	@Transactional
	public Book updateBook(Book book) {
		entityManager.getTransaction().begin();
		book = entityManager.merge(book);
		entityManager.getTransaction().commit();
		return book;
	}

	@Transactional
	public Integer deleteBook(Integer bookId) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(Book.class, bookId));
		entityManager.getTransaction().commit();
		return bookId;
	}

	public Book getBook(Integer id) {
		return entityManager.find(Book.class, id);
	}

	public List<Book> listBooks() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> query = cb.createQuery(Book.class);
		query.from(Book.class);
		return entityManager.createQuery(query).getResultList();
	}

	public List<Book> searchBook(String propertyName, String value) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> query = cb.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		Predicate predicate = cb.like(cb.lower(QueryBuilderUtility.<Book>getPropertyPath(root, propertyName)),
				"%" + value.toLowerCase() + "%");
		query.where(predicate);
		return entityManager.createQuery(query).getResultList();
	}

}
