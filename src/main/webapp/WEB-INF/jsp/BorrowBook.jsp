<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book List</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}">User Home</a>
	<h3>Books List</h3>
	<form:form method="GET" action="searchAvailableBook">
		<table>
			<tr>
				<td>Search Book By:</td>
				<td><select name="property">
						<option value="isbn">ISBN</option>
						<option value="title">Title</option>
						<option value="author">Author</option>
						<option value="publisher">Publisher</option>
				</select></td>
			</tr>
			<tr>
				<td>Value:</td>
				<td><input type="text" id="value" name="value" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form:form>

	<table border="1px">
		<tr>
			<td>Id</td>
			<td>ISBN</td>
			<td>Title</td>
			<td>Author</td>
			<td>Publisher</td>
			<td>Total Copies</td>
			<td>Available Copies</td>
			<td>Price</td>
		</tr>
		<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.id}</td>
				<td>${book.isbn}</td>
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.publisher}</td>
				<td>${book.totalCopies}</td>
				<td>${book.availableCopies}</td>
				<td>${book.price}</td>
				<td>
					<form method="post"
						action="${pageContext.request.contextPath}/bookborrow/issueBook">
						<input type="hidden" name="bookId" value="${book.id}" /> <input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> <input type="submit" value="Borrow" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>