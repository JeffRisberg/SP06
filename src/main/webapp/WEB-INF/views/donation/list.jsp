<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<div class="row" style="padding-top: 8px">
    <div class="col-md-6" style="font-size: 16px; font-weight: bold">
        Find, edit, and create your Donation
    </div>
    <div class="col-md-6">
        <a href="<c:url value="/donation/create" />" class="pull-right btn btn-default" style="padding: 0px 10px">
            Create New Donation &raquo
        </a>
    </div>
</div>
<div id="donationResults">
    <div class="list">
        <table class="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Date Created</th>
                <th>Donor</th>
                <th>Charity</th>
                <th style="text-align: right">Amount</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="donation" items="${donationList}" varStatus="rowCounter">
                <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                    <td>
                        <a href="<c:url value="/donation/show/${donation.id}" />">${donation.id}</a>
                    </td>
                    <td>${donation.dateCreated}</td>
                    <td>${donation.donor.email}</td>
                    <td>${donation.charity.title}</td>
                    <td style="text-align: right">${donation.amount}</td>
                    <td>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/donation/edit/${donation.id}" />">Edit</a>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/donation/delete/${donation.id}" />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty donationList}">
                <tr>
                    <td colspan="999">No donations found</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
