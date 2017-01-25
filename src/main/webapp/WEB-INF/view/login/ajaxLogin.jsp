<%@page contentType="text/xml" pageEncoding="utf-8"%><?xml version="1.0" encoding="UTF-8"?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="StripesResources"/>
<login>
    <c:choose>
        <c:when test="${not empty actionBean.errors}">
            <failures>
            <c:forEach var="error" items="${actionBean.errors}">
                <c:forEach var="e" items="${error.value}">
                <failure>
                    <fieldName>${e.fieldName}</fieldName>
                    <c:choose>
                        <c:when test="${e.class.name eq 'net.sourceforge.stripes.validation.SimpleError'}">
                            <message>${e.message}</message>
                        </c:when>
                        <c:otherwise>
                            <message><fmt:message key="${e.messageKey}"/></message>
                        </c:otherwise>
                    </c:choose>



                </failure>
            </c:forEach>

            </c:forEach>
                </failures>
        </c:when>
        <c:otherwise>
            <success>
                <jsp:include page="success.jsp"/>
            </success>
        </c:otherwise>
    </c:choose>
    <sessionid>${actionBean.context.request.session.id}</sessionid>
</login>