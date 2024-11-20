<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Variance Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>

<div id="mainBox">

<div id="leftContentFluid">

<h1><b><u>Variance Application(s)</u></b></h1></br> </br> 

<b><u>Submitter Option</u></b>
<p class="error" align="left">
<b><u>Do You have a Submitter for this Variance?</u></b>
</p>
<html:form action="/start" method="post" >
<p class="messageBox" align="left">
If your variance has a submitter different from owner choose this option
    <input type="hidden" name="method" id="METHOD_KEY" value="subQuestionY"/> 
          <input type="submit" value=" Yes " class="button"/>
        </br>        
</p>
</html:form> 
<html:form action="/start" method="post" >
<p class="messageBox" align="left">
If your variance does <u>NOT</u> have a submitter choose this option
    <input type="hidden" name="method" id="METHOD_KEY" value="subQuestionN"/> 
          <input type="submit" value=" No " class="button"/>
        </br>        
</p>
</html:form> 


<div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 