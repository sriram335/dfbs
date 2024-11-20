<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - DFBS system"/>
<c:set var="title" scope="request" value="Indiana LEPC's"/>
<jsp:include page="/main/IdhsPageHeader.jsp"/></br>
 <a   href="/dfbs/otherUsers/otherUser.do?method=logOutLepc" >
              [log out LEPC]</a>  &nbsp;&nbsp; 
  <a   href="/dfbs/lepc/lepc.do?method=viewLepcYear&lepcId=<c:out value="${sessionScope.LEPC_SELECTED_ID}"/>">
                 [back to LEPC]</a>
  &nbsp;&nbsp; 
 <a   href="/dfbs/lepc/roster.do?method=updateRoster&lepcId=<c:out value='${sessionScope.LEPC_SELECTED_ID}'/>">
                  [back to Roster]</a></br>
<div id="mainBox">
  <h2>Add / Update Representative Proxy</h2><c:out value="${personId}"/>

<div id="leftContentFluid">
 <c:if test="${PROXY_QUESTION == null}">
         
  <html:form action="/representative" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="proxyQuestionFirst"/>
  <html:hidden property="rosterId"/>
  <p class="error"> Are you a Federal /State / Local Govt Employee?
 <html:submit value="Yes" styleClass="button" property="proxyAction"/>
  <html:submit value="No" styleClass="button" property="proxyAction"/>
  </html:form>
</c:if>
<c:if test="${PROXY_QUESTION == 'ERROR'}">
<p class="error">
  NOT ELIGIBLE FOR PROXY, ONLY FEDERAL/STATE/LOCAL GOVERNMENT EMPLOYEES CAN HAVE PROXIES    </p>
  Click below to reurn to roster
  <a   href="/dfbs/lepc/roster.do?method=updateRoster&lepcId=<c:out value="${sessionScope.LEPC_SELECTED_ID}"/>">
                 [back to Roster]</a>
 </c:if>
 <c:if test="${PROXY_QUESTION == 'FIRST'}">
      
  <html:form action="/representative" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="proxyQuestionSecond"/>
  <html:hidden property="rosterId"/>
  <p class="error"> Enter which department you belong to ?
 <html:text property="personDept" size="50" maxlength="50"/>
  <html:submit value="ok" styleClass="button" property="proxyAction"/>
  </html:form>
</c:if>
<c:if test="${PROXY_QUESTION == 'SECOND'}">
      
     

<table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader">  
<html:form action="/representative" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="saveRepresentativeProxy"/>
   <html:hidden property="personId"/> 
    <html:hidden property="rosterId"/> 
   <c:if test="${sessionScope.ROSTER_PERSON_TYPE != null}">
   <html:hidden property="personType"/> 
   </c:if>
    
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > First Name:</th>
      <td>
       <html:text property="personFirstName" size="40" maxlength="40"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Middle Initial:</th>
      <td>
       <html:text property="personMi" size="1" maxlength="1"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Last Name:</th>
      <td>
       <html:text property="personLastName" size="40" maxlength="40"/>
       </td>     
    </tr>
    
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Email:</th>
      <td>
       <html:text property="personEmail" size="80" maxlength="80"/>
       </td>     
    </tr>
   <tr>
      <th scope="row"  style= "width:50%" class="required"  > Person Type:</th>
      <td>
       <html:select property="personType"  onchange="setSelectValue('SELECT_PERSON','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <html:options collection="LEPC_PERSON_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${LEPC_PERSON_ERROR.personType == 'ERROR'}">
          <br/>
          <span class="error">* please specify person type</span> 
        </c:if>
       </td>     
    </tr>
   
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Representative Add Date:</th>
      <td>
       <c:out value="${sessionScope.ROSTER_PROXY_UPDATE.addDateString}"/>
       <html:hidden property="addDate"/> 
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Person Status:</th>
      <td>
       <html:select property="personStatus"  onchange="setSelectValue('SELECT_PERSON','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <html:options collection="LEPC_PERSON_STATUS_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Representative Status Change Date:</th>
      <td>
       <c:out value="${sessionScope.ROSTER_PROXY_UPDATE.statusDateString}"/>
        <html:hidden property="statusDate"/> 
       </td>     
    </tr>
  <%--   <c:if test="${ETHICS_SECURITY == 'Y'}">
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Ethics User Id:</th>
      <td>
       <html:text property="personEthicsId" size="20" maxlength="20"/>
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Ethics Password:</th>
      <td>
       <html:text property="personEthicsPwd" size="20" maxlength="20"/>
       </td>     
    </tr>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Ethics Training Complete Date:</th>
      <td>
       <html:text property="lepcDate" size="10" maxlength="10"/>MM/DD/YYYY
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Ethics Training Status:</th>
      <td>
       <html:select property="personTrainStatus"  onchange="setSelectValue('SELECT_PERSON','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <html:options collection="LEPC_PERSON_TRAIN_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        </td>     
    </tr>
    </c:if>
    <c:if test="${ETHICS_SECURITY == null || ETHICS_SECURITY != 'Y'}">
     <html:hidden property="personEthicsId"/>
     <html:hidden property="personEthicsPwd"/>
     <html:hidden property="lepcDate"/>
     <html:hidden property="personTrainStatus"/>
     </c:if> --%>
<tr>
<th> </th>
<td>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null}"> 
  <html:submit value="Save Proxy" styleClass="button" />
</c:if>
</td>
</tr>
</html:form>
</tbody>
</table>
</c:if>
</div>
  <div class="clearer">&nbsp;</div>
 </div> 