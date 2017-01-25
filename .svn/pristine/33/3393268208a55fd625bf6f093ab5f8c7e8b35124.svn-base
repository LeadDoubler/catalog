<%@ include file="/taglibs.jsp"%>
<jsp:useBean id="categoryManager" scope="page" class="com.asap.catalog.dao.manager.CategoryManager" />
<cat:userAccess role="3">
    <stripes:link href="${pageContext.request.contextPath}/book/Book.action?edit">Tilføj bog</stripes:link>
</cat:userAccess>

<table>
    <c:forEach items="${categoryManager.allCategory}" var="category">
        <h3><a href="${pageContext.request.contextPath}/category/Category.action?view&category=${category}"> ${category.title}</a></h3>
        <ul>
            <c:forEach items="${category.products}" var="product">
                <c:if test="${product.type eq 'Book'}">
                    <li><a href="${pageContext.request.contextPath}/book/Book.action?view&book=${product.id}"> ${product.title}</a></li>
                </c:if>
            </c:forEach>
        </ul>
    </c:forEach>
</table>
