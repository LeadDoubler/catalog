<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Kategori: ${actionBean.category.title}">
    <stripes:layout-component name="contents">
       <cat:userAccess role="3">
            <stripes:link href="Category.action?edit&category=${actionBean.category}">Rediger</stripes:link>
            <stripes:link href="${pageContext.request.contextPath}/book/Book.action?edit&book.category=${actionBean.category}">Ny bog</stripes:link>
            <stripes:link href="${pageContext.request.contextPath}/course/Course.action?edit&course.category=${actionBean.category}">Nyt kursus</stripes:link>
            <stripes:link href="${pageContext.request.contextPath}/seminar/Seminar.action?edit&seminar.category=${actionBean.category}">Nyt seminar</stripes:link>
            <c:if test="${actionBean.category.childless}">
                  <a href="#" onclick="if(confirm('Er du sikker på at du vil slette denne kategori?'))
               window.location='${pageContext.request.contextPath}/category/Category.action?delete&category=${actionBean.category.id}';">Slet</a>
            </c:if>
        </cat:userAccess>

        <table>
            <p>
                - <em>${actionBean.category.intro}</em><br><br>
            </p>

            <p>
                <b>Beskrivelse:</b>
                ${actionBean.category.description}<br><br>

            </p>

        </table>

        <h3>Bøger tilhørende kategorien</h3>
        <table>
            <c:forEach items="${actionBean.category.products}" var="product">
                <c:if test="${product.type eq 'Book'}">
                    <li><a href="${pageContext.request.contextPath}/${product.type}/${product}/">${product.title}</a></li>
                </c:if>
            </c:forEach>
        </table>

        <h3>Kurser indenfor kategorien</h3>
        <table>
            <c:forEach items="${actionBean.category.products}" var="product">
                <c:if test="${product.type eq 'Course'}">
                    <li><a href="${pageContext.request.contextPath}/${product.type}/${product}/">${product.title}</a></li>
                </c:if>
            </c:forEach>
        </table>
    </stripes:layout-component>
</stripes:layout-render>
