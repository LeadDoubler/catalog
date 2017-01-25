<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Rediger/tilføj seminar">
    <stripes:layout-component name="contents">
         <jsp:useBean id="locationManager" scope="page" class="com.asap.catalog.dao.manager.LocationManager" />
         <jsp:useBean id="categoryManager" scope="page" class="com.asap.catalog.dao.manager.CategoryManager" />
                
        <stripes:form action="/seminar/Seminar.action" focus="page" method="post">
            <stripes:hidden name="seminar" />
            <table>
                <tr>
                    <td>Titel</td><td>: </td>
                    <td width="60%"><stripes:text name="seminar.title" size="40"/></td>
                </tr>
                <tr>
                    <td>Pris</td><td>: </td>
                    <td><stripes:text name="seminar.price" /></td>
                </tr>
                <tr>
                    <td>Beskrivelse</td><td>: </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <FCK:editor                           
                            id="seminar.description"     
                            width="100%"
                            height="300"
                            toolbarSet="Catalog">${actionBean.seminar.description}
                        </FCK:editor>
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
                            <stripes:select name="seminar.category">
                                <stripes:options-collection value='id' collection="${categories}" label="title" />
                            </stripes:select>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>Dato</td><td>: </td>
                    <td>
                        <stripes:text id="date" name="seminar.event.stringDate" size="10" style="width: 150px;"/>
                        <img src="${pageContext.request.contextPath}/jsCalendar/img.gif" id="triggerdate"
                             style="cursor: pointer; border: 1px solid red;"
                             title="Date selector"
                             onmouseover="this.style.background='red';"
                             onmouseout="this.style.background=''"/>
                        <script type="text/javascript">
                        Calendar.setup(
                        {
                              inputField  : "date",    // ID of the input field
                              ifFormat    : "%d/%m-%Y",    // the date format
                              button      : "triggerdate",       // ID of the button
                              showsTime   : false
                        }
                        );
                        </script>
                    </td>
                </tr>
                <tr>
                    <td>Lokale</td><td>: </td>
                    <td>
                        <c:set var="locations" value="${locationManager.allLocation}" />
                        <c:if test="${locations == null}">
                            Ingen afdelinger tilgængelige
                        </c:if>
                        <c:if test="${locations != null}">
                            <stripes:select name="seminar.event.location">
                                <stripes:options-collection value='id' collection="${locations}" label="fullname" />
                            </stripes:select>
                        </c:if>
                    </td>
                </tr>

                <tr><td colspan="3"><stripes:submit name="save" value="Gem" /></td></tr>
            </table>
        </stripes:form>
    </stripes:layout-component>
</stripes:layout-render>