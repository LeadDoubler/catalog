<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="StripesResources"/>
<table>
    <c:choose>
            <c:when test="${not empty actionBean.context.shopCart.items}">
                    <tr>
                        <th>Antal</th>
                        <th width="65%">Beskrivelse</th>
                        <th class="amount">Beløb</th>
                    </tr>

                    <c:forEach items="${actionBean.context.shopCart.items}" var="item">
                        <tr class="item">
                            <td class="number">${item.occurences}</td>
                            <td class="title">${item.name}</td>
                            <td class="amount">${item.totalprice}</td>
                        </tr>
                    </c:forEach>

                    <tr class="item">
                        <td class="number">Total</td>
                        <td class="title"></td>
                        <td class="sum">${actionBean.context.shopCart.sum}</td>
                    </tr>
            </c:when>
            <c:otherwise>
                <tr><td width="100%" style="text-align:center"><fmt:message key="emptycart"/></td></tr>
            </c:otherwise>
    </c:choose>
</table>