<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Forløb: ${actionBean.term.course.title}">
    <stripes:layout-component name="contents">
        <cat:userAccess role="3">
            <stripes:link href="Term.action?edit&term=${actionBean.term.id}">Rediger</stripes:link>
            <a href="#" onclick="if(confirm('Er du sikker på at du vil slette dette forløb?'))
            window.location='${pageContext.request.contextPath}/term/Term.action?delete&term=${actionBean.term}';">Slet</a>
        </cat:userAccess>
       
            
        <span class="actions">
            <stripes:link href="Term.action" event="addToShopCart">Køb<stripes:param name="term" value="${actionBean.term.id}" /></stripes:link>
        </span>

        <table>
            <tr>
                <td><b>Kursus</b></td><td>: </td>
                <td>${actionBean.term.course.title}</td>
            </tr>

            <tr>
                <td><b>Lærer</b></td><td>: </td>
                <td>${actionBean.term.teacher.user.lastName}, ${actionBean.term.teacher.user.firstName}</td><br>
            </tr>
            <tr>
                <td colspan="3"><br><br><b>Lektioner:</b></td>
            </tr>
        
            <tr>
                <td colspan="2">Sted</td>
                <td>Tidspunkt</td>
            </tr>
            
           <c:forEach items="${actionBean.term.events}" var="event">
                <tr>
                    <c:if test="${event.date != null}">
                        <td colspan="2">${event.location.fullname}</td>
                        <td><fmt:formatDate pattern="dd/MM-yyyy" value="${event.date}"/></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </stripes:layout-component>
</stripes:layout-render>