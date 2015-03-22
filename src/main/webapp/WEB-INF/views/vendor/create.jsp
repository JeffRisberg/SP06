<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Create a new Vendor</h3>

<c:url var="saveUrl" value="/vendor/save"/>
<form:form method="post" action="${saveUrl}">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Name:</td>
            <td><form:input path="name" size="40"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>
    </table>

    <div class="botButtons">
        <input class="save" type="submit" value="Submit"/>
    </div>
</form:form>
