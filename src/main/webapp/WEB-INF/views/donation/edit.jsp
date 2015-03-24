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

<c:url var="saveUrl" value="/donation/save"/>
<form:form method="post" action="${saveUrl}">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Charity:</td>
            <td>
                <form:select path="charity">
                    <form:options items="${charityList}" itemValue="id" itemLabel="name"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>Amount:</td>
            <td><form:input path="amount" size="40"/></td>
            <td><form:errors path="amount" cssClass="error"/></td>
        </tr>
    </table>

    <div class="botButtons">
        <input class="save" type="submit" value="Submit"/>
    </div>

    <form:hidden path="dateCreated"/>
</form:form>
