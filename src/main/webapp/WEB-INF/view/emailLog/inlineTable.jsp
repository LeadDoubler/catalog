<%@ include file="/taglibs.jsp"%>
<fmt:setBundle basename="StripesResources"/>
<fmt:setLocale value="${pageContext.request.locale}"/>

<stripes:layout-render name="/layout/standard.jsp" title="Log">

    <stripes:layout-component name="contents">

      
                <div class="search-panel">
                                    <stripes:form beanclass="${actionBean.class}">
								<fieldset>
									<stripes:text name="searchText"/>
									 <stripes:submit name="inlineTable" class="button" value="Search" />
                                                                      
								</fieldset>
							 </stripes:form> 
                                                     </div>
<display:table id="row" name="actionBean.objects" sort="external" requestURI="${pageContext.request.contextPath}/emailLog/${actionBean.capType}.action?searchText=${actionBean.searchText}" >
 <display:column property="created" title="Afsendt"/>
                     <display:column property="subject" title="Emne" sortable="true"/>
                     <display:column title="Indhold">
                         <a href="#" onclick="$('${row}_content').toggle()">
                            Vis indhold
                         </a>
                          <div id="${row}_content" style="display:none">
                                 ${row.content}
                             </div>
                     </display:column>
                     <display:column property="email" title="Til" sortable="true"/>
  <display:setProperty name="paging.banner.placement" value="bottom" />
</display:table>


  </stripes:layout-component>
</stripes:layout-render>