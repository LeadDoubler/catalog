
<%@ attribute name="columns" required="false" %>
<table>
    <thead>
        <tr>
        <c:forEach items="${columns}" var="column">
            <th><stripes:label for="${column}">${column}</stripes:label></th>
        </c:forEach>
    </tr>
    </thead>
    <tbody>
        <tr>
        <c:forEach items="${actionBean.pageComponents}" var="component">
        <c:forEach items="${columns}" var="column">
            
            <td>
                <%
                com.blob.pas.DefaultActionBean ab = (com.blob.pas.DefaultActionBean)request.getAttribute("actionBean");
               %></td>
        </c:forEach>
        </c:forEach>
        <tr>
    </tbody>
</table>  