package org.libmgmt.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.libmgmt.dao.BookBorrowerDao;
import org.libmgmt.dao.BookDao;
import org.libmgmt.model.Book;
import org.libmgmt.model.BookBorrower;
import org.libmgmt.model.User;
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

	@Resource(name = "BookBorrowerDao")
	BookBorrowerDao bookBorrowerDao;

	@Resource(name = "BookDao")
	BookDao bookDao;

	public BookBorrower issueBook(Integer bookId, Integer userId) {
		// Prepare Book Borrower Object
		// Due Date will be after 15 days of Issue Date
		Calendar calendar = Calendar.getInstance();
		Date issueDate = (Date) calendar.getTime().clone();
		calendar.add(Calendar.DAY_OF_MONTH, 15);
		Date dueDate = calendar.getTime();
		BookBorrower bookBorrower = new BookBorrower();
		bookBorrower.setBookId(new Book(bookId));
		bookBorrower.setUserId(new User(userId));
		bookBorrower.setIssueDate(issueDate);
		bookBorrower.setDueDate(dueDate);

		bookDao.decrementAvailableCopyCount(bookBorrower.getBookId().getId());
		return bookBorrowerDao.issueBook(bookBorrower);
	}

	public BookBorrower returnBook(BookBorrower bookBorrower) {
		bookDao.incrementAvailableCopyCount(bookBorrower.getBookId().getId());
		return bookBorrowerDao.returnBook(bookBorrower);
	}

	public BookBorrower getBookBorrower(Integer id) {
		return bookBorrowerDao.getBookBorrower(id);
	}

	public List<BookBorrower> listBookBorrowers() {
		return bookBorrowerDao.listBookBorrowers();
	}

	public List<BookBorrower> searchBookBorrowerDetails(String propertyName, String value) {
		return bookBorrowerDao.searchBookBorrowerDetails(propertyName, value);
	}

	public List<BookBorrower> listBorrowedBooks() {
		return bookBorrowerDao.listBorrowedBooks();
	}

	public List<BookBorrower> listReturnedBooks() {
		return bookBorrowerDao.listReturnedBooks();
	}

}
