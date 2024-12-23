<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Variance Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
<b><u> Calendar </u></b>
<table  >
 <tr>
    <th >If we receive Application by this - Date</th>
    <th >If application is complete it can go before -Commission Date</th>
   </tr>
     <tbody>
   <tr>
 <c:forEach var="file" items="${requestScope.VARIANCE_COMM_DATES}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
      </td>
        <td>
       <c:out value="${file.fileType}"/>&nbsp; <br/> 
         </td>
      <%--  <td>
        <c:out value="${file.filePlanType}"/>&nbsp; <br/> 
        </td> --%>
        
</tr><br/>
</c:forEach>
</tbody>
</table>
</div>
<div id="mainBox">

<div id="leftContentFluid">

<h1><b><u>Variance Application(s)</u></b></h1></br> </br> 

<b><u>Variance Application</u></b>
<c:if test="${VARIANCE_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.VARIANCE_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${VARIANCE_APP_STATUS != 'I'}">
<html:form action="/start" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="startApplication"/> 
 
<p class="messageBox" align="left">
The monthly meetings of the Fire Prevention and Building Safety Commission are held in the Indiana Government South Center Conference Room B, beginning at 9 a.m.  The public entry to the building is located at 302 W. Washington Street, Indianapolis,IN 46204.
</br>
Late filings may be placed on the commission's agenda for the next commission meeting without having to comply with the twenty-one (21) day time limit, mentioned above, if the applicant would be prejudiced by having to wait for a later meeting because of excessive loss of time or unreasonable cost. A request for a late filing to be placed on the next commission's agenda must put forth specific and reliable evidence showing that the loss of time would be excessive or the costs would be unreasonable.
</br>In order to facilitate late filing requests, please contact the Director of the Fire Prevention and Building Safety Commission, Douglas Boyle, at doboyle@dhs.in.gov.
 </br></br>
  <span class="error">   <b><u>IDHS SBC Project Number (If known) <html:text property="varAppSBCNum" size="12" maxlength="12"/></br> This information will help prefill parts of variance application
         </u></b></span></br> </br>  <input type="submit" value="Start New Application" class="button"/>
        </br>  </br>  </p>
  </html:form> 
  <html:form action="/start" method="post" >
<p class="messageBox" align="left">
    <input type="hidden" name="method" id="METHOD_KEY" value="updateVarApplication"/> 
          <input type="submit" value="Update Existing Application" class="button"/>
        </br>        
</p>
</html:form>  <br/> 
</c:if>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelVar != null }">  
<html:form action="/start" method="post" >
<p class="messageBox" align="left">
    <input type="hidden" name="method" id="METHOD_KEY" value="processCompApplications"/> 
          <input type="submit" value="Process Commission Action Letter(s)" class="button"/>
        </br>        
</p>
</html:form> 
</c:if>

<html:form action="/start" method="post" >
<p class="messageBox" align="left">
IDHS Variance Application view portal<br/> 
    <input type="hidden" name="method" id="METHOD_KEY" value="viewApplications"/> 
          <input type="submit" value="Search, View & Print Variance Information" class="button"/>
        </br>        
</p>
</html:form> 
 
 


 
<%--<b><u>Complete Variance Application</u></b>
<html:form action="/start" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="completeApplication"/> 
 
<p class="messageBox" align="left">
 To complete the Application you need the variance number assigned to your Pre-Application. IDHS will send this variance number by email
 to the pre-application submitter.</br></br>
              Variance Number:<html:text property="varAppVarNumber" size="12" maxlength="12"/></br> </br>  
            <input type="submit" value="Complete Application" class="button"/>
        </br>        
</p>
</html:form> 
<%--<html:form action="/start" method="post" >
<p class="messageBox" align="left">
    <input type="hidden" name="method" id="METHOD_KEY" value="processPreApplications"/> 
          <input type="submit" value="Process Pre-Application(s)" class="button"/>
        </br>        
</p>
</html:form> --%>



<div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 