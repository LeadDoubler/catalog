<%@ include file="/taglibs.jsp"%>

<stripes:layout-render name="/layout/standard.jsp" title="Nyheder">
    <stripes:layout-component name="contents">
        <span class="actions"><stripes:link href="News.action?edit">Ny nyhed</stripes:link></span>
        <br><br><table>
            <tr>
               <th>Dato</th>
               <th>Titel</th>
               <th>Beskrivelse</th>
               <th>Link</th>
               <th>Handling</th>
            </tr>
            <c:forEach items="${actionBean.components}" var="news" varStatus="loop">
                   <tr>
                       <td><fmt:formatDate pattern="dd/MM" value="${news.date}"/></td>
                       <td>${news.title}</td>
                       <td>${news.description}</td>
                       <td>${news.link}</td>
                       <td>
                           <stripes:link href="News.action?edit&news=${news.id}">Rediger</stripes:link><br>
                           <a href="#" onclick="if(confirm('Er du sikker på at du vil slette denne nyhed?'))
                              window.location='${pageContext.request.contextPath}/news/News.action?delete&news=${news}';">Slet</a>
                       </td>
                   </tr>
             </c:forEach>
        </table>
    </stripes:layout-component>
</stripes:layout-render>
