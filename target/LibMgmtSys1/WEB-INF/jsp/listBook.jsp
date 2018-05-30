<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book List</title>
</head>
<body>
	<table>
		<tr>
			<td>ISBN</td>
			<td>Title</td>
			<td>Author</td>
			<td>Publisher</td>
			<td>Quantity</td>
			<td>Price</td>
		</tr>
		<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.isbn}</td>
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.publisher}</td>
				<td>${book.quantity}</td>
				<td>${book.price}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>