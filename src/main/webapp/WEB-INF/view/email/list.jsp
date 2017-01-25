<%@ include file="/taglibs.jsp"%>

<jsp:useBean id="emailManager" class="org.mortena.mail.EmailBean" scope="page"/>


            <cat:userAccess role="3">
                <span onclick="$('emailList').toggle()" style="font-size:80%">Templates</span><br/>
                <table id="emailList" style="display:none">
                    
                    <c:forEach items="${emailManager.fileNamesWithoutFTL}" var="email">
                         <tr>
                            <td>
                                ${email} 
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/email/Email.action?editTemplate&template=${email}">
                                    Rediger
                                </a>
                            </td>
                           <%-- <td>
                                <a name="testing_template_${email}" href="#testing_template_${email}" onclick="new Ajax.Updater('testing_template_${email}','${pageContext.request.contextPath}/email/Email.action?testTemplate&template=${email}',{method:'get'})" title="Sender en testemail til den bruger, der er logget ind. (dig)">
                                    Test
                                </a> <span id="testing_template_${email}"> </span>
                               
                            </td>--%>
                        </tr>
                    </c:forEach>    
                    
                </table>
                <a href="${pageContext.request.contextPath}/emailLog/EmailLog.action?inlineTable">Se udsendte mails</a>
     </cat:userAccess>
  