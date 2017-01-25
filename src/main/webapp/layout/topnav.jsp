<%@ include file="/taglibs.jsp"%>
<c:if test="${page.root.xml != null}">
<x:parse var="tabsXML" xml="${page.root.xml}"/>
<c:import var="tabs" url="/xsl/ShowTabs.xsl"/>                
<x:transform xml="${tabsXML}" xslt="${tabs}">    
<x:param name="ctx" value="${pageContext.request.contextPath}"/>
</x:transform>
</c:if>
