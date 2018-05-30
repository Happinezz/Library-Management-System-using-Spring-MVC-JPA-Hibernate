package org.libmgmt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.libmgmt.dao.BookDao;
import org.libmgmt.model.Book;
import org.libmgmt.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/book")
public class BookController {

	@Resource(name = "BookServiceImpl")
	BookService bookService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("addBook", "book", new Book());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addBook(@ModelAttribute Book book) {
		bookService.addBook(book);
		return new ModelAndView("redirect:/book/get/" + book.getId(), "book", book);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ModelAndView deleteBook(@ModelAttribute Book book) {
		bookService.deleteBook(book.getId());
		return new ModelAndView("displayBook", "book", new Book());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateBook(@ModelAttribute Book book) {
		book = bookService.updateBook(book);
		return new ModelAndView("redirect:/book/get/" + book.getId(), "book", book);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ModelAndView getBook(@PathVariable("id") Integer id) {
		Book book = bookService.getBook(id);
		return new ModelAndView("displayBook", "book", book);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listBook() {
		List<Book> books = bookService.listBooks();
		return new ModelAndView("listBook", "books", books);
	}
}