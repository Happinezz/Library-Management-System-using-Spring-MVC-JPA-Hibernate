<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1>${title}</h1>
	<sec:authorize access="hasRole('ROLE_USER')">
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
				<td><a href="${pageContext.request.contextPath}/user/get">Edit
						Profile</a></td>
			</tr>
			<tr>
				<td><a href="${pageContext.request.contextPath}/book/available">Borrow
						Book</a></td>
			</tr>
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/bookborrow/listBorrowedBook">Return
						Book</a></td>
			</tr>
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/bookborrow/list">Borrowing
						History</a></td>
			</tr>
		</table>
	</sec:authorize>
</body>
</html>