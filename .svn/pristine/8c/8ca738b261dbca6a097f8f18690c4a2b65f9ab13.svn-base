<%@ include file="/taglibs.jsp"%>
<%@ taglib prefix="cat" tagdir="/WEB-INF/tags" %>
        
<stripes:layout-definition>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<c:choose>
<c:when test="${ requestScope['page'] eq null}">
    <fmt:setLocale value="${pageContext.request.locale}"/>        
</c:when>
<c:otherwise>
    <fmt:setLocale value="${requestScope['page'].locale}"/>        
</c:otherwise>    
</c:choose>
<fmt:setBundle basename="StripesResources"/>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta lang="${page.language}"/>

<title>${title}</title>
<meta name="keywords" content="${keywords}"/>
<meta name="description" content="${description}"/>
<%--<link href="${pageContext.request.contextPath}/css/style000.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/menu0000.css" rel="stylesheet" type="text/css" />--%>

<style type="text/css">
    @import url(${pageContext.request.contextPath}/jsCalendar/skins/aqua/theme.css);
    @import url(${pageContext.request.contextPath}/css/standard.css);
    @import url(${pageContext.request.contextPath}/css/style000.css);
    @import url(${pageContext.request.contextPath}/css/menu0000.css);
</style>

<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/images/menu0000.js"></script>
<!--[if IE 7]>
<style type="text/css">#mainnav ul li ul {background: url(images/clear.gif) 0 0 repeat;}</style>
<![endif]-->

<!--[if IE 6]>
<style type="text/css">
#mainnav ul li ul {position: absolute;height: auto;top: 0;}
#sidebar h4 {font-size: 1.25em;}
.box {overflow: hidden;}
.first {margin-left:20px;}
#content {margin-left: 4px; width: 325px;}
.contentitem {width: 444px;}
.actionlink {padding-right: 20px;}

/*Top level menu link items style*/
.suckertreemenu ul li a{
    display: block;
    width: 80px; Width of top level menu link items;
    padding: 4px 8px;
    text-decoration: none;
    color: #666;
}

.contentitem {
width: 466px;
float: left;
margin-bottom: 30px;
overflow: hidden;
padding-left:0px;
}

</style>
<![endif]-->

<%@ include file="javascripts.jsp" %>
<stripes:layout-component name="html-head"/>
</head>

<body>
<%--<div id="imageHeader" class="actions">
    <c:if test="${not empty user}">
        <stripes:link href="/user/Logout.action" ><fmt:message key="logout"/></stripes:link>
        <stripes:link href="/user/UserLoggedIn.jsp" ><fmt:message key="startpage"/></stripes:link>
    </c:if> 
    <c:if test="${empty user}">
        <stripes:link href="/user/Login.action" ><fmt:message key="login"/></stripes:link>
    </c:if>

    <stripes:link href="/page/sitemap.jsp" ><fmt:message key="sitemap"/></stripes:link>

    <stripes:link href="/ChooseLanguage.action">
        <stripes:param name="changeLanguage"/>
        <stripes:param name="language"><fmt:message key="otherLanguage"/></stripes:param>
        <stripes:param name="country"><fmt:message key="otherCountry"/></stripes:param>
        <fmt:message key="otherCountryName"/>
    </stripes:link>
</div>--%>

<div id="wrap">
    <div id="header">
        <img src="${pageContext.request.contextPath}/images/logo.jpg" alt="Softworld.dk Logo" id="logo" />
        <img src="${pageContext.request.contextPath}/images/phone.jpg" alt="7020 8055" id="phone" />

        <div id="topnav">
            <stripes:layout-component name="topnav">
              <jsp:include page="/layout/topnav.jsp"/>
            </stripes:layout-component>
         </div>

        <div id="banner">
            <div id="flash">
                 <stripes:layout-component name="banner">
                     
                     <cat:part part="banner" type="file"/>
                     <%-- <c:set var="type" value="${page.enheritedBannerType.value}"/>
                    <c:if test = "${type eq 1}">
                        <img src="${pageContext.request.contextPath}/page/Show.action?getEnheritedBanner&amp;page=${page.id}"/>
                    </c:if>
                    <c:if test="${type eq 2}">
                        <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
                            codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" 
                            title="Header">

                        <embed  src="${pageContext.request.contextPath}/page/Show.action?getEnheritedBanner&amp;page=${page.id}"
                                quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer"
                            type="application/x-shockwave-flash"/></object>
                    </c:if>--%>
                </stripes:layout-component>
            </div>
        </div>

        <div id="mainnav">
            <stripes:layout-component name="menu">
             <jsp:include page="/layout/menu.jsp"/>
            </stripes:layout-component>
        </div>

        <div id="breadcrumb">
            <p>                            
                <c:forEach items="${page.ancestors}" begin="1" var="ancestor" >
                    > <a href="${pageContext.request.contextPath}/page/Show.action?page=${ancestor}">${ancestor.title}</a>  
                </c:forEach>
            </p>
        </div>
        <div class="clr"></div>
    </div>
    <div id="fckeditorWindow" style="margin:0;padding:0"></div>
    
    <div id="main">
        <stripes:messages/>
        
        <div id="content">
            <div class="contentitem">
                    <br>
                    <c:if test="${method != 'showPage'}">
                        <h3>${title}</h3>
                    </c:if>
                    <stripes:layout-component name="contents"/>
            </div>
        </div>

        <div id="sidebar">
           <%--<stripes:layout-component name="box1">
                <div class="box">
                    <div class="boxtop" id="box0">
                        <h4  onclick="doSwitch('box0');"><fmt:message key="shopcart"/></h4>
                        <p style="cursor:pointer;cursor:hand;" onclick="window.location='${pageContext.request.contextPath}/shopcart/ShopCart.action?view'" class="boxcontent">
                            <small><%@ include file="../shopcart/items.jsp"%></small>
                        </p>
                    </div>
                    <div class="boxbtm"></div>
                </div>
            </stripes:layout-component>--%>
            
           <%-- <cat:userAccess role="3">
                <center>
                    <small style="color:red">Indhold af understående kasser nedarves i retning mod rod siden, som tilgås via<br>"CMS explorer".</small><br><br>
                </center>
            </cat:userAccess>--%>
            
            <stripes:layout-component name="box2">
                <div class="box">
                    <div class="boxtop" id="box2">
                            <h4 onclick="doSwitch('box2');"><cat:part part="cp1Head" type="heading"/></h4>
                            <p  class="boxcontent">                                           
                           <cat:part part="cp2"/>
                                </p>
                    </div>
                    <div class="boxbtm"></div>
                </div>
            </stripes:layout-component>

            <stripes:layout-component name="box3">
                <div class="box">
                       <div class="boxtop" id="box3" >
            <%--                <h4 onclick="doSwitch('box3');"><fmt:message key="seminars"/></h4>
                            <p  class="boxcontent">
                                <small>
                                    <c:forEach var="department" items="${departmentManager.allDepartment}">
                                        <b>${department.name}</b><br>
                                        <c:forEach var="seminar" items="${department.nextSeminars}">
                                            <a style='text-decoration:none' href="${pageContext.request.contextPath}/seminar/${seminar}"><fmt:formatDate pattern="dd/MM" value="${seminar.event.date}"/> ${seminar.title}</a><br>
                                        </c:forEach>
                                    </c:forEach>
                                </small>
                            </p>--%>
                        </div>
                        <div class="boxbtm"></div>
                </div>
            </stripes:layout-component>

            <stripes:layout-component name="box5">
                <div class="box">
                    <div class="boxtop" id="box5" >
                            <h4 onclick="doSwitch('box5');"><fmt:message key="support"/></h4>
                            <p class="boxcontent">
                                <cat:part part="cp5"/>
                            </p>
                    </div>
                    <div class="boxbtm"></div>
                </div>
            </stripes:layout-component>
            
             <stripes:layout-component name="box4">
                <div class="box">
                        <div class="boxtop" id="box4">
                            <h4 onclick="doSwitch('box4');"><fmt:message key="contact"/></h4>
                            <p  class="boxcontent"><%--<cat:fetch id="news" url="${pageContext.request.contextPath}/news/News.action?list&nr=3"/>--%>
                        <cat:part part="cp4"/>
                        </p>
                            <%--<script>doSwitch('box4');</script>--%>
                        </div>
                        <div class="boxbtm"></div>
                </div>
            </stripes:layout-component>
            
        </div>
        <div class="clr"></div>
    </div>
    
    <div id="footer">
        <stripes:layout-component name="footer">
            <cat:part part="footer"/>
        </stripes:layout-component>
    </div>
</div>

</body>
</html>      

</stripes:layout-definition>