<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="cat" tagdir="/WEB-INF/tags" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
   <%@attribute name="label" required="false"%>
   <%@attribute name="value" required="false" %>
   <%@ attribute name="layout" required="false" %>



<c:if test="${not empty value}">
    
<c:choose>
<c:when test="${layout eq 'row'}">
    <tr>
        <td>${label}
        </td>
        <td>:</td>
        <td>${value}
        </td>
    </div>
</c:when>
<c:otherwise>
    <div class="display_property">
        <span class="form_left">
            ${label}
        </span>
        <span class="form_right">
            ${value}
        </span>
    </div>
</c:otherwise>
</c:choose>


</c:if>

