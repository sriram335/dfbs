<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="Elevator Service Contractor " />
<c:set var="title" scope="request" value="Update Elevator Service Contractor" />

<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
  <jsp:include page="shoppingCartCont.jsp"/>
</div>
 <a   href="/dfbs/elevator/servContractor.do">
             [back to start]</a>  
<div>
 <h2>Add New Elevator Service Contractor</h2> 
 <html:form action="/servContractor" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveNewContractor"/> 
         <html:hidden property="contId"/> 
         <html:hidden property="licNumber"/> 
         <html:hidden property="contLicStatus"/> 
         <html:hidden property="contFee"/> 
         <html:hidden property="contIssueDate"/> 
         <html:hidden property="contExpDate"/> 
         <html:hidden property="contLetterDate"/> 
         <html:hidden property="contPrintDate"/> 
         <html:hidden property="contComplaints"/> 
         <html:hidden property="contViolations"/> 
         <html:hidden property="contConvictions"/> 
         <html:hidden property="contParentId"/>
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     <tr>
      <th scope="row" class="required">* First Name :</th>
      <td>
         <html:text property="contFirstName" size="30" maxlength="30"/>
       </td>
      </tr>
     <tr>
      <th scope="row" class="required">*Last Name :</th>
      <td>
        <html:text property="contLastName" size="30" maxlength="30"/>
       </td>
      </tr>
     <tr>
      <th scope="row">Middle Name :</th>
      <td>
        <html:text property="contFirstName" size="1" maxlength="1"/>
       </td>
      </tr>
     
     <tr>
      <th scope="row" class="required">*License Type:</th>
      <td>
               <html:select  property="contType" >
                <html:option value="">-----</html:option>
                <html:options collection="CON_TYPE_OPTIONS" property="value" labelProperty="label"/>
              </html:select>
              <c:if test="${SERVICE_CONTRACTOR_ERROR.contType == 'ERROR'}">
                <br/>
                <span class="error">* please select contractor type</span> 
              </c:if>
       </td>
      </tr>
    
  <tr>
      <th scope="row" class="required" >* Address1:</th>
      <td>
       <html:text property="contAddress1" size="30" maxlength="30"/>
       <c:if test="${SERVICE_CONTRACTOR_ERROR.contAddress1 == 'ERROR'}">
                <br/>
                <span class="error">* please specify address1</span> 
              </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row" >Address2:</th>
      <td>
       <html:text property="contAddress2" size="30" maxlength="30"/>
      </td>
    </tr>
     <tr>
      <th scope="row" class="required" >* City:</th>
      <td>
       <html:text property="contCity" size="30" maxlength="30"/>
       <c:if test="${SERVICE_CONTRACTOR_ERROR.contCity == 'ERROR'}">
                <br/>
                <span class="error">* please specify city</span> 
              </c:if>
      </td>
    </tr>
     <tr>
            <th scope="row" class="required">*state:</th>
            <td>
              <html:select  property="contState" >
                <html:option value="">-----</html:option>
                <html:options collection="DFBS_STATE_OPTIONS" property="value" labelProperty="label"/>
              </html:select>
              <c:if test="${SERVICE_CONTRACTOR_ERROR.contState == 'ERROR'}">
                <br/>
                <span class="error">* please select a state</span> 
              </c:if>
            </td>
          </tr>
     <tr>
            <th scope="row" class="required">* zip:</th>
            <td>
              <html:text property="contZip" size="5" maxlength="5" />
              <c:if test="${SERVICE_CONTRACTOR_ERROR.contZip == 'ERROR'}">
                <br/>
                <span class="error">* please specify zip code</span> 
              </c:if>
            </td>
          </tr>
          <tr>
            <th scope="row" >* zip2:</th>
            <td>
              <html:text property="contZip2" size="5" maxlength="5" />
              
            </td>
          </tr>
          <tr>
            <th scope="row" class="required">* phone:</th>
            <td>
              <html:text property="contPhone" size="10" maxlength="10" />
              <c:if test="${SERVICE_CONTRACTOR_ERROR.contPhone == 'ERROR'}">
                <br/>
                <span class="error">* please specify a phone number  in the correct format</span> 
              </c:if>
            </td>
          </tr>
          <tr>
            <th scope="row">fax:</th>
            <td>
              <html:text property="contFax" size="10" maxlength="10" />
            </td>
          </tr>
          <tr>
            <th scope="row" class="required">*email:</th>
            <td>
              <html:text property="contEmail" size="50" maxlength="80"/>
              <c:if test="${SERVICE_CONTRACTOR_ERROR.contEmail == 'ERROR'}">
                <br/>
                <span class="error">* please specify email address</span> 
              </c:if>
            </td>
          </tr>
   
    <tr>
            <th scope="row" class="required"></th>
            <td>
             <p class="message">
          <html:submit value="Save and Apply " styleClass="button"/>
        </p>
            </td>
          </tr>
   </tbody>
        </table>
        
   </html:form>   
   
</div>