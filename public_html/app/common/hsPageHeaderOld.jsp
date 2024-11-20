<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
<head>
    <meta name="DESCRIPTION" content="" />
    <meta name="KEYWORDS" content="" />
    <meta name="AUTHOR" content=" Dev Nimmagadda " />
    <meta name="COPYRIGHT" content="IDHS 2015" />
<%--    <title><c:out value="${title}" /></title> --%>
    <script type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="/dfbs/css/style_default.css" />
    <link rel="stylesheet" type="text/css" media="print" href="/dfbs/css/style_printVersion.css" />
    <link rel="stylesheet" href="/dfbs/css/styles.css" type="text/css"/>
    
    <link rel="Shortcut Icon" href="/dfbs/favicon.ico" />
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
       
       
        <html:link href="/dfbs/main/main.do" ><img src="/dfbs/img/dhs_logo_small.jpg" alt="DHS" /></html:link>
       <div id="subHeadBox1">
       <h1>Indiana Department of Homeland Security / Fire and Building Safety Division</h1>
       <h2><c:out value="${sessionScope.APPLICATION_HEADING}"/> <span class="message">2018-19 version</span></h2>
       
       </div>
        <div class="clearer">&nbsp;</div>
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
</div>

</body>
</html>
  
