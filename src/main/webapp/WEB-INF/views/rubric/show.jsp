<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>Title:</td>
        <td>${rubric.title}</td>
    </tr>
    <tr>
        <td>SeqNum:</td>
        <td>${rubric.seqNum}</td>
    </tr>
    <tr>
        <td>Body:</td>
        <td>${rubric.body}</td>
    </tr>
</table>

<div class="botButtons">
    <a href="<c:url value="/rubric/edit/${rubric.id}" />" class="btn btn-default">Edit</a>
</div>
