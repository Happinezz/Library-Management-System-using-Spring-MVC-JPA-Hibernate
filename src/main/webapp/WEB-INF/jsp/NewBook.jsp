<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Book Management</title>
</head>
<body>
	<h3>Add New Book</h3>
	<form:form method="POST" commandName="book" action="add">
		<table>
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
				<td>Quantity:</td>
				<td><form:input path="qty" /></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><form:input path="price" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Save Book" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>