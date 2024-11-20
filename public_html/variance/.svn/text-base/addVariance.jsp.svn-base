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

    
  <div id="leftContentFluid">
  <h1><b><u>Update Existing Variance Application</u></b></h1></br> </br> 
   <div class="styledBox" align="left">
 <span class="error"> <b><u> If the answer to the following questions is all "YES" then proceed with updating the application</u></b></span></br>
  1. Did you file the existing variance application online?</br>
  2. Do you have Variance ID or Variance Number and  Owner Email,Password for  Variance  and Payment Confirmation ID to start the application process?</br>
  <html:form action="/start" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="varAppVerified"/>  
     
<table cellspacing="0" class="noBorder" summary="OWNER DATA" align="left">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" ></th>
    </tr>
     <tr>
      <th >  </th>
      <td >
           
           Variance ID:<html:text property="varProjCity" size="12" maxlength="12"/></br>
           Owner Email:<html:text property="varProjAddress1" size="50" maxlength="100"/></br>
           Password for  Variance:<html:text property="varProjAddress2" size="12" maxlength="12"/></br>
           (all above information available in the email requesting affirmation)</br></br>
           Payment Confirmation ID :<html:text property="varAppSBCNum" size="12" maxlength="12"/></br>
           (payment confirmation ID available in the email confirming the receipt of payment) </br></br>
           
           
      </td>
    </tr>
     <tr>
     <th scope="row"     > </th>
    <td>
          <html:submit value="Start" styleClass="button" />
        
        
     
     </td>
     </tr> 
  </tbody>
   </table>
   </html:form>
   </br></br>
  
   <c:if test="${ADD_VARAPP_ERROR =='Y'}">
    <span class="error"><b><u>!!! ERROR !!! </u></b></br>
   We could not validate the information you entered with the data in the database. Try to enter the correct information or contact DfbsDatabase@dhs.in.gov, with variance reference id and project name and password provided in the email.</br>
  </span>
   </c:if>
  <c:if test="${ADD_VARAPP_ERROR =='P'}">
   <span class="error"><b><u>!!! ERROR !!! </u></b></br>
   Please enter valid information.</br>
  </span>
   </c:if>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 </div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
