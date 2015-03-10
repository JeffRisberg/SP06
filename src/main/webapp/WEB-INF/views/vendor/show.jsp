<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>Name:</td>
        <td>${vendor.name}</td>
    </tr>   
    <tr>
        <td>Date Created:</td>
        <td>${vendor.dateCreated}</td>
    </tr>
    <tr>
        <td>Last Updated:</td>
        <td>${vendor.lastUpdated}</td>
    </tr>
</table>

<div class="botButtons">
    <a href="<c:url value="/vendor/${vendor.id}" />" class="btn btn-default">Edit</a>
</div>
