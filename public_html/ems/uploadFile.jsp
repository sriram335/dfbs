<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Ems Operations" />
<c:set var="title" scope="request" value="File Upload(s)" />



<jsp:include page="/app/common/hsPageHeader.jsp" />

<c:if test="${sessionScope.INSTITUTION.institutionId >0}"> 
<a   href="/dfbs/ems/institution.do?method=institutionDetail&institutionId=<c:out value="${sessionScope.INSTITUTION.institutionId}"/>">
              [ back to institution ]</a> 
</c:if>
<c:if test="${sessionScope.PROVIDER.providerId >0}"> 
<a   href="/dfbs/ems/provider.do?method=providerDetail&providerId=<c:out value="${sessionScope.PROVIDER.providerId}"/>">
              [ back to provider ]</a> 
</c:if> 
<c:if test="${sessionScope.HOSPITAL.hospitalId >0}"> 
<a   href="/dfbs/ems/hospital.do?method=hospitalDetail&hospitalId=<c:out value="${sessionScope.HOSPITAL.hospitalId}"/>">
              [ back to hospital ]</a>
</c:if>
<c:if test="${sessionScope.VEHICLE.vehicleId >0}"> 
<a   href="/dfbs/ems/vehicle.do?method=vehicleDetail&vehicleId=<c:out value="${sessionScope.VEHICLE.vehicleId}"/>">
              [ back to vehicle ]</a>
</c:if>
<c:if test="${sessionScope.COURSE.courseId >0}"> 
<a   href="/dfbs/ems/course.do?method=courseDetail&courseId=<c:out value="${sessionScope.COURSE.courseId}"/>">
              [ back to course ]</a>
</c:if>
<c:if test="${sessionScope.EMS_INSTRUCTOR.personId >0}"> 
<a   href="/dfbs/ems/instructor.do?method=instructorDetail&personId=<c:out value="${sessionScope.EMS_INSTRUCTOR.personId}"/>">
              [ back to person ]</a>
</c:if>

<h2>  Upload File</h2>
<div id="sideContentFluid">
<c:if test="${sessionScope.NEW_COURSE  != null }"> 
  <jsp:include page="/ems/newCourseStatus.jsp"/>
  </c:if>
<c:if test="${sessionScope.RENEW_PROVIDER  != null }"> 
  <jsp:include page="/ems/renewalProviderStatus.jsp"/>
  </c:if>  
  </div>  
<div id="leftContentFluid">
<html:form action="/main" method="post" enctype="multipart/form-data">
  <input type="hidden" name="method" id="METHOD_KEY" value="uploadFile"/>
  <html:hidden property="idNumber"/> 
  <html:hidden property="idType"/> 
  <c:out value="${file.fileName}"/>
  <p class="messageBox" size="100">
   to upload file </br> 
      <b> 1.	The following documents must be uploaded in order to complete your course application</br> 
            a.	Course syllabus </br> 
            b.	Course Checklist (Not needed for Paramedic Course)</br> 
            c.	Narrative of requirements found on Course Checklist</br> 
          2.	Course Requests should be filled out in their entirety. Any blank sections will cause your request to be denied and will then need to be resubmitted.</br> 

      click "Browse" below and select file and then click Upload select button.</br> Only files with these extensions are accepted </br>
      BMP,DOC,DWF,GIF,HTM,JPEG,JPG,PDF,PPT,RTF,TIF,TIFF,TXT</b> </p>
      <c:if test="${sessionScope.FILE_EXTENSION == 'ERROR'}"> 
      <span class="error">ERROR !. Try to upload only acceptable files</span>
      </c:if>    
       <td width="100%" align="left" valign="middle"> 
	     <html:file property="caseFile" size="70"   />   </td> 
        <input type="submit" value="Upload select" class="button"/>
      
</html:form>
<c:if test="${sessionScope.COURSE.courseId >0}">
<b><u>You need to upload the following files for course approval.</u></b></br>
1.A list of resource materials, references, textbooks, workbooks, etc. that will be used by the students during the course</br>
2.Total number of classroom (didactic) hours __________</br>
3.Total number of skill (psychomotor) hours {if listed} __________</br>
4.Students Roaster.</br>
5.Signed copy of the course application.</br>

</c:if>
<c:if test="${sessionScope.RENEWAL.renewalId >0}">
<b><u>You need to upload the following files for completion of renewal process.</u></b></br>
<b><u>1. All Organizations</u></b></br>

PROTOCOLS � Submit copy of current protocols, signed and dated by the Medical Director or a letter signed and 
dated by the Medical Director stating that there have been no changes in the protocols since the previous application.
MEDICAL DIRECTOR APPROVAL FORM � Submit form, signed and dated by the Medical Director.
PERSONNEL ROSTER � Submit roster, signed and dated by organization CEO and Medical Director.
MEDICATIONS � Submit a list of any and all medications and solutions, including amounts, and dosages approved and signed by the Medical Director.
MEDICATION STORAGE � Describe method of storage for all medications including double locked system for all scheduled medications.</br>  

<b><u>2. If an Advanced Life Support or EMT Basic-Advanced organization, submit the following:</u></b></br>
CONTRACT � Submit a copy of the contract with the supervising hospital, or interdepartmental memo, if hospital based, or a letter signed and dated by the Administrator of the supervising hospital stating that the existing contract is still in effect.  If more that one hospital supervises the service, submit a copy of the agreement between the hospitals, which ensures consistency in medical control, or a letter from the hospitals stating that the contract is still in place.
ACCEPTANCE of PERSONNEL � Submit a letter from the supervising hospital stating the acceptance of paramedic, EMT-Intermediate, and/or EMT Basic-Advanced personnel. </br>

<b><u> 3. If a Non-Transport Provider, submit the following:</u></b></br>

AGREEMENT � Submit a copy of the agreement with an ambulance service which guarantees transportation of patients to 
a hospital or a letter from the transporting service stating the agreement is still in effect.  </br>
4.Signed copy of the personnel roaster working with the provider.</br>
5.Signed copy of the application.</br>

</c:if>
<table cellspacing="0" style="width:100%;" summary="sales" >
  <tr>
    <th >file name</th>
   
  </tr>
  <tbody>
<c:forEach var="file" items="${sessionScope.INSTITUTION.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a> File date:<c:out value="${file.fileDateString}"/> File sent by:<c:out value="${file.fileLoadedBy}"/>
          </td>
        
</tr>
</c:forEach>
<c:forEach var="file" items="${sessionScope.PROVIDER.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> File date:<c:out value="${file.fileDateString}"/>,File sent by:<c:out value="${file.fileLoadedBy}"/>   
       <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
          [download this file]</a> 
          </td>
        
</tr>
</c:forEach>
<c:forEach var="file" items="${sessionScope.COURSE.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a> File date:<c:out value="${file.fileDateString}"/> File sent by:<c:out value="${file.fileLoadedBy}"/>
          </td>
       
</tr>
</c:forEach>
<c:forEach var="file" items="${sessionScope.VEHICLE.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a> File date:<c:out value="${file.fileDateString}"/> File sent by:<c:out value="${file.fileLoadedBy}"/>
          </td>
       
</tr>
</c:forEach>
<c:forEach var="file" items="${sessionScope.HOSPITAL.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a> File date:<c:out value="${file.fileDateString}"/> File sent by:<c:out value="${file.fileLoadedBy}"/>
          </td>
       
</tr>
</c:forEach>

<c:forEach var="file" items="${sessionScope.EMS_INSTRUCTOR.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a> File date:<c:out value="${file.fileDateString}"/> File sent by:<c:out value="${file.fileLoadedBy}"/>
          </td>
     
</tr>
</c:forEach>
  </tbody>
</table> 
</div>

<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
