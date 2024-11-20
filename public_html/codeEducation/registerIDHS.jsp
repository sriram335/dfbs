<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Code Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">

 
 
<div id="leftContentFluid">
 
<p class="messageBox" align="left">  
 <html:form action="/user" method="post">
   <input type="hidden" name="method" id="METHOD_KEY" value="addNewUser"/>
   <html:hidden property="userType" value="IDHS"/>
     First time users,Register as a user and then submit education plan, then you can register for class. </br></br>
        <input type="submit" value="Register as user" class="button"/></br>
 </html:form >
 </p>
 
 <p class="messageBox" align="left">  
 <html:form action="/user" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="logOn"/>
      Return users Login to the system and register for calss.</br></br>
        <input type="submit" value="login" class="button"/></br>
 </html:form >
 </p>
  </div>    
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 