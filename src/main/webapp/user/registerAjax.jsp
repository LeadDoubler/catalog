<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

        SIGN UP
        
        <stripes:errors></stripes:errors>
        <stripes:form action="/user/Register.action" focus="">
            <stripes:hidden name="user"/>
            <stripes:hidden name="targetUrl"/>
                <div style="display:none">
                    <span class="form_left">Brugernavn :</span><span class="form_right"><stripes:text name="user.username" />
                    <stripes:errors field="user.username">
                        <br/>
                          <stripes:individual-error/>
                        </stripes:errors>
                    </span>
                </div>
                <div>
                    <span class="form_left">Email :</span><span class="form_right"><stripes:text name="user.email" />
                    <stripes:errors field="user.email">
                        <br/>
                          <stripes:individual-error/>
                        </stripes:errors>
                    </span>
                </div>
                <c:if test="${actionBean.user.id == null or actionBean.user.id == user.id}">
                    <div>
                        <span class="form_left">Adgangskode :</span><span class="form_right"><stripes:password name="password1"/>
                        <stripes:errors field="password1">
                            <br/>
                              <stripes:individual-error/>
                            </stripes:errors>
                        </span>
                    </div>

                    <div>
                        <span class="form_left">Gentag :</span><span class="form_right"><stripes:password name="password2"/>
                        <stripes:errors field="password2">
                            <br/>
                          <stripes:individual-error/>
                        </stripes:errors>
                        </span>
                    </div>
                </c:if>
                    <div>
                    <span class="form_left">Fornavn :</span>
                    <span class="form_right"><stripes:text name="user.firstName"/>
                    <stripes:errors field="user.firstName">
                      <br/><stripes:individual-error/>
                    </stripes:errors>
                    </span>
                </div>
                <div>
                    <span class="form_left">Efternavn :</span>
                    <span class="form_right"><stripes:text name="user.lastName"/>
                    <stripes:errors field="user.lastName">
                      <br/><stripes:individual-error/>
                    </stripes:errors></span>
                </div>

                <c:choose>
                    <c:when test="${sessionScope.user.role.value > 4}">
                        <div>
                        <span class="form_left">Rettigheder:</span>
                        <span class="form_right">
                        <stripes:radio name="user.role" value="ADMINISTRATOR"/> Administrator<br/>
                        <stripes:radio name="user.role" value="MODERATOR"/> Partner<br/>
                        <stripes:radio name="user.role" value="USER"/> Alm. bruger (studerende eller virksomhedsrepræsentant)<br/>
                        
                        
                        
                            <%--<stripes:select name="user.role">
                                        <stripes:options-enumeration enum="com.asap.security.RoleModerator" label="name" />
                                 
                                    
                            </stripes:select>--%>
                            </span>
                        </div>
                        </c:when>
                        <c:otherwise>
                         <stripes:hidden name="user.role" value="USER"/>

                            </c:otherwise>

                    </c:choose>

                <jsp:include page="extraRegister.jsp"/>
                <div>
                    <span class="form_left">
            <stripes:submit name="save" value="Gem"/>
            <br/>
            </span>
                </div>
                <div><br/></div>
        </stripes:form>

