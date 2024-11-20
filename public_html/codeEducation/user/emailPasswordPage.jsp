<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS -DFBS system"/>
<c:set var="title" scope="request" value="Password Recovery"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Password request</h2>
 <div id="sideContentFluid">
     <a   href="/dfbs/ems/ems/main.do?method=verifySecurity">
              [ Cancel ]</a>               
  </div>
  <div id="leftContentFluid">
 <html:form action="/user" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="emailPassword"/>
 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    <tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user id(registered email):</th>
      <td>
       <html:text property="userId" size="50" maxlength="50"/>
        <c:if test="${PASSWORD_STATUS == 'EMAIL_SENT'}">
          <br/>
          <span class="message">email sent</span> 
        </c:if>
      </td>
     
    </tr>
<tr>
 <th scope="row"  style= "width:100%"  ></th>
<td>
   
          <html:submit value="Email Password to my registered User Email" styleClass="button" />
</td>
</tr> 
<tr>
<th scope="row"  style= "width:100%" class="required"  ></th>
<td>
 <c:if test="${PASSWORD_STATUS == 'ERROR'}">
          <br/>
          <span class="error">* user email does not match with data in database contact DfbsDatabase@dhs.in.gov with details</span> 
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