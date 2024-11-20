 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="Entertainment Permits" />
<c:set var="title" scope="request" value="Seal Usage Information" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>

<h2>Process Seals</h2> 
<div id="leftContentFluid">
 <a   href="/dfbs/code/viewFacility.do?method=sealOrders&facilityId=<c:out value="${sessionScope.FACILITY.facilityId}"/>"> 
              [back to seal orders]</a>
  <div class="styledBox">
 <html:form action="/seal" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="createSealRecord"/> 
       <html:hidden property="facilityId"/>
       <html:hidden property="orderId"/> 
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >_______</th>
    </tr>
    <tr>
      <th style="font-size:medium;font-weight:bold" scope="row" > </th>
      <td style="font-size:medium;font-weight:bold">
       
      </td>
    </tr>
    <tr>
      <th scope="row"       >    M seals purchased:</th>
          <td>
          
          <c:out value="${sessionScope.SEAL_ORDER.mseals}"/>
             </td>
      </tr> 
     
      <tr>
      <th scope="row"   class="required"    >  *  M seals mailed From:</th>
          <td>
           <html:text property="msealsFrom" size="12" maxlength="12"/>
             </td>
      </tr> 
        <tr>
      <th scope="row"   class="required"    >  *  M seals mailed To:</th>
          <td>
           <html:text property="msealsTo" size="12" maxlength="12"/>
             </td>
      </tr> 
      <tr>
      <th scope="row"       >    P seals purchased:</th>
          <td>
            <c:out value="${sessionScope.SEAL_ORDER.pseals}"/>
             </td>
      </tr> 
      <tr>
      <th scope="row"   class="required"    >  *  P seals mailed From:</th>
          <td>
            <html:text property="psealsFrom" size="12" maxlength="12"/>
             </td>
      </tr> 
       <tr>
      <th scope="row"   class="required"    >  *  P seals mailed To:</th>
          <td>
           <html:text property="psealsTo" size="12" maxlength="12"/>
             </td>
      </tr> 
  </tbody>
        </table>
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCode != null }">  
        <p>
          <html:submit value=" Process " styleClass="button"/>
        
        </p>
      </c:if>
     
    </html:form>   </br>
    
  <b><u>Processed seals:</b></u>
 <%-- <jsp:include page="/code/common/assignedSeals.jsp"/> --%>
 
</div>
 <jsp:include page="/code/common/sealUsage.jsp" />

