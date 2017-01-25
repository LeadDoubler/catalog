<%@ include file="/taglibs.jsp"%>
<jsp:useBean id="dateManager" scope="page" class="com.asap.catalog.dao.manager.DateManager" />

<cat:userAccess role="3">
    <stripes:link href="Course.action?edit&course=${actionBean.course}">Rediger</stripes:link>
    <stripes:link href="${pageContext.request.contextPath}/term/Term.action?edit&term.course=${actionBean.course}">Tilføj forløb</stripes:link>
    <a href="#" onclick="if(confirm('Er du sikker på at du vil slette dette kursus?'))
       window.location='${pageContext.request.contextPath}/product/Product.action?delete&product=${actionBean.course}';">Slet</a>
</cat:userAccess>

<p>
    ${actionBean.course.description}<br><br>

</p>

<hr width="100%" size="2" />
<div align='center' style='font-size:11px'>
    Varighed: ${actionBean.course.duration} dage | 
    Forudsætning: <img src="${pageContext.request.contextPath}/images/${actionBean.course.entranceLevel.value}.gif"/> | 
    Nivau: <img src="${pageContext.request.contextPath}/images/${actionBean.course.complexity.value}.gif"/> | 
    Pris: ${actionBean.course.price} kr
</div>
<hr width="100%" size="2" />

<p>
    <small>Alle priser er ekskl. moms. Klik på den enkelte dato i nedenstående liste for at booke kurset online.</small>
</p><br><br>
<p>
    <small>${actionBean.course.extrainfo}</small><br><br>
</p>

<h4>Aktuelle kursus datoer</h4>

<c:set value="${actionBean.termsByMonth}" var="termlist"/>
<c:set value="${dateManager.monthList}" var="monthlist"/>

<table>
    <tr>
        <c:forEach var="j" begin="0" end="4">
            <tr><c:forEach var="i" begin="${3*j}" end="${3*j+2}">
                <td>
                    <table>
                        <tr><td><b>${monthlist[i]}</b></td></tr>
                        <c:forEach items="${termlist[i]}" var="term">
                            <tr><td><small>
                                <stripes:link href="/term/Term.action?view&term=${term}">
                                    ${term.period}
                                </stripes:link></small></td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </c:forEach></tr><tr></tr>
        </c:forEach>
    </tr>
</table>


