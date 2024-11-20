<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Entertainment Permits"/>
<c:set var="title" scope="request" value="Entertainment Permits"/>
<c:set var="step" scope="request" value="2"/>
<c:set var="renew" scope="request" value="renewed"/>

<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null}">
 <a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${sessionScope.VIEW_PERMIT_SELECTED.permitNumber}"/>">
             [ Inspections List for this permit ]</a>
 </c:if>            
 <div id="sideContentFluid">
 <b><u>Files Uploaded:</u></b></br>
<c:forEach var="file" items="${sessionScope.VIEW_PERMIT_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp;  uploaded on :<c:out value="${file.fileDateString}"/><br/> 
      
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null}">  
       <a   href="/dfbs/aepermits/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a></br>
        </c:if>
          <c:if test="${sessionScope.DFBS_SECURITY.userEmail !=null &&(sessionScope.DFBS_SECURITY.userEmail == sessionScope.APPLICATION_CONTACTS.contact1Email ||
         sessionScope.DFBS_SECURITY.userEmail == sessionScope.APPLICATION_CONTACTS.contact2Email)}">
           <a href="/dfbs/aepermits/permit.do?method=deleteFile&fileId=<c:out value="${file.fileId}"/>"><FONT color="#ff0000">[delete this file, can not be recovered if deleted]</FONT></a> </br>
          </c:if>
          </td>
        <td>
</tr>
</c:forEach>
</div>
<div id="mainBox">
 <h2>View Permit</h2>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null}">
<div id="rightContentFluid">
 <p class="control2">
      <html:link page="/application.do?method=step2">cancel</html:link>
    </p>
</div>
 </c:if>
  <div id="leftContentFluid">
    <html:form action="/permit" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="editPermit"/>
      <html:hidden property="ownerId"/>
      <html:hidden property="applicationKey"/>
      <html:hidden property="permitYear"/>
       <html:hidden property="applicationDate"/>
      <html:hidden property="county" styleId="COUNTY_NAME"/>
      <p style="font-size:medium;font-weight:bold;">
        <img class="icon" src="/dfbs/img/clearbits/arrow2_e.gif" alt="arrow"/>add new permit :
      </p>
         <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
     
     <tr>
      <th > <c:if test="${VIEW_PERMIT_SELECTED.permitType != 'Y' && VIEW_PERMIT_SELECTED.permitType != 'SU'}">
           <input type="checkbox" name="permitType"  class="switch"  value="Y"/> 
      </c:if>
       <c:if test="${VIEW_PERMIT_SELECTED.permitType == 'Y' ||VIEW_PERMIT_SELECTED.permitType == 'SU'}">
              <input type="checkbox" name="permitType"  class="switch"  value="Y" checked/>
       </c:if> </th>
      <td style="color:red">
     <b>Is any stage to be used</b> 
    
      </td>
    </tr> 
     <tr>
      <th scope="row" style="width : 50%">permit number:</th>
      <td style="font-size:medium;font-weight:bold">
        <c:out value="${VIEW_PERMIT_SELECTED.permitNumber}"/>
        <html:hidden property="permitNumber"/>
      </td>
    </tr>
    
    <tr>
      <th scope="row">facility name:</th>
      <td>
        <c:out value="${VIEW_PERMIT_SELECTED.actualLocation}"/>
        <html:hidden property="actualLocation" />
      </td>
      </tr>
    <tr>
    <tr>
      <th scope="row">street 1:</th>
      <td>
        <c:out value="${VIEW_PERMIT_SELECTED.street1}"/>
        <html:hidden property="street1" />
      </td>
      </tr>
    <tr>
      <th scope="row">street 2:</th>
      <td>
        <c:out value="${VIEW_PERMIT_SELECTED.street2}"/>
      </td>
    </tr>
    <tr>
      <th scope="row" >city:</th>
      <td>
        <c:out value="${VIEW_PERMIT_SELECTED.city}"/>
        <html:hidden property="city" />
      </td>
    </tr>
    <tr>
      <th scope="row" >state:</th>
      <td>
         <c:out value="${VIEW_PERMIT_SELECTED.state}"/>
        <html:hidden property="state" />
      </td>
    </tr>
    <tr>
      <th scope="row" >zip:</th>
      <td>
       <c:out value="${VIEW_PERMIT_SELECTED.zip}"/>
        <html:hidden property="zip" />
      </td>
    </tr>
    <tr>
      <th scope="row" >county:</th>
      <td>
         <c:out value="${VIEW_PERMIT_SELECTED.county}"/>
        <html:hidden property="county" />
      </td>
   </tr>
 
  <tr>
        <th scope="row" >contact:</th>
        <td>
           <c:out value="${VIEW_PERMIT_SELECTED.contactPerson}"/>
          <html:hidden property="contactPerson" />
        </td>
    </tr>
    <tr>
        <th scope="row" >phone:</th>
        <td>
           <c:out value="${VIEW_PERMIT_SELECTED.phoneNumber}"/>
          <html:hidden property="phoneNumber" />
        </td>
    </tr>
    <tr>
        <th scope="row" >fax:</th>
        <td>
           <c:out value="${VIEW_PERMIT_SELECTED.faxNumber}"/>
          <html:hidden property="faxNumber" />
        </td>
    </tr>
     <tr>
        <th scope="row" >email:</th>
        <td>
           <c:out value="${VIEW_PERMIT_SELECTED.emailAddress}"/>
          <html:hidden property="emailAddress" />
        </td>
    </tr>
    <tr>
        <th scope="row" >maximum occupant load:</th>
        <td>
           <c:out value="${VIEW_PERMIT_SELECTED.intendedOccupantLoad}"/>
          <html:hidden property="intendedOccupantLoad" />
        </td>
    </tr>
     <tr>
        <th scope="row" >event date:</th>
        <td>
           <c:out value="${VIEW_PERMIT_SELECTED.eventDate}"/>
          <html:hidden property="eventDate" />
        </td>
    </tr>
     <tr>
        <th scope="row" >event time:</th>
        <td>
           <c:out value="${VIEW_PERMIT_SELECTED.eventTime}"/>
          <html:hidden property="eventTime" />
        </td>
    </tr>
     <tr>
        <th scope="row" >event name:</th>
        <td>
           <c:out value="${VIEW_PERMIT_SELECTED.eventName}"/>
          <html:hidden property="eventName" />
        </td>
    </tr>
    <tr>
        <th scope="row" >maximum occupancy:</th>
        <td>
           <c:out value="${VIEW_PERMIT_SELECTED.maximumOccupancy}"/>
          <html:hidden property="maximumOccupancy" />
        </td>
    </tr>
    <tr>
        <th scope="row" >facility description:</th>
        <td>
           <c:out value="${VIEW_PERMIT_SELECTED.facilityDescription}"/>
          <html:hidden property="facilityDescription" />
        </td>
    </tr>
     <tr>
        <th scope="row" >facility exempt:</th>
        <td>
           <c:out value="${VIEW_PERMIT_SELECTED.feeExempt}"/>
          <html:hidden property="feeExempt" />
        </td>
    </tr>
     <tr>
        <th scope="row" >comments:</th>
        <td>
           <c:out value="${VIEW_PERMIT_SELECTED.notes}"/>
          <html:hidden property="notes" />
        </td>
    </tr>
 


   
    
 
     
   
          </tbody>
        </table>
        
    </html:form>
   

   
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>

