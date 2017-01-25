<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>


<%@attribute name="name" required="true"%>
<%@attribute name="label" required="true"%>
<div>
    <span class="form_left"></span>
    <span class="form_right">
       <stripes:button name="${name}" value="${label}"
                                onclick="voke(this.form, this.name, 'content');"/>
  
    </span>
</div> 
         
