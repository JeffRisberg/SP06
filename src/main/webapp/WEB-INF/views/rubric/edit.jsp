<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script src="<c:url value="/ckeditor/ckeditor.js" />"></script>

<style>
    .error {
        color: #ff0000;
    }
</style>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<c:url var="saveUrl" value="/rubric/save"/>
<form:form method="post" action="${saveUrl}">
    <form:hidden path="id"/>
    <input type="hidden" name="finalURL" value="${finalURL}"/>
    <table>
        <tr>
            <td>Title:</td>
            <td><form:input path="title" size="40"/></td>
            <td><form:errors path="title" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Seq Num:</td>
            <td><form:input path="seqNum" size="40"/></td>
            <td><form:errors path="seqNum" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Body:</td>
            <td><form:textarea path="body" rows="5" cols="100"/></td>
            <td><form:errors path="body" cssClass="error"/></td>
        </tr>
    </table>

    <div class="botButtons">
        <input class="save" type="submit" value="Submit"/>
    </div>

    <form:hidden path="dateCreated"/>
</form:form>
<script>
    CKEDITOR.replace('body');
</script>