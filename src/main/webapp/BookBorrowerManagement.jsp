<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Book Borrowers History</title>
</head>
<body>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<h3>Book Borrowers Details</h3>
		<table>
			<tr>
				<td><a href="${pageContext.request.contextPath}">Home</a></td>
			</tr>
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/bookborrow/list">All
						History</a></td>
			</tr>
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/bookborrow/listBorrowedBook">Only
						Borrowed Books</a></td>
			</tr>
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/bookborrow/listReturnedBook">Only
						Returned Books</a></td>
			</tr>
		</table>
	</sec:authorize>
</body>
</html>

