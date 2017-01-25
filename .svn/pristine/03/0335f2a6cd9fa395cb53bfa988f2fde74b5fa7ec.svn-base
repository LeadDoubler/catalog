<%@ include file="/taglibs.jsp"%>

<c:if test="${fn:length(actionBean.allUsers)>30}">

<div style="text-align:center">
    <c:if test="${actionBean.low > 0}">
            <stripes:link beanclass="com.asap.web.user.RegisterActionBean" event="list">
            <stripes:param name="low" value="0"/>
            <stripes:param name="filterUsername" value="${actionBean.filterUsername}"/>
            <stripes:param name="filterFirstName" value="${actionBean.filterFirstName}"/>
            <stripes:param name="filterLastName" value="${actionBean.filterLastName}"/>
            <<
        </stripes:link>
        ...
             <stripes:link beanclass="com.asap.web.user.RegisterActionBean" event="list">
            <stripes:param name="low" value="${actionBean.low - 30}"/>
            <stripes:param name="filterUsername" value="${actionBean.filterUsername}"/>
            <stripes:param name="filterFirstName" value="${actionBean.filterFirstName}"/>
            <stripes:param name="filterLastName" value="${actionBean.filterLastName}"/>
            <
        </stripes:link>
        
        </c:if>
        
        <fmt:formatNumber type="number" maxFractionDigits="0" value="${actionBean.low / 30}"/> 

        
        <c:if test="${actionBean.low < ( fn:length(actionBean.allUsers) - 30)}">
        <stripes:link beanclass="com.asap.web.user.RegisterActionBean" event="list">
            <stripes:param name="low" value="${actionBean.low+30}"/>
            <stripes:param name="filterUsername" value="${actionBean.filterUsername}"/>
            <stripes:param name="filterFirstName" value="${actionBean.filterFirstName}"/>
            <stripes:param name="filterLastName" value="${actionBean.filterLastName}"/>
            >
        </stripes:link>
        ...
        <stripes:link beanclass="com.asap.web.user.RegisterActionBean" event="list">
            <stripes:param name="low" value="${actionBean.pages*30-30+1}"/>
            <stripes:param name="filterUsername" value="${actionBean.filterUsername}"/>
            <stripes:param name="filterFirstName" value="${actionBean.filterFirstName}"/>
            <stripes:param name="filterLastName" value="${actionBean.filterLastName}"/>
            >>
        </stripes:link>
        </c:if>
        
    </div>
    </c:if>
