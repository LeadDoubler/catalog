<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Login" keywords="login" description="Login site">
    <stripes:layout-component name="contents">
        <stripes:form action="/user/Login.action" focus="">
            <stripes:hidden name="targetUrl" value="${request.parameterMap['targetUrl']}" />

            <div><span class="form_left"><stripes:label for="user.username" /></span>
                <span class="form_right">
                    <stripes:text name="username"/>
                    <span class="error">
                        <br><stripes:errors field="username">
                            <stripes:individual-error/>
                        </stripes:errors>
                    </span>
                </span>
            </div>

            <div><span class="form_left"><stripes:label for="user.password" /></span>
                <span class="form_right">
                    <stripes:password name="password"/>
                    <span class="error">
                        <br><stripes:errors field="password">
                            <stripes:individual-error/>
                        </stripes:errors>
                    </span>
                </span>
            </div>
            
            <stripes:submit name="login" value="Login" class="submit" style="float:right"/>
            
            <div>
                <span class="form_left"> </span>
                <span class="form_right">
                    <a href="#" onclick="new Ajax.Updater('forgotWindow','${pageContext.request.contextPath}/user/ForgotPass.action',{method:'get'});"><small><fmt:message key="forgotpassword"/></small></a>
                    <cat:else parameter="hideRegistration" value="yes">  
                        <a href="${pageContext.request.contextPath}/user/Add.action?init"><small><fmt:message key="register"/></small></a>
                    </cat:else>
                </span>
            </div>
            <div>
                <br/><br/>
            </div>
            
        </stripes:form>
        <span id="forgotWindow"/>
    </stripes:layout-component>
</stripes:layout-render>

