<%@ include file="/taglibs.jsp"%>

<stripes:layout-render name="/layout/standard.jsp" title="Rediger/tilføj kursus">
    <stripes:layout-component name="contents">
        <jsp:useBean id="categoryManager" scope="page" class="com.asap.catalog.dao.manager.CategoryManager" />
        
        <stripes:form action="/course/Course.action" focus="" method="post" >
            <stripes:hidden name="course"/>
            <table>
                <tr>
                    <td>Titel</td><td>: </td>
                    <td width="60%"><stripes:text name="course.title" size="40"/></td>
                </tr>
                <tr>
                    <td>Beskrivelse</td><td>: </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <FCK:editor                           
                            id="course.description"     
                            width="100%"
                            height="300"
                            toolbarSet="Catalog">${actionBean.course.description}
                        </FCK:editor>
                    </td>
                </tr>
                <tr>
                    <td>Extra info</td><td>: </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <FCK:editor                           
                            id="course.extrainfo"     
                            width="100%"
                            height="300"
                            toolbarSet="Catalog">${actionBean.course.extrainfo}
                        </FCK:editor>
                    </td>
                </tr>
                
                <tr>
                    <td>Pris</td><td>: </td>
                    <td><stripes:text name="course.price" /></td>
                </tr>
                
                <tr>
                    <td>Niveau</td><td>: </td>
                    <td>
                        <stripes:select name="course.complexity">
                            <stripes:options-enumeration
                                enum="com.asap.catalog.enums.CourseComplexity" label="value" />
                        </stripes:select>
                    </td>
                </tr>
                <tr>
                    <td>Varighed i dage</td><td>: </td>
                    <td><stripes:text name="course.duration" /></td>
                </tr>
                
                <tr>
                    <td>Forudsætning</td><td>: </td>
                    <td>
                        <stripes:select name="course.entranceLevel">
                            <stripes:options-enumeration
                                enum="com.asap.catalog.enums.EntranceLevel" label="value" />
                        </stripes:select>
                    </td>
                </tr>
                
                <tr>
                    <td>Kategori</td><td>: </td>
                    <td>
                        <c:set var="categories" value="${categoryManager.allCategory}" />
                        <c:if test="${categories == null}">
                            In kategorier tilgængelige
                        </c:if>
                        <c:if test="${categories != null}">
                            <stripes:select name="course.category">
                                <stripes:options-collection value='id' collection="${categories}" label="title" />
                            </stripes:select>
                        </c:if>
                    </td>
                </tr>

                <tr><td colspan="3"><stripes:submit name="save" value="Gem"/></td></tr>
            </table>
        </stripes:form>
    </stripes:layout-component>
</stripes:layout-render>

