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

	/**
	 * Adds entry for issuing book
	 * 
	 * @param bookBorrower
	 * @return
	 */
	public BookBorrower issueBook(BookBorrower bookBorrower) {
		entityManager.getTransaction().begin();
		entityManager.persist(bookBorrower);
		entityManager.getTransaction().commit();
		return bookBorrower;
	}

	/**
	 * Adds entry for returning the book
	 * 
	 * @param bookBorrower
	 * @return
	 */
	public BookBorrower returnBook(BookBorrower bookBorrower) {
		entityManager.getTransaction().begin();
		BookBorrower originalBorrowDetails = getBookBorrower(bookBorrower.getId());
		if (originalBorrowDetails.getReturnDate() == null) {
			throw new Error("Book has been already returned.");
		}
		bookBorrower = entityManager.merge(bookBorrower);
		entityManager.getTransaction().commit();
		return bookBorrower;
	}

	/**
	 * Returns the Book Borrower Details for given Id
	 * 
	 * @param id
	 * @return
	 */
	public BookBorrower getBookBorrower(Integer id) {
		return entityManager.find(BookBorrower.class, id);
	}

	/**
	 * Returns the list of all books borrowed
	 * 
	 * @return
	 */
	public List<BookBorrower> listBookBorrowers() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<BookBorrower> query = cb.createQuery(BookBorrower.class);
		query.from(BookBorrower.class);
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * Searches the book-borrower details for given property-name having given
	 * value and returns the matching list
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<BookBorrower> searchBookBorrowerDetails(String propertyName, String value) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<BookBorrower> query = cb.createQuery(BookBorrower.class);
		Root<BookBorrower> root = query.from(BookBorrower.class);
		Predicate predicate = cb.like(cb.lower(QueryBuilderUtility.<BookBorrower>getPropertyPath(root, propertyName)),
				"%" + value.toLowerCase() + "%");
		query.where(predicate);
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * Returns the list of books borrowed but not returned
	 * 
	 * @return
	 */
	public List<BookBorrower> listBorrowedBooks() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<BookBorrower> query = cb.createQuery(BookBorrower.class);
		Root root = query.from(BookBorrower.class);
		Predicate returnDateNullPredicate = cb.isNull(QueryBuilderUtility.getPropertyPath(root, "returnDate"));
		query.where(returnDateNullPredicate);
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * Returns the list of books that were borrowed and returned
	 * 
	 * @return
	 */
	public List<BookBorrower> listReturnedBooks() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<BookBorrower> query = cb.createQuery(BookBorrower.class);
		Root root = query.from(BookBorrower.class);
		Predicate returnDateNullPredicate = cb.isNotNull(QueryBuilderUtility.getPropertyPath(root, "returnDate"));
		query.where(returnDateNullPredicate);
		return entityManager.createQuery(query).getResultList();
	}

}
