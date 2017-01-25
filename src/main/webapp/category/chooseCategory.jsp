
<html>
    <head>
<script src="/catalog/ajax/prototype.js" type="text/javascript"></script>
<script src="/catalog/ajax/effects.js" type="text/javascript"></script>
<script src="/catalog/ajax/dragdrop.js" type="text/javascript"></script>

 <script type="text/javascript">
     
function dragit(){
    //alert('effects defined as'+typeof Draggable);
    var products = document.getElementsByClassName('item');
    for (var i = 0; i < products.length; i++) {
        //alert(products[i].id);
        new Draggable(products[i].id,{revert:true,ghosting:true});
        Droppables.add(products[i].id, {onDrop:function(element,droppableElement){
            
        ,accept:['item']});        
        //new Droppables.add(products[i].id,{onDrop:addCategory};
    }
}

function addCat(element){    
    $(element.id).innerHTML='';    
}
    
</script>

</head>
    
    <body onload="dragit()">
        <ul>
            <li class="item" id="a">
                a
            </li>

            <li class="item" id="b">
                b
            </li>
            <li  class="item" id="c">
                c
                <ul>
                    <li class="item" id="ca">
                        ca
                    </li>
                </ul>
            </li>
            <li  class="item" id="d">
                d
            </li>
        </ul> 
        <a href="#" id="lll" onclick="Effect.Fade('lll',{duration:3})">Fade me slower!</a>
        
       
    </body>
</html>

