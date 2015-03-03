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
        <a href="<c:url value="/box/create" />" class="pull-right btn btn-default" style="padding: 0px 10px">
            Create New Box &raquo
        </a>
    </div>
</div>
<div id="boxResults">
    <div class="list">
        <table class="table">
            <thead>
            <tr>
                <th>Site</th>
                <th>Title</th>
                <th>Row Index</th>
                <th>Col Index</th>
                <th>Width</th>
                <th>Date Created</th>
                <th>Last Updated</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="box" items="${boxList}" varStatus="rowCounter">
                <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                    <td>${box.site.name}</td>
                    <td>
                        <a href="<c:url value="/box/show/${box.id}" />">${box.title}</a>
                    </td>
                    <td>${box.rowIndex}</td>
                    <td>${box.colIndex}</td>
                    <td>${box.width}</td>
                    <td>${box.dateCreated}</td>
                    <td>${box.lastUpdated}</td>
                    <td>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/box/edit/${box.id}" />">Edit</a>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/box/delete/${box.id}" />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty boxList}">
                <tr>
                    <td colspan="999">No boxs found</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
