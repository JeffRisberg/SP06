<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
  .list { margin: 15px 0px; }
</style>

<c:if test="${flashMessage != null}">
  <div class="message">${flashMessage}</div>
</c:if>

<div id="administration">
  <div class="list">  
    <c:forEach var="adminPanel" items="${adminPanelList}" varStatus="rowCounter">          
      <div style="font-size: 16px; padding: 5px;">
          <a href="<c:url value="${adminPanel.url}" />">
          ${adminPanel.name}
          </a>
      </div>
    </c:forEach>           
  </div>
</div>
