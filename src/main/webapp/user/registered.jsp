 <%@ include file="/taglibs.jsp"%>
 <stripes:layout-render name="/layout/standard.jsp" title="Login" keywords="login" description="Login site">
    <stripes:layout-component name="contents">


    <c:choose>
        <c:when test="${user.role.value>2}">
            Ny bruger er gemt - 
            <a href="${pageContext.request.contextPath}/startup/Startup.action?home">Min side</a>

</c:when>
<c:otherwise>
    
    <cat:part part="registered.message" site="yes" />
    
    <a href="${pageContext.request.contextPath}/startup/Startup.action?home">Login</a>

</c:otherwise>
</c:choose>

    </stripes:layout-component>
</stripes:layout-render>