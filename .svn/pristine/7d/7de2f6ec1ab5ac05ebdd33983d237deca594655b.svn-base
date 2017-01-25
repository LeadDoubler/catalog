<%@ include file="/taglibs.jsp"%>
            
<stripes:form action="/category/Category.action" focus="" method="post" >
    <stripes:hidden name="category"/>
    <stripes:hidden name="category.parent"/>
        <label>Dansk titel :</label>
        <stripes:text name="category.title"/><br/>
        <label>Engelsk titel :</label>
        <stripes:text name="category.englishtitle"/>
        
         <c:choose>
                <c:when test="${actionBean.category.id != null}">
                    <stripes:button onclick="invoke(this.form, this.name, 'category_title_${actionBean.category.id}');$('category_form_${actionBean.category.id}').innerHTML = ''" name="inlineSave" value="Gem" />
            
                </c:when>
                <c:otherwise>                    
          <stripes:submit name="saveAndRefresh" value="gem"/>  
                </c:otherwise>
            </c:choose>
</stripes:form>