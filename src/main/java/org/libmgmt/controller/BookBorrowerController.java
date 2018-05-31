package org.libmgmt.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libmgmt.model.Book;
import org.libmgmt.model.BookBorrower;
import org.libmgmt.model.User;
import org.libmgmt.service.BookBorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/bookborrow")
public class BookBorrowerController {

	@Autowired
	BookBorrowerService bookBorrowerService;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ModelAndView getBook(@PathVariable("id") Integer id) {
		BookBorrower bookBorrower = bookBorrowerService.getBookBorrower(id);
		return new ModelAndView("displayBookBorrower", "bookBorrower", bookBorrower);
	}

	@RequestMapping(value = "/issueBook", method = RequestMethod.POST)
	public ModelAndView showForm() {
		return new ModelAndView("addBookBorrower", "bookBorrower", new BookBorrower());
	}

	@RequestMapping(value = "/returnBook", method = RequestMethod.GET)
	public ModelAndView returnBook(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		BookBorrower bookBorrower = bookBorrowerService.getBookBorrower(id);
		bookBorrower.setReturnDate(Calendar.getInstance().getTime());
		bookBorrowerService.returnBook(bookBorrower);
		return redirectToBookManagement();
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addBook(HttpServletRequest req, HttpServletResponse resp) {
		Calendar calendar = Calendar.getInstance();
		Date issueDate = (Date) calendar.getTime().clone();
		calendar.add(Calendar.DAY_OF_MONTH, 15);
		Date dueDate = calendar.getTime();

		BookBorrower bookBorrower = new BookBorrower();
		int bookId = Integer.parseInt(req.getParameter("bookId"));
		int userId = Integer.parseInt(req.getParameter("userId"));
		bookBorrower.setBookId(new Book(bookId));
		bookBorrower.setUserId(new User(userId));
		bookBorrower.setIssueDate(issueDate);
		bookBorrower.setDueDate(dueDate);
		bookBorrowerService.issueBook(bookBorrower);
		return redirectToBookManagement();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchBook(HttpServletRequest req, HttpServletResponse resp) {
		String propertyName = req.getParameter("property");
		String propertyValue = req.getParameter("value");
		List<BookBorrower> bookBorrowers = bookBorrowerService.searchBookBorrowerDetails(propertyName, propertyValue);
		return redirectToBookList(bookBorrowers);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listBook() {
		List<BookBorrower> books = bookBorrowerService.listBorrowedBooks();
		return redirectToBookList(books);
	}

	private ModelAndView redirectToBookManagement() {
		return new ModelAndView("redirect:/BookBorrowerManagement.jsp");
	}

	private ModelAndView redirectToBookList(List<BookBorrower> bookBorrowList) {
		return new ModelAndView("BorrowedBookList", "bookBorrowList", bookBorrowList);
	}
}