<%@ include file="/taglibs.jsp"%>
<fmt:setBundle basename="StripesResources"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
 

            <cat:userAccess role="3">
                    <stripes:link href="${pageContext.request.contextPath}/${actionBean.type}/${actionBean.capType}.action?edit">New ${actionBean.type}</stripes:link>
                </cat:userAccess>
              
                <display:table name="actionBean.components" id="row">
                    <display:column property="email" title="Email"/>  
                </display:table>