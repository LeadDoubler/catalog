<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

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

   <%@attribute name="id" required="true"%>
   <%@ attribute name="url" required="true" %>
   <%@ attribute name="createSpan" required="false" %>
   
       <script type="text/javascript">
        Event.observe(window, 'load', page_loaded, false);

	function page_loaded(evt) {
            var url = '${url}';
            ajax('${id}',url);
            //new Ajax.Updater('${id}',url,{method:'post'});	    
        }
    </script>
         <div style="margin:0px; padding:0px" id="${id}"><img src="${pageContext.request.contextPath}/images/indicator_arrows.gif"/></div>   
    
  