<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@ attribute name="url" required="true" %>


 <a href="#" onclick="new Ajax.Updater('content','${pageContext.request.contextPath}/${url}',{method:'get'});">
     <jsp:doBody/>
 </a>