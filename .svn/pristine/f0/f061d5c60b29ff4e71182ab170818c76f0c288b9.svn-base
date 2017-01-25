<%@ include file="/taglibs.jsp"%>
<%--<stripes:layout-render name="/layout/standard.jsp" title="Site adgangsforhold">
	<stripes:layout-component name="contents">--%>
		<stripes:form action="/resource/Resource.action" focus="">
			<table>
				<tr>
					<td>Komponent :</td>
					<td>URL :</td>
					<td>Rolle :</td>
					<td>Ny Rolle :</td>
				</tr>
				<c:forEach var="resource" items="${actionBean.resources}">
					<tr>
						<td>${resource.key}</td>
						<td>${resource.value.url}</td>
						<td><stripes:select name="resourcesMap['${resource.key}'].">
							<stripes:options-enumeration enum="com.asap.security.Role"
								label="name" />
						</stripes:select></td>
						<td>${resource.value.defaultRole.name}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="2"><stripes:submit name="save" value="Gem" /></td>
				</tr>
			</table>
		</stripes:form>
	<%--</stripes:layout-component>
</stripes:layout-render>--%>
