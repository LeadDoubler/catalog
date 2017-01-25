<%@ include file="/taglibs.jsp"%>

  
    
    <cat:userAccess role="3">
    <a href="${pageContext.request.contextPath}/pickableObjects/PickableObjects.action?edit">Indsæt nyt objekt</a>
</cat:userAccess>
<c:forEach items="${actionBean.components}" var="object">
    
        <%@ include file="inlineView.jsp" %>
<br/>     
</c:forEach>

