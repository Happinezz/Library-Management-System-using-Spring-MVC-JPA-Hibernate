package org.libmgmt.dao;

import java.util.List;

import org.libmgmt.model.Book;

public interface BookDao {

	Book addBook(Book book);

	Book updateBook(Book book);

	Integer deleteBook(Integer bookId);

	Book getBook(Integer id);

	List<Book> listBooks();

}
