<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>First Name:</td>
        <td>${user.firstName}</td>
    </tr>
    <tr>
        <td>Last Name:</td>
        <td>${user.lastName}</td>
    </tr>
    <tr>
        <td>Email:</td>
        <td>${user.email}</td>
    </tr>
    <tr>
        <td>Vendor:</td>
        <td>${user.vendor.name}</td>
    </tr>
</table>

<div class="botButtons">
    <a href="<c:url value="/user/edit/${user.id}" />" class="btn btn-default">Edit</a>
</div>