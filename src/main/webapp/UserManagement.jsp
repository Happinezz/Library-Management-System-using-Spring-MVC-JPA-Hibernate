<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>User Management</title>
</head>
<body>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<h3>User Management</h3>
		<table>
			<tr>
				<td><a href="${pageContext.request.contextPath}">Home</a></td>
			</tr>
			<tr>
				<td><a href="${pageContext.request.contextPath}/user/add">Add
						User</a></td>
			</tr>
			<tr>
				<td><a href="${pageContext.request.contextPath}/user/list">User
						List</a></td>
			</tr>
		</table>
	</sec:authorize>
</body>
</html>