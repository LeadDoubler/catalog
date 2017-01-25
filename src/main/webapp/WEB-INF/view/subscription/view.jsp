<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Tilmeldt til nyhedsbrev">
<fmt:setBundle basename="StripesResources"/>
    
    <stripes:layout-component name="contents">
            <cat:userAccess role="3">
                <stripes:link href="${actionBean.capType}.action?edit&${actionBean.type}=${actionBean.component.id}">Rediger</stripes:link>

                <a href="#" onclick="if(confirm('Vil du slette dette element fra listen? Den kan ikke gendannes'))
                   window.location='${pageContext.request.contextPath}/${actionBean.component}/delete';">Slet</a>
                    <stripes:link href="${pageContext.request.contextPath}/${actionBean.type}/list">List alle</stripes:link>
            </cat:userAccess>
            <jsp:include page="../${actionBean.viewFolder}/inlineView.jsp"/>

    </stripes:layout-component>
</stripes:layout-render>


