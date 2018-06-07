package org.libmgmt.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.libmgmt.dao.BookBorrowerDao;
import org.libmgmt.dao.BookDao;
import org.libmgmt.dao.UserDao;
import org.libmgmt.model.Book;
import org.libmgmt.model.BookBorrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides all services to issue the book, return the book, search the book,
 * list of all borrowed/returned books and book-borrowers history
 * 
 * @author Karan
 *
 */

@Service("BookBorrowerService")
@Transactional
public class BookBorrowerServiceImpl implements BookBorrowerService {

	@Autowired
	BookBorrowerDao bookBorrowerDao;

	@Autowired
	BookDao bookDao;

	@Autowired
	UserDao userDao;

	public BookBorrower issueBook(Integer bookId, String userName) {
		// Prepare Book Borrower Object
		// Due Date will be after 15 days of Issue Date
		Calendar calendar = Calendar.getInstance();
		Date issueDate = (Date) calendar.getTime().clone();
		calendar.add(Calendar.DAY_OF_MONTH, 15);
		Date dueDate = calendar.getTime();
		BookBorrower bookBorrower = new BookBorrower();
		bookBorrower.setBookId(new Book(bookId));
		bookBorrower.setUserId(userDao.findByUserName(userName));
		bookBorrower.setIssueDate(issueDate);
		bookBorrower.setDueDate(dueDate);

		Book book = bookDao.read(bookId);
		if (book.getAvailableCopies() < 1) {
			// TODO Exception Handling
			throw new Error("Book is not available");
		}
		book.setAvailableCopies(book.getAvailableCopies() - 1);
		book = bookDao.update(book);
		return bookBorrowerDao.create(bookBorrower);
	}

	public BookBorrower returnBook(BookBorrower bookBorrower) {
		Book book = bookDao.read(bookBorrower.getBookId().getId());
		book.setAvailableCopies(book.getAvailableCopies() + 1);
		book = bookDao.update(book);
		return bookBorrowerDao.update(bookBorrower);
	}

	public BookBorrower getBookBorrower(Integer id) {
		return bookBorrowerDao.read(id);
	}

	public List<BookBorrower> listBookBorrowers() {
		return bookBorrowerDao.list();
	}

	public List<BookBorrower> searchBookBorrowerDetails(String propertyName, String value) {
		return bookBorrowerDao.search(propertyName, value);
	}

	public List<BookBorrower> listBorrowedBooks() {
		return bookBorrowerDao.listOnlyBorrowedBooks();
	}

	public List<BookBorrower> listReturnedBooks() {
		return bookBorrowerDao.listOnlyReturnedBooks();
	}

	public List<BookBorrower> listHistoryByUser(String email) {
		return bookBorrowerDao.listHistoryByUser(userDao.findByUserName(email).getId());
	}

	public List<BookBorrower> listBorrowedBooksByUser(String email) {
		return bookBorrowerDao.listBorrowedBooksByUser(userDao.findByUserName(email).getId());
	}

}
