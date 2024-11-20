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
   

  <div id="leftContentFluid">
   <h2>Update Level Information</h2>

<a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> 
     <a href="/dfbs/ems/level.do?method=levelList&providerId=<c:out value="${PROVIDER.providerId}"/>">
            [back to level list]  </a>  
     <a   href="/dfbs/ems/provider.do?method=providerDetail&providerId=<c:out value="${PROVIDER.providerId}"/>">
            [back to provider]  </a>               
       <a   href="/dfbs/ems/waiver.do?method=waiverList&levelId=<c:out value="${LEVEL.levelId}" />">
              [ waiver list ]</a>      

 <html:form action="/level" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveLevel"/> 
       <html:hidden property="levelId"/> 
       <html:hidden property="providerId"/> 
   
       
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >* level name:</th>
      <td>
       <html:text property="levelName" size="70" maxlength="70"/>
         <c:if test="${LEVEL_ERROR.levelName == 'ERROR'}">
          <br/>
          <span class="error">* please enter level name</span> 
        </c:if>
         
      </td>
   
    
   <tr>
<td>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null }"> 
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



