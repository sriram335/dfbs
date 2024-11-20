<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="Elevator Permits" />
<c:set var="title" scope="request" value="Update Elevator Information" />

<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <a   href="/dfbs/elevator/owner.do?method=view&ownerId=<c:out value="${sessionScope.OWNER_SELECTED.ownerId}"/>">
             [back to owner]</a>  
<div>
 <h2>Update Elevator Application</h2> 
 <html:form action="/application" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateSaveApplication"/> 
         <html:hidden property="applicationId"/> 
         <html:hidden property="stateNumber"/> 
         <html:hidden property="applicationDate"/> 
         <html:hidden property="applicationType"/>
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     <tr>
      <th scope="row">state number :</th>
      <td>
        <c:out value="${sessionScope.ELEVATOR_SELECTED.stateNumber}"/>
       </td>
      </tr>
    <tr> 
     <tr>
      <th scope="row">application type and date:</th>
      <td>
        <c:out value="${requestScope.ELEV_APP_SELECTED.applicationType}"/>:<c:out value="${requestScope.ELEV_APP_SELECTED.applicationDateString}"/>
       </td>
      </tr>
    <tr> 
  <tr>
      <th scope="row" >owner/authorized officer:</th>
      <td>
       <html:text property="ownerBy" size="50" maxlength="100"/>
      </td>
    </tr>
    <tr>
      <th scope="row" >owner/authorized firm:</th>
      <td>
       <html:text property="ownerFirm" size="50" maxlength="100"/>
      </td>
    </tr>
     <tr>
      <th scope="row" >elevator owner phone:</th>
      <td>
       <html:text property="ownerDesignation" size="10" maxlength="10"/>
      </td>
    </tr>
     <tr>
      <th scope="row" >elevator owner email:</th>
      <td>
       <html:text property="ownerEmail" size="50" maxlength="100"/>
      </td>
    </tr>
    <tr>
      <th scope="row" >applied by :</th>
      <td>
       <html:text property="appliedBy" size="50" maxlength="100"/>
      </td>
    </tr>
    <tr>
      <th scope="row" >applied firm :</th>
      <td>
       <html:text property="appliedFirm" size="50" maxlength="100"/>
      </td>
    </tr>
     <tr>
      <th scope="row" >applied designation:</th>
      <td>
       <html:text property="appliedDesignation" size="50" maxlength="100"/>
      </td>
    </tr>
     <tr>
      <th scope="row" >applied email:</th>
      <td>
       <html:text property="appliedEmail" size="50" maxlength="100"/>
      </td>
    </tr>
     <tr>
      <th scope="row" > code reference alterations :</th>
      <td>
       <html:textarea property="codeForAlterations" rows="10" cols="70" />
      </td>
    </tr>
     <tr>
      <th scope="row"    >  application status:</th>
      <td>
          <html:select  property="appStatus">
          <html:option value="">Please Select</html:option>
          <html:option value="Approved">Approved</html:option>
          <html:option value="Pending">Pending</html:option>
         </html:select>
        </td>
    </tr>
   </tbody>
        </table>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelElev != null}"> 
        <p class="message">
          <html:submit value="Save " styleClass="button"/>
        </p>
        </c:if>
   </html:form>   
   
</div>