<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>Site:</td>
        <td>${box.site.name}</td>
    </tr>
    <tr>
        <td>Title:</td>
        <td>${box.title}</td>
    </tr>
    <tr>
        <td>Row:</td>
        <td>${box.rowIndex}</td>
    </tr>
    <tr>
        <td>Col:</td>
        <td>${box.colIndex}</td>
    </tr>
    <tr>
        <td>Width:</td>
        <td>${box.width}</td>
    </tr>
</table>

<div class="botButtons">
    <a href="<c:url value="/box/edit/${box.id}" />" class="btn btn-default">Edit</a>
</div>
