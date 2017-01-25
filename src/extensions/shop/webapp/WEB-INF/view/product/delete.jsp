<%@ include file="/taglibs.jsp"%>

<stripes:layout-render name="/layout/standard.jsp" title="Pickable Objects">
<stripes:layout-component name="contents">

Objektet er slettet. Se listen her: 

<cat:inlink url="pickableObjects/PickableObjects.action?list">Liste</cat:inlink>

<%--<a href="${pageContext.request.contextPath}/pickableObjects/PickableObjects.action?view&object=${actionBean.object}">${actionBean.object.title}</a>
--%>

</stripes:layout-component>

</stripes:layout-render>