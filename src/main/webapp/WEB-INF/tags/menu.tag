<%@ include file="/taglibs.jsp"%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@ attribute name="root" required="true" %>
<%@ attribute name="rootName" required="false" %>


 <x:parse var="menuXML" xml="${page.menu}"/>
                        <c:import var="menu" url="/xsl/ShowMenu.xsl"/>                
                        <x:transform xml="${menuXML}" xslt="${menu}">    
                        <x:param name="ctx" value="${pageContext.request.contextPath}"/>
                        <x:param name="openedPage" value="${page.id}"/>
                        <x:param name="root" value="${root}"/>
                        <x:param name="rootName" value="${rootName}"/>
                    </x:transform>
