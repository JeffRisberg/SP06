<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<div class="row" style="padding-top: 8px">
    <div class="col-md-6" style="font-size: 16px; font-weight: bold">
        Find, edit, and create your Users
    </div>
    <div class="col-md-6">
        <a href="<c:url value="/user/create" />" class="pull-right btn btn-default" style="padding: 0px 10px">
            Create New User &raquo
        </a>
    </div>
</div>

<div id="vendorResults">
    <div class="list">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>
                        <a href="<c:url value="/show/${user.id}" />">${user.lastName}, ${user.firstName}</a>
                    </td>
                    <td>${user.email}</td>
                    <td>
                        <a href="<c:url value="/edit/${user.id}" />" class="btn btn-default" style="padding: 0px 13px">
                            Edit
                        </a>
                    </td>
                    <td>
                        <a href="<c:url value="/delete/${user.id}" />" class="btn btn-default"
                           style="padding: 0px 13px">
                            Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty userList}">
                <tr>
                    <td colspan="999">No users found</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>


