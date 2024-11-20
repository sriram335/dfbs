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
 <div id="sideContentFluid">
 <b><u>Extract Emails</u></b>
  <html:form action="/lepc" method="post" >
 <input type="hidden" name="method" id="METHOD_KEY" value="getEmails"/> 
       <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
      <tr>
      <th scope="row" class="required">*year:</th>
      <td>      
        <html:select property="lepcYear"  styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_YEAR','LEPC_YEAR')">
          <html:option value="">-----</html:option>
          <html:options collection="LEPC_YEAR_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${LEPC_SEARCH_YEAR == 'ERROR'}">
          <br/>
          <span class="error">* please specify county</span> 
        </c:if> 
        </td>
        </tr>
        <tr>
      <th scope="row" class="required">*Lepc Type:</th>
      <td> 
         <html:select property="lepcDocType"  onchange="setSelectValue('SELECT_PERSON','DESCRIPTION')">
          <html:option value="All">All</html:option>
          <html:options collection="LEPC_GROUP_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${LEPC_GROUP_ERROR == 'ERROR'}">
          <br/>
          <span class="error">* please specify Lepc Group</span> 
        </c:if>
         </td>
        </tr>
         <tr>
      <th scope="row" class="required">*Filter by:</th>
      <td> 
         <html:select property="lepcStatus"  onchange="setSelectValue('SELECT_PERSON','DESCRIPTION')">
          <html:option value="All">All</html:option>
          <html:option value="Not Submitted">Not Submitted</html:option>
           <html:option value="Pending">Pending</html:option>
        </html:select>
        <c:if test="${LEPC_GROUP_ERROR == 'ERROR'}">
          <br/>
          <span class="error">* please specify Lepc Group</span> 
        </c:if>
         </td>
        </tr>
         <tr>
      <th scope="row" class="required">Emails:</th>
      <td> 
         <html:textarea property="lepcNotes" rows="5" cols="70"/>
         </td>
        </tr>
         <tr>
      <th scope="row" class="required"></th>
      <td> 
         <input type="submit" value="Get Emails" class="button"/>
         </td>
        </tr>
        </tbody>
        </table>
        </html:form>
</div>
 
<div id="mainBox">
<a   href="/dfbs/otherUsers/otherUser.do?method=logOutLepc" >
              [log out LEPC]</a>  &nbsp;&nbsp;
 <a   href="/dfbs/lepc/lepc.do?method=start">
              [back to start]</a></br>
     <html:form action="/lepc" method="post" >
     <input type="hidden" name="method" id="METHOD_KEY" value="searchAllCounties"/> 
       <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
      <tr>
      <th scope="row" class="required">*year:</th>
      <td>      
        <html:select property="lepcYear"  styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_YEAR','LEPC_YEAR')">
          <html:option value="">-----</html:option>
          <html:options collection="LEPC_YEAR_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${LEPC_SEARCH_YEAR == 'ERROR'}">
          <br/>
          <span class="error">* please specify county</span> 
        </c:if> 
        </td>
        </tr>
        <tr>
      <th scope="row" class="required">*Lepc Type:</th>
      <td> 
         <html:select property="lepcDocType"  onchange="setSelectValue('SELECT_PERSON','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <html:options collection="LEPC_GROUP_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${LEPC_GROUP_ERROR == 'ERROR'}">
          <br/>
          <span class="error">* please specify Lepc Group</span> 
        </c:if>
         </td>
        </tr>
           <tr>
      <th scope="row" class="required">*Status:</th>
      <td> 
         <html:select property="lepcStatus"  onchange="setSelectValue('SELECT_PERSON','DESCRIPTION')">
          <html:option value="">-----</html:option>
           <html:option value="All">All</html:option>
          <html:option value="Not Submitted">Not Submitted</html:option>
           <html:option value="Pending">Pending</html:option>
           <html:option value="Approved">Approved</html:option>
        </html:select>
        <c:if test="${LEPC_GROUP_ERROR == 'ERROR'}">
          <br/>
          <span class="error">* please select status</span> 
        </c:if>
         </td>
        </tr>
         <tr>
      <th scope="row" class="required"></th>
      <td> 
         <input type="submit" value="Search & View" class="button"/>
         </td>
        </tr>
        </tbody>
        </table>
        </html:form>
     
  
<div id="leftContentFluid">

 <c:if test="${SESSION_DOC_VIEW == ' Fiscal Report (Due by March 1)'}">   
 Fiscal Report Status:
<table cellspacing="0"   summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader"  >  

<tr >
<td>
<c:forEach var="county" items="${VIEW_LEPC_COUNTY_LIST}" varStatus="i">
County: <b><c:out value="${county.lepcCounty}"/></b></br>
<c:forEach var="fiscal" items="${county.lepcCountyFiscal}" varStatus="a" >
<table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;" >
 <tr>
      <th align="left">Fiscal Items</th>
       <th align="left"> Details </th>
  </tr> 
  <tbody class="rowHeader">  
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Account Balance (Prior Year)(+):</th>
      <td>
     <c:out value="${fiscal.acctBalance}"/>
       </td>     
    </tr>
   <tr>
      <th scope="row"  style= "width:50%" class="required"  > Receipts From State Previous year(+):</th>
      <td>
        <c:out value="${fiscal.acctReceipts}"/>
      </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Receipts From State Current year(+):</th>
      <td>
        <c:out value="${fiscal.acctReceiptsCurrent}"/>
      </td>     
    </tr>
      <tr>
      <th scope="row"  style= "width:50%" class="required"  > Receipts From Grants(+):</th>
      <td>
      <c:out value="${fiscal.acctGrants}"/>
       </td>     
      </tr>
      <tr>
      <th scope="row"  style= "width:50%" class="required"  > Investment Account Balances and Interest(+):</th>
      <td>
      <c:out value="${fiscal.invBalance}"/>
       </td>     
    </tr> 
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Expenditure current year(-):</th>
      <td>
      <c:out value="${fiscal.curAcctExps}"/>
       </td>     
    </tr>  
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Current Account Balances:</th>
      <td>
      <c:out value="${fiscal.curAcctBalance}"/>
       </td>     
    </tr>  
   
</tbody>
</table> </br></br>

      </c:forEach></br></br>
             
</c:forEach>
</td>
</tr>
</tbody>
</table>
</c:if>
<c:if test="${SESSION_DOC_VIEW == ' Exercise Report (Due 30 days after exercise and by December 31 )'}">
Exercise Report Status:
<table cellspacing="0"   summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader"  >  

<tr >
<td>
<c:forEach var="county" items="${VIEW_LEPC_COUNTY_LIST}" varStatus="j">
County: <b><c:out value="${county.lepcCounty}"/></b></br>
<c:forEach var="proposal" items="${county.lepcCountyExercise}" varStatus="b" >
<table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;" >
 <tr>
      <th align="left">Exercise Items</th>
       <th align="left"> Details </th>
  </tr> 
        <tbody class="rowHeader">  
    <tr>
    <th>____________________________</th>
    <td>
    <b><u>Values</u></b>
    </td>
    </tr>
    
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise Type:</th>
      <td>
      <c:out value="${proposal.reportType}"/>
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > HSEEP-NEXS Number:</th> 
      <td>
       <c:out value="${proposal.exerciseNumber}"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise Start Date:</th> 
      <td>
      <c:out value="${proposal.exerciseStartDateString}"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise Start Time:</th>
      <td>
      <c:out value="${proposal.exerciseStartTime}"/>
        </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >Exercise End Date:</th> 
      <td>
      <c:out value="${proposal.exerciseEndDateString}"/>
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise End Time:</th>
      <td>
      <c:out value="${proposal.exerciseEndTime}"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Location:</th>
      <td>
      <c:out value="${proposal.exerciseLocation}"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > City:</th>
      <td>
      <c:out value="${proposal.exerciseCity}"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > County:</th>
      <td>
      <c:out value="${proposal.exerciseCounty}"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Scenario:</th>
      <td>
      <c:out value="${proposal.exerciseScenario}"/>
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise Incident Type:</th>
      <td>
      <c:out value="${proposal.exerciseIncidentType}"/>
      </td>     
    </tr>
 
</tbody>
</table> 

      </c:forEach></br></br>
             
</c:forEach>
</td>
</tr>
</tbody>
</table>
</c:if>
 <c:if test="${SESSION_DOC_VIEW == ' Exercise Proposal (Due 30 days prior to exercise)'}">
 Exercise Proposal
<table cellspacing="0"   summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader"  >  

<tr >
<td>
<c:forEach var="county" items="${VIEW_LEPC_COUNTY_LIST}" varStatus="k">
County: <b><c:out value="${county.lepcCounty}"/></b></br>
<c:forEach var="proposal" items="${county.lepcCountyProposal}" varStatus="c" >
<table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;" >
 <tr>
      <th align="left">Exercise Items</th>
       <th align="left"> Details </th>
  </tr> 
        <tbody class="rowHeader">  
    <tr>
    <th>____________________________</th>
    <td>
    <b><u>Values</u></b>
    </td>
    </tr>
    
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise Type:</th>
      <td>
      <c:out value="${proposal.reportType}"/>
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > HSEEP-NEXS Number:</th> 
      <td>
       <c:out value="${proposal.exerciseNumber}"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise Start Date:</th> 
      <td>
      <c:out value="${proposal.exerciseStartDateString}"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise Start Time:</th>
      <td>
      <c:out value="${proposal.exerciseStartTime}"/>
        </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >Exercise End Date:</th> 
      <td>
      <c:out value="${proposal.exerciseEndDateString}"/>
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise End Time:</th>
      <td>
      <c:out value="${proposal.exerciseEndTime}"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Location:</th>
      <td>
      <c:out value="${proposal.exerciseLocation}"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > City:</th>
      <td>
      <c:out value="${proposal.exerciseCity}"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > County:</th>
      <td>
      <c:out value="${proposal.exerciseCounty}"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Scenario:</th>
      <td>
      <c:out value="${proposal.exerciseScenario}"/>
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise Incident Type:</th>
      <td>
      <c:out value="${proposal.exerciseIncidentType}"/>
      </td>     
    </tr>
 
</tbody>
</table> 

      </c:forEach></br></br>
             
</c:forEach>
</td>
</tr>
</tbody>
</table>
</c:if>

<c:if test="${SESSION_DOC_VIEW == ' Legal Notices (Due by January 31)'}">
 Legal Notices
<table cellspacing="0"   summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader"  >  

<tr >
<td>
<c:forEach var="county" items="${VIEW_LEPC_COUNTY_LIST}" varStatus="l">
County: <b><c:out value="${county.lepcCounty}"/></b></br>
 <%-- LEGAL STUFF --%>
  <c:forEach var="file" items="${county.lepcCountyLegal}" varStatus="d">
  <table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;" >
 
  <tbody class="rowHeader">  
      <tr>
      <th > </th>
      <td>
       <b> <c:out value="${file.fileName}"/></b> <br/> 
       File date:<b><c:out value="${file.fileDateString}"/></b>&nbsp; File sent by:<b><c:out value="${file.fileLoadedBy}"/></b>;&nbsp;</br>
       <c:if test="${file.filePlanType == 'Pending'}"> 
     <font color="red"> Status:<b><c:out value="${file.filePlanType}"/></b></font></br>
     </c:if>
       <c:if test="${file.filePlanType == 'Approved'}"> 
      <font color="green"> Status:<b><c:out value="${file.filePlanType}"/></b></font>
      </c:if>
      </td>
      </tr>
 </tbody>
 </table>
</c:forEach></br></br>
 </c:forEach>
</td>
</tr>
</tbody>
</table>
</c:if>
<c:if test="${SESSION_DOC_VIEW == ' Bylaws (Due by December 31)'}">
 By Laws
<table cellspacing="0"   summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader"  >  

<tr >
<td>
<c:forEach var="county" items="${VIEW_LEPC_COUNTY_LIST}" varStatus="m">
County: <b><c:out value="${county.lepcCounty}"/></b></br>
 <%-- BY LAWS STUFF --%>
  <c:forEach var="file" items="${county.lepcCountyLaws}" varStatus="e">
  <table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;" >
 
  <tbody class="rowHeader">  
      <tr>
      <th > </th>
      <td>
       <b> <c:out value="${file.fileName}"/></b> <br/> 
       File date:<b><c:out value="${file.fileDateString}"/></b>&nbsp; File sent by:<b><c:out value="${file.fileLoadedBy}"/></b>;&nbsp;</br>
       <c:if test="${file.filePlanType == 'Pending'}"> 
     <font color="red"> Status:<b><c:out value="${file.filePlanType}"/></b></font></br>
     </c:if>
       <c:if test="${file.filePlanType == 'Approved'}"> 
      <font color="green"> Status:<b><c:out value="${file.filePlanType}"/></b></font>
      </c:if>
      </td>
      </tr>
 </tbody>
 </table>
</c:forEach></br></br>
 </c:forEach>
</td>
</tr>
</tbody>
</table>
</c:if>
<c:if test="${SESSION_DOC_VIEW == ' LEPC Plan Update (Due by December 31)'}">
 Plan Updates
<table cellspacing="0"   summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader"  >  

<tr >
<td>
<c:forEach var="county" items="${VIEW_LEPC_COUNTY_LIST}" varStatus="n">
County: <b><c:out value="${county.lepcCounty}"/></b></br>
 <%-- PLAN UPDATE STUFF --%>
  <c:forEach var="file" items="${county.lepcCountyPlan}" varStatus="f">
  <table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;" >
 
  <tbody class="rowHeader">  
      <tr>
      <th > </th>
      <td>
       <b> <c:out value="${file.fileName}"/></b> <br/> 
       File date:<b><c:out value="${file.fileDateString}"/></b>&nbsp; File sent by:<b><c:out value="${file.fileLoadedBy}"/></b>;&nbsp;</br>
       <c:if test="${file.filePlanType == 'Pending'}"> 
     <font color="red"> Status:<b><c:out value="${file.filePlanType}"/></b></font></br>
     </c:if>
       <c:if test="${file.filePlanType == 'Approved'}"> 
      <font color="green"> Status:<b><c:out value="${file.filePlanType}"/></b></font>
      </c:if>
      </td>
      </tr>
 </tbody>
 </table>
</c:forEach></br></br>
 </c:forEach>
</td>
</tr>
</tbody>
</table>
</c:if>
<c:if test="${SESSION_DOC_VIEW == ' Meeting Minutes with meeting sign in sheets (Due December 31 )'}">
 Meeting Minutes
<table cellspacing="0"   summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader"  >  

<tr >
<td>
<c:forEach var="county" items="${VIEW_LEPC_COUNTY_LIST}" varStatus="o">
County: <b><c:out value="${county.lepcCounty}"/></b></br>
 <%-- MINUTES STUFF --%>
  <c:forEach var="file" items="${county.lepcCountyMinutes}" varStatus="g">
  <table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;" >
 
  <tbody class="rowHeader">  
      <tr>
      <th > </th>
      <td>
       <b> <c:out value="${file.fileName}"/></b> <br/> 
       File date:<b><c:out value="${file.fileDateString}"/></b>&nbsp; File sent by:<b><c:out value="${file.fileLoadedBy}"/></b>;&nbsp;</br>
       <c:if test="${file.filePlanType == 'Pending'}"> 
     <font color="red"> Status:<b><c:out value="${file.filePlanType}"/></b></font></br>
     </c:if>
       <c:if test="${file.filePlanType == 'Approved'}"> 
      <font color="green"> Status:<b><c:out value="${file.filePlanType}"/></b></font>
      </c:if>
      </td>
      </tr>
 </tbody>
 </table>
</c:forEach></br></br>
 </c:forEach>
</td>
</tr>
</tbody>
</table>
</c:if>
<c:if test="${SESSION_DOC_VIEW == ' Roster (Due by March 1) Note: Ethics training required for all roster members'}">
 Roster Details:
<table cellspacing="0"   summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader"  >  

<tr >
<td>
<c:forEach var="county" items="${VIEW_LEPC_COUNTY_LIST}" varStatus="p">
County: <b><c:out value="${county.lepcCounty}"/></b></br>
 <%-- ROSTER STUFF --%>
  <c:forEach var="roster" items="${county.lepcCountyRoster}" varStatus="h">
  <table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;" >
 
  <tbody class="rowHeader">  
      <tr>
      <th > </th>
      <td>
        Roster Added by:&nbsp;<c:out value='${roster.rosterAddBy}'/> &nbsp; Date: &nbsp;<c:out value='${roster.rosterDateString}'/>&nbsp;</br>
       <c:if test="${roster.rosterStatus =='Pending'}"> 
       <font color="red"> Status: <b> <c:out value='${roster.rosterStatus}'/></b></br></font>
       </c:if>
        <c:if test="${roster.rosterStatus =='Approved'}"> 
       <font color="green"> Status: <b> <c:out value='${roster.rosterStatus}'/></b></br></font>
       </c:if>
      </td>
      </tr>
 </tbody>
 </table>
</c:forEach></br></br>
 </c:forEach>
</td>
</tr>
</tbody>
</table>
</c:if>
</div>
  <div class="clearer">&nbsp;</div>
 