<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Seminar: ${actionBean.seminar.title}">
    <stripes:layout-component name="contents">
        <cat:userAccess role="3">
            <stripes:link href="Seminar.action?edit&seminar=${actionBean.seminar}">Rediger</stripes:link>
            <a href="#" onclick="if(confirm('Er du sikker på at du vil slette dette seminar?'))
               window.location='${pageContext.request.contextPath}/product/Product.action?delete&product=${actionBean.seminar}';">Slet</a>
        </cat:userAccess>
       
        <span class="actions">
            <stripes:link href="/product/Product.action?addToShopCart&product=${actionBean.seminar}">Køb</stripes:link>
        </span>
        
        <p>
            ${actionBean.seminar.description}<br><br>

        </p><br>

        <div align='center' style='font-size:11px'>
            Dato: <fmt:formatDate pattern="dd/MM-yyyy" value="${actionBean.seminar.event.date}"/> | 
            Sted: ${actionBean.seminar.event.location.fullname} | 
            Pris: ${actionBean.seminar.price} kr
        </div><br>

        <p>
            <small>Alle priser er ekskl. moms. Klik på den enkelte dato i nedenstående liste for at booke kurset online.</small>
        </p>
    </stripes:layout-component>
</stripes:layout-render>