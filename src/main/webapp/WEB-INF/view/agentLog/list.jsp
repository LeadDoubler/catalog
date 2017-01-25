<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="System Log">
<stripes:layout-component name="contents">
<fmt:setBundle basename="StripesResources"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
 
 <style>

table {
	border-collapse: collapse;
}

td, th {
	padding: 0.5em;
	border: 1px solid #CCC;
}

thead, tfoot {
	background-color: #DDD;
}

tr.rowodd {
	background-color: #FFF;
}

tr.roweven {
	background-color: #F2F2F2;
}

.sortcol {
	cursor: pointer;
	padding-right: 20px;
	background-repeat: no-repeat;
	background-position: right center;
}
.sortasc {
	background-color: #DDFFAC;
	background-image: url(images/up.gif);
}
.sortdesc {
	background-color: #B9DDFF;
	background-image: url(images/down.gif);
}
.nosort {
	cursor: default;
}

.urg1, .urg2, .urg3, .urg4, .urg5 {
	padding: 3px;
	text-align: center;
	border: 1px solid #333;
	width: 3em;
}

.urg1 {
	background-color: #FFFACD;
}
.urg2 {
	background-color: #FFD700;
}
.urg3 {
	background-color: #FFA500;
}
.urg4 {
	background-color: #E13E00;
}
.urg5 {
	background-color: Red;
}

th.resize-handle-active {
	cursor: e-resize;
}

div.resize-handle {
	cursor: e-resize;
	width: 2px;
	border-right: 1px dashed #1E90FF;
	position:absolute;
	top:0;
	left:0;
}

 </style>
             
                <display:table name="actionBean.logs" id="listOfEmailLogs" class="sortable resizable">
                     <display:column property="created" title="K�rsel"/>
                     <display:column property="agent" title="Klasse"/>
                </display:table>
</stripes:layout-component>
</stripes:layout-render>