package org.libmgmt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libmgmt.model.Book;
import org.libmgmt.service.BookService;
import org.libmgmt.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ModelAndView getBook(@PathVariable("id") Integer id) {
		Book book = bookService.getBook(id);
		return new ModelAndView("BookDetails", "book", book);
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("NewBook", "book", new Book());
	}

	// "hasPermission(#book, 'update')"
	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateBook(@ModelAttribute Book book) {
		book = bookService.updateBook(book);
		return redirectToBookManagement();
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addBook(@ModelAttribute Book book, HttpServletRequest req) {
		String ownerEmail = req.getUserPrincipal().getName();
		;
		bookService.addBook(book, ownerEmail);
		return redirectToBookManagement();
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteBook(@PathVariable("id") Integer id) {
		bookService.deleteBook(id);
		return redirectToBookManagement();
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchBook(HttpServletRequest req, HttpServletResponse resp) {
		String propertyName = req.getParameter("property");
		String propertyValue = req.getParameter("value");
		List<Book> books = bookService.searchBook(propertyName, propertyValue);
		return redirectToBookList(books);
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_USER)
	@RequestMapping(value = "/searchAvailableBook", method = RequestMethod.GET)
	public ModelAndView searchAvailableBook(HttpServletRequest req, HttpServletResponse resp) {
		String propertyName = req.getParameter("property");
		String propertyValue = req.getParameter("value");
		List<Book> books = bookService.searchAvailableBook(propertyName, propertyValue);
		return redirectToBorrowBook(books);
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView listBook() {
		List<Book> books = bookService.listBooks();
		return redirectToBookList(books);
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_USER)
	@RequestMapping(value = "/available", method = RequestMethod.GET)
	public ModelAndView getAvailableBookList() {
		List<Book> books = bookService.getAvailableBookList();
		return redirectToBorrowBook(books);
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	private ModelAndView redirectToBookManagement() {
		return new ModelAndView("redirect:/BookManagement.jsp");
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	private ModelAndView redirectToBookList(List<Book> books) {
		return new ModelAndView("BookList", "books", books);
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_USER)
	private ModelAndView redirectToBorrowBook(List<Book> books) {
		return new ModelAndView("BorrowBook", "books", books);
	}
}