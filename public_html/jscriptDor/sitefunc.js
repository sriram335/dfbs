var ns4 = (document.layers)? true:false;
var ns6 = (document.getElementById)? true:false;
var ie4 = (document.all)? true:false;
var ie5 = false;

// Microsoft Stupidity Check(tm).
if (ie4)
{
	if ((navigator.userAgent.indexOf('MSIE 5') > 0) || (navigator.userAgent.indexOf('MSIE 6') > 0))
	{
		ie5 = true;
	}
	if (ns6)
	{
		ns6 = false;
	}
}

function MM_findObj(n, d)
{
	var p,i,x;

	if(!d)
		d=document;

	if((p=n.indexOf("?"))>0&&parent.frames.length)
	{
 		d=parent.frames[n.substring(p+1)].document;
 		n=n.substring(0,p);
 	}

	if(!(x=d[n])&&d.all)
		x=d.all[n];

	for (i=0;!x&&i<d.forms.length;i++)
		x=d.forms[i][n];

	for(i=0;!x&&d.layers&&i<d.layers.length;i++)
		x=MM_findObj(n,d.layers[i].document);

	if(!x && document.getElementById)
		x=document.getElementById(n);

	return x;
}

function MM_preloadImages()
{
	var d=document;
	if(d.images)
	{
		if(!d.MM_p)
			d.MM_p=new Array();

		var i,j=d.MM_p.length,a=MM_preloadImages.arguments;
		for(i=0; i<a.length; i++)
		{
			if (a[i].indexOf("#")!=0)
			{
				d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];
			}
		}
	}
}

function MM_swapImage()
{
	var i,j=0,x,a=MM_swapImage.arguments;
	document.MM_sr=new Array;
	for(i=0;i<(a.length-2);i+=3)
	{
 		if ((x=MM_findObj(a[i]))!=null)
   	{
   		document.MM_sr[j++]=x;
   		if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];
   	}
 	}
}

function MM_swapImgRestore()
{
	var i,x,a=document.MM_sr;

	for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++)
		x.src=x.oSrc;
}

function ST_findObj(n, d)
{
	var p,i,x;

	if(!d)
		d=document;

	if((p=n.indexOf("?"))>0&&parent.frames.length)
	{
 		d=parent.frames[n.substring(p+1)].document;
 		n=n.substring(0,p);
 	}

	if(!(x=d[n])&&d.all)
		x=d.all[n];

	for (i=0;!x&&i<d.forms.length;i++)
		x=d.forms[i][n];

	for(i=0;!x&&d.layers&&i<d.layers.length;i++)
		x=ST_findObj(n,d.layers[i].document);

	if(!x && document.getElementById)
		x=document.getElementById(n);

	return x;
}

function ST_preloadImages()
{
	var d=document;
	if(d.images)
	{
		if(!d.ST_p)
			d.ST_p=new Array();

		var i,j=d.ST_p.length,a=ST_preloadImages.arguments;
		for(i=0; i<a.length; i++)
		{
			if (a[i].indexOf("#")!=0)
			{
				d.ST_p[j]=new Image; d.ST_p[j++].src=a[i];
			}
		}
	}
}

function ST_swapImage()
{
	var i,j=0,x,a=ST_swapImage.arguments;
	document.ST_sr=new Array;
	for(i=0;i<(a.length-2);i+=3)
	{
 		if ((x=ST_findObj(a[i]))!=null)
   	{
   		document.ST_sr[j++]=x;
   		if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];
   	}
 	}
}

function ST_swapImgRestore()
{
	var i,x,a=document.ST_sr;

	for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++)
		x.src=x.oSrc;
}

function right(e)
{
	if (navigator.appName == 'Netscape' && (e.which == 3 || e.which == 2))
	{
		return false;
	}
	else if (navigator.appName == 'Microsoft Internet Explorer' && (event.button == 2 || event.button == 3))
	{
		alert("Right click not available...");
		return false;
	}
//////////////////return false;
}

document.onmousedown=right;

if (document.layers) window.captureEvents(Event.MOUSEDOWN);
{
	window.onmousedown=right;
}

for (var i=0; i<document.images.length; i++)
{
	document.images[i].onmousedown=right;
}

for (var i=0; i<document.links.length; i++)
{
	document.links[i].onmousedown=right;
}

function refreshPage(pageref)
{
	if(ns4)
	{
		if(pageref != '')
		{
			location = pageref;
		}
	}
	else if(ns6)
	{
	  window.location.href = pageref;
	}
	else
	{
		if(pageref != '')
		{
			self.location.href  = pageref;
		}
	}
	return false;

}


function replaceButtons(prepend)
{
	var replacetable = '<table width="262" border="0" align="right" height="28">'+
		'<tr>'+
			'<td height="26"><img width="64" height="26" src="'+prepend+'mcs/back_btn_g.gif"></td>'+
			'<td height="26"><img width="64" height="26" src="'+prepend+'mcs/submit_btn_g.gif"></td>'+
			'<td height="26"><img width="64" height="26" src="'+prepend+'mcs/refresh_btn_g.gif"></td>'+
			'<td height="26"><img width="64" height="26" src="'+prepend+'mcs/cancel_btn_g.gif"></td>'+
		'</tr>'+
	'</table>';

	if(ns4)
	{
	  replaceText ('buttonBlock', replacetable);
	}
	else
	{
		self.buttonBlock.innerHtml = replacetable;
	}
}

var nextpage = "";

var sitemove = "";

function disableButtons(pageref, prepend)
{
	var replacetable = '<table width="262" border="0" align="right" height="28">'+
		'<tr>'+
			'<td height="26"><img width="64" height="26" src="'+prepend+'mcs/back_btn_g.gif"></td>'+
			'<td height="26"><img width="64" height="26" src="'+prepend+'mcs/submit_btn_g.gif"></td>'+
			'<td height="26"><img width="64" height="26" src="'+prepend+'mcs/refresh_btn_g.gif"></td>'+
			'<td height="26"><img width="64" height="26" src="'+prepend+'mcs/cancel_btn_g.gif"></td>'+
		'</tr>'+
	'</table>';

	if(ns4)
	{

	  replaceText ('buttonBlock', replacetable);

		if(pageref != '')
		{
			sitemove = pageref;
		}
	}
	else if(ns6)
	{
    document.getElementById('buttonBlock').innerHTML = replacetable;
	  sitemove = pageref;
	}
	else
	{
		self.buttonBlock.innerHTML = replacetable;

		if(pageref != '')
		{
			sitemove = pageref;
		}
	}

	setTimeout('siteMover()', 512);

}

function siteMover()
{
	if(ns4)
	{
		if(sitemove != '')
		{
			location = sitemove;
		}
	}
	else if(ns6)
	{
	  window.location.href = sitemove;
	}
	else
	{
		if(sitemove != '')
		{
			self.location.href  = sitemove;
		}
	}
}

function submitForm(prepend)
{
	var replacetable = '<table width="262" border="0" align="right" height="28">'+
		'<tr>'+
			'<td height="26"><img width="64" height="26" src="'+prepend+'mcs/back_btn_g.gif"></td>'+
			'<td height="26"><img width="64" height="26" src="'+prepend+'mcs/submit_btn_g.gif"></td>'+
			'<td height="26"><img width="64" height="26" src="'+prepend+'mcs/refresh_btn_g.gif"></td>'+
			'<td height="26"><img width="64" height="26" src="'+prepend+'mcs/cancel_btn_g.gif"></td>'+
		'</tr>'+
	'</table>';

	if(ns4)
	{
	  replaceText ('buttonBlock', replacetable);
	}
	else if(ns6)
	{
  	document.getElementById('buttonBlock').innerHTML = '<div>' + replacetable + '</div>';
	}
	else
	{
	  buttonBlock.innerHTML = replacetable;
	}

	setTimeout('formSubmitter()', 512);
}

function formSubmitter()
{
  document.forms[0].submit();
}

var win;

function openDep(arg1)
{
	win = window.open(arg1, "", "height=350,width=500,toolbar=no,scrollbars=yes,resize=no");
}

function closeDep()
{
  if (win && win.open && !win.closed) win.close();
}

function rewriteSection (id, html)
{
  if (document.layers)
  {
    var l = document[id];
    if (!l.ol)
    {
     var ol = l.ol = new Layer (l.clip.width);
     // comment the following line out if your div is initially empty
     ol.clip.width = l.clip.width; ol.clip.height = l.clip.height;
     ol.left = l.pageX; ol.top = l.pageY;
     ol.bgColor = l.bgColor;
     l.visibility = 'hide';
    }
    var ol = l.ol;
    ol.document.open();
    ol.document.write(html);
    ol.document.close();
    ol.visibility = 'show';
  }
  else if (document.all)
    document.all[id].innerHTML = html;
}

function replaceText (id, content)
{
  rewriteSection (id, content);
}

function placeFocus()
{
	if (document.forms.length > 0)
	{
		var field = document.forms[0];
		for (i = 0; i < field.length; i++)
		{
			if ((field.elements[i].type == "text") || (field.elements[i].type == "textarea") || (field.elements[i].type.toString().charAt(0) == "s"))
			{
				document.forms[0].elements[i].focus();
				break;
			}
		}
	}
}

function printPage()
{
	if(ns4 || ns6)
	{
		this.focus();
		window.print();
	}
	else if(ie4 || ie5)
	{
		this.focus();
		setTimeout("vbPrintPage(); this.focus()", 100);
	}
}

function doOnFocus(item)
{
}

function doOnChange(item)
{
}

function doRandomOnFocus(item)
{
	item.blur();
}

