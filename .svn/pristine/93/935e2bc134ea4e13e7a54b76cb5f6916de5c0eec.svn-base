<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

 <jsp:useBean id="pageActionBean" scope="page" class="com.asap.catalog.dao.manager.CategoryManager" />
            

<%-- Taglib directives can be specified here: --%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%--
<%@attribute name="title" required="true"%>
<%@attribute name="author"%>
<%@attribute name="price_info" fragment="true"%>
--%>

<%-- Use expression language to work with normal attributes or use --%>
<%-- the <jsp:invoke> or <jsp:doBody> actions to invoke JSP fragments or tag body: --%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

   <%@attribute name="part" required="true"%>
     

           
    
   <c:choose>
       <c:when test="${page != null}">
          <c:set var="page" value="${page}"/>   
       </c:when>
       <c:otherwise>
           <c:set var="page" value="${actionBean.context.page}"/>  
       </c:otherwise>
   </c:choose>
  
            <c:if test="${ sessionScope.user.role.value > 3 and page != null }">
       
                    <a href="#" onclick="new Ajax.Updater('fckeditorWindow','${pageContext.request.contextPath}/part/Part.action?inline&part.page=${page}&part.name=${part}&part=${page.parts[part]}&type=${type}',{method:'get'});this.style.display='none';return false">
             </c:if>
             ${page.viewParts[part].content}
             <c:if test="${ sessionScope.user.role.value > 3 and page != null }">
                    </a>
             </c:if>
                   
                
                    
           
    
    
    
    
   
