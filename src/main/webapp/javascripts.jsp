<script type="text/javascript" src="${pageContext.request.contextPath}/jsCalendar/calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsCalendar/lang/calendar-en.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsCalendar/calendar-setup.js"></script>

<script src="${pageContext.request.contextPath}/FCKeditor/fckeditor.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/ajax/prototype.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/ajax/effects.js" type="text/javascript"></script>
<%--<script src="${pageContext.request.contextPath}/ajax/dragdrop.js" type="text/javascript"></script>
--%>
<script type="text/javascript">
    
    function voke(form, event, container) {              
              var params = Form.serialize(form);
              if (event != null) params = event + '&' + params;
              new Ajax.Updater(container, form.action, {method:'post', postBody:params});              
       }
    
    function doSwitch(elm){
        if ($(elm).hasClassName('boxtop')){
            $(elm).toggleClassName('boxtop2');
        }
        else{
            $(elm).toggleClassName('boxtop');
        }
     }
    
      function invoke(form, event, container) {
              alert('invoke starts');
              var params = Form.serialize(form);
              if (event != null) params = event + '&' + params;
              new Ajax.Updater(container, form.action, {method:'post', postBody:params});
              alert('invoke ends');
       }
       
    function submitAndReplace(form,event,id,prefix){
        if(prefix == undefined){
        prefix = '';
        }
        var container = prefix+'title_'+id;
        var formContainer = prefix+'form_'+id;
        var params = Form.serialize(form);
        if (event != null) params = event + '&amp;' + params;
        new Ajax.Updater(container, form.action, 
            {
                method:'post', 
                postBody:params,
                onComplete:function(){
                    $(formContainer).innerHTML = '';
                }
            }
        );
    }
    
      
       
       
              
       function invokeAndInsert(form, event, container) {              
              var params = Form.serialize(form);
              if (event != null) params = event + '&amp;' + params;
              new Ajax.Updater(container, form.action, {method:'post', postBody:params,insertion: Insertion.Bottom});
       }
</script>

<%-- ToolTip JavaScript 

<style type="text/css">
/* This is where you can customize the appearance of the tooltip */
div#tipDiv {
  position:absolute; visibility:hidden; left:0; top:0; z-index:10000;
  background-color:#dee7f7; border:1px solid #336; 
  padding:4px;
  color:#000; font-size:11px; line-height:1.2;
}
/* These are optional. They demonstrate how you can individually format tooltip content  */
div.tp1 { font-size:12px; color:#336; font-style:italic }
div.tp2 { font-weight:bolder; color:#337; padding-top:4px }
</style>

<script src="js/dw_event.js" type="text/javascript"></script>
<script src="js/dw_viewport.js" type="text/javascript"></script>
<script src="js/dw_tooltip.js" type="text/javascript"></script>

<script type="text/javascript">
/*************************************************************************
  This code is from Dynamic Web Coding at dyn-web.com
  Copyright 2003-5 by Sharon Paine 
  See Terms of Use at http://www.dyn-web.com/bus/terms.html
  regarding conditions under which you may use this code.
  This notice must be retained in the code as is!
*************************************************************************/

function doTooltip(e, msg) {
  if ( typeof Tooltip == "undefined" || !Tooltip.ready ) return;
  Tooltip.show(e, msg);
}

function hideTip() {
  if ( typeof Tooltip == "undefined" || !Tooltip.ready ) return;
  Tooltip.hide();
}
	
// variables for tooltip content 
var tipRich = '<div class="tp1">This text is in a div with it\'s own class for different style specifications than the tooltip. </div>';
var tipImg = '<div style="text-align:center"><img src="images/dot-com-btn.gif" width="176" height="30" alt="" border="0"></div>';
var tipImgTxt = '<img src="images/sm-duck.gif" width="90" height="44" alt="" border="0"><div class="tp2">Images and text can go together in a tooltip.</div>';
var tipTerms = "If you plan to use our code, please follow our terms. Thank you.";

</script>--%>