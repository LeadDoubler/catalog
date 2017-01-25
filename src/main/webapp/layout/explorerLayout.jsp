<%@ include file="/taglibs.jsp"%>


<stripes:layout-definition>
  <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Softworld.dk</title>
<link href="${pageContext.request.contextPath}/css/style000.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/menu0000.css" rel="stylesheet" type="text/css" />

<stripes:layout-component name="html-head"/>
</head>

<body>
    
             <stripes:layout-component name="header">
                    <jsp:include page="/layout/header.jsp"/>
                </stripes:layout-component>


<div id="wrap">

	<div id="header">
		<img src="${pageContext.request.contextPath}/images/logo0000.jpg" alt="Softworld.dk Logo" id="logo" />
		<img src="${pageContext.request.contextPath}/images/phone000.jpg" alt="7020 8055" id="phone" />
		<div id="topnav">
                   
		</div>
		<div id="banner">
			<div id="flash">
			<!-- Flash will go here -->
			</div>
		</div>
                
		<div id="mainnav">
			<!-- Flash will go here -->
			
                        
                       
			
		</div>
		<div id="breadcrumb">
			<p>                            
                            <c:forEach items="${sessionScope.page.ancestors}"  var="ancestor">> ${ancestor.title}  </c:forEach> 
                       
                            
                            </p>
		</div>
                <div class="clr"></div>
	</div>
	<div id="main">
            
<div id="content">
		
                            <div class="contentitem">
                                <p>
		<stripes:messages/>
                    
                            <stripes:layout-component name="contents"/>
			
                            <stripes:layout-component name="sidebar">
                                <!-- Start Sidebar Boxes -->
                <div id="sidebar">
                
		</div>
                
                </stripes:layout-component>      
                
                </p>
                    </div>
                           
			
		</div>
<!-- End Sidebar Boxes -->
                	
		<div class="clr"></div>


                                
                                
                                    
                            
	</div>
	<div id="footer">
	<stripes:layout-component name="footer">
            
            <p>
             Vermundsgade 38 A, København Ø - Tel. 7020 8055 || Fiskergade 41, 8000 Århus C. -
Tel. 7020 8056
                
	 
            </p>
            
            
</stripes:layout-component>
	</div>
	
</div>

</body>
</html>      

</stripes:layout-definition>