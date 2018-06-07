package org.libmgmt.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libmgmt.model.BookBorrower;
import org.libmgmt.service.BookBorrowerService;
import org.libmgmt.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ModelAndView getBookBorrower(@PathVariable("id") Integer id) {
		BookBorrower bookBorrower = bookBorrowerService.getBookBorrower(id);
		return new ModelAndView("displayBookBorrower", "bookBorrower", bookBorrower);
	}

	/**
	 * Adds entry for returned book
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@PreAuthorize(Constants.HAS_ROLE_ROLE_USER)
	@RequestMapping(value = "/returnBook", method = RequestMethod.GET)
	public ModelAndView returnBook(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		BookBorrower bookBorrower = bookBorrowerService.getBookBorrower(id);
		bookBorrower.setReturnDate(Calendar.getInstance().getTime());
		bookBorrowerService.returnBook(bookBorrower);
		return redirectToUserHome();
	}

	/**
	 * Issues the book
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@PreAuthorize(Constants.HAS_ROLE_ROLE_USER)
	@RequestMapping(value = "/issueBook", method = RequestMethod.POST)
	public ModelAndView issueBook(HttpServletRequest req, HttpServletResponse resp) {
		int bookId = Integer.parseInt(req.getParameter("bookId"));
		String userName = req.getUserPrincipal().getName();
		bookBorrowerService.issueBook(bookId, userName);
		return redirectToUserHome();
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
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
	@PreAuthorize(Constants.HAS_ROLE_ROLE_USER)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listBookBorrowerHistory(HttpServletRequest req, HttpServletResponse resp) {
		List<BookBorrower> books = null;
		if (req.isUserInRole(Constants.HAS_ROLE_ROLE_ADMIN)) {
			books = bookBorrowerService.listBookBorrowers();
		} else {
			String email = req.getUserPrincipal().getName();
			books = bookBorrowerService.listHistoryByUser(email);
		}
		return redirectToBookList(books);
	}

	/**
	 * Fetches the list of books which were borrowed but not returned and
	 * renders the list
	 * 
	 * @return
	 */
	@PreAuthorize(Constants.HAS_ROLE_ROLE_USER)
	@RequestMapping(value = "/listBorrowedBook", method = RequestMethod.GET)
	public ModelAndView listBorrowedBook(HttpServletRequest req, HttpServletResponse resp) {
		List<BookBorrower> books = null;
		if (req.isUserInRole(Constants.HAS_ROLE_ROLE_ADMIN)) {
			books = bookBorrowerService.listBorrowedBooks();
		} else {
			String email = req.getUserPrincipal().getName();
			books = bookBorrowerService.listBorrowedBooksByUser(email);
		}
		return redirectToBookList(books);
	}

	/**
	 * Fetches the list of books which were borrowed and returned in the past
	 * and renders the list
	 * 
	 * @return
	 */
	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
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
	@PreAuthorize(Constants.HAS_ROLE_ROLE_USER)
	private ModelAndView redirectToUserHome() {
		return new ModelAndView("user_home");
	}

	/**
	 * Redirect to the List of Book-Borrowers.
	 * 
	 * @param bookBorrowList
	 * @return
	 */
	@PreAuthorize(Constants.HAS_ROLE_ROLE_USER)
	private ModelAndView redirectToBookList(List<BookBorrower> bookBorrowList) {
		return new ModelAndView("BorrowedBookList", "bookBorrowList", bookBorrowList);
	}
}