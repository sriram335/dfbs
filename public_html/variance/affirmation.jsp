<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<html:xhtml/><c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Variance Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>

<div id="mainBox">
<div id="rightContentFluid">
    <jsp:include page="status.jsp"/>
  </div>
 
    
  <div id="leftContentFluid">
  <h1><b><u>Variance Application Process</u></b></h1></br> </br> 
  <h2>Add Affirmation</h2> 
<html:link page="/start.do">[back to start]</html:link>
  <div class="styledBox">
 
  <c:if test="${NEW_VARIANCE_STATUS =='END_CODE'}">
   <html:form action="/checkout" method="post">        
 <%-- <html:form action="/start" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="submitApplication"/>  --%>
     
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     <tr>
      <th >Affirmation by Applicant  </th>
      <td  >
          <span class="error"> Check this to proceed --><input type="checkbox" name="varAppHistAffPhy"  class="switch"  value="Y"/> </span> </br> 
           Your IP gateway=<c:out value="${CURRENT_IP}"/> </br>
           Current Date=<c:out value="${CURRENT_DATE}"/> </br>
           
           <b>1.By checking this box, I hereby affirm under penalty of perjury  that all the information provided through this
     online application process is accurate.</b></br>
     
      </td>
    </tr>
     <tr>
     <th scope="row"     > </th>
    <td>
          <html:submit value="Complete Affirmation" styleClass="button" />
     
     </td>
     </tr> 
  </tbody>
   </table>
   </html:form>
   </c:if>
   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelVar != null }">  
 <html:form action="/completeCheckout" method="post" >
   <p class="messageBox">
    Your Application Process is complete. You will receive confirmation emails from IDHS regarding further course of Action shortly.</br>
    <input type="hidden" name="method" id="METHOD_KEY" value="check"/> 
    <html:hidden property="receiptId" value="12345"/>
          <input type="submit" value="Test" class="button"/>
        </br>        
   </p>
   </html:form> 
  </c:if>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 </div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
