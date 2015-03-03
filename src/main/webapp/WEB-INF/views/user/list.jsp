<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h1>Users</h1>
            <form:form method="post" action="save" commandName="user" class="form-horizontal">
            <div class="control-group">
                <form:label cssClass="control-label" path="firstName">First Name:</form:label>
                <div class="controls">
                    <form:input path="firstName"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="lastName">Last Name:</form:label>
                <div class="controls">
                    <form:input path="lastName"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="email">Email:</form:label>
                <div class="controls">
                    <form:input path="email"/>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <input type="submit" value="Add User" class="btn"/>
                    </form:form>
                </div>
            </div>

            <c:if test="${!empty users}">
                <h3>Users</h3>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
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
                                <a href="<c:url value="/delete/${user.id}" />" class="btn btn-default" style="padding: 0px 13px">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
</div>
