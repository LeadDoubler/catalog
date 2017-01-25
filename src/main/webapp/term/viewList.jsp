<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Terms">
    <stripes:layout-component name="contents">
        
        <stripes:form action="/term/Term.action" focus="" method="post">
            <stripes:hidden name="term" />
            <stripes:hidden name="course" />
            <table>
                <tr>
                    <td>Term Id</td>
                    <td>Event Id</td>
                    <td>Location Id</td>
                    <td>Occurence</td>
                    <td>Teacher Id</td>
                    <td>Couse Title</td>
                    <td>Action</td>
                </tr>
                <c:forEach items="${actionBean.terms}" var="term">
                    <tr>
                        <td>${term.id}</td>
                        <td>${term.event.id}</td>
                        <td>${term.event.location.id}</td>
                        <td><c:forEach items="${term.event.occurenceObject}"
 var="occurence">
                                
                                <c:out value="${occurence}"></c:out>
                                <br>
                        </c:forEach></td>
                        <td>${term.teacher.id}</td>
                        <td>${term.course.title}</td>
                        <td><stripes:link href="/term/Term.action"
    event="addToShopCart">Buy terms<stripes:param
                                    name="term" value="${term.id}" />
                                <stripes:param name="course" value="${actionBean.course}" />
                                <stripes:param name="term" value="${term.id}" />
                        </stripes:link></td>
                    </tr>
                    
                    
                </c:forEach>
                <tr>
                    <td>Course Title :</td>
                    <td>${term.course.title}
                    <td>
                </tr>
            </table>
        </stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
