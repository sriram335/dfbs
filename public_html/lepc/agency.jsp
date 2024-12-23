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
              [log out LEPC]</a>  
<div id="mainBox">
  <h2>Add / Update Agency</h2>

<div id="leftContentFluid">
<table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader">  
<html:form action="/agency" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="saveAgency"/>
   <html:hidden property="agencyId"/> 
    <html:hidden property="exerciseId"/> 
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Agency Name:</th>
      <td>
       <html:select property="agencyName"  onchange="setSelectValue('SELECT_AGENCY','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <html:options collection="LEPC_AGENCY_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       If other agency, type<html:text property="agencyOther" size="30" maxlength="30"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Agency Type:</th>
      <td>
      <html:select property="agencyType"  onchange="setSelectValue('SELECT_AGENCY','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <html:option value="Key">Key</html:option>
          <html:option value="Support">Support</html:option>
        </html:select>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Agency on Scene:</th>
      <td>
       <html:select property="agencyPresence"  onchange="setSelectValue('SELECT_AGENCY','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <html:option value="Yes">Yes</html:option>
          <html:option value="No">No</html:option>
        </html:select>
       </td>     
    </tr>
    <c:if test="${sessionScope.LEPC_EXERCISE.reportType == 'Exercise'}">
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Agency on Debriefing:</th>
      <td>
       <html:select property="agencyDebrief"  onchange="setSelectValue('SELECT_AGENCY','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <html:option value="Yes">Yes</html:option>
          <html:option value="No">No</html:option>
        </html:select>
       </td>     
    </tr>
     </c:if>   
<tr>
<th> </th>
<td>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null}"> 
  <html:submit value="Save Agency" styleClass="button" />
</c:if>
</td>
</tr>
</html:form>
</tbody>
</table> </br></br>

</div>
  <div class="clearer">&nbsp;</div>
 </div> 