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
<c:if test="${SECURITY_SUPER_USER == 'YES'}">  
<p class="messageBox" align="left">  
 <html:form action="/course" method="post">
  Add or Update Courses.</br></br>
  <input type="hidden" name="method" id="METHOD_KEY" value="courseList"/>
  
      
        <input type="submit" value="Courses" class="button"/></br>
      
      

 </html:form >
 </p>
  <p class="messageBox" align="left">
<html:form action="/user" method="post">
  Add or update users.</br></br>
      <input type="hidden" name="method" id="METHOD_KEY" value="userList"/>
     
        <input type="submit" value="Users" class="button"/>
   
  
 </html:form >
    </p>
 </c:if>
<c:if test="${SECURITY_SUPER_USER == null ||SECURITY_SUPER_USER == 'NO'}"> 
 You are not authorized for this part of the online application. Contact DfbsDatabase@dhs.in.gov for questions or concerns.
</c:if>
  </div>    
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 