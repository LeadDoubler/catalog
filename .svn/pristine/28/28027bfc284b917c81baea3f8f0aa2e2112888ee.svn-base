<%@ include file="/taglibs.jsp"%>
<fmt:setBundle basename="StripesResources"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
 

<jsp:include page="pagination.jsp"/>

            <cat:userAccess role="3">
                    <stripes:link beanclass="${actionBean.class}" event="edit">New</stripes:link>
            </cat:userAccess>
<c:choose>
<c:when test="${paramValues['columns'] != null}">
    <c:set var="columns" value="${paramValues['columns']}"/>
</c:when>
<c:otherwise>
     <c:set var="columns" value="title"/>
</c:otherwise>
</c:choose>

<table>
    <thead>
        <tr>
        <c:forEach items="${columns}" var="column">
            <th><stripes:label for="${column}">${column}</stripes:label></th>
        </c:forEach>
    </tr>
    </thead>
    <tbody>
        
           <c:forEach items="${actionBean.pageComponents}" var="component">
               <tr>
                   <c:forEach items="${columns}" var="column">
            
            <td>
                <%
                com.blob.pas.DefaultActionBean ab = (com.blob.pas.DefaultActionBean)request.getAttribute("actionBean");
                out.print(ab.getValueByName(pageContext.getAttribute("component"),pageContext.getAttribute("column")));
              
                
                %></td>
        </c:forEach>
        <tr>
        </c:forEach>
        
    </tbody>
</table>

<table>
    <thead>
        <tr>
        <c:forEach items="${actionBean.columns}" var="column">
            <th><stripes:label for="${column.name}">${column.name}</stripes:label></th>
        </c:forEach>
    </tr>
    </thead>
    <tbody>
        <tr>
        <c:forEach items="${actionBean.pageComponents}" var="component">
        <c:forEach items="${actionBean.columns}" var="column">
            
            <td>
                <%
                com.blob.pas.DefaultActionBean ab = (com.blob.pas.DefaultActionBean)request.getAttribute("actionBean");
               out.print(ab.getValue(pageContext.getAttribute("component"),pageContext.getAttribute("column")));
               %></td>
        </c:forEach>
        </c:forEach>
        <tr>
    </tbody>
</table>            


               <ul>
                    <c:forEach items="${actionBean.pageComponents}" var="component">
                        
                        <li>       
                        <stripes:link beanclass="${actionBean.class}" event="view">${component.title}</stripes:link>
                        
                        </li>   
                        
                    </c:forEach>
                </ul>

<jsp:include page="pagination.jsp"/>