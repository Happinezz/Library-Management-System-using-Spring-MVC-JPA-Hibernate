package org.libmgmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.libmgmt.dao.BookBorrowerDao;
import org.libmgmt.model.BookBorrower;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Can contain business logic and @Transactional because there can be multiple
 * objects to be updated.
 * 
 * @author Karan
 *
 */

@Service("BookBorrowerService")
@Transactional
public class BookBorrowerServiceImpl implements BookBorrowerService {

	@Resource(name = "BookBorrowerDao")
	BookBorrowerDao bookBorrowerDao;

	public BookBorrower issueBook(BookBorrower bookBorrower) {
		return bookBorrowerDao.issueBook(bookBorrower);
	}

	public BookBorrower returnBook(BookBorrower bookBorrower) {
		return bookBorrowerDao.returnBook(bookBorrower);
	}

	public BookBorrower getBookBorrower(Integer id) {
		return bookBorrowerDao.getBookBorrower(id);
	}

	public List<BookBorrower> listBorrowedBooks() {
		return bookBorrowerDao.listBorrowedBooks();
	}

	public List<BookBorrower> searchBookBorrowerDetails(String propertyName, String value) {
		return bookBorrowerDao.searchBookBorrowerDetails(propertyName, value);
	}

}
