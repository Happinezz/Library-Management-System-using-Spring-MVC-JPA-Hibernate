package org.libmgmt.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.libmgmt.model.BookBorrower;
import org.libmgmt.utility.QueryBuilderUtility;
import org.springframework.stereotype.Repository;

@Repository
public class BookBorrowerDao extends ModelDao<BookBorrower> {

	/**
	 * Returns the list of books borrowed but not returned
	 * 
	 * @return
	 */
	public List<BookBorrower> listOnlyBorrowedBooks() {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<BookBorrower> query = cb.createQuery(BookBorrower.class);
		Root<BookBorrower> root = query.from(BookBorrower.class);
		Predicate returnDateNullPredicate = cb.isNull(QueryBuilderUtility.getPropertyPath(root, "returnDate"));
		query.where(returnDateNullPredicate);
		return executeQuery(query);
	}

	/**
	 * Returns the list of books that were borrowed and returned
	 * 
	 * @return
	 */
	public List<BookBorrower> listOnlyReturnedBooks() {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<BookBorrower> query = cb.createQuery(BookBorrower.class);
		Root<BookBorrower> root = query.from(BookBorrower.class);
		Predicate returnDateNullPredicate = cb.isNotNull(QueryBuilderUtility.getPropertyPath(root, "returnDate"));
		query.where(returnDateNullPredicate);
		return executeQuery(query);
	}

	@Override
	public Class<BookBorrower> getClassName() {
		return BookBorrower.class;
	}

	public List<BookBorrower> listHistoryByUser(Integer id) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<BookBorrower> query = cb.createQuery(BookBorrower.class);
		Root<BookBorrower> root = query.from(BookBorrower.class);
		Predicate userPredicate = cb.equal(QueryBuilderUtility.getPropertyPath(root, "userId"), id);
		query.where(userPredicate);
		return executeQuery(query);
	}

	public List<BookBorrower> listBorrowedBooksByUser(Integer id) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<BookBorrower> query = cb.createQuery(BookBorrower.class);
		Root<BookBorrower> root = query.from(BookBorrower.class);
		Predicate userPredicate = cb.equal(QueryBuilderUtility.getPropertyPath(root, "userId"), id);
		Predicate returnDateNullPredicate = cb.isNull(QueryBuilderUtility.getPropertyPath(root, "returnDate"));
		query.where(userPredicate, returnDateNullPredicate);
		return executeQuery(query);
	}

}
