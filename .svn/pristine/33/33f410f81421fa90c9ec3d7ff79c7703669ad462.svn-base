<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Rediger/tilføj kategori">
	<stripes:layout-component name="contents">
            
        <stripes:form action="/category/Category.action" focus="" method="post" >
            <stripes:hidden name="category"/>
            <table>
                <tr>
                    <td>Titel</td><td> :</td>
                    <td width="70%"><stripes:text size="40" name="category.title"/></td>
                </tr>
                <tr>
                    <td>Introduktion</td><td> :</td>
                    <td><stripes:text name="category.intro"/></td>
                </tr>
                <tr>
                    <td>Beskrivelse</td><td>:</td>
                </tr>
                <tr>
                    <td colspan="3">
                        <FCK:editor                           
                            id="category.description"     
                            width="100%"
                            height="300"
                            toolbarSet="Catalog">${actionBean.category.description}
                        </FCK:editor>
                    </td>
                </tr>
                <tr>
                    <td><stripes:submit name="save" value="Gem"/></td>
                </tr>
            </table>
        </stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
