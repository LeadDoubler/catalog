<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="SMS-service">
    <stripes:layout-component name="contents">
<cat:part part="contact.edit.intro" site="yes"/>        

        
        <stripes:form action="/contact/Contact.action" focus="page" method="post">
            <stripes:hidden name="contact"/>

<cat:in name="contact.name" label="Navn"/>            
<cat:in name="contact.phone" label="Tlf. nummer"/>            
<cat:in name="contact.email" label="Email"/>            
<cat:in name="contact.message" type="textarea" label="Besked"/>            

            <p class="button">
                <button type="submit" onclick="invoke(this.form, this.name, 'contact_form')" name="send" value="">Send</button>
            </p>           
        </stripes:form>
    </stripes:layout-component>
</stripes:layout-render>