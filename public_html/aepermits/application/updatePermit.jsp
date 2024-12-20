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
 <div id="rightContentFluid">
    <jsp:include page="newPermits.jsp"/>
  </div>
<div id="mainBox">
<b><u>Files Uploaded:</u></b></br>
<c:forEach var="file" items="${sessionScope.UPDATE_PERMIT.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp;  uploaded on :<c:out value="${file.fileDateString}"/><br/> 
      
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null}">  
       <a   href="/dfbs/aepermits/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a></br>
        </c:if>
         
        <c:if test="${sessionScope.DFBS_SECURITY.userEmail !=null &&(sessionScope.DFBS_SECURITY.userEmail == sessionScope.APPLICATION_CONTACTS.contact1Email ||sessionScope.DFBS_SECURITY.userEmail == sessionScope.APPLICATION_CONTACTS.contact2Email)}">
           <a href="/dfbs/aepermits/permit.do?method=deleteFile&fileId=<c:out value="${file.fileId}"/>"><FONT color="#ff0000">[delete this file, can not be recovered if deleted]</FONT></a> </br>
          </c:if>
          </td>
        <td>
</tr>
</c:forEach>
  <h2>Update Permit</h2>
  <div id="rightContentFluid">
    <p class="control2">
   <a   href="/dfbs/aepermits/start.do?method=renewByPermitNumber&permitNumber=<c:out value="${session.UPDATE_PERMIT.permitNumber}"/>"> cancel</a>
    </p>
  </div>
  
  <div id="leftContentFluid">

    <html:form action="/permit" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveUpdatePermit"/>
      <html:hidden property="ownerId"/>
      <html:hidden property="applicationKey"/>
       <html:hidden property="permitNumber"/>
       <html:hidden property="applicationDate"/>
      <html:hidden property="county" styleId="COUNTY_NAME"  />
     
      
        <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
     
           <tr>
      <th scope="row" style="width : 50%;">permit number:</th>
      <td style="font-size:medium;font-weight:bold">
      <c:out value="${UPDATE_PERMIT.permitNumber}"/>
      </td>
    </tr>
    <tr>
      <th > <c:if test="${UPDATE_PERMIT.permitType != 'Y' && UPDATE_PERMIT.permitType != 'SU'}">
           <input type="checkbox" name="permitType"  class="switch"  value="Y"/> 
      </c:if>
       <c:if test="${UPDATE_PERMIT.permitType == 'Y' ||UPDATE_PERMIT.permitType == 'SU'}">
              <input type="checkbox" name="permitType"  class="switch"  value="Y" checked/>
       </c:if> </th>
      <td style="color:red">
     <b>Is any stage to be used</b> 
    
      </td>
    </tr> 
             <tr>
      <th scope="row" style="width : 50%;">Application Date:</th>
      <td style="font-size:medium;font-weight:bold">
      <c:out value="${UPDATE_PERMIT.applicationDateString}"/>
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
    <html:text property="emailAddress" size="30" maxlength="60"/><br/>email not mandatory, but if you provide email we will email you the permit once it is approved. 
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
      <th scope="row" >comments(max 1000 characters):</th>
      <td>
        <html:textarea property="notes" rows="10" cols="70" />
        <c:if test="${DFBS_PERMIT_ERROR.notes == 'ERROR'}">
                 <br/>
                <span class="error">* Notes field more than 1000 characters</span> 
                </c:if>
       </td>
    </tr>
    <tr>
              <th scope="row" class="required" >*fee Status:</th>
              <td>
                <html:radio property="feeExempt" value="E" styleClass="switch" />Fee Exempt ( Needs IRS Letter).Please upload electronic copy of 501c letter using upload facility  floor plan option.
                <br/>
                <html:radio property="feeExempt" value="N" styleClass="switch" />Non Exempt
                <br/>
                 <c:if test="${DFBS_PERMIT_ERROR.feeExempt == 'ERROR'}">
                 <br/>
                <span class="error">* please specify fee exempt</span> 
                </c:if>
                
              </td>
    </tr>
             
           <tr>
      <th scope="row" >online status:</th>
      <td >
      <html:select property="status" >
          <html:option value="">Please Select</html:option>
          <html:option value="NEW">NEW</html:option>
          <html:option value="APPROVED">APPROVED</html:option>
          <html:option value="DENIED">DENIED</html:option>
          <html:option value="PENDING">PENDING</html:option>
           <html:option value="PENDING">EXPIRED</html:option>
        </html:select>  
      </td>
    </tr>    
          </tbody>
        </table>
           <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null }">   
  
  
         <p>
          <html:submit value="update" styleClass="button"/>
        </p>
        </c:if> 
    </html:form>
    <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null }">   
     <html:form action="/permit" method="post">
        <input type="hidden" name="method" id="METHOD_KEY"  value="emailInspector"/>
            <p>
              <input type="submit" value="Email Inspector" class="button"/></br></br></br>
            </p>
             <c:if test="${PERMIT_INSP_EMAIL == 'Y'}">
              <p class="message">
              Email Sent!
              </p>
              </c:if>
        </html:form>
         <html:form action="/permit" method="post">
        <input type="hidden" name="method" id="METHOD_KEY"  value="showNewPermits"/>
            <p>
              <input type="submit" value="Show New Permits" class="button"/></br></br></br>
            </p>
            
        </html:form>
        <html:form action="/permit" method="post">
        <input type="hidden" name="method" id="METHOD_KEY"  value="emailPerson"/>
            <p>
            <html:text property="emailTo" size="30" maxlength="100"/>
              <input type="submit" value="send email" class="button"/></br></br></br>
            </p>
             <c:if test="${PERMIT_EMAIL_SENT == 'Y'}">
              <p class="message">
              Email Sent!
              </p>
              </c:if>
        </html:form>
   <%--      <html:form action="/permit" method="post">
        <input type="hidden" name="method" id="METHOD_KEY"  value="emailDenial"/>
            <p>
            <html:text property="emailTo" size="30" maxlength="100"/>
              <input type="submit" value="send denial email" class="button"/></br></br></br>
            </p>
             <c:if test="${DENIAL_EMAIL_SENT == 'Y'}">
              <p class="message">
              Denial Email Sent!
              </p>
              </c:if>
        </html:form> --%>
         <a  href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_ae_denial_letter.rdf&p_id=<c:out value="${sessionScope.UPDATE_PERMIT.permitNumber}"/>" target="_blank" >[print denial letter]</a></br>
  
     </c:if>
    <script type="text/javascript">
      setSelectValue('SELECT_COUNTY','COUNTY_NAME');
      showMiscRow();
    </script>
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>

