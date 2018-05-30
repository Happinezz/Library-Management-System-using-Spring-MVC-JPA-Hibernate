package org.libmgmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.libmgmt.dao.BookDao;
import org.libmgmt.model.Book;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BookServiceImpl implements BookService {

	@Resource(name = "BookDaoImpl")
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

	public List<Book> listBooks() {
		return bookDao.listBooks();
	}

}
