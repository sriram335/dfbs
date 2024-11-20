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
  <h1><b><u>Variance Application Process</u></b></h1></br> </br> 
  <h2>Add Designer Affirmation</h2> 
  <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnhtml&report=variance_view_application.rdf&p_var_id=<c:out value="${VARIANCE_APPLICATION_SELECTED.varId}"/>">[ view application in html]</a> <br/> 
  <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=variance_view_application.rdf&p_var_id=<c:out value="${VARIANCE_APPLICATION_SELECTED.varId}"/>">[ view app in pdf for IE ]</a> <br/> 
      <div class="styledBox">
 
  <c:if test="${DESIGNER_AFFIRMATION_ERROR =='N'}">
  <html:form action="/start" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="designerVerified"/>  
     
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     <tr>
      <th >  </th>
      <td >
           Your IP gateway=<c:out value="${CURRENT_IP}"/> </br>
           Current Date=<c:out value="${CURRENT_DATE}"/> </br>
           Password for  Affirmation:<html:text property="varAppSBCNum" size="12" maxlength="12"/></br></br>
           <b>1. I hereby affirm under penalty of perjury  that all the information provided through this
     online application process is true to the best of my knowledge.</br>
     2. I am the designer or I am a autorized person to submit the variance on Designer behalf.</b>
      
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
   <c:if test="${DESIGNER_AFFIRMATION_ERROR =='Y'}">
   We could not validate this owner and variance combination.Please contact DfbsDatabase@dhs.in.gov, with variance reference id and project name and password provided in the email.
   </c:if>
  
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 </div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
