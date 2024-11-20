<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="Contact Details"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Update Waiver Information</h2>

<a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> 
     <a href="/dfbs/ems/level.do?method=levelList&providerId=<c:out value="${PROVIDER.providerId}"/>">
            [back to level list]  </a>  
     <a href="/dfbs/ems/waiver.do?method=waiverList&levelId=<c:out value="${LEVEL.levelId}"/>">
            [back to waiver list]  </a> 
     <a   href="/dfbs/ems/provider.do?method=providerDetail&providerId=<c:out value="${PROVIDER.providerId}"/>">
            [back to provider]  </a>               
            

  <div id="leftContentFluid">

 <html:form action="/waiver" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveWaiver"/> 
       <html:hidden property="levelId"/> 
       <html:hidden property="waiverId"/> 
       
       
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >* waiver code:</th>
      <td>
       <html:text property="waiverCode" size="30" maxlength="30"/>
        <c:if test="${WAIVER_ERROR.waiverCode == 'ERROR'}">
          <br/>
          <span class="error">* please enter waiver code</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  >waiver date(mm/dd/yyyy):</th>
      <td>
       <html:text property="waiverDate" size="10" maxlength="10"/>
        
      </td>
    </tr>
    
   <tr>
<td>
<c:if test="${EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR' || EMS_SECURITY.currentUserLevel == 'USER'}">
<p>
   
          <html:submit value="Update changes" styleClass="button" />
 </p>
 </c:if>
 </td>
 </tr>
   </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>



