<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Add Book" language="${page.language.value}">
	<stripes:layout-component name="contents">
<script type="text/javascript" src="${pageContext.request.contextPath}/prototype.js"></script>
<h1>User Manager</h1>

<ul>
    <li>
        <a href="#" onclick="new Ajax.Updater('editorWindow','${pageContext.request.contextPath}/user/User.action?add',{method:'get'});">Add User</a>
                                            
    </li>    
</ul>

<span id="editorWindow"></span>

<jsp:include page="listUsers.jsp"/>


	</stripes:layout-component>
</stripes:layout-render>