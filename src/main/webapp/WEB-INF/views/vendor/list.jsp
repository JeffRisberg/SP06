<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<div class="row" style="padding-top: 8px">
    <div class="col-md-6" style="font-size: 16px; font-weight: bold">
        Find, edit, and create your Vendors
    </div>
    <div class="col-md-6">
        <a href="<c:url value="/vendor/create" />" class="pull-right btn btn-default" style="padding: 0px 10px">
            Create New Vendor &raquo
        </a>
    </div>
</div>

<div id="vendorResults">
    <div class="list">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Date Created</th>
                <th>Last Updated</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="vendor" items="${vendorList}" varStatus="rowCounter">
                <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                    <td>
                        <a href="<c:url value="/vendor/show/${vendor.id}" />">${vendor.name}</a>
                    </td>
                    <td>${vendor.dateCreated}</td>
                    <td>${vendor.lastUpdated}</td>
                    <td>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/vendor/edit/${vendor.id}" />">Edit</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty vendorList}">
                <tr>
                    <td colspan="999">No vendors found</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
