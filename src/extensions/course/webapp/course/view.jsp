<%@ include file="/taglibs.jsp"%>
<fmt:setBundle basename="StripesResources"/>
<stripes:layout-render name="/layout/standard.jsp" title="Kursus: ${actionBean.course.title}">
    <stripes:layout-component name="box2">
         <div class="box">
            <div class="boxtop" id="box2">
                    <h4 onclick="doSwitch('box2');"><fmt:message key="relatedp"/></h4>
                    <p  class="boxcontent">                                           
                        <small><c:forEach items="${actionBean.course.category.products}" var="product">
                                <c:if test="${product.type == 'Course' and product.id != actionBean.course.id }">
                                    <a style='text-decoration: none;' href="${pageContext.request.contextPath}/course/${product}"><b>Kursus:</b> ${product.title}</a><br/>                                                
                                </c:if>
                                <c:if test="${product.type == 'Seminar'}">
                                    <a style='text-decoration: none;' href="${pageContext.request.contextPath}/seminar/${product}"><b>Seminar:</b> ${product.title}</a><br/>                                                
                                </c:if>
                                <c:if test="${product.type == 'Book'}">
                                    <a style='text-decoration: none;' href="${pageContext.request.contextPath}/book/${product}"><b>Bog:</b> ${product.title}</a><br/>                                                
                                </c:if>
                        </c:forEach></small>

                        </p>
            </div>
            <div class="boxbtm"></div>
        </div>
    </stripes:layout-component>
    
   
    <stripes:layout-component name="contents">
       <jsp:include page="inlineView.jsp"/>
    </stripes:layout-component>
</stripes:layout-render>



