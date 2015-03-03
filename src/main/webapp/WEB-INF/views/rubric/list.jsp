<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<div class="row" style="padding-top: 8px">
    <div class="col-md-6" style="font-size: 16px; font-weight: bold">
        Find, edit, and create your Rubrics
    </div>
    <div class="col-md-6">
        <a href="<c:url value="/rubric/create" />" class="pull-right btn btn-default" style="padding: 0px 10px">
            Create New Rubric &raquo
        </a>
    </div>
</div>
<div id="rubricResults">
    <div class="list">
        <table class="table">
            <thead>
            <tr>
                <th>Title</th>
                <th>Seq Num</th>
                <th>Body</th>
                <th>Date Created</th>
                <th>Last Updated</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="rubric" items="${rubricList}" varStatus="rowCounter">
                <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                    <td>
                        <a href="<c:url value="/rubric/show/${rubric.id}" />">${rubric.title}</a>
                    </td>
                    <td>${rubric.seqNum}</td>
                    <td>${rubric.body}</td>
                    <td>${rubric.dateCreated}</td>
                    <td>${rubric.lastUpdated}</td>
                    <td style="white-space: nowrap">
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/rubric/edit/${rubric.id}" />">Edit</a>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/rubric/delete/${rubric.id}" />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty rubricList}">
                <tr>
                    <td colspan="999">No rubrics found</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
