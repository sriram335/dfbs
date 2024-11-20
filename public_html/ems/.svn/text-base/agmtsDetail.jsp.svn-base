<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="Site Agreement Details"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  
  <div id="leftContentFluid" align="left">
<h2>Update agreement Information</h2>

   <a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> 
     <a href="/dfbs/ems/agmts.do?method=agmtsList&institutionId=<c:out value="${sessionScope.INSTITUTION.institutionId}"/>">
            [back to  site agreements list]  </a>  
      <a   href="/dfbs/ems/institution.do?method=institutionDetail&institutionId=<c:out value="${sessionScope.INSTITUTION.institutionId}"/>">
              [ back to institution ]</a> 
     <a   href="/dfbs/ems/institution.do?method=institutionList">
            [back to institution list]  </a>               
    
 <html:form action="/agmts" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveAgmts"/> 
       <html:hidden property="institutionId"/> 
       <html:hidden property="agreementId"/> 
     
       
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >* name:</th>
      <td>
       <html:text property="name" size="30" maxlength="30"/>
        <c:if test="${AGREEMENT_ERROR.name == 'ERROR'}">
          <br/>
          <span class="error">* please enter  name</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* effective date:</th>
      <td>
       <html:text property="effectiveDate" size="10" maxlength="10"/>
        <c:if test="${AGREEMENT_ERROR.effectiveDate == 'ERROR'}">
          <br/>
          <span class="error">* please enter effective date</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > person expiration date:</th>
      <td>
       <html:text property="expirationDate" size="10" maxlength="10"/>
        
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >remarks</th>
      <td>
       <html:text property="remarks" size="100" maxlength="100"/>
        
      </td>
    </tr>
    
     
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


