<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:form action="/user/User.action" focus="">
    <stripes:hidden name="user"/>
    <table>
        <tr>
            <td>Brugernavn :</td><td width="70%"><stripes:text name="user.username"/>
            <stripes:errors field="user.username">
                <br/>
                  <stripes:individual-error/>
                </stripes:errors>
            </td>
        </tr>
        <tr>
            <td>Email :</td><td><stripes:text name="user.email"/>
            <stripes:errors field="user.email">
                <br/>
                  <stripes:individual-error/>
                </stripes:errors>
            </td>
        </tr>
        <c:if test="${actionBean.user.id == null or actionBean.user.id == user.id}">
            <tr>
                <td>Adgangskode :</td><td><stripes:password name="password1"/>
                <stripes:errors field="password1">
                    <br/>
                      <stripes:individual-error/>
                    </stripes:errors>
                </td>
            </tr>

            <tr>
                <td>Gentag :</td><td><stripes:password name="password2"/>
                <stripes:errors field="password2">
                    <br/>
                  <stripes:individual-error/>
                </stripes:errors>
                </td>
            </tr>
        </c:if>
            <td>Fornavn :</td>
            <td><stripes:text name="user.firstName"/>
            <stripes:errors field="user.firstName">
              <br/><stripes:individual-error/>
            </stripes:errors>
            </td>
        </tr>
        <tr>
            <td>Efternavn :</td>
            <td><stripes:text name="user.lastName"/>
            <stripes:errors field="user.lastName">
              <br/><stripes:individual-error/>
            </stripes:errors></td>
        </tr>

        <cat:userAccess role="4">
            <tr>
                <td>Rettigheder:</td>
                <td>
                    <stripes:select name="user.role">

                        <c:choose>
                            <c:when test="${user.role.value eq 4}">
                                <stripes:options-enumeration enum="com.asap.security.RoleModerator" label="name" />
                            </c:when>
                            <c:otherwise>
                                <stripes:options-enumeration enum="com.asap.security.Role" label="name" />
                            </c:otherwise>
                        </c:choose>

                    </stripes:select>
                </td>
            </tr>
        </cat:userAccess>
        
    </table>
    <stripes:submit name="save" value="Gem"/>
</stripes:form>