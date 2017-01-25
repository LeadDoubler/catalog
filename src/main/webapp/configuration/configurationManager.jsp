<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Configuration">
    <stripes:layout-component name="contents">
        <stripes:form action="/configuration/Configuration.action" name="theForm" focus="" method="post">
            <stripes:hidden name="idConf"/>
            <stripes:hidden name="nameConf"/>
            <stripes:hidden name="valueConf"/>
            <font color="red">
                <b>
                    <c:forEach items="${actionBean.errors}" var="error">
                        ${error}
                        <br>
                    </c:forEach>
                </b>
            </font>
            <h1>Configuration</h1>
            <table>
                <tr>
                   
                    <td>Name</td>
                    <td>Value</td>
                    <td>Action</td>
                    
                </tr>
                
                
                
                
                <c:forEach items="${actionBean.configurations}" var="configuration">
                    <tr>
                        <stripes:hidden name="idConf_${configuration.id}" value="${configuration.id}"/>
                       <%-- <td><stripes:text readonly="true" name="idConf_${configuration.id}">${configuration.id}</stripes:text></td>/--%>
                        <td><stripes:text name="nameConf_${configuration.id}">${configuration.withoutNamespaceName}</stripes:text></td>
                        <td><stripes:text name="valueConf_${configuration.id}">${configuration.value}</stripes:text></td>
                       
                        <td><stripes:submit name="save" value="Save" onclick="theForm.nameConf.value=theForm.nameConf_${configuration.id}.value;theForm.valueConf.value=theForm.valueConf_${configuration.id}.value;theForm.idConf.value=theForm.idConf_${configuration.id}.value;" ></stripes:submit>
                            <stripes:submit name="delete" value="Delete" onclick="theForm.idConf.value=theForm.idConf_${configuration.id}.value;"></stripes:submit>
                        </td>
                        
                    </tr>
                    
                </c:forEach>
                
                
                <tr>
                   
                        <td><stripes:text name="name"/></td>
                        <td><stripes:text name="value"/></td>
                        <td><stripes:submit name="add" value="Add"></stripes:submit></td>
                   
                </tr>
            </table>
        </stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
