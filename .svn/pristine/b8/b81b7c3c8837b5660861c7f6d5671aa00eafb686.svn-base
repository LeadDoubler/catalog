<%@ include file="/taglibs.jsp"%>
<jsp:useBean id="profileManager" scope="page" class="com.asap.catalog.dao.manager.ProfileManager" />
<cat:userAccess role="3">
    <stripes:link href="${pageContext.request.contextPath}/profile/Profile.action?edit">Tilføj profil</stripes:link>
</cat:userAccess>
<c:set var="profiles" value="${profileManager.profiles}"/>
<c:set var="nr" value="${profileManager.nrOfProfiles}"/>
<table>
    <tr>
        <c:forEach items="${profiles}" var="profile" varStatus="status">
            <td>
                <table>
                    <cat:userAccess role="3">
                        <tr><td><a href="${pageContext.request.contextPath}/profile/Profile.action?edit&profile=${profile.id}">Rediger</a></td></tr>
                        <tr><td><a href="#" onclick="if(confirm('Er du sikker på at du vil slette denne profil?'))
                                window.location='${pageContext.request.contextPath}/profile/Profile.action?delete&profile=${profile.id}';">Slet</a></td></tr>
                        <%--<tr><td>
                                <a href="#" onclick="if(confirm('Er du sikker på at du vil slette denne profil?'))
                                   window.location='${pageContext.request.contextPath}/profile/Profile.action?delete&profile=${profiles[j].id}';">Slet</a>
                        </td></tr>--%>
                    </cat:userAccess>
                    <tr><td>
                            <img src="${pageContext.request.contextPath}/profile/Profile.action?image&amp;profile=${profile.id}"/> 
                    </td></tr>
                    <tr><td><b><small>${profile.name}</small></b></td></tr>
                    <tr><td><small>${profile.position}</small></td></tr>
                    <tr><td><small>${profile.user.email}</small></td></tr>  
                    <tr><td><small>${profile.phone}</small></td></tr>
                    <tr><td><small>${profile.description}</small></td></tr>
                </table>
            </td>
            <c:if test="${status.index%3 == 2}"><tr/><tr></c:if>
        </c:forEach>
    </tr>
</table>