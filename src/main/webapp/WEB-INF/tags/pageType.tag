<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="type" required="true"%>
<c:if test="${page.pageType eq type and method eq 'showPage'}">
        <jsp:doBody/> 
</c:if>