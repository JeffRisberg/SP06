<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    .error {
        color: #ff0000;
    }
</style>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<c:url var="saveUrl" value="/box/save"/>
<form:form method="post" action="${saveUrl}">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Site:</td>
            <td>
                <form:select path="site">
                    <form:options items="${siteList}" itemValue="id" itemLabel="name"/>
                </form:select>
            </td>
            <td><form:errors path="title" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Title:</td>
            <td><form:input path="title" size="40"/></td>
            <td><form:errors path="title" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Row Index:</td>
            <td><form:input path="rowIndex" size="40"/></td>
            <td><form:errors path="rowIndex" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Col Index:</td>
            <td><form:input path="colIndex" size="40"/></td>
            <td><form:errors path="colIndex" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Width:</td>
            <td><form:input path="width" size="40"/></td>
            <td><form:errors path="width" cssClass="error"/></td>
        </tr>
    </table>

    <div class="botButtons">
        <input class="save" type="submit" value="Submit"/>
    </div>

    <form:hidden path="dateCreated"/>
</form:form>
