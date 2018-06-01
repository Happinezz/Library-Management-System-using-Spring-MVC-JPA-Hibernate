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
	<a href="${pageContext.request.contextPath}/UserManagement.jsp">Users
		Home</a>
	<h3>Books List</h3>
	<form:form method="GET" action="search">
		<table>
			<tr>
				<td>Search User By:</td>
				<td><select name="property">
						<option value="name">Name</option>
						<option value="contactNo">ContactNo</option>
						<option value="email">Email</option>
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
			<td>Name</td>
			<td>Email</td>
			<td>Contact No</td>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.email}</td>
				<td>${user.contactNo}</td>
				<td><a
					href="${pageContext.request.contextPath}/user/get/${user.id}">View/Edit</a>
				</td>
				<td><a
					href="${pageContext.request.contextPath}/user/delete/${user.id}">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>