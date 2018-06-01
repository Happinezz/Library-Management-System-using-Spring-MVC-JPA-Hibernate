<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>User Management</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/UserManagement.jsp">Users
		Home</a>
	<h3>Add New User</h3>
	<form:form method="POST" commandName="user" action="add">
		<table>
			<tr>
				<td>Name:</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>Contact No:</td>
				<td><form:input path="contactNo" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Save User" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>