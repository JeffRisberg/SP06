<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>Date:</td>
        <td>${donation.dateCreated}</td>
    </tr>
    <tr>
        <td>Donor:</td>
        <td>${donation.dateCreated}</td>
    </tr>
    <tr>
        <td>Charity:</td>
        <td>${donation.charity.title}</td>
    </tr>
    <tr>
        <td>Amount:</td>
        <td>${donation.amount}</td>
    </tr>
</table>

<div class="botButtons">
    <a href="<c:url value="/doantion/edit/${donation.id}" />" class="btn btn-default">Edit</a>
</div>
