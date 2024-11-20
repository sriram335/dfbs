<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="Instructor Details"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
  <h2>Current available files</h2> 
<c:forEach var="file" items="${sessionScope.EMS_INSTRUCTOR.fileList}" varStatus="i">
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a></br> File date:<c:out value="${file.fileDateString}"/></br> File sent by:<c:out value="${file.fileLoadedBy}"/>
</c:forEach>

</div>
<div id="mainBox">
<a   href="/dfbs/ems/course.do?method=courseList&institutionId=<c:out value="${sessionScope.INSTITUTION.institutionId}"/>">
              [ course list ]</a> 
 <a   href="/dfbs/ems/course.do?method=courseDetail&courseId=<c:out value="${sessionScope.COURSE.courseId}"/>">
              [ back to course ]</a> 
     <a   href="/dfbs/ems/institution.do?method=institutionDetail&institutionId=<c:out value="${sessionScope.INSTITUTION.institutionId}"/>">
              [ back to institution ]</a> 
      <c:if test="${EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR' || EMS_SECURITY.currentUserLevel == 'USER'}">
      <a href="/dfbs/ems/main.do?method=goToUpload&idNumber=<c:out value="${sessionScope.EMS_INSTRUCTOR.personId}"/>&idType=Person">
            [upload instructor files]  </a> 
      </c:if>
     <a href="/dfbs/ems/main.do?method=authUser">
              [back to main menu]  </a> </br>
  <h2>Instructor Details(view only)</h2>

   <div id="leftContentFluid">
 <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   
     <tr>
      <th scope="row"     > instructor name:</th>
      <td>
      <c:out value="${EMS_INSTRUCTOR.personLastName}" />,<c:out value="${EMS_INSTRUCTOR.personMi}" /> <c:out value="${EMS_INSTRUCTOR.personFirstName}" />
       </td>
    </tr>
    <tr>
      <th scope="row"    class="required" > address:</th>
      <td>
      <c:if test="${EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR' || EMS_SECURITY.currentUserLevel == 'USER'}">
       <c:out value="${EMS_INSTRUCTOR.personAddress1}" /></br>
       <c:out value="${EMS_INSTRUCTOR.personAddress2}" /></br> 
       <c:out value="${EMS_INSTRUCTOR.personCity}" /> </br><c:out value="${EMS_INSTRUCTOR.personState}" /></br><c:out value="${EMS_INSTRUCTOR.personZip}" /></br>
     </c:if>
     <c:out value="${EMS_INSTRUCTOR.personCounty}" /> County
      </td>
    </tr>
   <c:forEach var="cert" items="${EMS_INSTRUCTOR.certList}" varStatus="i" >
  <tr>
  <th scope="row"    ></th>
  <td>
  <div class="listing">
    Certification:<c:out value="${cert.certName}" /></br>
    Issue date: <c:out value="${cert.issueDate}" /></br>
    Expiration Date: <c:out value="${cert.expDate}" />
  </div>
 </td>
 </tr>
  </c:forEach>
  
   </tbody>
   </table>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>


