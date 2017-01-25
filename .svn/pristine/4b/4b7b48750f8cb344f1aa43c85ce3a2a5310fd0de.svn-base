<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Rediger/tilføj bog">
    <stripes:layout-component name="contents">
        <jsp:useBean id="categoryManager" scope="page" class="com.asap.catalog.dao.manager.CategoryManager" />
        <stripes:form action="/book/Book.action" focus="" method="post" >
            <stripes:hidden name="book"/>
            <table>
                <tr class="item">
                    <td>Titel</td><td>: </td>
                    <td width="70%"><stripes:text name="book.title" size="40"/></td>
                </tr>
                <tr>
                    <td>Forfatter</td><td>: </td>
                    <td><stripes:text name="book.author" size="40"/></td>
                </tr>
                <tr>
                    <td>ISBN</td><td>: </td>
                    <td><stripes:text name="book.isbn" size="40"/></td>
                </tr>
                <tr>
                    <td>Beskrivelse</td><td>: </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <FCK:editor                           
                            id="book.description"     
                            width="100%"
                            height="300"
                            toolbarSet="Catalog">${actionBean.book.description}
                        </FCK:editor>
                    </td>
                </tr>
                <tr>
                    <td>Pris</td><td>: </td>
                    <td><stripes:text name="book.price" size="40"/></td>
                </tr>
                <%--<tr>
                    <td>Antal på lager</td><td>: </td>
                    <td><stripes:text name="book.count" size="40"/></td>
                </tr>--%>
                <tr>
                    <td>Kategori</td><td>: </td>
                    <td>
                        <c:set var="categories" value="${categoryManager.allCategory}" />
                        <c:if test="${categories == null}">
                           Empty Categories List
                        </c:if>
                        <stripes:select name="book.category">
                            <stripes:options-collection value="id" collection="${categories}" label="title" />
                        </stripes:select>
                   </td>
                </tr>

                <tr><td colspan="3"><stripes:submit name="save" value="Gem"/></td></td>
            </table>
        </stripes:form>
    </stripes:layout-component>
</stripes:layout-render>