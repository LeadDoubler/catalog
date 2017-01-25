<%@ page import="util.URLUtil"%>
<%@ include file="/taglibs.jsp"%>
<%@ taglib prefix="cat" tagdir="/WEB-INF/tags" %>
    


<stripes:layout-definition>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>    
    
            <table>
                <tr>
                    <td width="100"></td>
                    <td style="font-size:smaller">
                        <%--<img src="<%=URLUtil.getLink(request)%>/images/logo0000.jpg">--%>
    <br/>
                        <stripes:layout-component name="contents"/>
                    </td>
                    
                </tr>
            </table>
            
</body>
</html>      

</stripes:layout-definition>