<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Rediger ${actionBean.type}">
<fmt:setBundle basename="StripesResources"/>
    
    <stripes:layout-component name="contents">
      <jsp:include page="../${actionBean.viewFolder}/inline.jsp"/>
    </stripes:layout-component>
</stripes:layout-render>