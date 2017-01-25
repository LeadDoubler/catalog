<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Kurser">
    <stripes:layout-component name="contents">
        <jsp:include page="inlineList.jsp"/>
   </stripes:layout-component>
</stripes:layout-render>

 <!-- AJAX -- <li><a href="#" onclick="new Ajax.Updater('courseWindow','${pageContext.request.contextPath}/course/Course.action?view&course=${course.id}',{method:'get'});">${course.title}</a></li> -->

