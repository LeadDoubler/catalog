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
        <h1>Betaling</h1>
        
<form action="https://www.paypal.com/row/cgi-bin/webscr" method="post">
    <input type="hidden" name="cmd" value="_cart">
    <input type="hidden" name="upload" value="1">
    <input type="hidden" name="business" value="morten@blobcom.com">
    <input type="hidden" name="currency_code" value="DKK">

    <c:forEach items="${actionBean.context.shopCart.items}" var="item" varStatus="status">                  
            <input type="hidden" name="item_name_${status.index+1}" value="${item.description}">
            <input type="hidden" name="amount_${status.index+1}" value="<fmt:formatNumber type="currency" maxFractionDigits="0" minFractionDigits="0" currencySymbol="">${item.totalprice}</fmt:formatNumber>">
            <input type="hidden" name="item_number_${status.index+1}" value="${item.occurences}Sho">
  
    </c:forEach>
    <input type="image" style="width:60px; height:40px" src="http://www.paypal.com/en_US/i/btn/x-click-but01.gif" name="submit" alt="Betal via paypal. Det er sikkert!">
</form>
<cat:part part="shopcart.payment.paypalinfo" site="yes"/>

    <table>
        <tr>
            <td>
                <cat:part part="shopcart.payment.endorder" site="yes"/>
            </td>
        </tr>
        <tr>
            <td>
                <a href="ShopCart.action?approve">Afslut ordre</a>
            </td>
        </tr>
    </table>
        
    </stripes:layout-component>
</stripes:layout-render>