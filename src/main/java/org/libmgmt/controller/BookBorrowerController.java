package org.libmgmt.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libmgmt.model.BookBorrower;
import org.libmgmt.service.BookBorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/bookborrow")
public class BookBorrowerController {

	// Book Borrower Service
	@Autowired
	private BookBorrowerService bookBorrowerService;

	/**
	 * Fetches the Book-Borrower details by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ModelAndView getBookBorrower(@PathVariable("id") Integer id) {
		BookBorrower bookBorrower = bookBorrowerService.getBookBorrower(id);
		return new ModelAndView("displayBookBorrower", "bookBorrower", bookBorrower);
	}

	@RequestMapping(value = "/issueBook", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("addBookBorrower", "bookBorrower", new BookBorrower());
	}

	/**
	 * Adds entry for returned book
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "/returnBook", method = RequestMethod.GET)
	public ModelAndView returnBook(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		BookBorrower bookBorrower = bookBorrowerService.getBookBorrower(id);
		bookBorrower.setReturnDate(Calendar.getInstance().getTime());
		bookBorrowerService.returnBook(bookBorrower);
		return redirectToBookManagement();
	}

	/**
	 * Issues the book
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "/issueBook", method = RequestMethod.POST)
	public ModelAndView issueBook(HttpServletRequest req, HttpServletResponse resp) {
		int bookId = Integer.parseInt(req.getParameter("bookId"));
		int userId = Integer.parseInt(req.getParameter("userId"));
		bookBorrowerService.issueBook(bookId, userId);
		return redirectToBookManagement();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchBook(HttpServletRequest req, HttpServletResponse resp) {
		String propertyName = req.getParameter("property");
		String propertyValue = req.getParameter("value");
		List<BookBorrower> bookBorrowers = bookBorrowerService.searchBookBorrowerDetails(propertyName, propertyValue);
		return redirectToBookList(bookBorrowers);
	}

	/**
	 * Returns the list of all Book-Borrowers
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listBookBorrowerHistory() {
		List<BookBorrower> books = bookBorrowerService.listBookBorrowers();
		return redirectToBookList(books);
	}

	/**
	 * Fetches the list of books which were borrowed but not returned and
	 * renders the list
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listBorrowedBook", method = RequestMethod.GET)
	public ModelAndView listBorrowedBook() {
		List<BookBorrower> books = bookBorrowerService.listBorrowedBooks();
		return redirectToBookList(books);
	}

	/**
	 * Fetches the list of books which were borrowed and returned in the past
	 * and renders the list
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listReturnedBook", method = RequestMethod.GET)
	public ModelAndView listReturnedBook() {
		List<BookBorrower> books = bookBorrowerService.listReturnedBooks();
		return redirectToBookList(books);
	}

	/**
	 * Redirect to Home-Page of BookBorrower Operations
	 * 
	 * @return
	 */
	private ModelAndView redirectToBookManagement() {
		return new ModelAndView("redirect:/BookBorrowerManagement.jsp");
	}

	/**
	 * Redirect to the List of Book-Borrowers.
	 * 
	 * @param bookBorrowList
	 * @return
	 */
	private ModelAndView redirectToBookList(List<BookBorrower> bookBorrowList) {
		return new ModelAndView("BorrowedBookList", "bookBorrowList", bookBorrowList);
	}
}