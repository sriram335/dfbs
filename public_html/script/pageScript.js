/* --- START PRODUCTION CODE --- */


function setSelectValue(SELECT_ID,CONTROL_ID) {

    var selectObj = document.getElementById(SELECT_ID);
    var obj = document.getElementById(CONTROL_ID); 
    
    obj.value = selectObj.options[selectObj.options.selectedIndex].text
    
    
}
function setCheckFieldSize(CONTROL_ID) {

    var obj = document.getElementById(CONTROL_ID); 
    
    if (obj.value.length >= 5){
      return false;
    }
    else {
      return true;
    }
    
    
}

function printMessage(boxId,text) {
    var box = document.getElementById(boxId);

    if(box != undefined) {

      while(box.childNodes.length > 0) {
        box.removeChild(box.childNodes[0]);
      }
      box.appendChild(document.createTextNode(text));

    }
}

function displayBlockSwitch(switchId,blockId) {
  
  var swt = document.getElementById(switchId);
  var blk = document.getElementById(blockId);
  if ( blk == undefined || swt == undefined) {
    return;
  }  
   
  if(swt.checked) {
  
    blk.style.display = "block";
    
  } 
  else 
  {
    blk.style.display = "none";
  }
   
 
}

function displayBlock(blockId) {
  
  var blk = document.getElementById(blockId);
  if ( blk == undefined ) {
    return;
  }  
   
  if ( blk.style.display == "none" || blk.style.display == "" ) { 
    blk.style.display = "block";
  } else if ( blk.style.display == "block") { 
    blk.style.display = "none";
  }  
}
function displayInline(blockId) {
  
  var blk = document.getElementById(blockId);
  if ( blk == undefined ) {
    return;
  }  
   
  if ( blk.style.display == "none" || blk.style.display == "" ) { 
    blk.style.display = "inline";
  } else if ( blk.style.display == "inline") { 
    blk.style.display = "none";
  }  
}

function displayType(blockId,displayType) {
  
  var blk = document.getElementById(blockId);
  if ( blk == undefined ) {
    return;
  }  
  blk.style.display = displayType;
  
}

function submitForm(id){
  var form = document.getElementById(id);
  form.submit();
}
function submitFormMethod(id,method){
  var form = document.getElementById(id);
  setMethod(method);
  form.submit();
}
function setCurrentPage(id){
  var menu = document.getElementById(id);
  if(menu != undefined) {
    menu.className = "currentPage";
  }
}
function setCurrentDoc(id){
  var menu = document.getElementById(id);
  
  if(menu != undefined) {
    menu.className = "currentDoc";
  }
}
function setSelectedOption(id){
  var opt = document.getElementById(id);
  opt.selected = true;
}
function setMethod(value) {
  var key = document.getElementById("METHOD_KEY");
  key.value = value;

  //return false;
}
function setMethodKey(key,value) {
  var k = document.getElementById(key);
  k.value = value;

  //return false;
}

function printContentOutline(source,destination) {
  var src = document.getElementById(source);
  var dest = document.getElementById(destination);
  if (dest == undefined) return;
  var H3 = document.createElement("h3");
  var UL = document.createElement("ul");
  dest.appendChild(H3);
  H3.appendChild(document.createTextNode("Document Outline"));
  dest.appendChild(UL);
  for(var i = 0; i < src.childNodes.length; i++) {
    if(src.childNodes[i].nodeName == "H3") {
      var LI = UL.appendChild(document.createElement("li"));
      LI.appendChild(document.createTextNode(src.childNodes[i].childNodes[0].nodeValue));
    }
  }
}

function Mapping(idx,typex,descx,itemTypex) {
  this.id = idx;
  this.type = typex;
  this.desc = descx;
  this.itemType = itemTypex; 
}


function filterList(listFilterId,listTargetId,mapping,selectedItem) {
  var listFilterObj = document.getElementById(listFilterId);
  var selectedArea = listFilterObj.options[listFilterObj.selectedIndex].value;
  
  var targetObj = document.getElementById(listTargetId);
  targetObj.options.length = 0;
  var opt = document.createElement("option");
  opt.value = "";
  opt.className = "";
  targetObj.appendChild(opt);
  opt.appendChild(document.createTextNode("-----"));
  for(var i=0; i < mapping.length; i++) {
    if(mapping[i].type == selectedArea) {
      var opt = document.createElement("option");
      opt.value = mapping[i].id;
      opt.appendChild(document.createTextNode(mapping[i].desc));
      opt.className = mapping[i].itemType;
      if(selectedItem == mapping[i].id) {
        opt.selected = true;
      }
      targetObj.appendChild(opt);
    }
  }

}


function printDevMessage(boxId,text) {
    var box = document.getElementById(boxId);

    if(box != undefined) {

      var p = document.createElement("p")
      p.appendChild(document.createTextNode(text));
      
      box.appendChild(p);

    }
}


/* --- END PRODUCTION CODE --- */



function showMiscRow() {

    var swt1 = document.getElementById('PERMIT_TYPE_SWITCH');
    
    var blk1 = document.getElementById('EVENT_NAME_ROW');
    var blk2 = document.getElementById('EVENT_TIME_ROW');
    var blk3 = document.getElementById('EVENT_DATE_ROW');
     
   
    if(swt1.checked) {
  
      blk1.style.display = "block";
      blk2.style.display = "block";
      blk3.style.display = "block";
    } 
    else 
    {
      blk1.style.display = "none";
      blk2.style.display = "none";
      blk3.style.display = "none";
    }
    
   function showMiscRowFireworks() {

    var swt1 = document.getElementById('WHOLESALE_SWITCH');
    var blk1 = document.getElementById('CATEGORY_ROW');
    var blk2 = document.getElementById('INSPECT_DATE_ROW');

     
   
   
    if(swt1.checked) {
  
      blk1.style.display = "block";
      blk2.style.display = "none";
    
    } 
    else 
    {
      blk1.style.display = "none";
      blk2.style.display = "block";

    }   

}
  function checkYear(yr) {  
  var year = yr.value;
  if(year != null || year == ""){
      alert("Please enter year for violations code...");
      return false;
  }else return true;
  
  }

}
