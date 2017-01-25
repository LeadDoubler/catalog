<html>
    <head>
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
<h1>Site explorer</h1>

<span id="editorWindow"></span>

<jsp:include page="/explorer/Structure.action?edit"/>

</body>
</html>