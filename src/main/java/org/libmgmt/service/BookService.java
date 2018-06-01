package org.libmgmt.service;

import java.util.List;

import org.libmgmt.model.Book;

/**
 * Provides all services for CRUD operations for the book entity. Also, provides
 * List
 * 
 * @author Karan
 *
 */
public interface BookService {

	/**
	 * Adds new book
	 * 
	 * @param book
	 * @return
	 */
	Book addBook(Book book);

	/**
	 * Updates the book
	 * 
	 * @param book
	 * @return
	 */
	Book updateBook(Book book);

	/**
	 * Deletes the book
	 * 
	 * @param bookId
	 * @return
	 */
	Integer deleteBook(Integer bookId);

	/**
	 * Returns the details of the book for given Id.
	 * 
	 * @param id
	 * @return
	 */
	Book getBook(Integer id);

	/**
	 * Returns the list of book-details
	 * 
	 * @return
	 */
	List<Book> listBooks();

	/**
	 * Searches the books for provided <code>propertyName</code> having provided
	 * <code>value</code> and returns the list
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	List<Book> searchBook(String propertyName, String value);

}
