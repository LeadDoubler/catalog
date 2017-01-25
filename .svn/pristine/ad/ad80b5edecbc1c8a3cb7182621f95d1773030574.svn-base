<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Add Family">
	<stripes:layout-component name="contents">

		<jsp:useBean id="categoryManager" scope="page"
			class="com.asap.catalog.dao.manager.CategoryManager" />

		<stripes:form action="/family/add.htm" focus="">
			<table>
				<tr>
					<td>Title :</td>
					<td><stripes:text name="title" /></td>
				</tr>
				<tr>
					<td>Intro :</td>
					<td><stripes:text name="intro" /></td>
				</tr>
				<tr>
					<td>description :</td>
					<td><stripes:text name="description" /></td>
				</tr>
				<tr>
					<td>Family :</td>
					<td><stripes:text name="family" /></td>
				</tr>
				<tr>
					<td>Category :</td>
					<c:set var="categories" value="${categoryManager.allCategory}" />
					<c:if test="${categories == null}">
						<td>Empty Categories List</td>
					</c:if>
					<c:if test="${categories != null}">
						<td><stripes:select name="parent">
							<stripes:option label="foo" value="foo">FOoo</stripes:option>
							<stripes:options-collection value="id"
								collection="${categories}" label="title" />
						</stripes:select></td>
					</c:if>
				</tr>
				<tr>
					<td colspan="2"><stripes:submit name="add" value="Add" /></td>
				</tr>
				<tr>
					<td>Result:</td>
					<td>${actionBean.result}</td>
				</tr>
			</table>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>
