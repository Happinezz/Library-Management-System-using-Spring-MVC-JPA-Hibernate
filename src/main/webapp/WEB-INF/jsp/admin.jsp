<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator Home</title>
</head>
<body>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<table>

			<tr>
				<td>
					<!-- For login user --> <c:url value="/logout" var="logoutUrl" />
					<form action="${logoutUrl}" method="post" id="logoutForm">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form> <script>
						function formSubmit() {
							document.getElementById("logoutForm").submit();
						}
					</script> <c:if test="${pageContext.request.userPrincipal.name != null}">
					Hi ${pageContext.request.userPrincipal.name}!
							<a href="javascript:formSubmit()">Logout</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td><a href="./BookManagement.jsp">Manage Books</a></td>
			</tr>
			<tr>
				<td><a href="./UserManagement.jsp">Manage Users</a></td>
			</tr>
			<tr>
				<td><a href="./BookBorrowerManagement.jsp">Books Borrowing History</a></td>
			</tr>
		</table>
	</sec:authorize>
</body>
</html>