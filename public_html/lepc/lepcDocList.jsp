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
<a   href="/dfbs/otherUsers/otherUser.do?method=logOutLepc" >
              [log out LEPC]</a>  &nbsp;&nbsp;
 <a   href="/dfbs/lepc/lepc.do?method=start">
              [back to start]</a></br>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null}"> 
  <h2><c:out value='${sessionScope.LEPC_COUNTY_SELECTED}'/> County LEPC </br>Compliance Document List & Status </br>
      Year-end Approval By:<c:out value='${sessionScope.LEPC_SELECTED.lepcApprovedBy}'/>&nbsp;&nbsp;</br>
      Year-end Approval Date:<c:out value='${sessionScope.LEPC_SELECTED.lepcApprovedDateString}'/>&nbsp;&nbsp;</br>
      <c:if test="${sessionScope.LEPC_SELECTED.lepcStatus == 'Approved'}">
     <font color="green"> Staus: <c:out value='${sessionScope.LEPC_SELECTED.lepcStatus}'/> </font>
     </c:if>
       <c:if test="${sessionScope.LEPC_SELECTED.lepcStatus == null||sessionScope.LEPC_SELECTED.lepcStatus == 'Pending'}">
     <font color="red"> Staus: <c:out value='${sessionScope.LEPC_SELECTED.lepcStatus}'/> </font></br>
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER'}"> 
       <a   href="/dfbs/lepc/lepc.do?method=approveLepc&lepcId=<c:out value="${sessionScope.LEPC_SELECTED.lepcId}"/>">
                 Approve LEPC for this year</a></br>
     </c:if>
     </c:if>
      </h2>
     
<div id="leftContentFluid">
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER'}"> 
 LEPC Notes(1000 char max):<c:out value='${sessionScope.LEPC_SELECTED.lepcNotes}'/> </br>
       <a   href="/dfbs/lepc/lepc.do?method=updateLepcNotes&lepcId=<c:out value="${sessionScope.LEPC_SELECTED.lepcId}"/>">
                 Edit Notes</a></br>
        <a   href="/dfbs/lepc/lepc.do?method=emailNotes&lepcId=<c:out value="${sessionScope.LEPC_SELECTED.lepcId}"/>">
                 Email Notes to LEPC contact(s)</a></br>
    <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=lepc_roster.rdf&p_county=
   <c:out value="${sessionScope.LEPC_COUNTY_SELECTED}"/>">[ roster report]</a></br>
    <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=lepc_roster_IERC_approval.rdf&p_county=
   <c:out value="${sessionScope.LEPC_COUNTY_SELECTED}"/>">[ roster report for IERC approval]</a></br>
</c:if>
      
<table cellspacing="0"   summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader"  >  

<tr >
<th  style="text-align:left;">1.&nbsp; Legal Notices </br> <font color="red">(Due by January 31)</font></br>Please click on "LEPC Meeting Schedule" to input your meeting dates, times and locations for the entire year. <br><font color="red">Legal Notice will not be approved if Meeting Schedule is not submitted.</font></th>
<td>
<html:form action="/lepc" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="viewLLTutorial"/>
      <input type="image" src="../img/lepc_ll.png" name="viewLLTutorial" value="viewLLTutorial"/>
</html:form>
<a   href="/dfbs/lepc/lepc.do?method=goToUpload&docType=<c:out value='${file.fileId}'/>">
             Upload Legal Notice</a></br>
<a   href="/dfbs/lepc/meeting.do?method=meetingList">
            LEPC Meeting Schedule</a></br>
<c:forEach var="file" items="${sessionScope.LEPC_DOC_LIST_LEGAL}" varStatus="i">
       <b> <c:out value="${file.fileName}"/></b> <br/> 
       File date:<b><c:out value="${file.fileDateString}"/></b>&nbsp; File sent by:<b><c:out value="${file.fileLoadedBy}"/></b>;&nbsp;</br>
       <c:if test="${file.filePlanType == 'Pending'}"> 
     <font color="red"> Status:<b><c:out value="${file.filePlanType}"/></b></font></br>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelLepc != null }"> 
      <a   href="/dfbs/lepc/lepc.do?method=Approve&docId=<c:out value="${file.fileId}"/>&docName=<c:out value="${file.fileName}"/>">
                 Approve</a>
        </c:if>
      </c:if>
       <c:if test="${file.filePlanType == 'Approved'}"> 
      <font color="green"> Status:<b><c:out value="${file.filePlanType}"/></b></font>
      </c:if>
           <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="downLoadFile"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="hidden" name="fileName"  value="${file.fileName}"/> 
          <input type="hidden" name="fileType"   value="${file.fileType}"/> 
           <input type="submit" value="View" class="button"/>
         </html:form>&nbsp;
         <c:if test="${(file.fileLoadedBy == OTHER_USER.userLoginId && file.filePlanType != 'Approved')||(sessionScope.DFBS_SECURITY.groupLevelLepc != null && file.filePlanType != 'Approved') }"> 
          <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="deleteFile"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="submit" value="Delete" class="button"/>
         </html:form>
         </c:if>
             
</c:forEach>
</td>
</tr>
<tr>
<th  style="text-align:left;">2.&nbsp; Roster<font color="red"> (Due by March 1)</font></br> Note: Ethics training required for all roster members  </th>
<td>
<html:form action="/lepc" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="viewRosterTutorial"/>
      <input type="image" src="../img/lepc_roster.png" name="viewRosterTutorial" value="viewRosterTutorial"/>
</html:form>
  <c:if test="${LEPC_DOC_ROSTER != 'null' }">
  <a   href="/dfbs/lepc/roster.do?method=updateRoster&lepcId=<c:out value='${sessionScope.LEPC_SELECTED_ID}'/>">
                  Update Roster</a></br>
   <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=lepc_roster.rdf&p_county=<c:out value="${sessionScope.LEPC_COUNTY_SELECTED}"/>&p_year=<c:out value="${sessionScope.LEPC_SELECTED_CURRENT_YEAR}"/>"> Print Roster </a></br>
           
  </c:if>
 <%-- <c:if test="${LEPC_DOC_ROSTER != 'null' && LEPC_DOC_ROSTER.rosterStatus =='Approved'}">
  <a   href="/dfbs/lepc/roster.do?method=updateRoster&lepcId=<c:out value='${sessionScope.LEPC_SELECTED_ID}'/>">
                  View Roster</a></br>
  </c:if> --%>
  Roster Added by:&nbsp;<c:out value='${sessionScope.LEPC_DOC_ROSTER.rosterAddBy}'/> &nbsp; Date: &nbsp;<c:out value='${sessionScope.LEPC_DOC_ROSTER.rosterDateString}'/>&nbsp;</br>
 <c:if test="${LEPC_DOC_ROSTER.rosterStatus =='Pending'}"> 
 <font color="red"> Status: <b> <c:out value='${sessionScope.LEPC_DOC_ROSTER.rosterStatus}'/></b></br></font>
 </c:if>
  <c:if test="${LEPC_DOC_ROSTER.rosterStatus =='Approved'}"> 
 <font color="green"> Status: <b> <c:out value='${sessionScope.LEPC_DOC_ROSTER.rosterStatus}'/></b></br></font>
 </c:if>
  <c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelLepc != null) &&LEPC_DOC_ROSTER.rosterStatus !='Approved' }"> 
  <html:form action="/roster" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="approve"/> 
          <input type="hidden" name="rosterId"   value ="${sessionScope.LEPC_DOC_ROSTER.rosterId}"/> 
           <html:text property="rosterAddress2" size="10" maxlength="10"/> 
            <c:if test="${sessionScope.ROSTER_APPROVED_DATE == 'ERROR'}"> 
             <span class="error">you have to enter roster date</span>
            </c:if>  
          <input type="submit" value="Approve Roster with above date" class="button"/>
         </html:form>&nbsp; <br/>
  <%--
      <a   href="/dfbs/lepc/roster.do?method=approve&rosterId=<c:out value='${sessionScope.LEPC_DOC_ROSTER.rosterId}'/>&docName=Roster">
                 Approve</a>
  --%>
  </c:if>
  <c:if test="${LEPC_DOC_ROSTER.rosterId == null }">
  <a   href="/dfbs/lepc/roster.do?method=addRoster&lepcId=<c:out value='${sessionScope.LEPC_SELECTED_ID}'/>">
                  Add Roster</a>
  </c:if>
  </td>
</tr>
<tr>
<th  style="text-align:left;">3.&nbsp; Fiscal Report </br><font color="red">(Due by March 1)</font> <br>County Auditor's report MUST be submitted </th>
<td>
<html:form action="/lepc" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="viewFiscalTutorial"/>
      <input type="image" src="../img/lepc_fiscal.png" name="viewFiscalTutorial" value="viewRosterTutorial"/>
</html:form>
<a   href="/dfbs/lepc/lepc.do?method=goToUpload&docType=<c:out value='${file.fileId}'/>">
             Upload County Auditor's Report</a></br>
<c:if test="${LEPC_FISCAL_REPORT != null && LEPC_FISCAL_REPORT.fiscalStatus =='Pending'}">
<a   href="/dfbs/lepc/fiscalReport.do?method=updateFiscalReport&fiscalId=<c:out value="${LEPC_FISCAL_REPORT.fiscalId}"/>">
                 Update Annual Fiscal Expenditure and Receipts</a></br>
</c:if>
<c:if test="${LEPC_FISCAL_REPORT != null && LEPC_FISCAL_REPORT.fiscalStatus =='Approved'}">
<a   href="/dfbs/lepc/fiscalReport.do?method=updateFiscalReport&fiscalId=<c:out value="${LEPC_FISCAL_REPORT.fiscalId}"/>">
                 View Fiscal Report</a></br>
</c:if>
 <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=lepc_fiscal_report.rdf&p_lepc_fiscal_id=<c:out value="${LEPC_FISCAL_REPORT.fiscalId}"/>"> Print Fiscal Report </a></br>
 
<c:if test="${LEPC_FISCAL_REPORT == null || LEPC_FISCAL_REPORT.fiscalStatus == null }">
<a   href="/dfbs/lepc/fiscalReport.do?method=addFiscalReport">
                 Add Fiscal Report</a> </br>
</c:if>
<c:forEach var="file" items="${sessionScope.LEPC_DOC_LIST_FISCAL}" varStatus="i">
        <b> <c:out value="${file.fileName}"/></b> <br/> 
                    File date:<b><c:out value="${file.fileDateString}"/></b>&nbsp; File sent by:<b><c:out value="${file.fileLoadedBy}"/></b>;&nbsp;</br>
      <c:if test="${file.filePlanType == 'Pending'}"> 
     <font color="red"> Status:<b><c:out value="${file.filePlanType}"/></b></font></br>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelLepc != null }"> 
      <a   href="/dfbs/lepc/lepc.do?method=Approve&docId=<c:out value="${file.fileId}"/>&docName=<c:out value="${file.fileName}"/>">
                 Approve</a>
        </c:if>
      </c:if>
       <c:if test="${file.filePlanType == 'Approved'}"> 
      <font color="green"> Status:<b><c:out value="${file.filePlanType}"/></b></font>
      </c:if>
        <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="downLoadFile"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="hidden" name="fileName"  value="${file.fileName}"/> 
          <input type="hidden" name="fileType"   value="${file.fileType}"/> 
           <input type="submit" value="View" class="button"/>
         </html:form>&nbsp;
       <c:if test="${(file.fileLoadedBy == OTHER_USER.userLoginId && file.filePlanType != 'Approved')||(sessionScope.DFBS_SECURITY.groupLevelLepc != null && file.filePlanType != 'Approved') }"> 
         <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="deleteFile"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="submit" value="Delete" class="button"/>
         </html:form>
         </c:if>
 </c:forEach>
</td>
</tr>
<tr>
<th  style="text-align:left;">4.&nbsp; LEPC Plan Update </br><font color="red"> (Due by October 17) </font></br> Submitted plan updates MUST be reviewed and approved by your LEPC </th>
<td>
<html:form action="/lepc" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="viewPlanTutorial"/>
      <input type="image" src="../img/lepc_plan.png" name="viewPlanTutorial" value="viewPlanTutorial"/>
</html:form>
<a   href="/dfbs/lepc/lepc.do?method=goToUpload&docType=<c:out value='${file.fileId}'/>">
             Upload Updated Plan Document</a><br/>
<c:forEach var="file" items="${sessionScope.LEPC_DOC_LIST_PLAN}" varStatus="i">
        <b> <c:out value="${file.fileName}"/></b> <br/> 
                   File date:<b><c:out value="${file.fileDateString}"/></b>&nbsp; File sent by:<b><c:out value="${file.fileLoadedBy}"/></b>;&nbsp;</br>
     <font color="red"> Status:<b><c:out value="${file.filePlanType}"/></b></font></br>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelLepc != null }"> 
      <a   href="/dfbs/lepc/lepc.do?method=Approve&docId=<c:out value="${file.fileId}"/>&approvalType=Reviewed&docName=<c:out value="${file.fileName}"/>">
                 IERC Reviewed</a>
       <a   href="/dfbs/lepc/lepc.do?method=Approve&docId=<c:out value="${file.fileId}"/>&approvalType=Viewed&docName=<c:out value="${file.fileName}"/>">
                 IERC Viewed</a>
         <a   href="/dfbs/lepc/lepc.do?method=Approve&docId=<c:out value="${file.fileId}"/>&approvalType=Previewed&docName=<c:out value="${file.fileName}"/>">
                 IERC Previewed</a>
       
      </c:if>
       <c:if test="${file.filePlanType == 'Reviewed'}"> 
      <font color="green"> Status:<b><c:out value="${file.filePlanType}"/></b></font>
      </c:if>
         <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="downLoadFile"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="hidden" name="fileName"  value="${file.fileName}"/> 
          <input type="hidden" name="fileType"   value="${file.fileType}"/> 
           <input type="submit" value="View" class="button"/>
         </html:form>&nbsp;
        <c:if test="${(file.fileLoadedBy == OTHER_USER.userLoginId && file.filePlanType != 'Approved')||(sessionScope.DFBS_SECURITY.groupLevelLepc != null && file.filePlanType != 'Approved') }"> 
           <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="deleteFile"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="submit" value="Delete" class="button"/>
         </html:form>
         </c:if>
  </c:forEach>
</td>
</tr>
<tr>
<th  style="text-align:left;">5.&nbsp; Bylaws <br/><font color="red">(Due by March 1)</font> <br>Must be signed and dated   </th>
<td>
<html:form action="/lepc" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="viewByLawTutorial"/>
      <input type="image" src="../img/lepc_laws.png" name="viewByLawTutorial" value="viewByLawTutorial"/>
</html:form>
<a   href="/dfbs/lepc/lepc.do?method=goToUpload&docType=<c:out value='${file.fileId}'/>">
             Upload Bylaws Document</a><br/>
<c:forEach var="file" items="${sessionScope.LEPC_DOC_LIST_BYLAWS}" varStatus="i">
      <b> <c:out value="${file.fileName}"/></b> <br/> 
                   File date:<b><c:out value="${file.fileDateString}"/></b>&nbsp; File sent by:<b><c:out value="${file.fileLoadedBy}"/></b>;&nbsp;</br>
   <c:if test="${file.filePlanType == 'Pending'}"> 
     <font color="red"> Status:<b><c:out value="${file.filePlanType}"/></b></font></br>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelLepc != null }"> 
      <a   href="/dfbs/lepc/lepc.do?method=Approve&docId=<c:out value="${file.fileId}"/>&docName=<c:out value="${file.fileName}"/>">
                 Approve</a>
        </c:if>
      </c:if>
       <c:if test="${file.filePlanType == 'Approved'}"> 
      <font color="green"> Status:<b><c:out value="${file.filePlanType}"/></b></font>
      </c:if>
       
        <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="downLoadFile"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="hidden" name="fileName"  value="${file.fileName}"/> 
          <input type="hidden" name="fileType"   value="${file.fileType}"/> 
           <input type="submit" value="View" class="button"/>
         </html:form>&nbsp;
        <c:if test="${(file.fileLoadedBy == OTHER_USER.userLoginId && file.filePlanType != 'Approved')||(sessionScope.DFBS_SECURITY.groupLevelLepc != null && file.filePlanType != 'Approved') }"> 
            <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="deleteFile"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="submit" value="Delete" class="button"/>
         </html:form>
         </c:if>
</c:forEach>
</td>
</tr>

<tr>
<th  style="text-align:left;">6.&nbsp; Exercise Proposal </br><font color="red">(Due 30 days prior to exercise) </font>    </th>
<td>
<html:form action="/lepc" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="viewExProTutorial"/>
      <input type="image" src="../img/lepc_ex_pro.png" name="viewExProTutorial" value="viewExProTutorial"/>
</html:form>
<a   href="https://oas.dhs.in.gov/dfbs/lepc/lepc.do?method=downLoadFile&fileName=Instructions for Exercise Proposal Notification (2012).doc 
&fileId=67246&fileType=MASTER" target="_blank">
             [View Exercise Rules /Instructions]</a></br>
<%--<c:if test="${ LEPC_PROPOSAL == null || LEPC_PROPOSAL.exerciseId == 0}">--%>
<a   href="/dfbs/lepc/exercise.do?method=addProposal&lepcId=<c:out value='${sessionScope.LEPC_SELECTED_ID}'/>">
                Add Exercise Proposal</a></br>


<c:forEach var="proposal" items="${sessionScope.LEPC_PROPOSAL_LIST}" varStatus="i">
Proposal Date:<c:out value='${proposal.exerciseStartDateString}'/>
<c:if test="${proposal != 'null' && proposal.exerciseStatus =='Approved' && proposal.reportType == 'Proposal' }">                
<a   href="/dfbs/lepc/exercise.do?method=updateExercise&exerciseId=<c:out value='${proposal.exerciseId}'/>">
                View Exercise Proposal</a>                
</c:if>
<c:if test="${proposal != 'null' && proposal.exerciseStatus =='Pending' && proposal.reportType == 'Proposal' }">                
<a   href="/dfbs/lepc/exercise.do?method=updateExercise&exerciseId=<c:out value='${proposal.exerciseId}'/>">
                Update Exercise Proposal</a>                
</c:if>
    <c:if test="${proposal.exerciseStatus =='Pending'}"> 
     <font color="red"> Status:<b><c:out value="${proposal.exerciseStatus}"/></b></font></br>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelLepc != null }"> 
      <a   href="/dfbs/lepc/exercise.do?method=approve&exerciseId=<c:out value='${proposal.exerciseId}'/>&docName=Exercise Proposal">
                 Approve</a></br>
        </c:if>
      </c:if>
       <c:if test="${proposal.exerciseStatus == 'Approved'}"> 
      <font color="green"> Status:<b><c:out value="${proposal.exerciseStatus}"/></b></font></br>
      </c:if>
</c:forEach>
</tr>
<tr>
<th  style="text-align:left;">7.&nbsp; Exercise Report </br><font color="red">(Due 30 days after exercise and by December 31 for December exercises )</font>      </th>
<td>
<html:form action="/lepc" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="viewExRepTutorial"/>
      <input type="image" src="../img/lepc_ex_rep.png" name="viewExRepTutorial" value="viewExRepTutorial"/>
</html:form>
<a   href="https://oas.dhs.in.gov/dfbs/lepc/lepc.do?method=downLoadFile&fileName=Instructions for Final exercise credit report (2012).doc 
&fileId=67247&fileType=MASTER" target="_blank">
             [View Exercise Rules /Instructions]</a></br>
<%--<c:if test="${LEPC_EXERCISE == null || LEPC_EXERCISE.exerciseId == 0}"> --%>
<a   href="/dfbs/lepc/exercise.do?method=addExercise&lepcId=<c:out value='${sessionScope.LEPC_SELECTED_ID}'/>">
                Add Exercise Report</a></br>
<c:forEach var="exercise" items="${sessionScope.LEPC_EXERCISE_LIST}" varStatus="i">
<c:if test="${exercise != 'null' && exercise.exerciseStatus =='Pending' && exercise.reportType == 'Exercise'}">                
<a   href="/dfbs/lepc/exercise.do?method=updateExercise&exerciseId=<c:out value='${exercise.exerciseId}'/>">
                Update Exercise Report</a>                
</c:if>
<c:if test="${exercise != 'null' && exercise.exerciseStatus =='Approved' && exercise.reportType == 'Exercise'}">                
<a   href="/dfbs/lepc/exercise.do?method=updateExercise&exerciseId=<c:out value='${exercise.exerciseId}'/>">
                View Exercise Report</a>                
</c:if></br>
<c:if test="${exercise.exerciseStatus =='Pending'}"> 
     <font color="red"> Status:<b><c:out value="${exercise.exerciseStatus}"/></b></font></br>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelLepc != null }"> 
      <a   href="/dfbs/lepc/exercise.do?method=approve&exerciseId=<c:out value='${exercise.exerciseId}'/>&docName=Exercise Report">
                 Approve</a></br>
        </c:if>
      </c:if>
       <c:if test="${exercise.exerciseStatus == 'Approved'}"> 
      <font color="green"> Status:<b><c:out value="${exercise.exerciseStatus}"/></b></font></br>
      </c:if>
  </c:forEach>
  </td>
</tr>
<tr>
<th  style="text-align:left;">8.&nbsp; Meeting Minutes with meeting sign in sheets </br><font color="red">(Due 2 months after each meeting ) </font>      </th>
<td>
<html:form action="/lepc" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="viewSignTutorial"/>
      <input type="image" src="../img/lepc_sign.png" name="viewSignTutorial" value="viewSignTutorial"/>
</html:form>
  <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=lepc_roster_sign_up.rdf&p_county=<c:out value="${sessionScope.LEPC_COUNTY_SELECTED}"/>&p_year=<c:out value="${sessionScope.LEPC_SELECTED_CURRENT_YEAR}"/>"> Print Sign-In Sheet </a></br>
 <a   href="/dfbs/lepc/lepc.do?method=goToUpload&docType=<c:out value='${file.fileId}'/>">
             Upload Meeting Minutes and Sign-In Sheets</a><br/>
<c:forEach var="file" items="${sessionScope.LEPC_DOC_LIST_MINUTES}" varStatus="i">
       <b> <c:out value="${file.fileName}"/></b> <br/>  
                 File date:<b><c:out value="${file.fileDateString}"/></b>&nbsp; File sent by:<b><c:out value="${file.fileLoadedBy}"/></b>;&nbsp;</br>
      <c:if test="${file.filePlanType == 'Pending'}"> 
     <font color="red"> Status:<b><c:out value="${file.filePlanType}"/></b></font></br>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelLepc != null }"> 
      <a   href="/dfbs/lepc/lepc.do?method=Approve&docId=<c:out value="${file.fileId}"/>&docName=<c:out value="${file.fileName}"/>">
                 Approve</a>
        </c:if>
      </c:if>
       <c:if test="${file.filePlanType == 'Approved'}"> 
      <font color="green"> Status:<b><c:out value="${file.filePlanType}"/></b></font>
      </c:if>
        <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="downLoadFile"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="hidden" name="fileName"  value="${file.fileName}"/> 
          <input type="hidden" name="fileType"   value="${file.fileType}"/> 
           <input type="submit" value="View" class="button"/>
         </html:form>&nbsp;
    <c:if test="${(file.fileLoadedBy == OTHER_USER.userLoginId && file.filePlanType != 'Approved')||(sessionScope.DFBS_SECURITY.groupLevelLepc != null && file.filePlanType != 'Approved') }"> 
                <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="deleteFile"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="submit" value="Delete" class="button"/>
         </html:form>
         </c:if>
 </c:forEach>
</td>
</tr>

</tbody>
</table>
</div>
  <div class="clearer">&nbsp;</div>
  </c:if>
 </div> 