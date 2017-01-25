<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

<%@attribute name="id" required="true"%>
<%@attribute name="stringName" required="true"%>
<%@attribute name="title"%>

<stripes:text id="${id}" name="${stringName}" size="10" style="width: 150px;"/>
                        
    
         <img src="${pageContext.request.contextPath}/jsCalendar/img.gif" id="trigger${id}"
                             style="cursor: pointer; border: 1px solid red;"
                             title="${title}"
                             onmouseover="this.style.background='red';"
                             onmouseout="this.style.background=''"/>
                        <script type="text/javascript">
                        Calendar.setup(
                        {
                              inputField  : "${id}",    // ID of the input field
                              ifFormat    : "%d/%m-%Y",    // the date format
                              button      : "trigger${id}",       // ID of the button
                              showsTime   : false
                        }
                        );
                        </script>
           