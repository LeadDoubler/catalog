<%@ include file="/taglibs.jsp"%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="cat" tagdir="/WEB-INF/tags" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

   <%@attribute name="name" required="true"%>   
   <%@attribute name="type" required="false"%>   
   <%@attribute name="label" required="false"%>
   <%@attribute name="info" required="false"%>
   <%@attribute name="value" required="false" %>
   <%@ attribute name="size" required="false" %>
   <%@ attribute name="id" required="false" %>
   <%@ attribute name="onfocus" required="false" %>
   <%@ attribute name="classname" required="false" %>
   <%@ attribute name="cols" required="false" %>
   <%@ attribute name="rows" required="false" %>

<c:if test="${rows ==null}">
    <c:set var="rows" value="6"/>
</c:if>
<c:if test="${cols ==null}">
    <c:set var="cols" value="20"/>
</c:if>

 <c:if test="${classname eq null}">
       <c:set var="classname" value="none"/>
   </c:if>

   <c:if test="${type eq null}">
       <c:set var="type" value="text"/>
   </c:if>
   
   <div title="${info}">       
       <span class="form_left">
           ${label}
       </span>
       <span class="form_right">
           <c:choose>
                <c:when test="${type eq 'date'}">
                <stripes:text class="txtInputStyle date" id="datepicker" name="${name}" value="${value}" size="${size}" onfocus="${onfocus}" />
               </c:when>
               <c:when test="${type eq 'text'}">
                   <stripes:text name="${name}" size="${size}" id="${id}" onfocus="${onfocus}" class="${classname}"/>
               </c:when>
               <c:when test="${type eq 'radio'}">
                   <stripes:radio value="true"  name="${name}" id="${id}" onfocus="${onfocus}" class="${classname}"/>
               </c:when>
               <c:when test="${type eq 'textarea'}">
                   <stripes:textarea name="${name}" class="${classname}" rows="${rows}" cols="${cols}" id="${id}" onfocus="${onfocus}">${value}</stripes:textarea>
               </c:when>
               <c:when test="${type eq 'checkbox'}">
                   <c:if test="${value eq true}">
                       <input type="hidden" name="${name}" value="false"/>
                   </c:if>
                   <stripes:checkbox name="${name}" value="${value}" id="${id}" onfocus="${onfocus}" class="${classname}"/>
               </c:when>
               
               <c:when test="${type eq 'password'}">
                   <stripes:password name="${name}" size="${size}" id="${id}" onfocus="${onfocus}" class="${classname}"/>
               </c:when>
               
              <%-- <c:when test="${type eq 'wysiwyg'}">
                   <FCK:editor                           
                    id="${name}"
                    width="400"
                    height="250"
                    toolbarSet="Basic">${value}</FCK:editor>
               </c:when>--%>
               
               <c:when test="${type eq 'file'}">
                   <stripes:file name="${name}" id="${id}" onfocus="${onfocus}" class="${classname}"/>
               </c:when>
               
           </c:choose>
           <stripes:errors field="${name}"><br/>
                            <stripes:individual-error/>
                        </stripes:errors>
                        
                        
                    </span>
                    <%--
                    (<a href="#" onclick="$('helptext_div_${name}').style.height='40px';">?</a>)
                    <div id="helptext_div_${name}" style="display:block;height:0px">
                    <cat:part part="helptext_${name}" site="yes"/>
                    </div>--%>
   </div>