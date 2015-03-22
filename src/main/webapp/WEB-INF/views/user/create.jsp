<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="saveUrl" value="/user/save"/>
<form:form method="post" action="${saveURL}">
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
        </div>
    </div>
</form:form>