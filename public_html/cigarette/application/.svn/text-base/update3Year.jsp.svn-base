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
<h2>update Cigarette Brand Information</h2> 
 <div class="styledBox">
 <div id="mainContentFluid" align="left">
 <html:form action="/brand" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveUpdatedBrand3Year"/> 
      <html:hidden property="cigaretteId"/> 
      <html:hidden property="applicationId"/>
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <tr>
      <th scope="row"  class="required"    > * brand :</th>
      <td>
       <html:text property="cigaretteBrand" size="30" maxlength="30"/></br>
       (always spell correctly and in same format since fee is charged per brand)
       <c:if test="${CIGARETTE_BRAND_ERROR.cigaretteBrand == 'ERROR'}">
          <br/>
          <span class="error">* please enter brand name</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * style :</th>
      <td>
       <html:text property="cigaretteStyle" size="30" maxlength="30"/>
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
        </html:select>
       <c:if test="${CIGARETTE_BRAND_ERROR.cigaretteFlavor == 'ERROR'}">
          <br/>
          <span class="error">* please specify cigarette flavor</span> 
        </c:if> 
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
          <span class="error">* please specify radiation level</span> 
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
 <%--    <tr>
      <th scope="row"  class="required"    > * test lab name:</th>
      <td>
       <html:text property="testLabName" size="30" maxlength="30"/>
       <c:if test="${CIGARETTE_BRAND_ERROR.testLabName == 'ERROR'}">
          <br/>
          <span class="error">* please enter test lab name</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"  class="required"    > * test date:</th>
      <td>
       <html:text property="testDate" size="10" maxlength="10"/>(mm/dd/yyyy)
       <c:if test="${CIGARETTE_BRAND_ERROR.testLabName == 'ERROR'}">
          <br/>
          <span class="error">* please enter test lab name</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"    class="required" > *  address1:</th>
      <td>
       <html:text property="address1" size="30" maxlength="30"/>
       <c:if test="${CIGARETTE_BRAND_ERROR.address1 == 'ERROR'}">
          <br/>
          <span class="error">* please enter  address1</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"     >  address2:</th>
      <td>
       <html:text property="address2" size="30" maxlength="30"/>
       </td>
    </tr>
    <tr>
      <th scope="row"    class="required" > *  city:</th>
      <td>
       <html:text property="city" size="30" maxlength="30"/>
       <c:if test="${CIGARETTE_BRAND_ERROR.city == 'ERROR'}">
          <br/>
          <span class="error">* please enter  city</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"    class="required" > *  state:</th>
      <td>
          <html:select property="state" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','STATE_INITIAL')">
          <html:option value="00">Please Select</html:option>
          <html:options collection="BRAND_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${CIGARETTE_BRAND_ERROR.state == 'ERROR'}">
          <br/>
          <span class="error">* please specify state</span> 
        </c:if> 
       </td>
    </tr>
    <tr>
      <th scope="row"    class="required" > *  zip:</th>
      <td>
       <html:text property="zip" size="5" maxlength="5"/>
       <c:if test="${CIGARETTE_BRAND_ERROR.zip == 'ERROR'}">
          <br/>
          <span class="error">* please enter  zip</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"     >  zip2:</th>
      <td>
       <html:text property="zip2" size="4" maxlength="4"/>
       </td>
    </tr>
     
     <tr>
      <th scope="row"     class="required"> *   lab phone:</th>
      <td>
       <html:text property="phone" size="10" maxlength="10"/>
       <c:if test="${CIGARETTE_BRAND_ERROR.phone == 'ERROR'}">
          <br/>
          <span class="error">* phone format error</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"    >  lab fax:</th>
      <td>
       <html:text property="fax" size="10" maxlength="10"/>
       <c:if test="${CIGARETTE_BRAND_ERROR.fax == 'ERROR'}">
          <br/>
          <span class="error">* fax format error</span> 
        </c:if>
       </td>
    </tr> --%>
    
     <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="Update Cigarette Brand" styleClass="button" />
     
     </td>
     </tr>
   </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>
