<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Cigarette Brand Information"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">


  <div class="styledBox">
 <div id="mainContentFluid" align="left">

  
 <h2>Add / Edit Cigarette Brand Family Information</h2> 
<b>Company Name: <c:out value="${CIGARETTE_COMPANY.companyName}"/></b></br>
 <html:form action="/brand" method="post">
      <input type="hidden" name="method" id="METHOD_KEY2" value="saveBrand3YearRenewal"/> 
      <html:hidden property="cigaretteId"/> 
      
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    <td>
     <p class="messageBox">
          If you do not see any of your company brand(s). Contact <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>"  href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/>  </A>
          or <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>"  href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/>  </A> with details. Inform
          your company name, brand name to add in the email.
          </p> 
      </td>
    </tr>
    
    <tr>
      <th scope="row"  class="required"    > * brand&nbsp;family :</th>
      <td>
     <html:select property="cigaretteBrand" styleId="SELECT_BRAND" onchange="setSelectValue('SELECT_FLAVOR','ABBREVIATION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="CIG_BRANDS_OPTIONS" property="value" labelProperty="label"/>
        </html:select></br>
      <c:if test="${CIGARETTE_BRAND_ERROR.cigaretteBrand == 'ERROR'}">
          <br/>
          <span class="error">* please specify cigarette brand family</span> 
        </c:if> 
        </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * style :</th>
      <td>
       <html:text property="cigaretteStyle" size="50" maxlength="50"/>
       <c:if test="${CIGARETTE_BRAND_ERROR.cigaretteStyle == 'ERROR'}">
          <br/>
          <span class="error">* please enter brand style</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * marking :</th>
      <td>
       <html:text property="cigaretteMarking" size="30" maxlength="30"/>
       <c:if test="${CIGARETTE_BRAND_ERROR.cigaretteMarking == 'ERROR'}">
          <br/>
          <span class="error">* please enter marking</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * length (in mm):</th>
      <td>
       <html:text property="cigaretteLength" size="6" maxlength="6"/>
       <c:if test="${CIGARETTE_BRAND_ERROR.cigaretteLength == 1}">
          <br/>
          <span class="error">* please enter length</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * circumference (in mm) :</th>
      <td>
       <html:text property="cigaretteCircumference" size="6" maxlength="6"/>
       <c:if test="${CIGARETTE_BRAND_ERROR.cigaretteCircumference == 1}">
          <br/>
          <span class="error">* please enter circumference</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * flavor:</th>
      <td>
       <html:select property="cigaretteFlavor" styleId="SELECT_FLAVOR" onchange="setSelectValue('SELECT_FLAVOR','ABBREVIATION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="CIG_FLAVOR_OPTIONS" property="value" labelProperty="label"/>
        </html:select></br>(Choose Other if not in list)
          <c:if test="${CIGARETTE_BRAND_ERROR.cigaretteFlavor == 'ERROR'}">
          <br/>
          <span class="error">* please specify cigarette flavor</span> 
        </c:if> 
       </td>
    </tr>
     <tr>
      <th scope="row"     >  If other flavor details:</th>
      <td>
       <html:text property="cigaretteFlavorOther" size="50" maxlength="50"/>
       </td>
    </tr>
    <tr>
      <th scope="row"  class="required"    > * filter:</th>
      <td>
       <html:select property="cigaretteFilter" styleId="SELECT_FILTER" onchange="setSelectValue('SELECT_FILTER','ABBREVIATION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="CIG_FILTER_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${CIGARETTE_BRAND_ERROR.cigaretteFilter == 'ERROR'}">
          <br/>
          <span class="error">* please specify cigarette filter</span> 
        </c:if> 
       </td>
    </tr>
      <tr>
      <th scope="row"  class="required"    > * package:</th>
      <td>
       <html:select property="cigarettePackage" styleId="SELECT_PACKAGE" onchange="setSelectValue('SELECT_PACKAGE','ABBREVIATION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="CIG_PACKAGE_OPTIONS" property="value" labelProperty="label"/>
         </html:select>
       <c:if test="${CIGARETTE_BRAND_ERROR.cigarettePackage == 'ERROR'}">
          <br/>
          <span class="error">* please specify cigarette package</span> 
        </c:if> 
       </td>
    </tr>
  
    
     <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="Save Cigarette Brand" styleClass="button" />
     
     </td>
     </tr>
   </tbody>
   </table>
   </html:form>
   </div>
   </div>
 
 </div>  
  <div class="clearer">&nbsp;</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>


