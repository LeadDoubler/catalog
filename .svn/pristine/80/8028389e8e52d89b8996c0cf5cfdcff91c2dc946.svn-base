<%@ include file="/taglibs.jsp"%>


<fmt:setBundle basename="StripesResources"/>
      <stripes:form action="/${actionBean.type}/${actionBean.capType}.action" focus="" method="post" >
            <stripes:hidden name="${actionBean.type}"/>
            <h2>Tilmeld nyhedsbrev</h2>
            
            <cat:part part="subscriptionHead" site="yes"/>
            
            <cat:in name="subscription.email" label="Email" info="Skriv din email her og bliv tilmeldt nyhedsbrev" type="text"/>
        
            <cat:inlineSubmit name="invoke" label="Gem"/>
            
            <%--
            <stripes:button name="invoke" value="Gem"
                                onclick="voke(this.form, this.name, 'content');"/>
          --%>
           <%--<stripes:submit name="save" value="Gem"/>--%>
              
        </stripes:form>
        
               