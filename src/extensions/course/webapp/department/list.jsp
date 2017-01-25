<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Afdelinger">
    <stripes:layout-component name="contents">

        <jsp:useBean id="departmentManager" scope="page" class="com.asap.catalog.dao.manager.DepartmentManager" />
        
        <cat:userAccess role="3">
            <stripes:link href="${pageContext.request.contextPath}/department/Department.action?edit">Tilføj afdeling</stripes:link>
        </cat:userAccess>

        <table>
            <tr>
                <td><b>Navn</b></td>
                <td><b>Vej</b></td>
                <td><b>Postnr.</b></td>
                <td><b>By</b></td>
            </tr>

            <c:forEach items="${departmentManager.allDepartment}" var="department">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/department/Department.action?view&department=${department.id}">${department.name}</a></td>
                    <td>${department.street}</td>
                    <td>${department.zip}</td>
                    <td>${department.city}</td>
                </tr>
            </c:forEach>
        </table>
        </table>
    </stripes:layout-component>
</stripes:layout-render>
