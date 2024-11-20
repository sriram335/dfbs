<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Company Information"/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Update Manufacturer Company and Agent Information</h2> 
<div id="sideContentFluid">
<a   href="/dfbs/cigarette/applicationsView.do?method=addSupApp">
              [Cancel]</a>
</div>
<div id="mainBox">
 <div id="mainContentFluid" align="left">
  <div class="styledBox">
 
  <html:form action="/cigarette" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateCompany"/> 
      <html:hidden property="companyId"/> 
      <html:hidden property="agentId"/> 
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     <tr>
      <th scope="row"    >  company name:</th>
      <td>
      <c:out value="${sessionScope.CIGARETTE_COMPANY.companyName}"/>
      <html:hidden property="companyName"/>
       </td>
    </tr>
    <tr>
      <th scope="row"    >  company address1:</th>
      <td>
        <c:out value="${sessionScope.CIGARETTE_COMPANY.companyAddress1}"/>
      <html:hidden property="companyAddress1"/>
       </td>
    </tr>
     <tr>
      <th scope="row"     > company address2:</th>
      <td>
       <c:out value="${sessionScope.CIGARETTE_COMPANY.companyAddress2}"/>
      <html:hidden property="companyAddress2"/>
       </td>
    </tr>
    <tr>
      <th scope="row"    >  company city:</th>
      <td>
        <c:out value="${sessionScope.CIGARETTE_COMPANY.companyCity}"/>
        <html:hidden property="companyCity"/>
       </td>
    </tr>
  
    <tr>
      <th scope="row"   >  company state:</th>
      <td>
           <c:out value="${sessionScope.CIGARETTE_COMPANY.companyState}"/>
          <html:hidden property="companyState"/>
       </td>
    </tr>
    <tr>
      <th scope="row"   >  company province:</th>
      <td>
           <c:out value="${sessionScope.CIGARETTE_COMPANY.companyProvince}"/>
          <html:hidden property="companyProvince"/>
       </td>
    </tr>
     <tr>
      <th scope="row"   >  company country:</th>
      <td>
           <c:out value="${sessionScope.CIGARETTE_COMPANY.companyCountry}"/>
          <html:hidden property="companyCountry"/>
       </td>
    </tr>
  
    <tr>
      <th scope="row"    >  company zip:</th>
      <td>
       <c:out value="${sessionScope.CIGARETTE_COMPANY.companyZip}"/>
      <html:hidden property="companyZip"/>
       </td>
    </tr>
     <tr>
      <th scope="row"     > company zip2:</th>
      <td>
        <c:out value="${sessionScope.CIGARETTE_COMPANY.companyZip2}"/>
      <html:hidden property="companyZip2"/>
       </td>
    </tr>
     
     <tr>
      <th scope="row"     > company  phone:</th>
      <td>
       <html:text property="companyPhone" size="10" maxlength="10"/>
      
       </td>
    </tr>
    <tr>
      <th scope="row"    > company contact fax:</th>
      <td>
       <html:text property="companyFax" size="10" maxlength="10"/>
      
       </td>
    </tr>
     
     
     <tr>
      <th scope="row"    ><u></b> Manufacturer Company Registered Indiana Agent Information:</u></b></th>
      <td>
       </td>
    </tr>
     <tr>
      <th scope="row"    >  agent name:</th>
      <td>
      <c:out value="${sessionScope.CIGARETTE_AGENT.agentName}"/>
      <html:hidden property="agentName"/>
       </td>
    </tr>
    <tr>
      <th scope="row"    >  agent address1:</th>
      <td>
        <c:out value="${sessionScope.CIGARETTE_AGENT.agentAddress1}"/>
      <html:hidden property="agentAddress1"/>
       </td>
    </tr>
     <tr>
      <th scope="row"     > agent address2:</th>
      <td>
       <c:out value="${sessionScope.CIGARETTE_AGENT.agentAddress2}"/>
      <html:hidden property="agentAddress2"/>
       </td>
    </tr>
    <tr>
      <th scope="row"    >  agent city:</th>
      <td>
        <c:out value="${sessionScope.CIGARETTE_AGENT.agentCity}"/>
        <html:hidden property="agentCity"/>
       </td>
    </tr>
  
    <tr>
      <th scope="row"   >  agent state:</th>
      <td>
           <c:out value="${sessionScope.CIGARETTE_AGENT.agentState}"/>
          <html:hidden property="agentState"/>
       </td>
    </tr>
  
  
    <tr>
      <th scope="row"    >  agent zip:</th>
      <td>
       <c:out value="${sessionScope.CIGARETTE_AGENT.agentZip}"/>
      <html:hidden property="agentZip"/>
       </td>
    </tr>
     <tr>
      <th scope="row"     > agent zip2:</th>
      <td>
        <c:out value="${sessionScope.CIGARETTE_AGENT.agentZip2}"/>
      <html:hidden property="agentZip2"/>
       </td>
    </tr>
     
     <tr>
      <th scope="row"    >  agent  phone:</th>
      <td>
       <html:text property="agentPhone" size="10" maxlength="10"/>
       
       </td>
    </tr>
    <tr>
      <th scope="row"    > agent contact fax:</th>
      <td>
       <html:text property="agentFax" size="10" maxlength="10"/>
      
       </td>
    </tr>
      <tr>
      <th scope="row"    > agent contact email:</th>
      <td>
       <html:text property="agentEmail" size="80" maxlength="80"/>
       
       </td>
    </tr>
    
    
     <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="Update" styleClass="button" />
     
     </td>
     </tr>
    
   </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
  </div> 
<jsp:include page="/app/common/hsPageFooter.jsp"/>