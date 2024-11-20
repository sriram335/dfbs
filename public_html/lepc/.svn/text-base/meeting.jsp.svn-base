<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - DFBS system"/>
<c:set var="title" scope="request" value="Indiana LEPC's Meeting Date, Time and Location List"/>
<jsp:include page="/main/IdhsPageHeader.jsp"/></br>
<div id="mainBox">
<a   href="/dfbs/lepc/lepc.do?method=viewLepcYear&lepcId=<c:out value="${sessionScope.LEPC_SELECTED_ID}"/>">
                 [back to LEPC]</a></br>
   <a   href="/dfbs/otherUsers/otherUser.do?method=logOutLepc" >
              [log out LEPC]</a>  
   
  <h2>Add / Update LEPC Meeting Schedule</h2>

<div id="leftContentFluid">
<table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
  <tbody class="rowHeader">  
    
<html:form action="/meeting" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="saveMeeting"/>
   <html:hidden property="meetingId"/> 
    <html:hidden property="lepcId"/> 
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >Meeting Date:</th>
      <td>
       <html:text property="meetingDate" size="10" maxlength="10"/>MM/DD/YYYY
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Meeting Time:</th>
      <td>
       <html:text property="meetingTime" size="15" maxlength="15"/>
       </td>     
    </tr>
      <tr>
      <th scope="row"  style= "width:50%" class="required"  > Address1:</th>
      <td>
       <html:text property="meetingAddress1" size="50" maxlength="50"/>
       </td>     
    </tr>
    </tr>
      <tr>
      <th scope="row"  style= "width:50%"   > Address2:</th>
      <td>
       <html:text property="meetingAddress2" size="50" maxlength="50"/>
       </td>     
    </tr> 
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >City:</th>
      <td>
       <html:text property="meetingCity" size="50" maxlength="50"/>
       </td>     
    </tr>  
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > State:</th>
      <td>
       <html:text property="meetingState" size="2" maxlength="2"/>
       </td>     
    </tr> 
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Zip:</th>
      <td>
       <html:text property="meetingZip" size="5" maxlength="5"/>
       </td>     
    </tr>  
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Contact:</th>
      <td>
       <html:text property="meetingContact" size="50" maxlength="50"/>
       </td>     
    </tr> 
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Phone:</th>
      <td>
       <html:text property="meetingContactPhone" size="10" maxlength="10"/>
       </td>     
    </tr> 
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Email:</th>
      <td>
       <html:text property="meetingContactEmail" size="80" maxlength="80"/>
       </td>     
    </tr> 
<tr>
<th> </th>
<td>
<c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null) 
         && (sessionScope.LEPC_FISCAL_REPORT.fiscalStatus == null || sessionScope.LEPC_FISCAL_REPORT.fiscalStatus =='Pending')}"> 
  <html:submit value="Save Meeting" styleClass="button" />
</c:if>
</td>
</tr>
</html:form>
</tbody>
</table> </br></br>
</div>
  <div class="clearer">&nbsp;</div>
 </div> 