package org.libmgmt.service;

import java.util.List;

import org.libmgmt.model.BookBorrower;

public interface BookBorrowerService {

	BookBorrower issueBook(BookBorrower bookBorrower);

	BookBorrower returnBook(BookBorrower bookBorrower);

	BookBorrower getBookBorrower(Integer id);

	List<BookBorrower> listBorrowedBooks();

	List<BookBorrower> searchBookBorrowerDetails(String propertyName, String value);

}
