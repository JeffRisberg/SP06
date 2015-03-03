<%@page isErrorPage="true" session="false" contentType="text/html"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
<title><fmt:message key="error.accessdeniedexception.title" /></title>
<body>
	<h2 class="error">
		<fmt:message key="error.accessdeniedexception.head" />
	</h2>

	<div>
		<fmt:message key="error.accessdeniedexception.message" />
	</div>

	<tiles:insertDefinition name="exceptionDetails" />
	
</body>
</html>
