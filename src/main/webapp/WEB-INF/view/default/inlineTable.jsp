<%@ include file="/taglibs.jsp"%>
<fmt:setBundle basename="StripesResources"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
<%--<%@ taglib uri="http://ajaxtags.sourceforge.net/tags/ajaxtags"
    prefix="ajax"%>

<ajax:area id="${actionBean.type}_table"
  ajaxAnchors="true">--%>
      
                <div class="search-panel">
                                    <stripes:form beanclass="${actionBean.class}">
								<fieldset>
									<stripes:text name="searchText"/>
									 <stripes:submit name="inlineTable" class="button" value="Search" onclick="invoke(this.form, 'search', '${actionBean.type}_table');return false;"/>
                                                                      
								</fieldset>
							 </stripes:form> 
                                                     </div>
<display:table id="row" name="actionBean.objects" sort="external" requestURI="${pageContext.request.contextPath}/${actionBean.type}/${actionBean.capType}.action?searchText=${actionBean.searchText}" >
    <display:column property="title" title="Title" sortable="true" >
           </display:column>
  
  <display:setProperty name="paging.banner.placement" value="bottom" />
</display:table>
<%--
</ajax:area>
--%>