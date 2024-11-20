<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>



<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">




<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <meta name="DESCRIPTION" content="" />
    <meta name="KEYWORDS" content="" />
    <meta name="AUTHOR" content="Dev Nimmagadda /Dennis" />
    <meta name="COPYRIGHT" content="" />
    <title><c:out value="${title}" /></title>
    <script type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="/dfbs/css/style_default.css" />
    <link rel="stylesheet" type="text/css" media="print" href="/dfbs/css/style_printVersion.css" />
    
    <link rel="Shortcut Icon" href="/hs/favicon.ico" />
    <script src="/dfbs/script/pageScript.js" type='text/javascript'></script>
    <script src="/dfbs/script/sorttable.js" type='text/javascript'></script>
    <script language="javascript" type="text/javascript" src="/dfbs/css/datetimepicker.js">

//Date Time Picker script- by TengYong Ng of http://www.rainforestnet.com
//Script featured on JavaScript Kit (http://www.javascriptkit.com)
//For this script, visit http://www.javascriptkit.com 

</script>  
</head>

<body>

<div id="headBox">
<div id="subHeadBox">
       
       <div id="subHeadBox3">
        <html:link href="/dfbs/main/main.do" ><img src="/dfbs/img/dhs_logo_small.jpg" alt="DHS" /></html:link>
       </div>
       <div id="subHeadBox1">
       <h1>Indiana Department of Homeland Security  </h1>
       <h2>IDHS / DFBS applications <span class="message"></span></h2>
       </div>
    
</div>


</div>



 <div id="pageBox">

<c:if test="${sessionScope.DFBS_SECURITY != null && sessionScope.DFBS_SECURITY.userId != null && sessionScope.DFBS_SECURITY.userId != 'PILLINGWORTH' && sessionScope.DFBS_SECURITY.userId != 'PROBISON'}"> 
 <a   href="/dfbs/main/main.do?method=backToMain">
             [Main Menu]</a>
<a   href="/dfbs/main/main.do?method=logOut">
             [DFBS Log Out]</a>
<a   href="/dfbs/main/main.do?method=changePassword">
             [change password]</a>
</c:if>
  
<%
main.data.DfbsDataMap dmap = (main.data.DfbsDataMap)
application.getAttribute(hs.util.HsConstant.DFBS_DATA_MAP_KEY);
main.data.FireReportsDAO fDAO = (main.data.FireReportsDAO) dmap.getHsDAO(dmap.FIRE_REPORTS);
hs.data.HsDatabaseConnection dc = fDAO.getHsDatabaseConnection();
if(dc.getDatabase() != null && dc.getDatabase().equals("semt")) {
%>

<p class="error" style="font-size:x-large;font-weight:bold;padding: 20px;">
Running on TEST Database...
</p>

<% } %> 
  
