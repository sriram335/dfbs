<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:choose>
  <c:when test="${PERMIT_SELECTED == null || PERMIT_SELECTED.new}">
  <tr>
  <th scope="row" style="width : 50%;"> permit number:</th>
  <td>  
    <html:text property="permitNumber" size="12" maxlength="12"/>
    </td>
    </tr>
     <tr>
      <th scope="row" class="required">*facility name:</th>
      <td>
        <html:text property="daycareName" size="30" maxlength="30"/>
        <c:if test="${CHILDCARE_PERMIT_ERROR.daycareName == 'ERROR'}">
          <br/>
          <span class="error">* please specify actual location</span> 
        </c:if>
      </td>
    </tr> 
    <tr>
      <th scope="row" class="required">*address 1:</th>
      <td>
        <html:text property="address1" size="30" maxlength="30"/>
        <c:if test="${CHILDCARE_PERMIT_ERROR.address1 == 'ERROR'}">
          <br/>
          <span class="error">* please specify address address</span> 
        </c:if>
        <br/>
      </td>
    </tr>
    <tr>
      <th scope="row">address 2:</th>
      <td>
        <html:text property="address2" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*city:</th>
      <td>
        <html:text property="city" size="30" maxlength="30"/>
        <c:if test="${CHILDCARE_PERMIT_ERROR.city == 'ERROR'}">
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
        <c:if test="${CHILDCARE_PERMIT_ERROR.state == 'ERROR'}">
          <br/>
          <span class="error">* please select a state</span> 
        </c:if>
        <c:if test="${CHILDCARE_PERMIT_ERROR.state == 'ERROR2'}">
          <br/>
          <span class="error">* please select &quot;INDIANA&quot; as state</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*zip:</th>
      <td>
        <html:text property="zip" size="5" maxlength="5"/>
        <c:if test="${CHILDCARE_PERMIT_ERROR.zip == 'ERROR'}">
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
       <c:if test="${CHILDCARE_PERMIT_ERROR.county == 'ERROR'}">
          <br/>
          <span class="error">* please specify county</span> 
        </c:if> 
      </td>
    </tr>
   <tr>
      <th scope="row" class="required">* day care structure type:</th>
      <td>
        <html:select property="structureType" styleId="SELECT_STRUCTURE" onchange="setSelectValue('SELECT_STRCURE','STRUCTURE_TYPE')">
          <html:option value="">-----</html:option>
          <html:options collection="CHILDCARE_STRUCTURE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${CHILDCARE_PERMIT_ERROR.structureType == 'ERROR'}">
          <br/>
          <span class="error">* please specify structure type</span> 
        </c:if> 
      </td>
    </tr>
     </c:when>
  <c:otherwise>
    <tr>
      <th scope="row" style="width : 50%">permit number:</th>
      <td style="font-size:medium;font-weight:bold">
        <c:out value="${PERMIT_SELECTED.permitNumber}"/>
        <html:hidden property="permitNumber"/>
      </td>
    </tr>
    
    <tr>
      <th scope="row">facility name:</th>
      <td>
        <c:out value="${PERMIT_SELECTED.daycareName}"/>
        <html:hidden property="daycareName" />
      </td>
      </tr>
    <tr>
    <tr>
      <th scope="row">address 1:</th>
      <td>
        <c:out value="${PERMIT_SELECTED.address1}"/>
        <html:hidden property="address1" />
      </td>
      </tr>
    <tr>
      <th scope="row">address 2:</th>
      <td>
        <c:out value="${PERMIT_SELECTED.address2}"/>
      </td>
    </tr>
    <tr>
      <th scope="row" >city:</th>
      <td>
        <c:out value="${PERMIT_SELECTED.city}"/>
        <html:hidden property="city" />
      </td>
    </tr>
    <tr>
      <th scope="row" >state:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.state}"/>
        <html:hidden property="state" />
      </td>
    </tr>
    <tr>
      <th scope="row" >zip:</th>
      <td>
       <c:out value="${PERMIT_SELECTED.zip}"/>
        <html:hidden property="zip" />
      </td>
    </tr>
    <tr>
      <th scope="row" >county:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.county}"/>
        <html:hidden property="county" />
      </td>
   </tr>
    <tr>
      <th scope="row" >structure type:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.structureType}"/>
        <html:hidden property="structureType" />
      </td>
   </tr>
   </c:otherwise>
</c:choose>
   <tr>
      <th scope="row"   class="required"     >  * fire department:</th>
      <td>
          <html:select property="fdId" styleId="SELECT_ID" onchange="setSelectValue('SELECT_ID','STRUCTURE_TYPE')">
          <html:option value="">Please Select</html:option>
          <html:options collection="DFBS_FD_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${PERMIT_ERROR.fdId == 'ERROR'}">
              <br/><span class="error">* please enter fire department  </span> 
            </c:if>
       </td>
    </tr> 
    <tr>
        <th scope="row"  class="required">phone:</th>
        <td>
           <html:text property="telephone" size="10" maxlength="10"/>
    <c:if test="${CHILDCARE_PERMIT_ERROR.telephone == 'ERROR'}">
      <br/>
      <span class="error">* please specify phone number in the correct format</span> 
    </c:if>
        </td>
    </tr>
    <tr>
        <th scope="row" >fax:</th>
        <td>
            <html:text property="fax" size="10" maxlength="10"/> 
        </td>
    </tr>
     <tr>
        <th scope="row" >email:</th>
        <td>
           <html:text property="email" size="30" maxlength="60"/>
        </td>
    </tr>
    
     <tr>
        <th scope="row" >comments:</th>
        <td>
           <html:textarea property="notes" /> (max 255 characters)
        </td>
    </tr>
  
<tr>
  <th colspan="2">&nbsp;</th>
</tr>





 


    

 