package org.libmgmt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.libmgmt.model.BookBorrower;
import org.libmgmt.utility.QueryBuilderUtility;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class BookBorrowerDao {

	EntityManagerFactory entityManagerFactory;

	@PersistenceContext
	EntityManager entityManager;

	public void setEntityManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public BookBorrower issueBook(BookBorrower bookBorrower) {
		entityManager.getTransaction().begin();
		entityManager.persist(bookBorrower);
		entityManager.getTransaction().commit();
		return bookBorrower;
	}

	public BookBorrower returnBook(BookBorrower bookBorrower) {
		entityManager.getTransaction().begin();
		bookBorrower = entityManager.merge(bookBorrower);
		entityManager.getTransaction().commit();
		return bookBorrower;
	}

	public BookBorrower getBookBorrower(Integer id) {
		return entityManager.find(BookBorrower.class, id);
	}

	public List<BookBorrower> listBorrowedBooks() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<BookBorrower> query = cb.createQuery(BookBorrower.class);
		query.from(BookBorrower.class);
		return entityManager.createQuery(query).getResultList();
	}

	public List<BookBorrower> searchBookBorrowerDetails(String propertyName, String value) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<BookBorrower> query = cb.createQuery(BookBorrower.class);
		Root<BookBorrower> root = query.from(BookBorrower.class);
		Predicate predicate = cb.like(cb.lower(QueryBuilderUtility.<BookBorrower>getPropertyPath(root, propertyName)),
				"%" + value.toLowerCase() + "%");
		query.where(predicate);
		return entityManager.createQuery(query).getResultList();
	}

}
