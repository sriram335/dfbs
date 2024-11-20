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
   <html:hidden property="userType" value="OTHER"/>
     First time users click "Start" for registering for class. </br>
        <input type="submit" value="Start" class="button"/></br>
 </html:form >
 </p>
 <p class="messageBox" align="left">  
 <html:form action="/user" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="logOn"/>
        Return users Login to the system and register for class.You can also Cancel your class registration or View your registered course list or print your certificates using this option.</br>
        <input type="submit" value="Login" class="button"/></br>
 </html:form >
 </p>
  <p class="messageBox" align="left">  
 <html:form action="/course" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="courseList"/>
       View course list.</br>
        <input type="submit" value="View Course List" class="button"/></br>
 </html:form >
 </p>
 <h2>Contact Us</h2>
          <p>
          You can contact us via email 
          
         <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>"  href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/>  </A>
          </p>
                 
   
  </div>    
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 