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
 <b><u>Files Uploaded:</u></b></br>
<c:forEach var="file" items="${sessionScope.SERVICE_CONTRACTOR_SELECTED.fileList}" varStatus="i">
<table cellspacing="0" style="width: 75%;" summary="sales">
<tr>
    <th>Files </th>
  </tr>
  <tbody>
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelElev != null}">  
       <a   href="/dfbs/elevator/elevator.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a></br>
        </c:if>
          </td>
        <td>
</tr>
</tbody>
</table>
</c:forEach>
</div>
 <a   href="/dfbs/elevator/servContractor.do">
             [back to start]</a>  
             <a   href="/dfbs/elevator/servContractor.do?method=emailLicence">
             [Email Licence]</a>
<div>
 <h2>Update Elevator Service Contractor</h2> </br>
 
 <html:form action="/servContractor" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveUpdate"/> 
         <html:hidden property="contId"/> 
         
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
        <html:text property="contMI" size="1" maxlength="1"/>
       </td>
      </tr>
      <tr>
      <th scope="row">License Number :</th>
      <td>
        <html:text property="licNumber" size="30" maxlength="30"/>
       </td>
      </tr>
     <tr>
      <th scope="row" class="required">*License Type:</th>
      <td>
               <html:select  property="contType" >
                <html:option value="">-----</html:option>
                <html:options collection="CON_TYPE_OPTIONS" property="value" labelProperty="label"/>
              </html:select>
              <c:if test="${SERVICE_CONTRACTOR_ERROR.contState == 'ERROR'}">
                <br/>
                <span class="error">* please select a state</span> 
              </c:if>
       </td>
      </tr>
     <tr>
      <th scope="row" class="required">*License Status:</th>
      <td>
               <html:select  property="contLicStatus" >
                <html:option value="">-----</html:option>
                <html:options collection="CON_STATUS_OPTIONS" property="value" labelProperty="label"/>
              </html:select>
              <c:if test="${SERVICE_CONTRACTOR_ERROR.contState == 'ERROR'}">
                <br/>
                <span class="error">* please select a state</span> 
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
              <c:if test="${SERVICE_CONTRACTOR_ERROR.zip == 'ERROR'}">
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
              <c:if test="${SERVICE_CONTRACTOR_ERROR.phoneNumber == 'ERROR'}">
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
              <c:if test="${SERVICE_CONTRACTOR_ERROR.ownerEmail == 'ERROR'}">
                <br/>
                <span class="error">* please specify email address</span> 
              </c:if>
            </td>
          </tr>
           <tr>
            <th scope="row">issue date:</th>
            <td>
              <html:text property="contIssueDate" size="10" maxlength="10" />
            </td>
          </tr>
         <tr>
            <th scope="row">expiration date:</th>
            <td>
              <html:text property="contExpDate" size="10" maxlength="10" />
            </td>
          </tr>
          <tr>
            <th scope="row">certificate print date:</th>
            <td>
              <html:text property="contPrintDate" size="10" maxlength="10" />
            </td>
          </tr>
          <tr>
            <th scope="row">letter print date:</th>
            <td>
              <html:text property="contLetterDate" size="10" maxlength="10" />
            </td>
          </tr>
           <tr>
            <th scope="row">complaints:</th>
            <td>
              <html:text property="contComplaints" size="10" maxlength="10" />
            </td>
          </tr>
           <tr>
            <th scope="row">violations:</th>
            <td>
              <html:text property="contViolations" size="10" maxlength="10" />
            </td>
          </tr>
          <tr>
            <th scope="row">convictions:</th>
            <td>
              <html:text property="contConvictions" size="10" maxlength="10" />
            </td>
          </tr>
          <tr>
            <th scope="row" class="required">*parent organization:</th>
            <td>
              <html:select  property="contParentId" >
                <html:option value="">-----</html:option>
                <html:options collection="CON_PARENT_OPTIONS" property="value" labelProperty="label"/>
              </html:select>
              <c:if test="${SERVICE_CONTRACTOR_ERROR.contState == 'ERROR'}">
                <br/>
                <span class="error">* please select a state</span> 
              </c:if>
            </td>
          </tr>
    <tr>
            <th scope="row" class="required"></th>
            <td>
             <p class="message">
          <html:submit value="Save and Renew " styleClass="button"/>
        </p>
            </td>
          </tr>
   </tbody>
        </table>
        
   </html:form>   
   
</div>