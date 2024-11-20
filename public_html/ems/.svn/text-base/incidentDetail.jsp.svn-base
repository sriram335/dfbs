<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="Update Hospital"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
  <h2>Current available files</h2> 
<c:forEach var="file" items="${sessionScope.INCIDENT.fileList}" varStatus="i">
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a></br> File date:<c:out value="${file.fileDateString}"/></br> File sent by:<c:out value="${file.fileLoadedBy}"/>
</c:forEach>

</div>
<div id="mainBox">
  <h2>Update incident Information</h2>
  
  <a   href="/dfbs/ems/person.do?method=personHospList&incidentId=<c:out value="${sessionScope.INCIDENT.incidentId}"/>">
              [ contact list ]</a> 
  <a   href="/dfbs/ems/provider.do?method=hospProvList&incidentId=<c:out value="${sessionScope.INCIDENT.incidentId}"/>">
              [ attached provider list ]</a> 
  <c:if test="${EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR' || EMS_SECURITY.currentUserLevel == 'USER'}">
   <a href="/dfbs/ems/main.do?method=goToUpload&idNumber=<c:out value="${sessionScope.INCIDENT.incidentId}"/>&idType=Hospital">
            [upload incident files]  </a> 
  </c:if>
 <a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> </br>
  <div id="leftContentFluid">

 <html:form action="/search" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveHospital"/> 
<table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >* incident name:</th>
      <td>
       <html:text property="incidentName" size="30" maxlength="30"/>
        <c:if test="${INCIDENT_ERROR.incidentName == 'ERROR'}">
          <br/>
          <span class="error">* please enter incident name</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* incident address1:</th>
      <td>
       <html:text property="address1" size="30" maxlength="30"/>
        <c:if test="${INCIDENT_ERROR.Address1 == 'ERROR'}">
          <br/>
          <span class="error">* please enter incident address1</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* incident address2:</th>
      <td>
       <html:text property="address2" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* incident city:</th>
      <td>
       <html:text property="city" size="30" maxlength="30"/>
        <c:if test="${INCIDENT_ERROR.city == 'ERROR'}">
          <br/>
          <span class="error">* please enter incident city</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* incident state:</th>
      <td>
       <html:text property="state" size="2" maxlength="2"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* incident zip:</th>
      <td>
       <html:text property="zip" size="5" maxlength="5"/>
        <c:if test="${INCIDENT_ERROR.incidentZip == 'ERROR'}">
          <br/>
          <span class="error">* please enter incident zip</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* incident zip2:</th>
      <td>
       <html:text property="zip2" size="30" maxlength="30"/>
        <c:if test="${INCIDENT_ERROR.incidentZip2 == 'ERROR'}">
          <br/>
          <span class="error">* please enter incident zip2</span> 
        </c:if>
      </td>
    </tr>
   
  <tr>
      <th scope="row"  style= "width:50%"  class="required"   >* incident certification number:</th>
      <td>
       <html:text property="certNumber" size="30" maxlength="30"/>
        <c:if test="${INCIDENT_ERROR.certNumber == 'ERROR'}">
          <br/>
          <span class="error">* please enter certification number</span> 
        </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  class="required"  >* incident district:</th>
      <td>
       <html:text property="distirict" size="30" maxlength="30"/>
        <c:if test="${INCIDENT_ERROR.district == 'ERROR'}">
          <br/>
          <span class="error">* please enter district</span> 
        </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  class="required"  >* incident county:</th>
      <td>
        <html:text property="county" size="30" maxlength="30"/>
        <c:if test="${INCIDENT_ERROR.county == 'ERROR'}">
          <br/>
          <span class="error">* please enter county</span> 
        </c:if>
      </td>
    </tr>
  
    <tr>
      <th scope="row"  style= "width:50%"  height =20 >comments:</th>
      <td>
       <html:text property="comments" size="255" maxlength="225"/>
      </td>
    </tr>
    
       <th  style="color:green"><b><u>INCIDENT DATES</b></u></th>
     </tr>
     </tbody>
     </table>
     <%-- dates table --%>
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
   <tbody class="rowHeader" >
   <tr>
    <th colspan="4" style="color:white" >_____________________________</th>
   <td>
    <th colspan="4" style="color:white" >_____________________________</th>
   </td>
   </tr>
  <c:if test="${EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR' }">  
   <tr>
     <th  colspan="4" scope="row"  style= "width:25%"   >incident start date:</th>
      <td>
       <html:text property="iniCertDate" size="10" maxlength="10"/>
      </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >incident end date:</th>
      <td>
       <html:text property="visitDate" size="10" maxlength="10"/>
      </td>
    </tr>
     
</c:if>


    <tr>
<td>
<c:if test="${EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR' || EMS_SECURITY.currentUserLevel == 'USER'}">
<p>
   
          <html:submit value="Update changes" styleClass="button" />
 </p>
 </c:if>
 </td>
 </tr>
   </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>

