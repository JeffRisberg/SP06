<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <c:forEach items="${row}" var="box">
        <div class="col-md-${box.width}">
            <c:forEach items="${box.rubrics}" var="rubric" varStatus="status">
                <a href="<c:url value="/rubric/edit/${rubric.id}?finalURL=/page/${site.id}" />"
                   style="position:absolute; right: 0px; top:${(status.count - 1)*20}px">
                    <span class="glyphicon glyphicon-pencil"></span>
                </a>
                <c:if test="${not empty rubric.title}">
                    <div style="background: #eee; font-size: 16px; font-weight: bold">${rubric.title}</div>
                </c:if>
                <c:if test="${not empty rubric.body}">
                    <div style="background: white">${rubric.body}</div>
                </c:if>
            </c:forEach>
        </div>
    </c:forEach>
    <div style="clear:both"></div>
</div>
