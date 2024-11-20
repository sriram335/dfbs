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
 <a   href="/dfbs/lepc/fiscalReport.do?method=updateFiscalReport&fiscalId=<c:out value="${LEPC_FISCAL_REPORT.fiscalId}"/>">
                 back to Fiscal Report</a></br>
<div id="mainBox">
  <h2>Add/Update Disbursements</h2>

<div id="leftContentFluid">
<table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader">  
<html:form action="/fiscalReport" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="saveDisbursement"/>
   <html:hidden property="fiscalId"/> 
    <html:hidden property="lepcId"/> 
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Disbursement Type:</th>
      <td>
      <html:select property="reportBy"  onchange="setSelectValue('SELECT_PERSON','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <html:options collection="LEPC_EXPENDITURE_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Account Balance:</th>
      <td>
       $<html:text property="acctBalance" size="20" maxlength="20"/>
       </td>     
    </tr>
   
        
<tr>
<th> </th>
<td>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null}"> 
  <html:submit value="Save Expenditure" styleClass="button" />
</c:if>
</td>
</tr>
</html:form>
</tbody>
</table> </br></br>

</div>
  <div class="clearer">&nbsp;</div>
 </div> 