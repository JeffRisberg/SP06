<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<div class="row" style="padding-top: 8px">
    <div class="col-md-6" style="font-size: 16px; font-weight: bold">
        Find, edit, and create your Boxes
    </div>
    <div class="col-md-6">
        <a href="<c:url value="/charity/create" />" class="pull-right btn btn-default" style="padding: 0px 10px">
            Create New Box &raquo
        </a>
    </div>
</div>
<div id="charityResults">
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
            <c:forEach var="charity" items="${charityList}" varStatus="rowCounter">
                <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                    <td>
                        <a href="<c:url value="/charity/show/${charity.id}" />">${charity.name}</a>
                    </td>                    
                    <td>${charity.dateCreated}</td>
                    <td>${charity.lastUpdated}</td>
                    <td>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/charity/edit/${charity.id}" />">Edit</a>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/charity/delete/${charity.id}" />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty charityList}">
                <tr>
                    <td colspan="999">No charities found</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
