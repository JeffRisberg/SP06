<%@page isErrorPage="true" session="false" contentType="text/html"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
	<c:if test="${not empty exception}">
		<ul>
			<li><fmt:message key="error.status" /> &nbsp;
				${pageContext.errorData.statusCode}</li>
			<li><fmt:message key="error.exception" /> &nbsp;
				${pageContext.errorData.throwable}</li>
			<li><fmt:message key="error.stack.trace" /> &nbsp; <c:forEach
					items="${exception.stackTrace}" var="trace">
					<c:out value="${trace}" />
					<br />
				</c:forEach>
			</li>
		</ul>
	</c:if>
</body>
</html>
