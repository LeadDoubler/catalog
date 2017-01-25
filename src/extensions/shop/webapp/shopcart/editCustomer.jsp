<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Indtast kontaktoplysninger">
<fmt:setBundle basename="StripesResources"/>
    <stripes:layout-component name="contents">
        <h1>Varer:</h1>
         <table style="border:1px solid black;">
                <tr>
                    <th>Antal</th>
                    <th width="65%">Beskrivelse</th>
                    <th class="amount">Beløb</th>
                </tr>
                
                <c:forEach items="${actionBean.context.shopCart.items}" var="item">
                    <tr class="item">
                        <td class="number">${item.occurences}</td>
                        <td class="title">${item.name}</td>
                        <td class="amount"><fmt:formatNumber type="currency" maxFractionDigits="2" minFractionDigits="2" currencySymbol="">${item.totalprice}</fmt:formatNumber></td>
                    </tr>
                </c:forEach>
                
                <tr class="item">
                    <td class="number">Total</td>
                    <td class="title"></td>
                    <td class="sum"><fmt:formatNumber type="currency" maxFractionDigits="0" minFractionDigits="2" currencySymbol="">${actionBean.context.shopCart.sum}</fmt:formatNumber></td>
                </tr>
        </table>  
            
        <h1>Levering:</h1>
        <stripes:form action="/shopcart/ShopCart.action" focus="" method="post" >
            <stripes:hidden name="customer"/>
            <table>
                <tr>
                    <td>Navn</td><td>: </td>
                    <td width="70%">
                        <stripes:text name="customer.name" size="40"/>
                        <stripes:errors field="customer.name"><br/>
                            <stripes:individual-error/>
                        </stripes:errors>
                    </td>
                </tr>
                <tr>
                    <td>Firma</td><td>: </td>
                    <td><stripes:text name="customer.company" size="40"/></td>
                </tr>
                <tr>
                    <td>Email</td><td>: </td>
                    <td width="70%">
                        <stripes:text name="customer.email" size="40"/>
                        <stripes:errors field="customer.email"><br/>
                            <stripes:individual-error/>
                        </stripes:errors>
                    </td>
                </tr>
                <tr>
                    <td>Adresse</td><td>: </td>
                    <td width="70%">
                        <stripes:text name="customer.address" size="40"/>
                        <stripes:errors field="customer.address"><br/>
                            <stripes:individual-error/>
                        </stripes:errors>
                    </td>
                </tr>
                <tr>
                    <td>Postnummer</td><td>: </td>
                    <td width="70%">
                        <stripes:text name="customer.zip" size="40"/>
                        <stripes:errors field="customer.zip"><br/>
                            <stripes:individual-error/>
                        </stripes:errors>
                    </td>
                </tr>
                <tr>
                    <td>By</td><td>: </td>
                    <td width="70%">
                        <stripes:text name="customer.city" size="40"/>
                        <stripes:errors field="customer.city"><br/>
                            <stripes:individual-error/>
                        </stripes:errors>
                    </td>
                </tr>
                <tr>
                    <td>Telefon</td><td>: </td><td width="70%">
                        <stripes:text name="customer.phone" size="40"/>
                        <stripes:errors field="customer.phone"><br/>
                            <stripes:individual-error/>
                        </stripes:errors>
                    </td>
                </tr>
                <tr>
                    <td colspan="3"><br><strong>For private virksomheder</strong></td>
                </tr>
                <tr>
                    <td>CVR</td><td>: </td>
                    <td><stripes:text name="customer.cvr" size="40"/></td>
                </tr>
               <tr>
                    <td colspan="3"><br><strong>For offentlige virksomheder</strong></td>
                </tr>
                <tr>
                    <td>EAN</td><td>: </td>
                    <td><stripes:text name="customer.ean" size="40"/></td>
                </tr>
                <tr>
                    <td colspan="3"><stripes:submit name="approve" value="Bestil"/></td>
                </tr>
            </table>
        </stripes:form>
    </stripes:layout-component>
</stripes:layout-render>