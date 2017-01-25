<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

<%-- Taglib directives can be specified here: --%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%--
<%@attribute name="title" required="true"%>
<%@attribute name="author"%>
<%@attribute name="price_info" fragment="true"%>
--%>

<%-- Use expression language to work with normal attributes or use --%>
<%-- the <jsp:invoke> or <jsp:doBody> actions to invoke JSP fragments or tag body: --%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

   <%@attribute name="title" required="false"%>
   <%@attribute name="id" required="false"%>
   <%@ attribute name="url" required="false" %>
   
   <c:if test="${url != null and id != null}">
       <script type="text/javascript">
        Event.observe(window, 'load', page_loaded, false);

	function page_loaded(evt) {
            $('${id}').innerHTML = 'Opdateres';
            var url = '${url}';
            ajax('${id}',url);
            //new Ajax.Updater('${id}',url,{method:'get'});	    
        }
    </script>
   </c:if>
   
   
   <table class="adminTable">
       <thead>
        <tr>
            <th>${title}</th>            
        </tr>
    </thead><tbody>
        <tr>
            <td id="${id}">
                <jsp:doBody/>                
            </td>
        </tr>
    </tbody>
    </table>
   
  