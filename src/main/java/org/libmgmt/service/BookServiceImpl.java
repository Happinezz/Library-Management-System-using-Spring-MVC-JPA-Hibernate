package org.libmgmt.service;

import java.util.List;

import org.libmgmt.dao.BookDao;
import org.libmgmt.dao.UserDao;
import org.libmgmt.model.Book;
import org.libmgmt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	BookDao bookDao;

	@Autowired
	UserDao userDao;

	public Book addBook(Book book, String ownerEmail) {
		book.setAvailableCopies(book.getTotalCopies());
		User owner = userDao.findByUserName(ownerEmail);
		book.setBookOwner(owner);
		return bookDao.create(book);
	}

	public Book updateBook(Book modifiedBook) {
		Book originalData = bookDao.read(modifiedBook.getId());
		modifiedBook.setBookOwner(originalData.getBookOwner());
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
		return bookDao.update(modifiedBook);
	}

	public Integer deleteBook(Integer bookId) {
		return bookDao.delete(bookId);
	}

	public Book getBook(Integer id) {
		return bookDao.read(id);
	}

	public List<Book> searchBook(String propertyName, String value) {
		return bookDao.search(propertyName, value);
	}

	public List<Book> listBooks() {
		return bookDao.list();
	}

	public List<Book> getAvailableBookList() {
		return bookDao.getAvailablBookList();
	}

	public List<Book> searchAvailableBook(String propertyName, String value) {
		return bookDao.searchAvailableBook(propertyName, value);
	}

}
