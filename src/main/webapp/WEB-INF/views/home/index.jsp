<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    .list {
        margin: 15px 0px;
    }
</style>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<h2>SP06 Home</h2>

<h4>Recent Donations</h4>

<div id="donationResults">
    <div class="list">
        <table class="table">
            <thead>
            <tr>
                <th>Charity</th>
                <th style="text-align: right">Amount</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="donation" items="${donationList}" varStatus="rowCounter">
                <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                    <td>${donation.charity.title}</td>
                    <td style="text-align: right">${donation.amount}</td>
                    <td>${donation.dateCreated}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="row" style="margin-bottom: 20px">
    <div class="col-md-10">
        <a class="btn btn-primary" href="<c:url value="/reporting" />">Reporting</a>
        <a class="btn btn-primary" href="<c:url value="/donation" />">Donations</a>
        <a class="btn btn-primary" href="<c:url value="/charity" />">Charities</a>
        <a class="btn btn-primary" href="<c:url value="/user" />">Users</a>
    </div>
    <div class="col-md-2">
        <a class="btn btn-primary pull-right" href="<c:url value="/adminHome" />">Admin</a>
    </div>
</div>
