<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Tilføj/rediger lokale">
    <stripes:layout-component name="contents">
        <jsp:useBean id="departmentManager" scope="page" class="com.asap.catalog.dao.manager.DepartmentManager" />
        
        <stripes:form action="/location/Location.action" focus="" method="post">
            <stripes:hidden name="location" />
            <table>
                <tr>
                    <td>Navn</td><td>: </td>
                    <td width="75%"><stripes:text name="location.name" /></td>
                </tr>
                <tr>
                    <td>Kapacitet</td><td>: </td>
                    <td width="75%"><stripes:text name="location.capacity" /></td>
                </tr>
                <tr>
                    <td>Afdeling</td><td>: </td>
                    <td>
                        <c:set var="departments" value="${departmentManager.allDepartment}" />
                        <c:if test="${departments == null}">
                            Ingen afdelinger tilgængelige
                        </c:if>
                        <c:if test="${departments != null}">
                            <stripes:select name="location.department">
                                <stripes:options-collection value='id' collection="${departments}" label="name" />
                            </stripes:select>
                        </c:if>
                    </td>
                </tr>
                <tr><td colspan="3"><stripes:submit name="save" value="Gem"/></td></tr>
            </table>
        </stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
