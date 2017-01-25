<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
    <meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
	<title>Folder tree with Drag and Drop capabilities</title>
	<script type="text/javascript" src="treeMenu/js/ajax.js"></script>
	<script type="text/javascript" src="treeMenu/js/drag-drop-folder-tree.js">
	/************************************************************************************************************
	(C) www.dhtmlgoodies.com, July 2006
	
	Update log:
	
	
	This is a script from www.dhtmlgoodies.com. You will find this and a lot of other scripts at our website.	
	
	Terms of use:
	You are free to use this script as long as the copyright message is kept intact.
	
	For more detailed license information, see http://www.dhtmlgoodies.com/index.html?page=termsOfUse 
	
	Thank you!
	
	www.dhtmlgoodies.com
	Alf Magne Kalleland
	
	************************************************************************************************************/	
	</script>
	<link rel="stylesheet" href="treeMenu/css/drag-drop-folder-tree.css" type="text/css"></link>
	<style type="text/css">
	/* CSS for the demo */
	img{
		border:0px;
	}
	</style>
	<script type="text/javascript">
	//--------------------------------
	// Save functions
	//--------------------------------
	var ajaxObjects = new Array();
	 
	// Use something like this if you want to save data by Ajax.
	function saveMyTree()
	{
			saveString = treeObj.getNodeOrders();
            //alert(saveString);
            var ajaxIndex = ajaxObjects.length;
			ajaxObjects[ajaxIndex] = new sack();
            var s = '<ul>'+saveString+'</ul>';
            var url = 'saveMenuTree.do?text=' + encodeURI(s);
            //alert(encodeURI(saveString));
            saveString = encodeURI(saveString);
            ajaxObjects[ajaxIndex].requestFile = url;	// Specifying which file to get
			ajaxObjects[ajaxIndex].onCompletion = function() { saveComplete(ajaxIndex); } ;	// Specify function that will be executed after file has been found
			ajaxObjects[ajaxIndex].runAJAX();		// Execute AJAX function			
		
	}
	function saveComplete(index)
	{
		//alert(ajaxObjects[index].response);
	}

	
	// Call this function if you want to save it by a form.
	function saveMyTree()
	{
        //alert('saving tree');
        document.myForm.elements['text'].value = '<ul>'+treeObj.getNodeOrders()+'</ul>';
        //alert(document.myForm.elements['text'].value);
        document.myForm.submit();
	}
	

	</script>
	
</head>
<body>


<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>






    <ul id="dhtmlgoodies_tree2" class="dhtmlgoodies_tree">
        <li id="node999999999" noDrag="true" noSiblings="true"><a href="#">Menu</a>
         <c:out value="${menu.text}" escapeXml="yes"/>
        </li>

	</ul>
	<form name="myForm" id="myForm" action="saveMenuTree.do" method="post">
        <input type="hidden" name="text"/>
    <input type="button" onclick="saveMyTree()" value="Save">
	</form>
	<script type="text/javascript">	
	treeObj = new JSDragDropTree();
	treeObj.setTreeId('dhtmlgoodies_tree2');
	treeObj.setMaximumDepth(7);
	treeObj.setMessageMaximumDepthReached('Maximum depth reached'); // If you want to show a message when maximum depth is reached, i.e. on drop.
	treeObj.initTree();
	treeObj.expandAll();
	
	
	


	
	</script>
	<a href="#" onclick="treeObj.collapseAll()"><bean:message key="collapse"/></a> |
	<a href="#" onclick="treeObj.expandAll()"><bean:message key="expand"/></a>
	<p style="margin:10px"><bean:message key="editMenuTree.message"/></p>
</body>
</html>