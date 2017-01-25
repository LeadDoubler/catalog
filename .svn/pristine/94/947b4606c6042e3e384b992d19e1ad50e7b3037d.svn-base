<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Tilføj/rediger afdeling">
    <stripes:layout-component name="contents">
        <stripes:form action="/department/Department.action" focus="">
            <stripes:hidden name="department" />
            <table>
                <tr>
                    <td>Navn</td><td>: </td>
                    <td width="75%"><stripes:text name="department.name" /></td>
                </tr>
                <tr>
                    <td>Vej</td><td>: </td>
                    <td width="75%"><stripes:text name="department.street" /></td>
                </tr>
                <tr>
                    <td>Postnr.</td><td>: </td>
                    <td width="75%"><stripes:text name="department.zip" /></td>
                </tr>
                <tr>
                    <td>By</td><td>: </td>
                    <td width="75%"><stripes:text name="department.city" /></td>
                </tr>
                <tr>
                    <td colspan="3"><stripes:submit name="save" value="Gem" /></td>
                </tr>
            </table>
        </stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
