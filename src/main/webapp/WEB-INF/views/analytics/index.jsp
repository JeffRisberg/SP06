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

Analytics.

<div id="dimensionResults">
    <div class="list">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Table Name</th>
                <th>Field Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="dimension" items="${dimensions}" varStatus="rowCounter">
                <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                    <td>${dimension.name}</td>
                    <td>${dimension.tableName}</td>
                    <td>${dimension.fieldName}</td>
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
