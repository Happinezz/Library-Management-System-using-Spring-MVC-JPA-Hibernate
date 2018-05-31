package org.libmgmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.libmgmt.dao.BookDao;
import org.libmgmt.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Can contain business logic and @Transactional because there can be multiple
 * objects to be updated.
 * 
 * @author Karan
 *
 */

@Service("BookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Resource(name = "BookDao")
	BookDao bookDao;

	public Book addBook(Book book) {
		return bookDao.addBook(book);
	}

	public Book updateBook(Book book) {
		return bookDao.updateBook(book);
	}

	public Integer deleteBook(Integer bookId) {
		return bookDao.deleteBook(bookId);
	}

	public Book getBook(Integer id) {
		return bookDao.getBook(id);
	}

	public List<Book> searchBook(String propertyName, String value) {
		return bookDao.searchBook(propertyName, value);
	}

	public List<Book> listBooks() {
		return bookDao.listBooks();
	}

}
