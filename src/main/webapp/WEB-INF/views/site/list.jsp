<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<div class="row" style="padding-top: 8px">
    <div class="col-md-6" style="font-size: 16px; font-weight: bold">
        Find, edit, and create your sites
    </div>
    <div class="col-md-6">
        <a href="<c:url value="/site/create" />" class="pull-right btn btn-default" style="padding: 0px 10px">
            Create New Site &raquo
        </a>
    </div>
</div>
<div id="siteResults">
    <div class="list">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Custom CSS</th>
                <th>Date Created</th>
                <th>Last Updated</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="site" items="${siteList}" varStatus="rowCounter">
                <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                    <td>
                        <a href="<c:url value="/site/show/${site.id}" />">${site.name}</a>
                    </td>
                    <td>${site.customCSS}</td>
                    <td>${site.dateCreated}</td>
                    <td>${site.lastUpdated}</td>
                    <td>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/page/${site.id}" />">View</a>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/site/edit/${site.id}" />">Edit</a>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/site/delete/${site.id}" />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty siteList}">
                <tr>
                    <td colspan="999">No sites found</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
