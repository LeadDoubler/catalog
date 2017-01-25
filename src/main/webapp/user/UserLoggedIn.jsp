<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Min side" language="${page.language.value}">
    <stripes:layout-component name="contents">
        <jsp:include page="admin/${user.role}.jsp"/>
    </stripes:layout-component>
</stripes:layout-render>