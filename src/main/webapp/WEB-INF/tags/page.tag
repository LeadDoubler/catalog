<%@tag description="Displays content." pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout/standard.jsp" title="Rediger/tilføj bruger">
    <stripes:layout-component name="contents">

    <jsp:doBody/>
    
    </stripes:layout-component>
</stripes:layout-render>