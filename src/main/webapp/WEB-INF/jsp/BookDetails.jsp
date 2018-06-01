<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Details</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/BookManagement.jsp">Books
		Home</a>
	<h3>Book Details</h3>
	<form:form method="POST" commandName="book"
		action="${pageContext.request.contextPath}/book/update">
		<table>
			<tr>
				<td>Id:</td>
				<td><form:input path="id" readonly="true" /></td>
			</tr>
			<tr>
				<td>ISBN:</td>
				<td><form:input path="isbn" /></td>
			</tr>
			<tr>
				<td>Title:</td>
				<td><form:input path="title" /></td>
			</tr>
			<tr>
				<td>Author:</td>
				<td><form:input path="author" /></td>
			</tr>
			<tr>
				<td>Publisher:</td>
				<td><form:input path="publisher" /></td>
			</tr>
			<tr>
				<td>Total Copies:</td>
				<td><form:input path="totalCopies" /></td>
			</tr>
			<tr>
				<td>Available Copies:</td>
				<td><form:input path="availableCopies" readonly="true" /></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><form:input path="price" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Update" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>