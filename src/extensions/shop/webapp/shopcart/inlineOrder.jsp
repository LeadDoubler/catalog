
<p>Kære ${actionBean.order.customer.name}</p>

<cat:part part="shopcart.orderView.beforeEmail" site="yes"/> <p>${actionBean.order.customer.email}</p>

<p>Dit ordrenummer er: ${actionBean.order.id}</p><br><br>

<h3>Produkter</h3>
<table>
    <tr>
        <th>Antal</th>
        <th>Beskrivelse</th>
        <th class="amount">Beløb</th>
    </tr>

    <c:forEach items="${actionBean.order.shopcart.items}" var="item">
        <tr class="item">
            <td class="number">${item.occurences}</td>
            <td class="title">${item.name}</td>
            <td class="amount">${item.totalprice}</td>
        </tr>
    </c:forEach>

    <tr class="item">
        <td class="number">Total</td>
        <td class="title"></td>
        <td class="sum">${actionBean.order.shopcart.sum}</td>
    </tr>
</table><br><br>
    
<h3>Kontaktoplysninger</h3>
<table>
    <tr><td width="13%"><b>Kunde:</b></td><td align="left">${actionBean.order.customer.name}</td></tr>
    <tr><td></td><td align="left">${actionBean.order.customer.address}</td></tr>
    <tr><td></td><td align="left">${actionBean.order.customer.zip} ${actionBean.order.customer.city}</td></tr>
    <tr><td colspan="2"> </td></tr>
    <c:if test="${not actionBean.order.customer.company eq null}">
    <tr><td><b>Firma:</b></td><td align="left">${actionBean.order.customer.company}</td></tr>
    </c:if>
    <tr><td><b>Email:</b></td><td align="left">${actionBean.order.customer.email}</td></tr>
    <tr><td><b>Telefon:</b></td><td align="left">${actionBean.order.customer.phone}</td></tr>
    <c:if test="${not actionBean.order.customer.ean eq null}">        
        <tr><td><b>EAN:</b></td><td align="left">${actionBean.order.customer.ean}</td></tr>
    </c:if>
</table>