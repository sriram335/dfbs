<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<html:xhtml/><c:set var="module" scope="request" value="Fire Display Permits" />
<c:set var="title" scope="request" value="Owner Information" />
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Add Owner Information</h2> 
<html:link page="/start.do">[back to start]</html:link>
<div id="sideContentFluid">

</div>
  <div class="styledBox">
 <html:form action="/application" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveNewOwner"/> 
      <html:hidden property="ownerId"/> 
       
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
   
        <tr>
          <th scope="row"  class="required"    > * owner last name:</th>
          <td>
           <html:text property="ownerLastName" size="60" maxlength="60"/>
                 <c:if test="${OWNER_ERROR.ownerLastName == 'ERROR'}">
              <br/><span class="error">* please enter last name  </span> 
            </c:if>
            </td>
          </th>
        </tr>
         <tr>
         <th scope="row"  class="required"    > * owner first name:</th>
         <td>
           <html:text property="ownerFirstName" size="30" maxlength="30"/>
            <c:if test="${OWNER_ERROR.ownerFirstName == 'ERROR'}">
              <br/><span class="error">* please enter first name  </span> 
            </c:if>
            </td>
          </th>
        </tr>
         <tr>
          <th  > owner middle intial </th>
          <td>
           <html:text property="ownerMI" size="1" maxlength="1"/>    
           </td>
        </tr>
         <tr>
          <th scope="row"     >  doing business as:</th>
          <td>
           <html:text property="ownerDBA" size="80" maxlength="80"/>
          </td> 
          </th>
        </tr>
         <tr>
         <th scope="row"  class="required"    > * owner address1:</th>
         <td>
           <html:text property="ownerAddress1" size="30" maxlength="30"/>
            <c:if test="${OWNER_ERROR.ownerAddress1 == 'ERROR'}">
              <br/><span class="error">* please enter address1  </span> 
            </c:if>
            </td>
          </th>
        </tr>
        <tr>
          <th scope="row"     >  address2:</th>
          <td>
           <html:text property="ownerAddress2" size="30" maxlength="30"/>
          </td> 
          </th>
        </tr>
         <tr>
         <th scope="row"  class="required"    > * owner city:</th>
         <td>
           <html:text property="ownerCity" size="30" maxlength="30"/>
            <c:if test="${OWNER_ERROR.ownerCity == 'ERROR'}">
              <br/><span class="error">* please enter city  </span> 
            </c:if>
            </td>
          </th>
        </tr>
         <tr>
      <th scope="row"    class="required" > *  state:</th>
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
         <th scope="row"  class="required"    > * owner zip:</th>
         <td>
           <html:text property="ownerZip" size="5" maxlength="5"/>
            <c:if test="${OWNER_ERROR.ownerZip == 'ERROR'}">
              <br/><span class="error">* please enter zip  </span> 
            </c:if>
            
            </td>
          </th>
        </tr>
        <tr>
          <th scope="row" class="required"> * phone:</th>
          <td>
            <html:text property="ownerPhone" size="10" maxlength="10"/>
            <c:if test="${OWNER_ERROR.ownerPhone == 'ERROR'}">
              <br/><span class="error">* please enter phone  </span> 
            </c:if>
           </td>
        </tr>
        <tr>
          <th scope="row">fax:</th>
          <td>
           <html:text property="ownerFax" size="10" maxlength="10"/>
          </td>
        </tr>
        <tr>
         <th scope="row"  class="required"   >* email:</th>
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

       
              <html:submit value="Next" styleClass="button" />
     
     </td>
     </tr> 
  </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>
