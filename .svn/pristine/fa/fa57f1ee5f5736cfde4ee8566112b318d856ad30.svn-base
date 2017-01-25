

 <cat:userAccess role="3">
    
<a href="${pageContext.request.contextPath}/product/Product.action?edit&product=${product.id}">Rediger</a>
<a href="#" onclick="if(confirm('Er du sikker på at du vil slette dette objekt?'))
       window.location='${pageContext.request.contextPath}/product/Product.action?delete&product=${product.id}';">Slet</a>

       <cat:inlink url="product/Product.action?list">List</cat:inlink>

       <%--<a href="${pageContext.request.contextPath}/product/Product.action?list">List</a>--%>
    
</cat:userAccess>
<table width="350">
    <tr>
        <th>
            ${product.title}
        </th>
        <th>
            <span class="code">
            Code: ${product.code}
            </span>
        </th>
        
    </tr>
    <tr>
            <td>
                
                <table><tr><td>Customer:</td><td>
                            ${product.customer}
                    </td></tr>
                    <tr><td>Segment:</td><td>${product.segment}</td></tr>
                    <tr><td>Material:</td><td>${product.material}</td></tr>
                    <tr><td>Dimensions:</td><td>${product.dimensions}</td></tr>
                    
                </table>
            </td>
            <td>
               
                <span class="product_image"><img src="${pageContext.request.contextPath}/product/Product.action?image&product=${product.id}"/></span>
   
                 </td>
    </tr>
    <tr><td colspan="2">${product.description}</td></tr>
</table>