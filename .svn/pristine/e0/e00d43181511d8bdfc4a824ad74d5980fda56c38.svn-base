<#assign title = "Softworld A/S kvittering">

<div>
<h3>Kvittering for k&oslash;b af Softworld A/S produkter</h3>
</div>

<div>
Ordrenr.: ${order.id}
/div>

<div>
<h4>Produkter</h4>
<table>
<tr>
    <td><strong>Antal</strong></td>
    <td><strong>Beskrivelse</strong></td>
    <td><strong>Bel&oslash;b</strong></td>
</tr>
<#list items as item>
    <tr>
            <td>${item.occurences}</td>
            <td>${item.name}</td>
            <td>${item.totalprice}</td>
    <tr/>
    <tr>
            <td>Total</td>
            <td></td>
            <td>${order.shopcart.sum}</td>
    </tr>
</#list><br/><br/>
</table>
</div>
<div>
<h4>Kontaktoplysninger</h4>
<table>
    <tr><td>Kunde:</td><td>${order.customer.name}</td></tr>
    <tr><td></td><td>${order.customer.address}</td></tr>
    <tr><td></td><td>${order.customer.zip} ${order.customer.city}</td></tr>
    <tr><td>Firma:</td><td>${order.customer.company}</td></tr>
    <tr><td>Email:</td><td>${order.customer.email}</td></tr>
    <tr><td>Telefon:</td><td>${order.customer.phone}</td></tr>
    <tr><td>EAN:</td><td>${order.customer.ean}</td></tr>
</table><br><br>
</div>

<div>
Med venlig hilsen<br><br>
Softworld A/S<br>
<small>
K&oslash;benhavn:<br>
Vermundsgade 38 A.<br>
2100 K&oslash;benhavn &Oslash;.<br>
Tel. 7020 8055<br>
Fax 7020 8057<br><br>
&Aring;rhus<br>
Fiskergade 41<br>
8000 &Aring;rhus C.<br>
Tel. 7020 8056<br>
Fax 8741 1408
</small>
</div>
</body>
</html>