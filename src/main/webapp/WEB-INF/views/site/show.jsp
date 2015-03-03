<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>Name:</td>
        <td>${site.name}</td>
    </tr>
    <tr>
        <td>Custom CSS:</td>
        <td>${site.customCSS}</td>
    </tr>
    <tr>
        <td>Date Created:</td>
        <td>${site.dateCreated}</td>
    </tr>
    <tr>
        <td>Last Updated:</td>
        <td>${site.lastUpdated}</td>
    </tr>
</table>

<c:if test="${not empty site.boxes}">
    <h4>Contains boxes:</h4>

    <div>
        <c:forEach var="box" items="${site.boxes}" varStatus="rowCounter">
            <span>${box.title}</span>
        </c:forEach>
    </div>
</c:if>

<div class="botButtons">
    <a href="<c:url value="/site/${site.id}" />" class="btn btn-default">Edit</a>
</div>
