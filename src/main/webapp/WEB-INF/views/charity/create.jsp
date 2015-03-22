<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Create a new charity</h3>

<c:url var="saveUrl" value="/charity/save"/>
<form:form method="post" action="${saveUrl}">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Title:</td>
            <td><form:input path="title" size="40"/></td>
            <td><form:errors path="title" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><form:input path="description" size="40"/></td>
            <td><form:errors path="description" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Ein:</td>
            <td><form:input path="ein" size="40"/></td>
            <td><form:errors path="ein" cssClass="error"/></td>
        </tr>
    </table>

    <div class="botButtons">
        <input class="save" type="submit" value="Submit"/>
    </div>
</form:form>
