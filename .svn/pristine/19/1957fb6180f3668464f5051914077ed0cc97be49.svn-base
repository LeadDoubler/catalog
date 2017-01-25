<%@ include file="/taglibs.jsp"%>
<cat:userAccess role="3">
    <stripes:link href="Book.action?edit&book=${actionBean.book}">Rediger</stripes:link>
    <a href="#" onclick="if(confirm('Er du sikker på at du vil slette denne bog?'))
       window.location='${pageContext.request.contextPath}/product/Product.action?delete&product=${actionBean.book}';">Slet</a>
</cat:userAccess>    

 <span class="actions">
     <stripes:link href="/product/Product.action?addToShopCart&product=${actionBean.book}">Køb</stripes:link>
</span>

<p>
    Forfatter: <em>${actionBean.book.author}</em><br><br>
</p>

<p>
    ${actionBean.book.description}<br><br>

</p><br>

<hr width="100%" size="2" />
<div align='center' style='font-size:11px'>
    ISBN: ${actionBean.book.isbn} | 
    Pris: ${actionBean.book.price} kr
</div>
<hr width="100%" size="2" />

<p>
    <small>Alle priser er ekskl. moms. Klik på den enkelte dato i nedenstående liste for at booke kurset online.</small>
</p>

