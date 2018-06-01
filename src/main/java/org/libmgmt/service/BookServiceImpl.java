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
		book.setAvailableCopies(book.getTotalCopies());
		return bookDao.addBook(book);
	}

	public Book updateBook(Book modifiedBook) {
		Book originalData = bookDao.getBook(modifiedBook.getId());
		if (originalData.getTotalCopies() != modifiedBook.getTotalCopies()) {
			Integer modifiedTotalCopies = modifiedBook.getTotalCopies();
			Integer currentlyBorrowedCopies = originalData.getTotalCopies() - originalData.getAvailableCopies();
			if (modifiedTotalCopies < currentlyBorrowedCopies) {
				throw new Error("Total copies can not be updated because you are trying to update total copies to: "
						+ modifiedTotalCopies + " and currently borrowed copies: " + currentlyBorrowedCopies);
			}
			modifiedBook.setAvailableCopies(
					modifiedBook.getAvailableCopies() - (originalData.getTotalCopies() - modifiedTotalCopies));
		}
		return bookDao.updateBook(modifiedBook);
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
