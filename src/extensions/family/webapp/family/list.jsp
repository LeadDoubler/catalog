<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Category List">
	<stripes:layout-component name="contents">
		<h1>Category List</h1>

		<jsp:useBean id="familyManager" scope="page"
			class="com.asap.catalog.dao.manager.FamilyManager" />

		<stripes:form action="/family/list.htm" focus="">
			<table>
				<tr>
					<td>Title</td>
					<td>Intro</td>
					<td>Description</td>
					<td>Category</td>
				</tr>

				<c:forEach items="${familyManager.allFamily}" var="family">
					<tr>
						<td>${family.title}</td>
						<td>${family.intro}</td>
						<td>${family.description}</td>
						<td>${family.family}</td>
						<td><stripes:link href="/family/edit.htm" event="preEdit">
                                Edit
                <stripes:param name="family.id" value="${family.id}" />
						</stripes:link> <stripes:link href="/family/show.htm" event="preEdit">Show<stripes:param
								name="family.id" value="${family.id}" />
						</stripes:link></td>
					</tr>
				</c:forEach>
			</table>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>
