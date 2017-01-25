<%@ include file="/taglibs.jsp"%>
            
<stripes:form action="/category/Category.action" focus="" method="post" >
    <stripes:hidden name="category"/>
        <label>Dansk titel :</label>
        <stripes:text name="category.title" />
        <label>Engelsk titel :</label>
        <stripes:text name="category.englishtitle"/>
<stripes:button name="inlineSave" value="Gem"
         onclick="invoke(this.form, this.name, '${category.id}');"/>
    <stripes:submit name="save" value="Gem"/>

</stripes:form>