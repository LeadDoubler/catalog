<%@ include file="/taglibs.jsp"%>
<c:set var="length" value="${fn:length(actionBean.components)}"/>
[
    <c:forEach items="${actionBean.components}" var="news" varStatus="loop">
           {
		"Id":"${loop.index}",
		"LinkText":"${news.description}",
		"Url":"${news.link}",
		"Title":"${news.title}",
		"Date":"${news.stringDate}"
		
	}
         <c:if test="${not (loop.index == (length-1)) }">
            ,
        </c:if>
    </c:forEach>
]