<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<stripes:layout-render name="/layout/standard.jsp" title="Rediger/tilføj bruger">
    <stripes:layout-component name="contents">
        <h2>${actionBean.user.firstName} ${actionBean.user.lastName}</h2>
        
        <stripes:errors></stripes:errors>
        <stripes:form action="/user/Register.action" focus="">
            <stripes:hidden name="user"/>
                <div>
                    <span class="form_left">Brugernavn :</span><span class="form_right"><stripes:text name="user.username"/>
                    <stripes:errors field="user.username">
                        <br/>
                          <stripes:individual-error/>
                        </stripes:errors>
                    </span>
                </div>
                <div>
                    <span class="form_left">Email :</span><span class="form_right"><stripes:text name="user.email"/>
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
                    <c:when test="${sessionScope.user.role.value > 3}">
                        <div>
                        <span class="form_left">Rettigheder:</span>
                        <span class="form_right">
                            <stripes:select name="user.role">
                                <c:choose>
                                    <c:when test="${sessionScope.user.role.value > 4}">   
                                <stripes:options-enumeration enum="com.asap.security.Role" label="name" />
                                 </c:when>
                                 <c:otherwise>
                                     <stripes:options-enumeration enum="com.asap.security.RoleModerator" label="name" />
                                
                                 </c:otherwise>
                                    
                                </c:choose>
                            </stripes:select>
                            </span>
                        </div>
                        </c:when>
                        <c:otherwise>
                         <stripes:hidden name="user.role" value="Bruger"/>

                            </c:otherwise>

                    </c:choose>

                <jsp:include page="extraRegister.jsp"/>
                <div>
                    <span class="form_left">
            <stripes:submit name="saveExisting" value="Gem"/>
            </span>
                </div>
        </stripes:form>
    </stripes:layout-component>
</stripes:layout-render>

