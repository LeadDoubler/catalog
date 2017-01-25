<%@ include file="/taglibs.jsp"%>

<c:forEach items="${actionBean.components}" var="news" varStatus="loop">
    <div id="news">
        <span id="date">
            (<fmt:formatDate pattern="dd/MM" value="${news.date}"/>)
        </span>
       
        <span id="title">
            ${news.title}<br>
        </span>
                
        <span id="description">
            ${news.description}<br>
        </span>
        
        <span id="link">
            <a href="${news.link}">Læs mere...</a><br>
        </span>
        
        <c:if test="${loop.count != actionBean.nrOfNews}">
            <br><span id="line"><hr align="center" size="1"></span><br>
        </c:if>
    </div>
</c:forEach>
