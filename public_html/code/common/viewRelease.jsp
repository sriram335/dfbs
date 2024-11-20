<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="Code Enforcement"/>
<c:set var="title" scope="request" value="Application for Industrialized Building Systems/ Mobile Structures"/>
<c:set var="level" scope="request" value="2"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>

  <div class="styledBox">
    <table cellspacing="0" class="noBorder" summary="MANUFACTURER DATA">
      <tbody class="rowHeader">
        <tr>
          <th class="row" style="font-size:large;font-weight:bold;">
            <c:out value="${sessionScope.MANUFACTURER.manufacturerName}"/>&nbsp;</br><br/>
           <br/> <u>      Design Releases List      </u>
          </th>
         
        </tr>
    <c:forEach var="release" items="${sessionScope.MANUFACTURER.releaseList}" varStatus="i">
             <c:set scope="request" var="release" value="${mapItem.value}"/>
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
      <hr/>   System Release Number :<c:out value="${release.systemReleaseNumber}"/></br>
         Addendum Release Number: <c:out value="${release.addReleaseNumber}"/></br>
         Addendum Sequence: <c:out value="${release.addSequence}"/></br>
         <c:if test="${release.releaseApproval == null}"> 
          Release Date:This release is in process.
        </c:if>
         <c:if test="${release.releaseApproval == 'Y'}"> 
         Release Date: <c:out value="${release.systemReleaseDateString}"/>
         </br>
           <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=code_design_release&p_system_id=<c:out value="${release.systemId}"/>" target="_blank">
         [ View ]</a>
         </c:if>
         <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCode != null }">  
            <a   href="/dfbs/code/newRelease.do?method=addReleaseDate&systemId=<c:out value="${release.systemId}"/>"> 
              [ edit this release ] </a>  
            <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=code_design_release_application&p_system_id=<c:out value="${release.systemId}"/>" target="_blank">
             [ view application ]</a>  
             <b><u>Files Uploaded:</u></b></br>
                  <c:forEach var="file" items="${release.fileList}" varStatus="i">
                       <tr >
                        <td>
                         <c:out value="${file.fileName}"/>&nbsp; <br/> 
                          <a   href="/dfbs/code/newRelease.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
                               [download this file]</a></br>
                            </td>
                          <td>
                  </tr>
                  </c:forEach>
           
          </c:if>
           
        </td>
             
      </tr>
     
    </c:forEach>
  </tbody>
</table>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
