<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<c:if test="${not empty site.name}">
    <div style="font-size: 16px; font-weight: bold; padding: 15px 0px 15px 0px">
        Custom giving site for ${site.name}
    </div>
</c:if>
<c:if test="${empty site}">
    <div style="font-size: 16px; font-weight: bold; padding: 15px 0px 15px 0px">
        Page not found
    </div>
</c:if>

<c:forEach items="${rowList}" var="row">
    <%@include file="row.jsp" %>
</c:forEach>