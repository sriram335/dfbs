<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Complete Organization Information</h2> 
<div id="sideContentFluid">
<a   href="/dfbs/hazmat/hazmat.do?">
              [Cancel]</a>
</div>
  <div class="styledBox">
 <html:form action="/hazmat" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="savePermit"/> 
      <html:hidden property="orgId"/> 
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * organization name:</th>
      <td>
       <html:text property="orgName" size="30" maxlength="30"/>
       <c:if test="${HAZMAT_ORG_ERROR.orgName == 'ERROR'}">
          <br/>
          <span class="error">* please enter organization name</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"    class="required" > * organization address1:</th>
      <td>
       <html:text property="orgAddress1" size="30" maxlength="30"/>
       <c:if test="${HAZMAT_ORG_ERROR.orgAddress1 == 'ERROR'}">
          <br/>
          <span class="error">* please enter organization address1</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"     > organization address2:</th>
      <td>
       <html:text property="orgAddress2" size="30" maxlength="30"/>
       </td>
    </tr>
    <tr>
      <th scope="row"    class="required" > * organization city:</th>
      <td>
       <html:text property="orgCity" size="30" maxlength="30"/>
       <c:if test="${HAZMAT_ORG_ERROR.orgCity == 'ERROR'}">
          <br/>
          <span class="error">* please enter organization city</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"     > * organization state:</th>
      <td>
          <html:select property="orgState" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','STATE_INITIAL')">
          <html:option value="00">Please Select</html:option>
          <html:options collection="ORG_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>(Required for USA, others can leave it blank)
       <c:if test="${HAZMAT_ORG_ERROR.orgState == 'ERROR'}">
          <br/>
          <span class="error">* please specify state</span> 
        </c:if> 
       </td>
    </tr>
    <tr>
      <th scope="row"    class="required" > * organization zip/pin:</th>
      <td>
       <html:text property="orgZip" size="10" maxlength="10"/>
       <c:if test="${HAZMAT_ORG_ERROR.orgZip == 'ERROR'}">
          <br/>
          <span class="error">* please enter organization zip</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"     > organization zip2:</th>
      <td>
       <html:text property="orgZip2" size="4" maxlength="4"/>
       </td>
    </tr>
     <tr>
      <th scope="row"     > organization county:</th>
      <td>
       <html:text property="orgCounty" size="30" maxlength="30"/>
       </td>
    </tr>
     <tr>
      <th scope="row"  class="required"   > * organization contact name:</th>
      <td>
       <html:text property="orgContact" size="30" maxlength="30"/>
        <c:if test="${HAZMAT_ORG_ERROR.orgContact == 'ERROR'}">
          <br/>
          <span class="error">* please enter contact name</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"     > organization contact title:</th>
      <td>
       <html:text property="orgTitle" size="50" maxlength="50"/>
       </td>
    </tr>
     <tr>
      <th scope="row"     class="required"> * organization contact phone:</th>
      <td>
       <html:text property="orgPhone" size="10" maxlength="10"/>
       <c:if test="${HAZMAT_ORG_ERROR.orgPhone == 'ERROR'}">
          <br/>
          <span class="error">* phone format error</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"    > organization contact fax:</th>
      <td>
       <html:text property="orgFax" size="10" maxlength="10"/>
       <c:if test="${HAZMAT_ORG_ERROR.orgFax == 'ERROR'}">
          <br/>
          <span class="error">* fax format error</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"  class="required"   > * organization contact email:</th>
      <td>
       <html:text property="orgEmail" size="50" maxlength="50"/>
        <c:if test="${HAZMAT_ORG_ERROR.orgEmail == 'ERROR'}">
          <br/>
          <span class="error">* please enter email address</span> 
        </c:if>
       </td>
    </tr>
     
    
     
     <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="Save" styleClass="button" />
     
     </td>
     </tr>
   </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>