<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Seminarer">
    <stripes:layout-component name="contents">
        <jsp:useBean id="categoryManager" scope="page" class="com.asap.catalog.dao.manager.CategoryManager" />
        <cat:userAccess role="3">
            <stripes:link href="${pageContext.request.contextPath}/seminar/Seminar.action?edit">Tilføj seminar</stripes:link>
        </cat:userAccess>

        <table>
            <c:forEach items="${categoryManager.allCategory}" var="category">

                <a href="${pageContext.request.contextPath}/category/Category.action?view&category=${category}"><b>${category.title}</b></a>
                <ul>
                    <c:forEach items="${category.products}" var="product">
                        <c:if test="${product.type eq 'Seminar'}">
                            <li><a href="${pageContext.request.contextPath}/seminar/Seminar.action?view&seminar=${product.id}"> ${product.title}</a></li>
                        </c:if>
                    </c:forEach>
                </ul>
            </c:forEach>
        </table>
   </stripes:layout-component>
</stripes:layout-render>

 <!-- AJAX -- <li><a href="#" onclick="new Ajax.Updater('seminarWindow','${pageContext.request.contextPath}/seminar/Seminar.action?view&seminar=${seminar.id}',{method:'get'});">${seminar.title}</a></li> -->

