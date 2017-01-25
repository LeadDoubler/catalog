<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Edit Family">
	<stripes:layout-component name="contents">

		<jsp:useBean id="categoryManager" scope="page"
			class="com.asap.catalog.dao.manager.CategoryManager" />

		<stripes:form action="/family/edit.htm" focus="">
			<table>
				<tr>
					<td>Id :</td>
					<td><stripes:text name="id" value="${family.stringId}" /></td>
				</tr>
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
					<td>Category :</td>
					<c:set var="categories" value="${categoryManager.allCategory}" />
					<c:if test="${categories == null}">
						<td>Empty Categories List</td>
					</c:if>
					<c:if test="${categories != null}">
						<td><stripes:select name="parentId">
							<stripes:option label="foo" value="foo">FOoo</stripes:option>
							<stripes:options-collection value="stringId"
								collection="${categories}" label="title" />
						</stripes:select></td>
					</c:if>
				</tr>

				<tr>
					<td colspan="2"><stripes:submit name="edit" value="Edit" /></td>
				</tr>
			</table>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>
