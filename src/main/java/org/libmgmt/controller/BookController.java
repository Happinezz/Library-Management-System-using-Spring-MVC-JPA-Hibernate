package org.libmgmt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libmgmt.model.Book;
import org.libmgmt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ModelAndView getBook(@PathVariable("id") Integer id) {
		Book book = bookService.getBook(id);
		return new ModelAndView("BookDetails", "book", book);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("NewBook", "book", new Book());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateBook(@ModelAttribute Book book) {
		book = bookService.updateBook(book);
		return redirectToBookManagement();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addBook(@ModelAttribute Book book) {
		bookService.addBook(book);
		return redirectToBookManagement();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteBook(@PathVariable("id") Integer id) {
		bookService.deleteBook(id);
		return redirectToBookManagement();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchBook(HttpServletRequest req, HttpServletResponse resp) {
		String propertyName = req.getParameter("property");
		String propertyValue = req.getParameter("value");
		List<Book> books = bookService.searchBook(propertyName, propertyValue);
		return redirectToBookList(books);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listBook() {
		List<Book> books = bookService.listBooks();
		return redirectToBookList(books);
	}

	private ModelAndView redirectToBookManagement() {
		return new ModelAndView("redirect:/BookBorrowerManagement.jsp");
	}

	private ModelAndView redirectToBookList(List<Book> books) {
		return new ModelAndView("BookList", "books", books);
	}
}