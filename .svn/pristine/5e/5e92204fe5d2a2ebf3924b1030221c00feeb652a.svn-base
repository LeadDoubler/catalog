<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Rediger/tilføj forløb">
    <stripes:layout-component name="contents">
        <jsp:useBean id="profileManager" scope="page" class="com.asap.catalog.dao.manager.ProfileManager" />
        <jsp:useBean id="courseManager" scope="page" class="com.asap.catalog.dao.manager.CourseManager" />
        <jsp:useBean id="locationManager" scope="page" class="com.asap.catalog.dao.manager.LocationManager" />
 
        <stripes:form action="/term/Term.action" focus="page" method="post">
            <stripes:hidden name="term" />
            <table>
                <tr>
                    <td>Lærer</td><td>: </td>
                    <td>
                        <c:set var="teachers" value="${profileManager.profiles}" />
                        <c:if test="${teachers == null}">
                            <label>Ingen lærere til rådighed</label>
                        </c:if>
                        <c:if test="${teachers != null}">
                            <stripes:select name="term.teacher">
                                <stripes:options-collection value="id" collection="${teachers}" label="user.fullname" />
                            </stripes:select>
                        </c:if>
                    </td> 
                </tr>
                <tr>
                    <td>Kursus</td><td>: </td>
                    <td>
                        <c:set var="courses" value="${courseManager.allCourse}" />
                        <c:if test="${courses == null}">
                            <label>Empty Course List</label>
                        </c:if>
                        <stripes:select name="term.course">
                            <stripes:options-collection value="id" collection="${courses}" label="title" />
                        </stripes:select>
                    </td>
                </tr>
                
                
                <c:forEach items="${actionBean.term.events}" var="event" varStatus="loop">
                    <tr>
                        <td><br><label>${loop.index+1}. lektion</label></td>
                    </tr>
                    <tr>
                        <td>Sted</td><td>: </td>
                        <td>
                            <c:set var="locations" value="${locationManager.allLocation}" />
                            <c:if test="${locations == null}">
                                Intet lokale til rådighed
                            </c:if>
                            <c:if test="${locations != null}">
                                <stripes:select name="term.events[${loop.index}].location">
                                    <stripes:options-collection value="stringId"
                                                                collection="${locations}" label="fullname" />
                                </stripes:select>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Dato</td><td>: </td>
                        <td>
                            <stripes:text id="date${loop.index}" name="term.events[${loop.index}].stringDate" size="10" style="width: 150px;"/>
                            <img src="${pageContext.request.contextPath}/jsCalendar/img.gif" id="triggerdate${loop.index}"
                                 style="cursor: pointer; border: 1px solid red;"
                                 title="Date selector"
                                 onmouseover="this.style.background='red';"
                                 onmouseout="this.style.background=''"/>
                            <script type="text/javascript">
                            Calendar.setup(
                            {
                                  inputField  : "date${loop.index}",    // ID of the input field
                                  ifFormat    : "%d/%m-%Y",    // the date format
                                  button      : "triggerdate${loop.index}",       // ID of the button
                                  showsTime   : false
                            }
                            );
                            </script>
                        </td>
                    </tr>
                </c:forEach>

                
                
                <tr><td colspan="3"><stripes:submit name="save" value="Gem" /></td></tr>
            </table>
        </stripes:form>
    </stripes:layout-component>
</stripes:layout-render>