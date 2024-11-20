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
 <b>
 <a   href="/hs/training/public/calendar.do?method=filter&filterBy=TYPE&filterValue=TRAIN_CAL_23">
             [Code Education Calendar]</a> </b>
 
<p class="messageBox" align="left">  
 <html:form action="/start" method="post">
  IDHS Inspectors, Local Building Officials, Local Fire Officials click this option to register for the class. All users in this category
  need to  submit education plan, before registering for class.</br></br>
  <input type="hidden" name="method" id="METHOD_KEY" value="registerIDHS"/>
        <input type="submit" value="IDHS Inspectors/ LBO's / LFO's" class="button"/></br>
  </html:form >
 </p>
  <p class="messageBox" align="left">
<html:form action="/start" method="post">
  Indiana State Employees other than IDHS Inspectors, click this option to register for the class.</br></br>
      <input type="hidden" name="method" id="METHOD_KEY" value="registerState"/>
         <input type="submit" value="State Employees" class="button"/>
   </html:form >
    </p>
  <p class="messageBox" align="left">
  <html:form action="/start" method="post">
     All others who does not fall under above two categories, click this option to register for the class. 
     You need to make a credit card payment for registering for the classes, in this category.
     </br></br>
      <input type="hidden" name="method" id="METHOD_KEY" value="registerOthers"/>
        <input type="submit" value="Others" class="button"/>
    
</html:form >
  </p>
  
  <p class="message">
  If you are not sure in which category you can apply contact one of the application contacts listed.
  </p> 
          <h2>Contact Us</h2>
          <p>
           You can contact us via email or by phone (click the name to launch email)
          </p>
          <p>
          <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>" href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>">
          <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/></A> : <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Phone}"/></strong>
          </p>
          <p>
          <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>" href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>">
          <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/> </A>: <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Phone}"/></strong>
          </p> 
  
  <%--
  <p class="messageBox" align="left">  
 <html:form action="/start" method="post">
  Code Education Administrators. Add or update users and courses</br></br>
  <input type="hidden" name="method" id="METHOD_KEY" value="courseAdmin"/>
      
        <input type="submit" value="Admin" class="button"/></br>
    
 </html:form >
 </p> --%>
  </div>    
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 