package org.libmgmt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.libmgmt.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BookDaoImpl implements BookDao {

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
		entityManager.remove(bookId);
		entityManager.getTransaction().commit();
		return bookId;
	}

	public Book getBook(Integer id) {
		return entityManager.find(Book.class, id);
	}

	public List<Book> listBooks() {
		return entityManager.createQuery("FROM " + Book.class.getName()).getResultList();
	}

}
