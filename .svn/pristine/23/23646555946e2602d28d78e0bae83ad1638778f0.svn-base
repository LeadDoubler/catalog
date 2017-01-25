<%@ include file="/taglibs.jsp"%>

<stripes:form beanclass="${actionBean.class}">
    <cat:in name="email.subject" label="Emne"/>
    <div>
       
  
        <%--<a href="#" onclick="$('preview').innerHTML=$('texteditarea').value;$('textedit').toggle();$('preview').toggle();$('editButton').toggle();$('previewButton').toggle()" id="previewButton">Preview</a>
        <a href="#" style="display:none" onclick="$('textedit').toggle();$('preview').toggle();$('editButton').toggle();$('previewButton').toggle()" id="editButton">Edit</a>
        <div id="textedit">

--%>            <%--
         <stripes:textarea cols="60" rows="40" name="email.content" id="texteditarea"/>
--%>

<FCK:editor                           
                    id="email.content"     
                    width="100%"
                    height="300"
                    toolbarSet="Basic">${actionBean.email.content}</FCK:editor>
    <%--    </div>
        <div id="preview" style="display:none;">
            <div style="width:300px;height:400px;background-color:white">
            ${actionBean.email.content}
        </div>
        </div>
    </div>
    --%>

          <div>
              <h4>Modtagere</h4>
    <c:forEach items="${actionBean.users}" var="user">
             <stripes:checkbox name="users" value="${user}"/>${user.fullname} (${user.email})<br/>
        </c:forEach>
        <stripes:text name="receivers"/>
</div>
    <div>
        <c:choose>
          
                <c:when test="${param.inline != null}">
                    <stripes:button onclick="invoke(this.form, this.name, '${param.inline}');" name="send" value="Send" />
            
                </c:when>
                <c:otherwise>    
                <stripes:submit name="send" value="Send"/>
          
                </c:otherwise>
            </c:choose>
        
    </div>
</stripes:form>