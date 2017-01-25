<%@ include file="/taglibs.jsp"%> 
<c:if test="${page != null}">
<x:parse var="pages" xml="${page.menu}"/>
<c:import var="pagesToList" url="/xsl/ShowMenu.xsl"/>
<x:transform xml="${pages}" xslt="${pagesToList}">
  <x:param name="ctx" value="${pageContext.request.contextPath}"/>  
  <x:param name="openedPage" value="${page.id}"/>
  <x:param name="root" value="${page.root}"/>
  <x:param name="rootName" value="${page.root.title}"/>
</x:transform>
</c:if>