<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Rediger email">
<fmt:setBundle basename="StripesResources"/>
    
    <stripes:layout-component name="contents">
    
       <%-- <a href="#" onclick="alert('preview');new Ajax.Updater('preview','${pageContext.request.contextPath}/email/Email.action?preview&template=${actionBean.template}&text=$('textField').value',{method:'post'});"">
        
        Preview
        </a>
        <div id="preview"> </div>--%>
    
            <h2>Rediger email</h2>
            <p>
                Her kan du redigere de emails, der sendes ud fra systemet automatisk.
            </p>
        <stripes:form action="/email/Email.action" focus="" method="post" >
            <stripes:hidden name="template"/>
    <stripes:hidden name="name"/>
            
            <%--
            <cat:in name="from" label="Fra" info="Email, der skal sendes fra" type="text"/>
        
            <cat:in name="subject" label="Emne" info="Emne" type="text"/>
        --%>
        <ul>
            <li>
        <a name="testing_template" href="#testing_template" onclick="new Ajax.Updater('testing_template','${pageContext.request.contextPath}/email/Email.action?testTemplate&template=${actionBean.template}',{method:'get'});return false;" title="Sender en testemail til den bruger, der er logget ind. (dig)">
                                    Send test mail til dig selv
                                </a>
                                
                            </li>
                            <li>
                                <stripes:button name="save" value="Gem"
                                onclick="voke(this.form, this.name, 'testing_template');"/>
                            </li>
                               
                        </ul>
                        <span id="testing_template"></span>
           
            <div>
                 <stripes:textarea name="text" rows="50" cols="60" id="textField"/>
            </div>
            
        
            
            <stripes:button name="invoke" value="Gem"
                                onclick="voke(this.form, this.name, 'testing_template');"/>
        </stripes:form>
        
    
  </stripes:layout-component>
</stripes:layout-render>