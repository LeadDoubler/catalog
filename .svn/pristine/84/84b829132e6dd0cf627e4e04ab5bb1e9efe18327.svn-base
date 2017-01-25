<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Bruger oversigt">
    <stripes:layout-component name="contents">
        <jsp:useBean id="userManager" scope="page" class="com.asap.catalog.dao.manager.UserManager" />
        
        <cat:userAccess role="4">
            <stripes:link href="${pageContext.request.contextPath}/user/Register.action?edit">Tilføj bruger</stripes:link>
        </cat:userAccess>

<jsp:include page="pagination.jsp"/>
       
      <table>
            <tr>
                <th>
                        <stripes:link beanclass="${actionBean.class}" event="list">
                            <stripes:param name="order" value="username:asc"/>
                        
                    Brugernavn
                    </stripes:link>
                </th>
                <th>
                    
                        <stripes:link beanclass="${actionBean.class}" event="list">
                            <stripes:param name="order" value="firstName:asc"/>
                        
                    Fornavn
                    </stripes:link>
                </th>
                <th> <stripes:link beanclass="${actionBean.class}" event="list">
                            <stripes:param name="order" value="lastName:asc"/>
                       
                    Efternavn
                    
                    </stripes:link>
                </th>
                <th>
                    <stripes:link beanclass="${actionBean.class}" event="list">
                            <stripes:param name="order" value="role:asc"/>
                       
                    Rettigheder
                    </stripes:link>
                </th>
                <th>
                    <stripes:link beanclass="${actionBean.class}" event="list">
                            <stripes:param name="order" value="cap:desc"/>
                       
                    Rolle
                    </stripes:link>
                </th>
            </tr>
            <stripes:form beanclass="${actionBean.class}">
            <tr>
                <td><stripes:text name="filterUsername"/></td>
                <td><stripes:text name="filterFirstName"/></td>
                <td><stripes:text name="filterLastName"/></td>
                <td><stripes:submit name="list" value="Filtrer"/></td>
                <td></td>
                
            </tr>
            </stripes:form>
            <c:forEach items="${actionBean.users}" var="user">
                <tr>
                        <td><a href="${pageContext.request.contextPath}/user/Register.action?view&user=${user.id}">${user.username}</a></td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td> ${user.role.name}</td>
                        <td>
                        <c:choose>
<c:when test="${user.cap eq 'compRep'}">Virksomhedsrepræsentant
</c:when>
<c:when test="${user.cap eq 'student'}">Studerende
</c:when>
<c:otherwise>
</c:otherwise>
</c:choose>
</td>
                </tr>
            </c:forEach>
        </table>
        
<jsp:include page="pagination.jsp"/>
       
        
    </stripes:layout-component>
</stripes:layout-render>
