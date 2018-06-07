<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User List</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}">User Home</a>
	<h3>Borrowed Books List</h3>
	<form:form method="GET" action="search">
		<table>
			<tr>
				<td>Search Book/Borrower Details By:</td>
				<td><select name="property">
						<option value="bookId.title">Book Title</option>
						<option value="bookId.isbn">ISBN</option>
						<option value="bookId.author">Author</option>
						<option value="bookId.publisher">Publisher</option>
						<option value="userId.name">User Name</option>
						<option value="userId.email">Email</option>
						<option value="userId.contactNo">Contact No</option>
						<option value="bookId.author">Author</option>
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
			<td>Book Id</td>
			<td>User Id</td>
			<td>Issue Date</td>
			<td>Return Date</td>
			<td>Due Date</td>
		</tr>
		<c:forEach items="${bookBorrowList}" var="bookBorrow">
			<tr>
				<td>${bookBorrow.id}</td>
				<td>${bookBorrow.bookId.id}</td>
				<td>${bookBorrow.userId.id}</td>
				<td>${bookBorrow.issueDate}</td>
				<td>${bookBorrow.returnDate}</td>
				<td>${bookBorrow.dueDate}</td>
				<c:if test="${empty bookBorrow.returnDate}">
					<td><a
						href="${pageContext.request.contextPath}/bookborrow/returnBook?id=${bookBorrow.id}">Return
							Book</a></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>