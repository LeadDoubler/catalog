<html>
<head>
  <title>FreeMarker Example Web Application 1</title>
</head>
<body>
<img src="http://localhost:8084/catalog/images/logo.jpg">
<table>
<tr>
    <td>Name</td>    
    <td>Descrition</td>
    <td>Count</td>
    <td>Price</td>
    <td>Total Price</td>
</tr>
<#list items as item>
<tr>
    <td>
        ${item.title}
    </td>
    <td>
        ${item.description}
    </td>
    <td>
        ${item.count}
    </td>
    <td>
        ${item.price}
    </td>
    <td>
        ${item.price * item.count}
    </td>
</tr>
</#list>
<tr>
<td></td>
<td></td>
<td></td>
<td>Price</td>
<td>${request.totalPrice}</td>
</tr>

<tr>
<td></td>
<td></td>
<td></td>
<td>VAT</td>
<td>${request.totalPrice * 0.25}</td>
</tr>

<tr>
<td></td>
<td></td>
<td></td>
<td>Total price</td>
<td>${request.totalPrice * 1.25}</td>
</tr>
</table>
<img src="http://localhost:8084/catalog/images/PIC_blue.jpg">
<img src="http://localhost:8084/catalog/images/PIC_earth.jpg">
</body>
</html>