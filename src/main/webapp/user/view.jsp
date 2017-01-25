<%@ include file="/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout/standard.jsp" title="Brugeroplysninger">
    <stripes:layout-component name="contents">
      
         <cat:userAccess role="4" usr_id="${actionBean.user.id}">
            <c:if test="${actionBean.user.role.value <= user.role.value}">
                <a href="Register.action?edit&user=${actionBean.user}">Rediger</a>
            </c:if>
            <c:choose>
<c:when test="${actionBean.user.deactivated eq 'yes'}">
<a href="${pageContext.request.contextPath}/user/Register.action?save&user=${actionBean.user}&user.deactivated=no';">Aktiver</a>
</c:when>
<c:otherwise>
            <c:if test="${actionBean.user.id != user.id && actionBean.user.role.value <= user.role.value}">
               
               <a href="#" onclick="if(confirm('Are you sure you want to delete this user?'))
                   window.location='${pageContext.request.contextPath}/user/Register.action?delete&user=${actionBean.user}';">  Delete</a>
            </c:if>

</c:otherwise>
</c:choose>
           <%-- <c:if test="${actionBean.user.id != user.id && actionBean.user.role.value <= user.role.value}">
                <a href="#" onclick="if(confirm('Er du sikker på at du vil slette denne bruger?'))
                   window.location='${pageContext.request.contextPath}/user/Register.action?delete&user=${actionBean.user}';">Slet</a>
            </c:if>--%>
         </cat:userAccess>

        <table>
            <tr>
                    <td>Fornavn</td><td>: </td>
                    <td>${actionBean.user.firstName}</td>
            </tr>
            <tr>
                    <td>Efternavn</td><td>: </td>
                    <td>${actionBean.user.lastName}</td>
            </tr> 
            <tr>
                    <td>Brugernavn</td><td>: </td>
                    <td>${actionBean.user.username}</td>
            </tr>
            <tr>
                    <td>Password</td><td>: </td>
                    <td>${actionBean.user.password}
                        
                        <%--<c:forEach begin="1" end="${actionBean.user.passwordLength}">*</c:forEach>--%>
                        </td>
            </tr>
            <tr>
                    <td>Email</td><td>: </td>
                    <td>${actionBean.user.email}</td>
            </tr>
            <c:if test="${actionBean.user.phone != null}">
                <tr>
                    <td>Telefon</td><td>: </td>
                    <td>${actionBean.user.phone}</td>
                </tr>
            </c:if>
            <tr>
                    <td>Rettigheder</td><td>: </td>
                    <td>${actionBean.user.role.name}</td>
            </tr>
            
            <c:if test="${actionBean.user.street != null}">
                <tr>
                    <td>Adresse</td><td>: </td>
                    <td>${actionBean.user.street}</td>
                </tr>
            </c:if>
            
            <c:if test="${actionBean.user.postalCode != null and actionBean.user.city != null}">
                <tr>
                    <td> </td><td>: </td>
                    <td>
                        <c:if test="${actionBean.user.postalCode != null}">${actionBean.user.postalCode} </c:if>
                        <c:if test="${actionBean.user.city != null}">${actionBean.user.city}</c:if>
                    </td>
                </tr>
            </c:if>

            <c:if test="${actionBean.user.country != null}">
                <tr>
                    <td>Land</td><td>: </td>
                    <td>${actionBean.user.country}</td>
                </tr>
            </c:if>
            
            <c:if test="${actionBean.user.cvr != null}">
                <tr>
                    <td>CVR</td><td>: </td>
                    <td>${actionBean.user.cvr}</td>
                </tr>
            </c:if>
        </table>
        <%@ include file="userViewExtra.jsp" %>
    </stripes:layout-component>
</stripes:layout-render>



