<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<html:xhtml/><c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Variance Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">

  <div id="leftContentFluid">
  <h1><b><u>View Owner</u></b></h1></br> </br> 
<h2>Applicant Information(Person who would be in violation if variance is not granted;usually this is the owner)</h2> 

  <div class="styledBox">
 <html:form action="/owner" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateOwner"/> 
      <html:hidden property="ownerId"/> 
       
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
   
        <tr>
          <th scope="row"      >  applicant last name:</th>
          <td>
           <html:text property="ownerLastName" size="50" maxlength="60"/>
               
            </td>
          </th>
        </tr>
         <tr>
         <th scope="row"      >  applicant first name:</th>
         <td>
           <html:text property="ownerFirstName" size="30" maxlength="30"/>
            
            </td>
          </th>
        </tr>
         <tr>
          <th  > applicant middle intial </th>
          <td>
           <html:text property="ownerMI" size="1" maxlength="1"/>  
           
           </td>
        </tr>
         <tr>
          <th scope="row"    class="required" > applicant organization name:</th>
          <td>
           <html:text property="ownerDBA" size="50" maxlength="80"/>
                 <c:if test="${OWNER_ERROR.ownerDBA == 'ERROR'}">
              <br/><span class="error">* please enter DBA  </span> 
            </c:if>
          </td> 
          </th>
        </tr>
         <tr>
         <th scope="row"  class="required"    > * applicant address1:</th>
         <td>
           <html:text property="ownerAddress1" size="30" maxlength="30"/>
            <c:if test="${OWNER_ERROR.ownerAddress1 == 'ERROR'}">
              <br/><span class="error">* please enter address1  </span> 
            </c:if>
            </td>
          </th>
        </tr>
        <tr>
          <th scope="row"     >  applicant address2:</th>
          <td>
           <html:text property="ownerAddress2" size="30" maxlength="30"/>
          </td> 
          </th>
        </tr>
         <tr>
         <th scope="row"  class="required"    > * applicant city:</th>
         <td>
           <html:text property="ownerCity" size="30" maxlength="30"/>
            <c:if test="${OWNER_ERROR.ownerCity == 'ERROR'}">
              <br/><span class="error">* please enter city  </span> 
            </c:if>
            </td>
          </th>
        </tr>
         <tr>
      <th scope="row"    class="required" > *  applicant state:</th>
      <td>
          <html:select property="ownerStateId" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','STATE_ID')">
          <html:option value = "0" >Please Select</html:option>
          <html:options collection="OWNER_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${OWNER_ERROR.ownerStateId == '0' }">
          <br/>
          <span class="error">* please specify state</span> 
        </c:if> 
       
       </td>
    </tr>
        <tr>
         <th scope="row"  class="required"    > * applicant zip:</th>
         <td>
           <html:text property="ownerZip" size="5" maxlength="5"/>
            <c:if test="${OWNER_ERROR.ownerZip == 'ERROR'}">
              <br/><span class="error">* please enter zip  </span> 
            </c:if>
            
            </td>
          </th>
        </tr>
        <tr>
          <th scope="row" class="required"> * applicant phone:</th>
          <td>
            <html:text property="ownerPhone" size="10" maxlength="10"/>
            <c:if test="${OWNER_ERROR.ownerPhone == 'ERROR'}">
              <br/><span class="error">* please enter phone  </span> 
            </c:if>
           </td>
        </tr>
        <tr>
          <th scope="row">applicant fax:</th>
          <td>
           <html:text property="ownerFax" size="10" maxlength="10"/>
          </td>
        </tr>
        <tr>
         <th scope="row"  class="required"   >* applicant email:</th>
          <td>
            <html:text property="ownerEmail" size="50" maxlength="80"/>
          
          <c:if test="${OWNER_ERROR.ownerEmail == 'ERROR'}">
              <br/><span class="error">* please enter email in correct format  </span> 
            </c:if>
            </td>
        </tr>
        <tr>
     <th scope="row"     > </th>
    <td>

       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelVar != null }">  
              <html:submit value="Update" styleClass="button" />
       </c:if>
     
     </td>
     </tr> 
  </tbody>
   </table>
   </html:form>
   </div>
   </div>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>
