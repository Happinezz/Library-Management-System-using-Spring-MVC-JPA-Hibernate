package org.libmgmt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.libmgmt.model.Book;
import org.libmgmt.utility.QueryBuilderUtility;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

	EntityManagerFactory entityManagerFactory;

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Validates the copy of the book is available and then decreases the count
	 * of available count for given bookId.
	 * 
	 * @param bookId
	 * @return
	 */
	public Book decrementAvailableCopyCount(final Integer bookId) {
		Book book = getBook(bookId);
		if (book.getAvailableCopies() < 1) {
			// TODO Exception Handling
			throw new Error("Book is not available");
		}
		book.setAvailableCopies(book.getAvailableCopies() - 1);
		book = updateBook(book);
		return book;
	}

	/**
	 * Increases the count of available book in case of book-return.
	 * 
	 * @param bookId
	 * @return
	 */
	public Book incrementAvailableCopyCount(final Integer bookId) {
		Book book = getBook(bookId);
		book.setAvailableCopies(book.getAvailableCopies() + 1);
		book = updateBook(book);
		return book;
	}

	public void setEntityManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	/**
	 * Adds the book
	 * 
	 * @param book
	 * @return
	 */
	public Book addBook(Book book) {
		entityManager.getTransaction().begin();
		entityManager.persist(book);
		entityManager.getTransaction().commit();
		// entityManager.flush();
		return book;
	}

	/**
	 * Updates the book details
	 * 
	 * @param book
	 * @return
	 */
	public Book updateBook(Book book) {
		entityManager.getTransaction().begin();
		book = entityManager.merge(book);
		entityManager.getTransaction().commit();
		return book;
	}

	/**
	 * Deletes the book
	 * 
	 * @param bookId
	 * @return
	 */
	public Integer deleteBook(Integer bookId) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(Book.class, bookId));
		entityManager.getTransaction().commit();
		return bookId;
	}

	/**
	 * Returns the book-details for given book-Id
	 * 
	 * @param id
	 * @return
	 */
	public Book getBook(Integer id) {
		return entityManager.find(Book.class, id);
	}

	/**
	 * Returns the list of books
	 * 
	 * @return
	 */
	public List<Book> listBooks() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> query = cb.createQuery(Book.class);
		query.from(Book.class);
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * Searches the book using given property-name having given value and
	 * returns the matching list
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
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
