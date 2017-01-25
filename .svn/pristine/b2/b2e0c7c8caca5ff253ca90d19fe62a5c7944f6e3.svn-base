<%@ tag import="com.asap.configuration.Configuration" %>

<%-- 
    Document   : if
    Created on : 2008-03-15, 15:43:34
    Author     : mortenmatras
--%>

<%@tag description="Checks whether the value of the selected configuration parameter is equals to the value" pageEncoding="MacRoman"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@ attribute name="parameter" required="true"%>
<%@ attribute name="value" required="false"%>


 
<%

if (value == null){
    value="yes";
}

if ( ! value.equals(Configuration.getInstance().getProps().getProperty(parameter) ) ){
    %>
    <jsp:doBody/>
    <%
}
%>

