<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Tilføj/Rediger profil">
    <stripes:layout-component name="contents">
        <jsp:useBean id="userManager" scope="page" class="com.asap.catalog.dao.manager.UserManager" />
        <stripes:form action="/profile/Profile.action" focus="">
            <stripes:hidden name="profile"/>
            <table>
                <%--<tr>
                    <td>Parameter :</td>
                    <td>Value :</td>
                </tr>
                <tr>
                    <td>Email :</td>
                    <td><stripes:text name="profile.email" /></td>
                </tr>
                <tr>
                    <td>Phone :</td>
                    <td><stripes:text name="profile.phone" /></td>
                </tr>
                <tr>
                    <td>Mobile :</td>
                    <td><stripes:text name="profile.mobile" /></td>
                </tr>
                <tr>
                    <td>Street :</td>
                    <td><stripes:text name="profile.street" /></td>
                </tr>
                <tr>
                    <td>Street 2 :</td>
                    <td><stripes:text name="profile.street2" /></td>
                </tr>
                <tr>
                    <td>Postal code :</td>
                    <td><stripes:text name="profile.postalCode" /></td>
                </tr>
                <tr>
                    <td>City :</td>
                    <td><stripes:text name="profile.city" /></td>
                </tr>--%>
                <tr>
                    <td>Bruger</td><td>: </td>
                    <td>
                        <c:set var="users" value="${userManager.users}" />
                        <c:if test="${users == null}">
                           Ingen tilgængelige brugere
                        </c:if>
                        <stripes:select name="profile.user">
                            <stripes:options-collection value="id" collection="${users}" label="username" />
                        </stripes:select>
                   </td>
                </tr>
                <tr>
                    <td>Navn</td><td> :</td>
                    <td><stripes:text name="profile.name" /></td>
                </tr>
                <tr>
                    <td>Stilling</td><td> :</td>
                    <td><stripes:text name="profile.position" /></td>
                </tr>
                <tr>
                    <td>Telefon</td><td> :</td>
                    <td><stripes:text name="profile.phone" /></td>
                </tr>
                <tr>
                    <td>Billede</td><td> :</td>
                    <td><stripes:file name="photo"/></td>
                </tr>
                <tr>
                    
                <td>
                Beskrivelse</td><td>
                </td>
                <td><stripes:textarea name="profile.description"/></td>
            </table>
            <stripes:submit name="save" value="Gem" />
        </stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
