<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Book Management</title>
</head>
<body>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<h3>Book Management</h3>
		<table>
			<tr>
				<td><a href="${pageContext.request.contextPath}">Home</a></td>
			</tr>

			<tr>
				<td><a href="${pageContext.request.contextPath}/book/add">Add
						Book</a></td>
			</tr>
			<tr>
				<td><a href="${pageContext.request.contextPath}/book/all">Book
						List</a></td>
			</tr>
		</table>
	</sec:authorize>
</body>
</html>