<%@ include file="/taglibs.jsp"%>
<fmt:setBundle basename="StripesResources"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
 

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/view/default/pagination.jsp"/>

            <cat:userAccess role="3">
                    <stripes:link href="${pageContext.request.contextPath}/${actionBean.type}/${actionBean.capType}.action?edit">New ${actionBean.type}</stripes:link>
                </cat:userAccess>
                    <ul>
                    <c:forEach items="${actionBean.components}" var="component">
                        
                        <li>                            
                            <a href="${pageContext.request.contextPath}/${actionBean.type}/${component.id}">${component.title}</a>
                        </li>   
                        
                    </c:forEach>
                </ul>

