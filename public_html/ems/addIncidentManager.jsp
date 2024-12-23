<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="Contact Details"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
  <h2>Current available files</h2> 
<c:forEach var="file" items="${sessionScope.HOSPITAL.fileList}" varStatus="i">
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a></br> File date:<c:out value="${file.fileDateString}"/></br> File sent by:<c:out value="${file.fileLoadedBy}"/>
</c:forEach>

</div>
<div id="mainBox">
  <h2>Add new incident manager</h2>

<a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> 
<c:if test="${EMS_PERSON.personInstId > 0 }"> 
     <a href="/dfbs/ems/person.do?method=personInstList&institutionId=<c:out value="${EMS_PERSON.personInstId}"/>">
            [back to incident]  </a>  
</c:if> 
 <a href="/dfbs/ems/main.do?method=goToUpload&idNumber=<c:out value="${sessionScope.HOSPITAL.hospitalId}"/>&idType=Hospital">
            [upload incident manager files]  </a> 

  <div id="leftContentFluid">

 <html:form action="/person" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="savePerson"/> 
       <html:hidden property="emsPersonId"/> 
       <html:hidden property="personInstId"/> 
       <html:hidden property="personHospId"/> 
       <html:hidden property="personProvId"/> 
       
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >* person first name:</th>
      <td>
       <html:text property="personFirstName" size="30" maxlength="30"/>
        <c:if test="${PERSON_ERROR.personFirstName == 'ERROR'}">
          <br/>
          <span class="error">* please enter person first name</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* person last name:</th>
      <td>
       <html:text property="personLastName" size="30" maxlength="30"/>
        <c:if test="${PERSON_ERROR.personLastName == 'ERROR'}">
          <br/>
          <span class="error">* please enter person last name</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* person middle intial:</th>
      <td>
       <html:text property="personMi" size="30" maxlength="30"/>
        <c:if test="${PERSON_ERROR.personMi == 'ERROR'}">
          <br/>
          <span class="error">* please enter person middle initial</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* person address1:</th>
      <td>
       <html:text property="personAddress1" size="30" maxlength="30"/>
        <c:if test="${PERSON_ERROR.personAddress1 == 'ERROR'}">
          <br/>
          <span class="error">* please enter person address1</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* person address2:</th>
      <td>
       <html:text property="personAddress2" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* person city:</th>
      <td>
       <html:text property="personCity" size="30" maxlength="30"/>
        <c:if test="${PERSON_ERROR.personCity == 'ERROR'}">
          <br/>
          <span class="error">* please enter person city</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* person state:</th>
      <td>
       <html:text property="personState" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* person zip:</th>
      <td>
       <html:text property="personZip" size="5" maxlength="5"/>
        <c:if test="${PERSON_ERROR.personZip == 'ERROR'}">
          <br/>
          <span class="error">* please enter person zip</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* person zip2:</th>
      <td>
       <html:text property="personZip2" size="30" maxlength="30"/>
        <c:if test="${PERSON_ERROR.personZip2 == 'ERROR'}">
          <br/>
          <span class="error">* please enter person zip2</span> 
        </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > person email:</th>
      <td>
       <html:text property="personEmail" size="100" maxlength="100"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > person fax:</th>
      <td>
       <html:text property="personFax" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > person phone:</th>
      <td>
       <html:text property="personPhone" size="10" maxlength="10"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > person type:</th>
      <td>
       <html:text property="personType" size="30" maxlength="30"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > person title:</th>
      <td>
       <html:text property="personTitle" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > person no longer works here as of date:</th>
      <td>
       <html:text property="endDate" size="10" maxlength="10"/>
      </td>
    </tr>
   <tr>
<td>
<c:if test="${EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR' || EMS_SECURITY.currentUserLevel == 'USER'}">
<p>
   
          <html:submit value="Update changes" styleClass="button" /></br>
         
           <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=incident_id.rdf&p_personid=81688">[Print Photo ID]</a>
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


