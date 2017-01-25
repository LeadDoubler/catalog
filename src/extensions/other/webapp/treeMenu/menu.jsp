<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>



    <html><head>
            <meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
            <title>Explorer</title>
            
            <%@ include file="/taglibs.jsp"%>
            
            <script src="${pageContext.request.contextPath}/ajax/prototype.js" type="text/javascript"></script>
            <script type="text/javascript">

    function edit(id){
        //alert('Rediger-'+id);
        var pars = 'page='+id+'&'+'inline';
        new Ajax.Updater('form_'+id,'${pageContext.request.contextPath}/page/Page.action',{method:'get',parameters: pars});
    }

    function addChild(id){
        var pars = 'page.parent='+id+'&'+'inlineNew';
        
        new Ajax.Updater('addForm_'+id,'${pageContext.request.contextPath}/page/Page.action',{method:'get',parameters: pars});
    }
   
    function movePage(page,parent){
    var pars = 'page='+page+'&'+'page.parent='+parent+'&'+'save=';
    //alert('url=/catalog/Page.htm?'+pars);

    new Ajax.Updater('message', '${pageContext.request.contextPath}/page/Page.action', {method: 'get', parameters: pars});
    //alert('html - movePage('+page+','+parent+') finished');
    }

    var curId;

    function showLinks(id){
        if (curId != null){
            hideLinks(curId);
        }
        curId = id;
        $(id).style.display='inline';
    }

    function hideLinks(id){
        $(id).style.display='none';
    }
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/treeMenu/js/ajax.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/treeMenu/js/drag-drop-folder-tree.js">
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/treeMenu/css/drag-drop-folder-tree.css" type="text/css"></link>
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
        alert(saveString);
        var ajaxIndex = ajaxObjects.length;
                    ajaxObjects[ajaxIndex] = new sack();
                    var url = 'saveNodes.php?saveString=' + saveString;
                    ajaxObjects[ajaxIndex].requestFile = url;	// Specifying which file to get
                    ajaxObjects[ajaxIndex].onCompletion = function() { saveComplete(ajaxIndex); } ;	// Specify function that will be executed after file has been found
                    ajaxObjects[ajaxIndex].runAJAX();		// Execute AJAX function

    }
    function saveComplete(index)
    {
            alert(ajaxObjects[index].response);
    }


    // Call this function if you want to save it by a form.
    function saveMyTree_byForm()
    {
            document.myForm.elements['saveString'].value = treeObj.getNodeOrders();
            document.myForm.submit();
    }

        </script>
    </head>
    <body>

        <span id="nomessage"></span>
        <div id="content">  
            <div class="contentitem">

                <x:parse var="pages" xml="${page.root.xml}"/>
                <c:import var="pagesToList" url="../xsl/EditMenu.xsl"/>
                <x:transform xml="${pages}" xslt="${pagesToList}"/>

            </div>
        </div> 

        <script type="text/javascript">
        treeObj = new JSDragDropTree();
        treeObj.setTreeId('dhtmlgoodies_tree2');
        treeObj.setMaximumDepth(7);
        treeObj.setMessageMaximumDepthReached('Maximum depth reached'); // If you want to show a message when maximum depth is reached, i.e. on drop.
        treeObj.initTree();
        treeObj.expandAll();
        </script>

    </body>
</html>

