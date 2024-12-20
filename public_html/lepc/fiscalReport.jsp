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
<div id="mainBox">
<a   href="/dfbs/lepc/lepc.do?method=viewLepcYear&lepcId=<c:out value="${sessionScope.LEPC_SELECTED_ID}"/>">
                 back to LEPC</a></br>
   <a   href="/dfbs/otherUsers/otherUser.do?method=logOutLepc" >
              [log out LEPC]</a>  
   
  <h2>Add / Update Fiscal Report</h2>
<p class="messageBox" size="200" align="left"><u><b> FISCAL REPORT</b></u></br>
>>	DO input ONLY prior year's LEPC expenditure
</p>
<h4>County Name : ${sessionScope.LEPC_COUNTY_SELECTED}&nbsp;&nbsp;&nbsp;Year : ${sessionScope.LEPC_SELECTED_CURRENT_YEAR}</h4>

<div id="leftContentFluid">
<table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
 <tr>
      <th >Fiscal Items</th>
       <th> Details </th>
  </tr> 
  <tbody class="rowHeader">  
    
<html:form action="/fiscalReport" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="saveFiscalReport"/>
   <html:hidden property="fiscalId"/> 
    <html:hidden property="lepcId"/> 
   <html:hidden property="fiscalStatus"/> 
    <html:hidden property="reportBy"/> 
    <html:hidden property="repDate"/> 
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Account Balance (Prior Year)(+):</th>
      <td>
      <c:if test="${ sessionScope.DFBS_SECURITY != null }">
       $<html:text property="acctBalance" size="20" maxlength="20"/>
       </c:if>
       <c:if test="${ sessionScope.DFBS_SECURITY == null}">
       $<c:out value="${LEPC_FISCAL_REPORT.acctBalance}"/>&nbsp;&nbsp; OFFICIAL USE ONLY
       <html:hidden property="acctBalance"/> 
       </c:if>
       </td>     
    </tr>
   <%-- <tr>
      <th scope="row"  style= "width:50%" class="required"  > Previous Receipts From State(+):</th>
      <td>
       <c:if test="${sessionScope.DFBS_SECURITY != null }">
        $<html:text property="acctReceipts" size="20" maxlength="20"/>
       </c:if>
       <c:if test="${sessionScope.DFBS_SECURITY == null}">
        $<c:out value="${LEPC_FISCAL_REPORT.acctReceipts}"/> &nbsp;&nbsp;OFFICIAL USE ONLY
       <html:hidden property="acctReceipts"/> 
       </c:if>
      
       </td>     
    </tr> --%>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Current Year Receipts From State [Tier II Funds](+):</th>
      <td>
       <c:if test="${sessionScope.DFBS_SECURITY != null }">
        $<html:text property="acctReceiptsCurrent" size="20" maxlength="20"/>
       </c:if>
       <c:if test="${sessionScope.DFBS_SECURITY == null}">
        $<c:out value="${LEPC_FISCAL_REPORT.acctReceiptsCurrent}"/> &nbsp;&nbsp;OFFICIAL USE ONLY
       <html:hidden property="acctReceiptsCurrent"/> 
       </c:if>
      
       </td>     
    </tr>
      <tr>
      <th scope="row"  style= "width:50%" class="required"  > Receipts From Grants [HMEP](+):</th>
      <td>
       $<html:text property="acctGrants" size="20" maxlength="20"/>
       </td>     
    </tr> 
     </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Investment Account Balances and Interest(+):</th>
      <td>
      $<html:text property="invBalance" size="20" maxlength="20"/>
       </td>     
    </tr>  
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Amendment Credit(+):</th>
      <td>
    
      <c:if test="${sessionScope.DFBS_SECURITY != null }">
        $<html:text property="amendCredit" size="20" maxlength="20"/>
       </c:if>
       <c:if test="${sessionScope.DFBS_SECURITY == null}">
        $<c:out value="${LEPC_FISCAL_REPORT.amendCredit}"/> &nbsp;&nbsp;OFFICIAL USE ONLY
       <html:hidden property="amendCredit"/> 
       </c:if>
      
       </td>     
    </tr>  
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Amendment Debit(-):</th>
      <td>
  
        <c:if test="${sessionScope.DFBS_SECURITY != null }">
        $<html:text property="amendDebit" size="20" maxlength="20"/>
       </c:if>
       <c:if test="${sessionScope.DFBS_SECURITY == null}">
        $<c:out value="${LEPC_FISCAL_REPORT.amendDebit}"/> &nbsp;&nbsp;OFFICIAL USE ONLY
       <html:hidden property="amendDebit"/> 
       </c:if>
       </td>     
    </tr>  
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Total Disbursements (-):</th>
      <td>
       $<c:out value="${FISCAL_CURRENT_ACCOUNT_EXPENDITURE}"/>
       </td>     
    </tr>  
      <tr>
      <th scope="row"  style= "width:50%" class="required"  > Account Balance (Year End):</th>
      <td>
       $<c:out value="${FISCAL_CURRENT_ACCOUNT_BALANCE}"/>
       </td>     
    </tr>  
<tr>
<th>If you do not see disbursement option, click save to start the new year fiscal process!</th>
<td>
<c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null) 
         && (sessionScope.LEPC_FISCAL_REPORT.fiscalStatus == null || sessionScope.LEPC_FISCAL_REPORT.fiscalStatus =='Pending')}"> 
  <html:submit value="Save Fiscal Report" styleClass="button" />
  
</c:if>
</td>
</tr>
</html:form>
</tbody>
</table> </br></br>
<c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null) }"> 
<c:if test="${sessionScope.LEPC_FISCAL_REPORT.fiscalId > 0 && sessionScope.LEPC_FISCAL_REPORT.fiscalStatus =='Pending'}">
<a  href="/dfbs/lepc/fiscalReport.do?method=addDisbursement&fiscalId=<c:out value='${sessionScope.LEPC_FISCAL_REPORT.fiscalId}'/>">
                  Add Disbursements</a><br/>  <br/>
</c:if>
<p class="error"> If your LEPC has no disbursements for the year,</br> please click "Add Disbursements" and 
select "No Disbursements for the year" </br>then enter a zero "0.00" amount. This will ensure that you receive credit for fiscal report submission.</p>
 <b><u>Disbursements</u></b><br/>  <br/> 
<c:forEach var="disbursement" items="${sessionScope.LEPC_FISCAL_EXP_LIST}" varStatus="i">
        Disbursement Type:<c:out value="${disbursement.reportBy}"/>
  <c:if test="${sessionScope.LEPC_FISCAL_REPORT.fiscalId > 0 && sessionScope.LEPC_FISCAL_REPORT.fiscalStatus =='Pending'}">
      <a  href="/dfbs/lepc/fiscalReport.do?method=updateDisbursement&expId=<c:out value='${disbursement.lepcId}'/>">
                  Edit</a><br/>
   </c:if>
         Amount:$<c:out value="${disbursement.acctBalance}"/> <br/>  <br/> 
</c:forEach>                 
</c:if>

<h2> Account Balance as of this Year End: $<c:out value="${FISCAL_CURRENT_ACCOUNT_BALANCE}"/></h2>
</div>
  <div class="clearer">&nbsp;</div>
 </div> 