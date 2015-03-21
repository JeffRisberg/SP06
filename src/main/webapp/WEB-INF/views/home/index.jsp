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
                <th>Amount</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="donation" items="${donationList}" varStatus="rowCounter">
                <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                    <td>${donation.charity.title}</td>
                    <td>${donation.amount}</td>
                    <td>${donation.dateCreated}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div id="measureResults">
    <div class="list">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Field Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="measure" items="${measures}" varStatus="rowCounter">
                <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                    <td>${measure.name}</td>
                    <td>${measure.fieldName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
