<%@tag description="Checks for user access to resource" pageEncoding="UTF-8"%>

   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.user.id eq actionBean.user.id}">
    <jsp:doBody/>
</c:if>
           
	   
	   
