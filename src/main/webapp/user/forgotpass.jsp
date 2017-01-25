<%@ include file="/taglibs.jsp"%>

<stripes:form action="/user/ForgotPass.action" focus="" method="post">
    
     <div>
        <span class="form_left"><stripes:label for="insertEmail" />:</span>
         
        <span class="form_right">
            <stripes:text name="email"/>
            <span class="error">
                <br><stripes:errors field="email">
                    <stripes:individual-error/>
                </stripes:errors>
            </span>
        </span>
    </div>
    <div>
    <stripes:button class="submit" style="float:right;" name="send" value="Send" onclick="invoke(this.form, this.name, 'forgotWindow');"/>
</div>
<div></div>
</stripes:form>

