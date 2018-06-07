package org.libmgmt.service;

import java.util.List;

import org.libmgmt.model.BookBorrower;

/**
 * Provides all services to issue the book, return the book, search the book,
 * list of all borrowed/returned books and book-borrowers history
 * 
 * @author Karan
 *
 */
public interface BookBorrowerService {

	/**
	 * Validates the availability of the book, decrement count of available
	 * copies and issues the book
	 * 
	 * @param bookId
	 * @param userName
	 * @return
	 */
	BookBorrower issueBook(Integer bookId, String userName);

	/**
	 * Increment the count of available copies and add entry for book-returns
	 * 
	 * @param bookBorrower
	 * @return
	 */
	BookBorrower returnBook(BookBorrower bookBorrower);

	/**
	 * Fetches Book Borrower Details for given Id
	 * 
	 * @param id
	 * @return
	 */
	BookBorrower getBookBorrower(Integer id);

	/**
	 * Returns history of all books borrowed in the past
	 * 
	 * @return
	 */
	List<BookBorrower> listBookBorrowers();

	/**
	 * Returns list of Books which were borrowed but not returned yet.
	 * 
	 * @return
	 */
	List<BookBorrower> listBorrowedBooks();

	/**
	 * Searches the Book-Borrower Details for provided propertyName having
	 * provide value
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	List<BookBorrower> searchBookBorrowerDetails(String propertyName, String value);

	/**
	 * Returns the list of Books which were borrowed and has been returned
	 * 
	 * @return
	 */
	List<BookBorrower> listReturnedBooks();
	
	List<BookBorrower> listHistoryByUser(String email);
	
	List<BookBorrower> listBorrowedBooksByUser(String email);

}
