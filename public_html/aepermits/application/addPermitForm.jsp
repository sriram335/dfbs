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
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Add New Permit</h2>
  <div id="rightContentFluid">
    <p class="control2">
      <html:link page="/application.do?method=step2">cancel</html:link>
    </p>
  </div>
  <div id="leftContentFluid">

    <html:form action="/permit" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="addPermit"/>
      <html:hidden property="ownerId"/>
      <html:hidden property="applicationKey"/>
      <html:hidden property="status"/>
      <html:hidden property="county" styleId="COUNTY_NAME"  />
      <p style="font-size:medium;font-weight:bold;">
        <img class="icon" src="/dfbs/img/clearbits/arrow2_e.gif" alt="arrow"/>add new permit :
      </p>
      
        <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
           
           <tr>
      <th scope="row" style="width : 50%;">permit number:</th>
      <td style="font-size:medium;font-weight:bold">NEW</td>
    </tr>
     <tr>
      <th >  <input type="checkbox" name="permitType"  class="switch"  value="Y"/> 
    </th>
      <td style="color:red">
     <b>Is any stage to be used</b> 
       </td>
    </tr>
     <tr>
      <th scope="row" class="required">*facility name:</th>
      <td>
        <html:text property="actualLocation" size="30" maxlength="30"/>
        <c:if test="${DFBS_PERMIT_ERROR.actualLocation == 'ERROR'}">
          <br/>
          <span class="error">* please specify actual location</span> 
        </c:if>
      </td>
    </tr> 
    <tr>
      <th scope="row" class="required">*street 1:</th>
      <td>
        <html:text property="street1" size="30" maxlength="30"/>
        <c:if test="${DFBS_PERMIT_ERROR.street1 == 'ERROR'}">
          <br/>
          <span class="error">* please specify street address</span> 
        </c:if>
        <br/>
      </td>
    </tr>
    <tr>
      <th scope="row">street 2:</th>
      <td>
        <html:text property="street2" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*city:</th>
      <td>
        <html:text property="city" size="30" maxlength="30"/>
        <c:if test="${DFBS_PERMIT_ERROR.city == 'ERROR'}">
          <br/>
          <span class="error">* please specify city</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*state:</th>
      <td>
        <html:select property="state">
          <html:option value="IN">INDIANA</html:option>
          <html:options collection="DFBS_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${DFBS_PERMIT_ERROR.state == 'ERROR'}">
          <br/>
          <span class="error">* please select a state</span> 
        </c:if>
        <c:if test="${DFBS_PERMIT_ERROR.state == 'ERROR2'}">
          <br/>
          <span class="error">* please select &quot;INDIANA&quot; as state</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*zip:</th>
      <td>
        <html:text property="zip" size="5" maxlength="5"/>
        <c:if test="${DFBS_PERMIT_ERROR.zip == 'ERROR'}">
          <br/>
          <span class="error">* please specify zip code</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*county:</th>
      <td>
        <html:select property="county" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">-----</html:option>
          <html:options collection="DFBS_COUNTY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${DFBS_PERMIT_ERROR.county == 'ERROR'}">
          <br/>
          <span class="error">* please specify county</span> 
        </c:if> 
      </td>
    </tr>
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
              <th scope="row" class="required" >*fee Status:</th>
              <td>
                <html:radio property="feeExempt" value="E" styleClass="switch" />Fee Exempt ( Needs IRS Letter)
                <br/>
                <html:radio property="feeExempt" value="N" styleClass="switch" />Non Exempt
                <br/>
                 <c:if test="${DFBS_PERMIT_ERROR.feeExempt == 'ERROR'}">
                 <br/>
                <span class="error">* please specify fee exempt</span> 
                </c:if>
                 <br/>
                  <p class="messageBox"><span class="error"><b><u>Important Note:</u> If you are claiming fee exempt status, you must provide a valid 501C. 
                  If you have questions contact <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/></A> :at <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Phone}"/> or
                  <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/></A> :at <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Phone}"/> with details after submitting the application.
                  If you have a electronic copy of the letter please upload it as a document when you upload the floor plan.</b></span></p>
     
              </td>
    </tr>
            
          </tbody>
        </table>
         <p>
          <html:submit value="save to your application" styleClass="button"/>
        </p>
        
    </html:form>
    <script type="text/javascript">
      setSelectValue('SELECT_COUNTY','COUNTY_NAME');
      showMiscRow();
    </script>
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
