 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="Fire Display Permits" />
<c:set var="title" scope="request" value="Contact Information" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Verify and update owner contact Information</h2> 
<a   href="/dfbs/childCare/start.do">
              [back to start]</a>
<a   href="/dfbs/childCare/start.do?method=renewBy">
              [back to search]</a>
<a   href="/dfbs/display/ownerListDisplay.do?method=renew&ownerId=<c:out value="${OWNER_SELECTED.ownerId}"/>">
              [back to owner]</a>
<div id="sideContentFluid">

</div>
<div id="leftContentFluid">
  <div class="styledBox">
 <html:form action="/contact" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="renewPermit"/> 
      <html:hidden property="ownerId"/> 
      <html:hidden property="personId"/>
       <html:hidden property="addressId"/>
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
     <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >* person first name:</th>
      <td>
       <html:text property="personFirstName" size="30" maxlength="30"/>
        <c:if test="${PERSON_ERROR.personFirstName == 'ERROR'}">
          <br/>
          <span class="error">* please enter person first name</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* person last name:</th>
      <td>
       <html:text property="personLastName" size="30" maxlength="30"/>
        <c:if test="${PERSON_ERROR.personLastName == 'ERROR'}">
          <br/>
          <span class="error">* please enter person last name</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > person middle intial:</th>
      <td>
       <html:text property="personMi" size="1" maxlength="1"/>
        
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required"   >* person address1:</th>
      <td>
       <html:text property="personAddress1" size="30" maxlength="30"/>
       <c:if test="${PERSON_ERROR.personAddress1 == 'ERROR'}">
          <br/>
          <span class="error">* please enter address1</span> 
        </c:if> 
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > person address2:</th>
      <td>
       <html:text property="personAddress2" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required"    > * person city:</th>
      <td>
       <html:text property="personCity" size="30" maxlength="30"/>
       <c:if test="${PERSON_ERROR.personCity == 'ERROR'}">
          <br/>
          <span class="error">* please enter city</span> 
        </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"   class="required"   >  * state:</th>
      <td>
          <html:select property="personStateId" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','STATE_ID')">
          <html:option value="15">INDIANA</html:option>
          <html:options collection="PERSON_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${PERSON_ERROR.personStateId == 0}">
          <br/>
          <span class="error">* please enter state</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   class="required"  > * person zip:</th>
      <td>
       <html:text property="personZip" size="5" maxlength="5"/>
        <c:if test="${PERSON_ERROR.personZip == 'ERROR'}">
          <br/>
          <span class="error">* please enter zip</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > person zip2:</th>
      <td>
       <html:text property="personZip2" size="30" maxlength="30"/>
       
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   class="required"  > * person email:</th>
      <td>
       <html:text property="personEmail" size="100" maxlength="100"/>
       <c:if test="${PERSON_ERROR.personEmail == 'ERROR'}">
          <br/>
          <span class="error">* please enter email</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > person fax:</th>
      <td>
       <html:text property="personFax" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required" > * person phone:</th>
      <td>
       <html:text property="personPhone" size="10" maxlength="10"/>
       <c:if test="${PERSON_ERROR.personPhone == 'ERROR'}">
          <br/>
          <span class="error"> phone format error</span> 
        </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > person type:</th>
      <td>
       <html:text property="personType" size="30" maxlength="30"/>
      </td>
    </tr>
     
    <tr>
    <td>
   
  </tbody>
        </table>
        <p>
          <html:submit value="Next " styleClass="button"/>
        
        </p>
    </td>
    </tr>
    </html:form>   
</div>
 
