<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<jsp:useBean id="categoryManager" scope="page" class="com.asap.catalog.dao.manager.CategoryManager" />
<stripes:layout-render name="/layout/standard.jsp" title="Administrer kategorier">
	
<stripes:layout-component name="html-head">
    
    
      <script src="${pageContext.request.contextPath}/ajax/prototype.js" type="text/javascript"></script>
        <script type="text/javascript">

        function edit(id){
            //alert('Rediger-'+id);
            var pars = 'category='+id+'&'+'inlineEdit';
            new Ajax.Updater('category_form_'+id,'${pageContext.request.contextPath}/category/Category.action',{method:'get',parameters: pars});
            return false;
            }
            
         function deleteCat(id){
            if(confirm('Vil du slette denne kategori?')){
               var pars = 'inlineRemove'+'&'+'category='+id;
               var url = '${pageContext.request.contextPath}/category/Category.action';
               
               new Ajax.Updater(''+id,url,{method:'get',parameters: pars});
            }
            return false;
            }
            
            
        function addChild(id){
            var pars = 'category.parent='+id+'&'+'inlineEdit';
            new Ajax.Updater('addForm_'+id,'${pageContext.request.contextPath}/category/Category.action',{method:'get',parameters: pars});
            return false;
            }

        function movePage(page,parent){
            var pars = 'category='+page+'&'+'category.parent='+parent+'&'+'save=';
            //alert('url=${pageContext.request.contextPath}/category/Category.action?'+pars);

            new Ajax.Updater('message', '${pageContext.request.contextPath}/category/Category.action', {method: 'get', parameters: pars});
            //alert('html - movePage('+page+','+parent+') finished');
        }

        var curId;

        function showLinks(id){
            /*if (curId != null){
                hideLinks(curId);
            }
            curId = id;
            */
            $(id).style.display='inline';
        }

        function hideLinks(id){
            $(id).style.display='none';
        }
        </script>
        
        <style type="text/css">
            .catLinks a{
                margin-left:10px;
                background-color:#eee;
                color:black;
                padding:0 3px 0 3px;
                border: black solid 1px;
                text-decoration:none;
            }
        </style>
    
</stripes:layout-component>

<stripes:layout-component name="contents">
    <%--
   <ul id="dhtmlgoodies_tree2" class="dhtmlgoodies_tree">
            <li id="node0" noDrag="true" noSiblings="true">
                <span onmouseover="showLinks('link_root')" onmouseout="hideLinks('link_root')">
                    <span id="category-title_root">
                    Kategorier
                    </span>                    
                    <span id="link_root" class="catLinks" style="display:none">
                        <a href="#addForm_root" onclick="addChild('root');">Adder kategori</a>
                        <span id="category_form_root">
                            
                        </span>    
                        
                    </span>
                </span>
                <ul>
                    
                
  <c:forEach items="${categoryManager.categoryCollection}" var="category">
      <li id="{id}" >
            <span onmouseover="showLinks('link_{id}')" onmouseout="hideLinks('link_{id}')"><a href="#" >
                <span id="category_title_{id}">
                ${title}
                </span>
                
            </a> 
            <span id="link_{id}" class="catLinks" style="display:none;">
                   <a href="#" onclick="edit('{id}');return false;">Rediger</a> 
                    <a href="#" onclick="deleteCat('{id}')">Slet</a> 
                     <a href="#" onclick="addChild('{id}');return false;">Adder underkategori</a>
                     <!--<a href="#" onclick="addBook('{id}')">Adder bog her</a>-->
                   </span>
                     <span id="category_form_{id}">
                     
                 </span>    
                     
                
                </span>
            <span id="addForm_{id}"></span>
        </li>
  </c:forEach>
  </ul>
                <a name="addForm_root"/>
                <span id="addForm_root"></span>

            </li>
        </ul>
 --%>
    <x:parse var="pages" xml="${categoryManager.catXml}"/>
    <c:import var="pagesToList" url="/category/editCategories.xsl"/>
    <x:transform xml="${pages}" xslt="${pagesToList}"/>
    
</stripes:layout-component>

</stripes:layout-render>

