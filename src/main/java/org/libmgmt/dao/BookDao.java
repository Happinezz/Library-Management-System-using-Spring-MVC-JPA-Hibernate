package org.libmgmt.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.libmgmt.model.Book;
import org.libmgmt.utility.QueryBuilderUtility;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao extends ModelDao<Book> {

	@Override
	public Class<Book> getClassName() {
		return Book.class;
	}

	public List<Book> getAvailablBookList() {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<Book> query = cb.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		Predicate availableCopyPredicate = cb.gt(QueryBuilderUtility.getPropertyPath(root, Book.AVAILABLE_COPIES), 0);
		query.where(availableCopyPredicate);
		return executeQuery(query);
	}

	/**
	 * This will search the book with given propertyName having given value and
	 * return the list of Books having availableCopies > 0
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Book> searchAvailableBook(String propertyName, String value) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> query = cb.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		Predicate predicate = cb.like(cb.lower(QueryBuilderUtility.<Book>getPropertyPath(root, propertyName)),
				"%" + value.toLowerCase() + "%");
		Predicate availableCopyPredicate = cb.gt(QueryBuilderUtility.getPropertyPath(root, Book.AVAILABLE_COPIES), 0);
		query.where(predicate, availableCopyPredicate);
		return entityManager.createQuery(query).getResultList();
	}

}
