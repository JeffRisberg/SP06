<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>Name:</td>
        <td>${charity.name}</td>
    </tr>
</table>

<div class="botButtons">
    <a href="<c:url value="/charity/edit/${charity.id}" />" class="btn btn-default">Edit</a>
</div>
