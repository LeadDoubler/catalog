<%@ include file="/taglibs.jsp"%>
<jsp:useBean id="shopcartManager" scope="page" class="com.asap.catalog.dao.manager.ShopcartManager" />
<stripes:layout-render name="/layout/standard.jsp" title="Bestillinger">
    <stripes:layout-component name="contents">
        <table>
            <tr>
                <th width="7%"><b>Nr.<b></th>
                <th width="12%"><b>Dato<b></th>
                <th><b>Navn<b></th>
                <th><b>Beløb(kr)<b></th>
                
            </tr>
            
            <c:forEach items="${shopcartManager.orders}" var="order">
                <c:if test="${order.status.value == 0}">
                    <tr style="color:red;cursor:pointer;cursor:hand;" onclick="window.location='ShopCart.action?viewOrder&order=${order}'">
                </c:if>
                <c:if test="${order.status.value == 2}">
                    <tr style="color:green;cursor:pointer;cursor:hand;" onclick="window.location='ShopCart.action?viewOrder&order=${order}'">
                </c:if>
                    
                    <td>${order.id}</td>
                    <td><small><fmt:formatDate pattern="dd/MM-yy kk:mm" value="${order.date}"/></small></td>
                    <td>${order.customer.name}</td>
                    <td>${order.shopcart.sum}</td>
                       
                    <td>
                        <c:if test="${order.status.value == 0}"><a href="ShopCart.action?markOrderAsTreated&order=${order}">Afslut</a></c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </stripes:layout-component>
</stripes:layout-render>
