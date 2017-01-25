<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Kontakter">
    <stripes:layout-component name="contents">
        <table>
            <strong><tr style="font-size:11px">
                    <td width="15%">Dato</td>
                    <td width="15%">Navn</td>
                    <td width="11%">Telefon</td>
                    <td>Email</td>
                    <td width="4%">SMS</td>
                    <td>Besked</td>
            </tr></strong>
            <c:forEach items="${actionBean.allContacts}" var="contact">
                <tr valign="top">
                    <td><fmt:formatDate value="${contact.time}" type="both" pattern="dd.MM.yy-HH:mm"/></td>
                    <td>${contact.name}</td>
                    <td>${contact.phone}</td>
                    <td>${contact.email}</td>
                    <td>${contact.result}</td>
                    <td>${contact.message}</td>
                </tr>
            </c:forEach>
        </table>
    </stripes:layout-component>
</stripes:layout-render>