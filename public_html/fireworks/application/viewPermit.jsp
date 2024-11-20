 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>


<c:set var="module" scope="request" value="Fireworks"/>
<c:set var="title" scope="request" value="Fireworks Seller"/>
<c:set var="step" scope="request" value="2"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <div id="sideContentFluid">
 <b><u>Files Uploaded:</u></b></br>
<c:forEach var="file" items="${sessionScope.DFBS_VIEW_PERMIT.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null  }">  
       <a   href="/dfbs/fireworks/permit.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a></br>
        </c:if>
          </td>
        <td>
</tr>
</c:forEach>
</div>
 <div id="leftContentFluid">
 <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
   <tr>
      <th scope="row" >permit number:</th>
      <td style="font-size:medium;font-weight:bold">
        <c:out value="${DFBS_VIEW_PERMIT.permitNumber}"/>
        
      </td>
    </tr>
    <tr>
      <th scope="row" style="width:30%">type:</th>
      <td>
        <c:out value="${DFBS_VIEW_PERMIT.permitTypeString}"/>
      </td>
    </tr>
    <c:if test="${DFBS_VIEW_PERMIT.permitType == 1 }">
      <tr>
        <th scope="row">category:</th>
        <td>
          <c:out value="${DFBS_VIEW_PERMIT.categoryString}"/>
       <%--   <html:hidden property="manufacturer"/>
          <html:hidden property="wholesaler"/>
          <html:hidden property="importer"/>
          <html:hidden property="distributor"/> --%>
        </td>
      </tr>
    </c:if>
        <tr>
      <th scope="row" >street 1:</th>
      <td>
        <c:out value="${DFBS_VIEW_PERMIT.street1}"/>
       
        <br/>
      </td>
    </tr>
    <tr>
      <th scope="row">street 2:</th>
      <td>
        <c:out value="${DFBS_VIEW_PERMIT.street2}"/>
      </td>
    </tr>
    <tr>
      <th scope="row" >city:</th>
      <td>
        <c:out value="${DFBS_VIEW_PERMIT.city}"/>
       </td>
    </tr>
    <tr>
      <th scope="row" >state:</th>
      <td>
        <c:out value="${DFBS_VIEW_PERMIT.state}"/>
      </td>
    </tr>
    <tr>
      <th scope="row" >zip:</th>
      <td>
        <c:out value="${DFBS_VIEW_PERMIT.zip}"/>
      </td>
    </tr>
    <tr>
      <th scope="row" > county:</th>
      <td>
         <c:out value="${DFBS_VIEW_PERMIT.county}"/>
      </td>
    </tr>
   
<tr>
  <th colspan="2">&nbsp;</th>
</tr>
<tr>
  <th scope="row" >merchant #:</th>
  <td>
    <c:out value="${DFBS_VIEW_PERMIT.merchantNumber}"/>
  </td>
</tr>
<tr>
  <th scope="row" >contact:</th>
  <td>
    <c:out value="${DFBS_VIEW_PERMIT.contactPerson}"/>
  </td>
</tr>
<tr>
  <th scope="row" >phone:</th>
  <td>
     <c:out value="${DFBS_VIEW_PERMIT.phoneNumber}"/>
  </td>
</tr>
<tr>
  <th scope="row">fax:</th>
  <td>
    <c:out value="${DFBS_VIEW_PERMIT.faxNumber}"/>
  </td>
</tr>
<tr>
  <th scope="row">email:</th>
  <td>
     <c:out value="${DFBS_VIEW_PERMIT.emailAddress}"/>
  </td>
</tr>

<th scope="row" >ready to inspect:</th>
<td>
   <c:out value="${DFBS_VIEW_PERMIT.inspectDateString}"/>
</td>
<tr>
  <th scope="row" >open date:</th>
  <td>
    <c:out value="${DFBS_VIEW_PERMIT.openDateString}"/>
  </td>
</tr>
<tr>
  <th scope="row" > hrs of operation:</th>
  <td>
     <c:out value="${DFBS_VIEW_PERMIT.hoursOfOperation}"/>
  </td>
</tr>
  </tbody>
        </table>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>