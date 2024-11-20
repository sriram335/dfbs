<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Child Care Facility Renewal"/>
<c:set var="title" scope="request" value="Child Care Facility Information"/>
<c:set var="step" scope="request" value="2"/>
<c:set var="renew" scope="request" value="renewed"/>

<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <div id="sideContentFluid">
 <jsp:include page="/childCare/application/shoppingCart.jsp"/>
<c:forEach var="file" items="${sessionScope.PERMIT_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
       <a   href="/dfbs/childCare/permit.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a>
        </c:if>
          </td>
        <td>
</tr>
</c:forEach>
</div>
 <h2>Verify Permit Information</h2>
      <a   href="/dfbs/childCare/childcare.do?method=renewPermit&permitKey=<c:out value="${PERMIT_SELECTED.permitNumber}"/>&ownerKey=<c:out value="${PERMIT_SELECTED.ownerId}" />">
             [cancel ]</a>   
      <html:link page="/start.do">[back to start]</html:link>
<div id="mainBox">

  <div id="leftContentFluid">
    <html:form action="/childcare" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="renewPermitVerified"/>
      <html:hidden property="ownerId"/>
      <html:hidden property="applicationKey"/>
       <html:hidden property="receivedDate"/>
       <html:hidden property="active"/>
      <html:hidden property="county" styleId="COUNTY_NAME"/>
      
         <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
      
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
           <html:text property="email" size="60"/>
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
   </tbody>
        </table>
        <p>
          <html:submit value="Verified" styleClass="button"/>
        
        </p>
    </html:form>
   

    <c:if test="${requestScope.DFBS_PERMIT.new}">
      <script type="text/javascript">
        setSelectValue('SELECT_COUNTY','COUNTY_NAME');
        showMiscRow();
      </script>
    </c:if> 
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>





 


    

 
