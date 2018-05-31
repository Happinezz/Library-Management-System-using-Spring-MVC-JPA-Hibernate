package org.libmgmt.service;

import java.util.List;

import org.libmgmt.model.Book;

public interface BookService {

	Book addBook(Book book);

	Book updateBook(Book book);

	Integer deleteBook(Integer bookId);

	Book getBook(Integer id);

	List<Book> listBooks();

	List<Book> searchBook(String propertyName, String value);

}
