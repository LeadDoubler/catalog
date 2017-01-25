<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Partner slettet">
    

    
    <stripes:layout-component name="contents">
         Opgaven er slettet
        <a href="${pageContext.request.contextPath}/${actionBean.type}/list">List alle opgaver</a>
    </stripes:layout-component>
</stripes:layout-render>


