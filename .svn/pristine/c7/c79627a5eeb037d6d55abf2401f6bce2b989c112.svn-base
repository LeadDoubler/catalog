<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Afdeling: ${actionBean.department.name}">
    <stripes:layout-component name="contents">
        <cat:userAccess role="3">
            <stripes:link href="Department.action?edit&department=${actionBean.department.id}">Rediger</stripes:link>
            <stripes:link href="/location/Location.action?edit&location.department=${actionBean.department.id}">Tilføj lokale</stripes:link>
        </cat:userAccess>

        <table>
            <tr>
                <td>Navn</td><td>: </td></td>
                <td width="70%">${actionBean.department.name}</td>
            </tr>
            <tr>
                <td>Vej</td><td>: </td></td>
                <td>${actionBean.department.street}</td>
            </tr>
            <tr>
                <td>Postnr.</td><td>: </td></td>
                <td>${actionBean.department.zip}</td>
            </tr>
            <tr>
                <td>By</td><td>: </td></td>
                <td>${actionBean.department.city}</td>
            </tr>
            
            <tr colspan="4"><td><br><br><b>Lokaler:</b></td></tr>
            <tr>
                <td>Navn</td>
                <td>Kapacitet</td>
            </tr>

            <c:forEach items="${actionBean.department.locations}" var="location">
                <tr>
                    <td>${location.name}</td>
                    <td>${location.capacity}</td>
                    <td><a href="${pageContext.request.contextPath}/location/Location.action?edit&location=${location.id}">Rediger</a></td>
                </tr>
            </c:forEach>
        </table>
    </stripes:layout-component>
</stripes:layout-render>
