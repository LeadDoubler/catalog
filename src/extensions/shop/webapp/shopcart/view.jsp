<%@ include file="/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="StripesResources"/>
                        
<stripes:layout-render name="/layout/standard.jsp" title="Indk�bskurv">
	<stripes:layout-component name="contents">
		<stripes:form action="/shopcart/ShopCart.action" focus=""method="post">
			<stripes:hidden name="shopcart" />
                        De �nskede varer er tilf�jet indk�bskurven. Alle priser er ekskl. moms. Tryk "Bestil" for at g� til kassen eller fors�t med at k�be flere <a href="${pageContext.request.contextPath}/product/Product.action?list">produkter</a>.
                                               
                        <c:choose>
                            <c:when test="${not empty actionBean.context.shopCart.items}">
                                <h3>Aktuelle varer i indk�bskurven</h3>
                                <table>
                                    <tr>
                                        <th>Antal</th>
                                        <th width="65%">Beskrivelse</th>
                                        <th class="amount">Bel�b</th>
                                        <th></th>
                                    </tr>

                                    <c:forEach items="${actionBean.context.shopCart.items}" var="item">
                                        <tr class="item">
                                            <td class="number">${item.occurences}</td>
                                            <td class="title">${item.description}</td>
                                            <td class="amount">${item.totalprice}</td>
                                            <td>
                                                <img    style="cursor:pointer;cursor:hand;" 
                                                        onclick="if(confirm('Er du sikker p� at du vil slette denne vare fra indk�bskurven?')) window.location='${pageContext.request.contextPath}/shopcart/ShopCart.action?deleteItem&item=${item.id}';" 
                                                        src="${pageContext.request.contextPath}/images/trashcan.gif"
                                                 />
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    <tr class="item">
                                        <td class="number">Total</td>
                                        <td class="title"></td>
                                        <td class="sum">${actionBean.context.shopCart.sum}</td>
                                        <td></td>
                                    </tr>
                                </table>
                                <stripes:submit name="editCustomer" value="Bestil varer" />
                            </c:when>
                            <c:otherwise>
                                <h3><fmt:message key="emptycart"/></h3>
                            </c:otherwise>
                        </c:choose>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>
