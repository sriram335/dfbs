<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Variance Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/><hs:control/>
<link rel="stylesheet" type="text/css" media="all" href="/dfbs/idhsInspections/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="/dfbs/idhsInspections/jsDatePick.min.1.3.js"></script>
<script type="text/javascript">
window.onload = function(){
		new JsDatePick({
			useMode:2,
			target:"varCommDate",
			dateFormat:"%m/%d/%Y"
		});
		new JsDatePick({
			useMode:2,
			target:"varCommPrintDate",
			dateFormat:"%m/%d/%Y"
		});
	};
</script>
<div id="mainBox">
<div id="sideContentFluid">
   
 <b><u>Applications to be processed</u></b></br>
 <html:form action="/start" method="post" >

    <input type="hidden" name="method" id="METHOD_KEY" value="processCompApplications"/> 
          <input type="submit" value="Show Applications to be Processed" class="button"/>
        </br>        

</html:form> </br>
<html:form action="/start" method="post" >

    <input type="hidden" name="method" id="METHOD_KEY" value="processComDateApplications"/> 
          <input type="submit" value="Show Last Commission Variances " class="button"/>
        </br>        

</html:form> </br>
<c:if test="${DFBS_SECURITY.userId != null}"> 
 <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsmailipdf&report=variance_commission_report.rdf&p_variance_number=<c:out value="${VARIANCE_APPLICATION_SELECTED.varAppVarNumber}"/>&p_commission_date=<c:out value="${VARIANCE_VIEW_CODE.varCommDateString}"/>
                &from=<c:out value="${DFBS_SECURITY.userId}"/>@dhs.in.gov&desname=<c:out value="${sessionScope.VARIANCE_APP_EMAIL_LIST}"/>&subject=commission report attached">[ Email Commission Letter ]</a> <br/> 
</c:if>
<table cellspacing="0"  summary="sales">
  <tr>
    <th>Variance Details</th>
   
  </tr>
  <tbody>
       <c:forEach var="varApp" items="${requestScope.VARIANCE_APPLICATION_LIST}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message" style="font-weight:bold;font-size:medium;">
         <b> <c:out value="${varApp.varAppVarNumber}"/>&nbsp;</span></b></br>
         Project Name:<c:out value="${varApp.varProjName}"/>&nbsp;</br>
         Application Date:<c:out value="${varApp.varAppRecDateString}"/>&nbsp;</br>
          <c:forEach var="code" items="${varApp.varCodesList}" varStatus="i">
          Code Name:<c:out value="${code.varCode}"/>&nbsp;</br>
          Code Specific Section:<c:out value="${code.varCodeName}"/>&nbsp;</br>
             <a   href="/dfbs/variance/code.do?method=viewCode&varCodeId=<c:out value="${code.varCodeId}"/>">
             [view code Details]</a> 
          </c:forEach>
        </td>
      </tr>
       </c:forEach>
  </tbody>
</table>
    </p>
</div>

<div id="leftContentFluid">

<h1><b><u>Variance Code(s)</u></b></h1></br> </br> 
 
 
<html:form action="/code" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="updateCode"/>
     <html:hidden property="varId"/> 
     <html:hidden property="varCodeId"/> 
     <html:hidden property="varAppPhAffirm"/>
     <html:hidden property="varAppPhAffirmComment"/>
     <html:hidden property="varAppHistAffPhy"/>
     <html:hidden property="varAppHistAffMaj"/>
     <html:hidden property="varAppHistAffExc"/>
     <html:hidden property="varAppHistAffHist"/>
     <html:hidden property="varAppHistAffPhyComment"/>
      <html:hidden property="staffCommentsRec"/>
       <html:hidden property="staffComments"/>
     
<table cellspacing="0" class="noBorder" summary="OWNER FORM" >
     <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
  
      <tr>
      <th > <b>Variance Number</b> </th>
      <td >
           <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppVarNumber}"/>
        </td>
    </tr>
    <tr>
      <th > <b>Variance Edition</b> </th>
      <td >
           <html:text property="varEdition" size="10" maxlength="10"/>
        </td>
    </tr>
    <tr>
      <th > <b>Variance Code Name</b> </th>
      <td >
           <html:text property="varCode" size="40" maxlength="200"/>
        </td>
    </tr>
    <tr>
      <th > <b>Code Specific Section</b> </th>
      <td >
           <html:text property="varCodeName" size="30" maxlength="100"/>
        </td>
    </tr>
     <tr>
      <th > <b>Variance Information</b><br>(max 2000 characters)</th>
      <td >
       <html:textarea property="varNatureNonComp" rows="10" cols="40"/>
        </td>
    </tr>
      <tr>
      <th > <b>Variance Commission Conditions</b><br>(max 2000 characters)</th>
      <td >
       <html:textarea property="varAppStaffCommConditions" rows="10" cols="40"/>
        </td>
    </tr>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelVar != null || sessionScope.DFBS_SECURITY.groupLevelAll != null}">  
 <tr>
      <th > <b>Variance Commission Status</b> </th>
      <td >
           <html:select property="varCommStatus" >
                <html:option value="">--</html:option>
                <html:options  collection="VAR_COMM_ACTION_OPTIONS" property="value" labelProperty="label" />
                </html:select>  
        </td>
    </tr>
    <tr>
      <th > <b>Variance Commission Date</b> </th>
      <td >
           <input type="text" size="10" maxlength="10" name="varCommDate" id="varCommDate" /> mm/dd/yyyy
        </td>
    </tr>
    <tr>
      <th > <b>Variance Print Date</b> </th>
      <td >
           <input type="text" size="10" maxlength="10" name="varCommPrintDate" id="varCommPrintDate" /> mm/dd/yyyy
        </td>
    </tr>
    <tr>
      <th > Comm. Letter Release </th>
      <td >
     <c:if test="${VARIANCE_VIEW_CODE.varReleaseFlag != 'Y'}">
           <input type="checkbox" name="varReleaseFlag"  class="switch"  value="Y"/> 
      </c:if>
       <c:if test="${VARIANCE_VIEW_CODE. varReleaseFlag == 'Y'}">
              <input type="checkbox" name="varReleaseFlag"  class="switch"  value="Y" checked/>
       </c:if>
      </td>
    </tr>
 </c:if>
 <tr>
     <th scope="row"     > </th>
    <td>
    <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelVar != null }">  
         <html:submit value="Update" styleClass="button" />
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