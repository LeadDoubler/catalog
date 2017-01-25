<%@ include file="/taglibs.jsp"%>

<%@ attribute name="event" required="true" %>
<%@ attribute name="area" required="true" %>
<%@ attribute name="url" required="false" %>
<c:if test="${fn:length(actionBean.components) > actionBean.numberOnPage}">



        <div style="text-align:center">
 <c:if test="${actionBean.low > 0}">
            <stripes:link beanclass="${actionBean.class}" event="${event}" onclick="ajax('${area}',this.href);return false;">
            <stripes:param name="low" value="0"/>
            <<
        </stripes:link>
        ...
             <stripes:link beanclass="${actionBean.class}" event="${event}" onclick="ajax('${area}',this.href);return false;">
            <stripes:param name="low" value="${actionBean.low - actionBean.numberOnPage}"/>
           <
        </stripes:link>
        
        </c:if>
        
        <fmt:formatNumber type="number" maxFractionDigits="0" value="${actionBean.low / actionBean.numberOnPage}"/> 

        
        <c:if test="${actionBean.low < ( fn:length(actionBean.components) - actionBean.numberOnPage*(actionBean.low / actionBean.numberOnPage) )}">
        <stripes:link beanclass="${actionBean.class}" event="${event}" onclick="ajax('${area}',this.href);return false;">
            <stripes:param name="low" value="${actionBean.low+actionBean.numberOnPage}"/>
            >
        </stripes:link>
        ...
        <stripes:link beanclass="${actionBean.class}" event="${event}" onclick="ajax('${area}',this.href);return false;">
            <stripes:param name="low" value="${actionBean.pages* actionBean.numberOnPage-actionBean.numberOnPage+1}"/>
            >>
        </stripes:link>
        </c:if>
        
    </div>
    </c:if>
