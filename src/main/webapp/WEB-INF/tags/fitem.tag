<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cat" tagdir="/WEB-INF/tags" %>

  <%@attribute name="label" required="false"%>
   <%@attribute name="id" required="false"%>

   <div>
       <span class="form_left">
    
    <c:choose>
<c:when test="${id eq null}">
${label}
</c:when>
<c:otherwise>
     <cat:part part="${id}" type="area"> ${label}</cat:part>
</c:otherwise>
</c:choose>
       </span>
       <span class="form_right">
           <jsp:doBody/>
           
       </span>
   </div>
   
 
   
