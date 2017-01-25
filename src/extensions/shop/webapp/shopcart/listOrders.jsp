<%@ include file="/taglibs.jsp"%>
<jsp:useBean id="shopcartManager" scope="page" class="com.asap.catalog.dao.manager.ShopcartManager" />
<stripes:layout-render name="/layout/standard.jsp" title="Bestillinger">
    <stripes:layout-component name="contents">
        <table>
            <tr>
                <td width="3%"><b>Ordrenr.<b></th>
                <td width="25%"><b>Dato<b></th>
                <td width="40%"><b>Navn<b></th>
                <td colspan="2"><b>Beløb(kr)<b></th>
            </tr>
            
            <c:forEach items="${shopcartManager.orders}" var="order">
                <tr class="item">
                    <td>${order.id}</td>
                    <td><small><fmt:formatDate pattern="dd/MM-yy kk:mm" value="${order.date}"/></small></td>
                    <td>${order.customer.name}</td>
                    <td>${order.shopcart.sum}</td>
                    <td><a href="ShopCart.action?viewOrder&order=${order}">Vis</a>
                </tr>
            </c:forEach>
        </table>
    </stripes:layout-component>
</stripes:layout-render>
