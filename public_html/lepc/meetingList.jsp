<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - DFBS system"/>
<c:set var="title" scope="request" value="Indiana LEPC's Meeting"/>
<jsp:include page="/main/IdhsPageHeader.jsp"/></br>
 <a   href="/dfbs/otherUsers/otherUser.do?method=logOutLepc" >
              [log out LEPC]</a> 
<a   href="/dfbs/lepc/lepc.do?method=viewLepcYear&lepcId=<c:out value="${sessionScope.LEPC_SELECTED_ID}"/>">
                 [back to LEPC]</a>
<div id="mainBox">
  <h2><c:out value='${sessionScope.LEPC_COUNTY_SELECTED}'/> County LEPC Meeting Details</h2>
<a   href="/dfbs/lepc/meeting.do?method=addMeeting">
             Add New Meeting</a></br>
 <c:if test="${(sessionScope.DFBS_SECURITY.groupLevelLepc != null ) }"> 
  <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=lepc_meeting_schedule">
              [meeting schedule for all counties]</a> &nbsp;&nbsp;    
   <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepyopen&report=lepc_meeting_schedule&destype=mail&desformat=delimiteddata&FROM=ierc@dhs.in.gov&desname=iwusi@dhs.in.gov&subject=LEPC meeting schedule attached">[meeting schedule .csv for all counties]</a> &nbsp;&nbsp;
 </c:if>
<a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=lepc_meeting_schedule&p_county=<c:out value='${sessionScope.LEPC_COUNTY_SELECTED}'/>">
              [print meeting schedule for this county]  </a> </br>
<a   href="/dfbs/lepc/meeting.do?method=sendEmail&lepcId=<c:out value="${sessionScope.LEPC_SELECTED_ID}"/>">
             Send Updated Meeting Schedule to LEPC Roster</a></br>
<div id="leftContentFluid">
<table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader">  
<c:forEach var="meeting" items="${LEPC_MEETING_LIST.list}" varStatus="i" >
<tr>
<td>
<h3><a   href="/dfbs/lepc/meeting.do?method=updateMeeting&meetingId=<c:out value='${meeting.meetingId}'/>">
<c:out value='${meeting.meetingDateString}'/> &nbsp; :&nbsp;<c:out value='${meeting.meetingTime}'/>  </a>&nbsp;&nbsp;
 </h3> 
 Address: <c:out value='${meeting.meetingAddress1}'/></br>
<c:out value='${meeting.meetingAddress2}'/></br>
<c:out value='${meeting.meetingCity}'/>, <c:out value='${meeting.meetingState}'/>,<c:out value='${meeting.meetingZip}'/></br>
<c:out value='${meeting.meetingContact}'/></br>
<c:out value='${meeting.meetingContactPhone}'/></br>
<c:out value='${meeting.meetingContactEmail}'/></br>
<a   href="/dfbs/lepc/meeting.do?method=updateMeeting&meetingId=<c:out value='${meeting.meetingId}'/>"> [View/Update] </a> </br>
<a   href="/dfbs/lepc/meeting.do?method=copyMeeting&meetingId=<c:out value='${meeting.meetingId}'/>"> [Use this meeting info to create new meeting] </a>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
  <div class="clearer">&nbsp;</div>
 </div> 