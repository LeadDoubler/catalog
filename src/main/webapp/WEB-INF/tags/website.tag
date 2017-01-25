<%@ include file="/taglibs.jsp"%>
<%@ taglib prefix="cat" tagdir="/WEB-INF/tags" %>

    <c:choose>
    <c:when test="${page eq null}">
       <jsp:useBean id="pageManager" scope="page" class="com.asap.catalog.dao.manager.PageManager" />

        <c:set var="page" value="${pageManager.danishLandingPage}"/>
        
    </c:when>
    <c:otherwise>
       
    </c:otherwise>
</c:choose>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<c:choose>
<c:when test="${ requestScope['page'] eq null}">
    <fmt:setLocale value="${pageContext.request.locale}"/>        
</c:when>
<c:otherwise>
    <fmt:setLocale value="${requestScope['page'].locale}"/>        
</c:otherwise>    
</c:choose>
<fmt:setBundle basename="StripesResources"/>
<%@ include file="/layout/javascripts.jsp" %>

<html xml:lang="da" xmlns="http://www.w3.org/1999/xhtml">     
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <meta name="ROBOTS" content="NOINDEX,NOFOLLOW"/> 
		
    <title>${title}</title>

   <meta name="description" content="${description}" />
 
   <meta name="keywords" content="${keywords}" />
		<script type="text/javascript"> </script>

		<style type="text/css">
			@import url(${pageContext.request.contextPath}/styles/standard.css);
			@import url(${pageContext.request.contextPath}/styles/layout.css);
			@import url(${pageContext.request.contextPath}/styles/nav.css);
		</style>

		<!--[if IE]>
			<style type="text/css">
				@import url(styles/IEbugs.css);
			</style>
		<![endif]-->
                
                
            <%@ include file="/layout/javascripts.jsp" %>
            <script src="hideEmails.js" type="text/javascript"></script>
</head>

<jsp:doBody/>


</html>
  

