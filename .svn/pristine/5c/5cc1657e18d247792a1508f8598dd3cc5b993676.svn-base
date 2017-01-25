<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

 <jsp:useBean id="pageActionBean" scope="page" class="com.asap.catalog.dao.manager.CategoryManager" />

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

   <%@attribute name="part" required="true"%>
   <%@attribute name="type" required="false"%>
   <%@ attribute name="site" required="false" %>
   <%@ attribute name="inherited" required="false" %>
   <%@ attribute name="def" required="false" %>
   <%@ attribute name="selectedPage" required="false" %>
   
    <c:if test="${site eq 'yes'}">
        <c:set var="page" value="${page.root}"/>
    </c:if>

    <%@attribute name="markupstart" required="false"%>
    <%@attribute name="markupend" required="false"%>
    
    <c:choose>
        <c:when test="${inherited eq 'no'}">
             <c:set var="content" value="${page.parts[part].content}"/>
        </c:when>
        <c:otherwise>
             <c:set var="content" value="${page.viewParts[part].content}"/>
        </c:otherwise>
    </c:choose>
        
    <c:if test="${content eq null or content eq ''}">
        <c:set var="content" value="${def}"/>
    </c:if>

    <c:if test="${selectedPage != null}">
        <c:set var="page" value="${selectedPage}"/>
    </c:if>        
    
       <c:choose>
       <c:when test="${page != null}">
          <c:set var="page" value="${page}"/>   
       </c:when>
       <c:otherwise>
           <c:set var="page" value="${actionBean.context.page}"/>  
       </c:otherwise>
   </c:choose>
  
    <c:if test="${ sessionScope.user.role.value > 3 and page != null and type eq 'file'}">
        <span class="actions">
                    <a href="#" onclick="ajax('fckeditorWindow','${pageContext.request.contextPath}/part/Part.action;jsessionid=${actionBean.context.request.session.id}?inline&part.page=${page}&part.name=${part}&part=${page.parts[part]}&type=${type}');return false"><img width="12" height="12" alt="Rediger"  src="${pageContext.request.contextPath}/green_edit.gif"/></a>
                </span>
               
    </c:if>
   
    
${markupstart}
   <span style="margin:0;padding:0;" class="insertedPart" id="part_${part}">
        <c:choose>
            <c:when test="${type eq 'link'}">
                <a href="${pageContext.request.contextPath}${page.viewParts[part].linkTo}">${content}</a>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${content eq null or content eq ''}">
                        <jsp:doBody/>
                    </c:when>
                    <c:otherwise>
                        ${content}
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </span>
${markupend}

   
  
    <c:if test="${ sessionScope.user.role.value > 3 and page != null and type != 'file'}">
        <span class="actions">
                    <a href="#" onclick="ajax('fckeditorWindow','${pageContext.request.contextPath}/part/Part.action;jsessionid=${actionBean.context.request.session.id}?inline&part.page=${page}&part.name=${part}&part=${page.parts[part]}&type=${type}');return false"><img width="12" height="12" alt="Rediger"  src="${pageContext.request.contextPath}/green_edit.gif"/></a>
                </span>
                
                
                
                <%--
        <c:choose>
              <c:when test="${type eq 'link'}">                
                    <a class="actionsLink" href="#" onclick="var rand = Math.random()*9999999;new Ajax.Updater('fckeditorWindow','${pageContext.request.contextPath}/part/Part.action?inline&part.page=${page}&part.name=${part}&part=${page.parts[part]}&type=${type}&random='+rand,{method:'get'});return false"><img width="12" height="12" alt="Rediger" src="${pageContext.request.contextPath}/green_edit.gif"/></a>
            </c:when>
            <c:when test="${type eq 'heading'}">
                <span class="actions">
                    <a href="#" onclick="var rand = Math.random()*9999999;var url = '${pageContext.request.contextPath}/part/Part.action?inline&part.page=${page}&part.name=${part}&part=${page.parts[part]}&type=${type}&random='+rand;new Ajax.Updater('fckeditorWindow',url,{method:'get'});return false"><img width="12" height="12" alt="Rediger"  src="${pageContext.request.contextPath}/green_edit.gif"/></a>
                </span>
            </c:when>
            <c:otherwise>
                 <span class="actions">
                    <a href="#" onclick="var rand = Math.random()*9999999;new Ajax.Updater('fckeditorWindow','${pageContext.request.contextPath}/part/Part.action?inline&part.page=${page}&part.name=${part}&part=${page.parts[part]}&type=${type}&random='+rand,{method:'get'});return false"><img width="12" height="12" alt="Rediger" src="${pageContext.request.contextPath}/green_edit.gif"/></a>
                </span>
            </c:otherwise>
        </c:choose>--%>
    </c:if>

    

    
    
    
   
