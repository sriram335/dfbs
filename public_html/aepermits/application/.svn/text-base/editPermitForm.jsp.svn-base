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
 <div id="sideContentFluid">
<c:forEach var="file" items="${sessionScope.PERMIT_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       "/dfbs/aepermits  
       <a   href="/dfbs/aepermits/permit.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a>
          </td>
        <td>
</tr>
</c:forEach>
</div>
<div id="mainBox">
 <h2>Edit Permit</h2>
  
<div id="rightContentFluid">
 <p class="control2">
      <html:link page="/application.do?method=step2">cancel</html:link>
    </p>
</div>
  <div id="leftContentFluid">
    <html:form action="/permit" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="editPermit"/>
      <html:hidden property="ownerId"/>
      <html:hidden property="applicationKey"/>
       <html:hidden property="status"/>
      <html:hidden property="permitYear"/>
       <html:hidden property="applicationDate"/>
      <html:hidden property="county" styleId="COUNTY_NAME"/>
         <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
       <tr>
      <th >
       <c:if test="${DFBS_PERMIT.permitType != 'Y'}">
           <input type="checkbox" name="permitType"  class="switch"  value="Y"/> 
      </c:if>
       <c:if test="${DFBS_PERMIT.permitType == 'Y' ||DFBS_PERMIT.permitType == 'SU'}">
              <input type="checkbox" name="permitType"  class="switch"  value="Y" checked/>
       </c:if>
      </th>
      <td style="color:red">
    <b>Is any stage to be used </b>     
      </td>
    </tr> 
      <tr>
      <th scope="row" style="width : 50%">permit number:</th>
      <td style="font-size:medium;font-weight:bold">
        <c:out value="${DFBS_PERMIT.permitNumber}"/>
        <html:hidden property="permitNumber"/>
      </td>
    </tr>
   
    <tr>
      <th scope="row">facility name:</th>
      <td>
        <c:out value="${DFBS_PERMIT.actualLocation}"/>
        <html:hidden property="actualLocation" />
      </td>
      </tr>
    <tr>
    <tr>
      <th scope="row">street 1:</th>
      <td>
        <c:out value="${DFBS_PERMIT.street1}"/>
        <html:hidden property="street1" />
      </td>
      </tr>
    <tr>
      <th scope="row">street 2:</th>
      <td>
        <c:out value="${DFBS_PERMIT.street2}"/>
      </td>
    </tr>
    <tr>
      <th scope="row" >city:</th>
      <td>
        <c:out value="${DFBS_PERMIT.city}"/>
        <html:hidden property="city" />
      </td>
    </tr>
    <tr>
      <th scope="row" >state:</th>
      <td>
         <c:out value="${DFBS_PERMIT.state}"/>
        <html:hidden property="state" />
      </td>
    </tr>
    <tr>
      <th scope="row" >zip:</th>
      <td>
       <c:out value="${DFBS_PERMIT.zip}"/>
        <html:hidden property="zip" />
      </td>
    </tr>
    <tr>
      <th scope="row" >county:</th>
      <td>
         <c:out value="${DFBS_PERMIT.county}"/>
        <html:hidden property="county" />
      </td>
   </tr>
   <c:if test="${DFBS_PERMIT.status == 'VALID'    }">
   <tr>
        <th scope="row" >contact:</th>
        <td>
           <c:out value="${DFBS_PERMIT.contactPerson}"/>
          <html:hidden property="contactPerson" />
        </td>
    </tr>
    <tr>
        <th scope="row" >phone:</th>
        <td>
           <c:out value="${DFBS_PERMIT.phoneNumber}"/>
          <html:hidden property="phoneNumber" />
        </td>
    </tr>
    <tr>
        <th scope="row" >fax:</th>
        <td>
           <c:out value="${DFBS_PERMIT.faxNumber}"/>
          <html:hidden property="faxNumber" />
        </td>
    </tr>
     <tr>
        <th scope="row" >email:</th>
        <td>
           <c:out value="${DFBS_PERMIT.emailAddress}"/>
          <html:hidden property="emailAddress" />
        </td>
    </tr>
    <tr>
        <th scope="row" >maximum occupant load:</th>
        <td>
           <c:out value="${DFBS_PERMIT.intendedOccupantLoad}"/>
          <html:hidden property="intendedOccupantLoad" />
        </td>
    </tr>
     <tr>
        <th scope="row" >event date:</th>
        <td>
           <c:out value="${DFBS_PERMIT.eventDate}"/>
          <html:hidden property="eventDate" />
        </td>
    </tr>
     <tr>
        <th scope="row" >event time:</th>
        <td>
           <c:out value="${DFBS_PERMIT.eventTime}"/>
          <html:hidden property="eventTime" />
        </td>
    </tr>
     <tr>
        <th scope="row" >event name:</th>
        <td>
           <c:out value="${DFBS_PERMIT.eventName}"/>
          <html:hidden property="eventName" />
        </td>
    </tr>
    <tr>
        <th scope="row" >maximum occupancy:</th>
        <td>
           <c:out value="${DFBS_PERMIT.maximumOccupancy}"/>
          <html:hidden property="maximumOccupancy" />
        </td>
    </tr>
    <tr>
        <th scope="row" >facility description:</th>
        <td>
           <c:out value="${DFBS_PERMIT.facilityDescription}"/>
          <html:hidden property="facilityDescription" />
        </td>
    </tr>
     <tr>
        <th scope="row" >comments:</th>
        <td>
           <c:out value="${DFBS_PERMIT.notes}"/>
          <html:hidden property="notes" />
        </td>
    </tr>
    <tr>
      <th scope="row" >fee exempt:</th>
      <td>
         <c:out value="${DFBS_PERMIT.feeExemptString}"/>
        <html:hidden property="feeExempt" />
      </td>
   </tr>
  </c:if> 
   <c:if test="${ DFBS_PERMIT.status != 'VALID'   }">
<tr>
  <th colspan="2">&nbsp;</th>
</tr>
<tr>
  <th scope="row" class="required">*contact:</th>
  <td>
    <html:text property="contactPerson" size="25" maxlength="60"/>
    <c:if test="${DFBS_PERMIT_ERROR.contactPerson == 'ERROR'}">
      <br/>
      <span class="error">* please specify contact person</span> 
    </c:if>
  </td>
</tr>

<tr>
  <th scope="row" class="required">*phone:</th>
  <td>
    <html:text property="phoneNumber" size="10" maxlength="10"/>
    <c:if test="${DFBS_PERMIT_ERROR.phoneNumber == 'ERROR'}">
      <br/>
      <span class="error">* please specify phone number in the correct format</span> 
    </c:if>
  </td>
</tr>
<tr>
  <th scope="row">fax: </th>
  <td>
    <html:text property="faxNumber" size="10" maxlength="10"/> 
  </td>
</tr>
<tr>
</tr>
<tr>
  <th scope="row">email:</th>
  <td>
    <html:text property="emailAddress" size="60"/><br/>email not mandatory, but if you provide email we will email you the permit once it is approved. 
  </td>
</tr>
 
 <tr>
      <th scope="row" class="required">*maximum occupant load:</th>
      <td>
        <html:select property="intendedOccupantLoad"  onchange="setSelectValue('SELECT_OCCUPANCY','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <html:options collection="DFBS_OCCUPANCY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${DFBS_PERMIT_ERROR.intendedOccupantLoad == 'ERROR'}">
          <br/>
          <span class="error">* please specify intended occupant load</span> 
        </c:if>
      </td>
    </tr>
    

    <tr>
     <th scope="row" >event date:</th>
      <td>
        <html:text property="eventDate" size="10" maxlength="10"/>(mm/dd/yyyy)
       
      </td>
    </tr>
    
    <tr>
      <th scope="row">event time:</th>
      <td>
        <html:text property="eventTime" size="15" maxlength="15"/>
       
      </td>
    </tr>
    <tr>
      <th scope="row" >event name:</th>
      <td>
        <html:text property="eventName" size="30" maxlength="30"/>
      
      </td>
    </tr>
 
     <tr>
      <th scope="row" >maximum occupancy:</th>
      <td>
        <html:text property="maximumOccupancy" size="30" maxlength="30"/>
       </td>
    </tr>
     <tr>
      <th scope="row" class="required">*facility description:</th>
      <td>
        <html:text property="facilityDescription" size="30" maxlength="30"/>
        <c:if test="${DFBS_PERMIT_ERROR.facilityDescription == 'ERROR'}">
          <br/>
          <span class="error">* please specify facility description</span> 
        </c:if>
      </td>
    </tr>  
    <tr>
      <th scope="row" >comments:</th>
      <td>
        <html:text property="notes" size="100" maxlength="100"/>
       </td>
    </tr>
     <tr>
      <th scope="row" >fee exempt:</th>
      <td>
         <c:out value="${DFBS_PERMIT.feeExemptString}"/>
        <html:hidden property="feeExempt" />
         <p class="messageBox"><span class="error"><b><u>Important Note:</u> If you are claiming fee exempt status, you must provide a valid 501C. 
                  If you have questions contact <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/></A> :at <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Phone}"/> or
                  <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/></A> :at <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Phone}"/> with details after submitting the application.
                  If you have a electronic copy of the letter please upload it as a document when you upload the floor plan.</b></span></p>
     
      </td>
   </tr>
</c:if>
            <tr>
              <th colspan="2">&nbsp;</th>
            </tr>
          </tbody>
        </table>
        <p>
          <html:submit value="save to your application" styleClass="button"/>
        
        </p>
    </html:form>
   

    <c:if test="${requestScope.DFBS_PERMIT.new}">
      <script type="text/javascript">
        setSelectValue('SELECT_COUNTY','COUNTY_NAME');
        showMiscRow();
      </script>
    </c:if> 
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
