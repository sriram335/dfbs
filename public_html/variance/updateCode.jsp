<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Variance Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
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
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelVar != null }">  
<a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepyhtml&report=variance_agenda_report.rdf ">
       [ Agenda Report]</a><br/>
<a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepyhtml&report=variance_staff_comments_report.rdf ">
       [ Staff Comments Report]</a><br/>
<a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepyhtml&report=variance_staff_comments_report_by_varid.rdf">[ Staff Comments by ID# ]</a> <br/>
<a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsmailipdf&report=variance_commission_report.rdf&p_variance_number=<c:out value="${VARIANCE_APPLICATION_SELECTED.varAppVarNumber}"/>&p_commission_date=<c:out value="${VARIANCE_VIEW_CODE.varCommDateString}"/>
                &from=<c:out value="${DFBS_SECURITY.userId}"/>@dhs.in.gov&desname=<c:out value="${sessionScope.VARIANCE_APP_EMAIL_LIST}"/>&subject=commission report attached">[ Email Commission Letter ]</a> <br/> 
<a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=variance_commission_report.rdf&p_variance_number=<c:out value="${VARIANCE_APPLICATION_SELECTED.varAppVarNumber}"/>&p_commission_date=<c:out value="${VARIANCE_VIEW_CODE.varCommDateString}"/>">[ Print Commission Letter ]</a> <br/>
 
 </c:if> 
   <c:if test="${sessionScope.idhsInsp == null}">
    <c:forEach var="code" items="${VARIANCE_APPLICATION_SELECTED.varCodesList}" varStatus="i">
          Code Name:<c:out value="${code.varCode}"/>&nbsp;
             <a   href="/dfbs/variance/code.do?method=viewCodeDetail&varCodeId=<c:out value="${code.varCodeId}"/>&varId=<c:out value="${VARIANCE_VIEW_CODE.varId}"/>">
             [view code Details]</a> <br/> 
    </c:forEach><br>
   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelVar != null }">  
    <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppVarNumber == null}">
     <a   href="/dfbs/variance/code.do?method=updateVarNum&&varId=<c:out value="${VARIANCE_VIEW_CODE.varId}"/>&varNumber=<c:out value="${VARIANCE_NUM_LIST.resultType}"/>">
       <font color="red">      <c:out value="${VARIANCE_NUM_LIST.resultType}"/> [use this variance number]</a></font> <br/>
      <a   href="/dfbs/variance/code.do?method=updateVarNum&varId=<c:out value="${VARIANCE_VIEW_CODE.varId}"/>&varNumber=<c:out value="${VARIANCE_NUM_LIST.resultAddress1}"/>">
         <font color="red">    <c:out value="${VARIANCE_NUM_LIST.resultAddress1}"/>[use this variance number]</font></a> <br/>
       <a   href="/dfbs/variance/code.do?method=updateVarNum&varId=<c:out value="${VARIANCE_VIEW_CODE.varId}"/>&varNumber=<c:out value="${VARIANCE_NUM_LIST.resultAddress2}"/>">
          <font color="red">   <c:out value="${VARIANCE_NUM_LIST.resultAddress2}"/>[use this variance number]</font></a> <br/>
   </c:if>
   </c:if>
  </c:if> 
</div>
<div id="leftContentFluid">

 <h1><b><u>Variance Code Detail</u></b></h1></br> </br> 

  <a   href="/dfbs/variance/start.do?method=viewApplications&varProjType=Pre">
             [back to Variance List]</a> <br/> 

<table cellspacing="0" class="noBorder" summary="OWNER FORM" >
     <tbody class="rowHeader">
     <html:form action="/code" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="updateCodeDetail"/>
     <html:hidden property="varId"/> 
     <html:hidden property="varCodeId"/> 
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <tr>
      <th > <b>Variance Id</b> </th>
      <td >
           <c:out value="${VARIANCE_APPLICATION_SELECTED.varId}"/>
        </td>
    </tr>
     <tr>
      <th > <b>Variance Number</b> </th>
      <td >
           <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppVarNumber}"/>
        </td>
    </tr>
     <tr>
      <th > <b>SBC project Number</b> </th>
      <td >
           <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppSBCNum}"/>
        </td>
    </tr>
  <tr>
      <th > </th>
      <td >
          <b><u> Description of the requested Variance</u></b>
      </td>
    </tr>
     <tr>
      <th > <b>Name of Code or standard and edition involved</b> </th>
      <td >
       <html:textarea property="varCode" rows="4" cols="50"/>
          
        </td>
    </tr>
    <tr>
      <th > <b>Specific code section</b> </th>
      <td >
            <html:text property="varCodeName" size="30" maxlength="30"/>
        </td>
    </tr>
    
     <tr>
	 <c:if test="${sessionScope.VAR_APP_LIST_FILTER != null && sessionScope.VAR_APP_LIST_FILTER != 'Pre'}">
      <th > <b>Conditions</b> <br>(max 2000 characters)</th>
	 </c:if>
	 <c:if test="${sessionScope.VAR_APP_LIST_FILTER != null && sessionScope.VAR_APP_LIST_FILTER == 'Pre'}">
      <th > <b>Code Requirements/Non Compliance with the code is</b> <br>(max 2000 characters)</th>
	 </c:if>
      <td >
       <html:textarea property="varNatureNonComp" rows="10" cols="50"/>
        </td>
    </tr>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelVar != null || sessionScope.DFBS_SECURITY.groupLevelAll != null}">  
 <c:if test="${sessionScope.VAR_APP_LIST_FILTER != null && sessionScope.VAR_APP_LIST_FILTER != 'Pre'}">
 <tr>
      <th > <b>Variance Commission Status</b> </th>
      <td >
      <html:select property="varCommStatus"  onchange="setSelectValue('SELECT_DESCRIPTION','ABBREVIATION')">
          <html:option value = "0" >Please Select</html:option>
          <html:options collection="VAR_COMM_ACTION_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        </td>
    </tr>
	    <tr>
      <th > <b>Variance Commission Date</b> </th>
      <td >
        
        <input type="text" size="10" maxlength="10" name="varCommDate" id="varCommDate"  value="<fmt:formatDate value="${VARIANCE_VIEW_CODE.varCommDate}" pattern="MM/dd/yyyy" />"/> mm/dd/yyyy
        </td>
    </tr>
    <tr>
      <th > <b>Variance Print Date</b> </th>
      <td >
        <input type="text" size="10" maxlength="10" name="varCommPrintDate" id="varCommPrintDate"  
		value="<fmt:formatDate value="${VARIANCE_VIEW_CODE.varCommPrintDate}" pattern="MM/dd/yyyy" />"
		/> mm/dd/yyyy
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
 </c:if>
  <tr>
      <th > </th>
      <td >
          <b><u>DEMONSTRATION THAT PUBLIC HEALTH, SAFETY, AND WELFARE ARE PROTECTED:</u></b>
      </td>
    </tr>
 <tr>
       <th scope="row" style="width:30%" class="required">Select one of the following statements</th>
      <td>
        <html:radio property="varAppPhAffirm" value="1"  />Non-compliance with the rule will not be adverse to the public health, safety or welfare; or
        <br/>
        <html:radio property="varAppPhAffirm" value="2"  />Applicant will undertake alternative actions in lieu of compliance with the rule to ensure that granting of the variance 
        will not be adverse to public health, safety, or welfare.  Explain why alternative actions would be adequate (be specific).
          <br/> 
      </td>
    </tr>
     <tr>
      <th scope="row"   class="required"     >  facts demonstrating that the above selected statement is true:(max 2000 characters), if more upload the document</th>
          <td>
           If you have a document use upload file option below.</br>
            <html:textarea property="varAppPhAffirmComment" rows="10" cols="50"/>
                 <c:if test="${VIOLATION_ERROR.varAppPhAffirmComment == 'ERROR'}">
              <br/><span class="error">* please enter description  </span> 
            </c:if>
            </td>
      </tr> 
      <tr>
      <th > </th>
      <td >
          <b><u>DEMONSTRATION OF UNDUE HARDSHIP OR HISTORICALLY SIGNIFICANT STRUCTURE:</u></b>
      </td>
    </tr>
    <tr>
      <th > Select one of the following statements </th>
      <td >
     <c:if test="${VARIANCE_VIEW_CODE.varAppHistAffPhy != 'Y'}">
           <input type="checkbox" name="varAppHistAffPhy"  class="switch"  value="Y"/> 
           Imposition of the rule would result in an undue hardship (unusual difficulty) because of physical limitations of the construction site or its utility services.
      </c:if>
       <c:if test="${VARIANCE_VIEW_CODE. varAppHistAffPhy == 'Y'}">
              <input type="checkbox" name="varAppHistAffPhy"  class="switch"  value="Y" checked/>
              Imposition of the rule would result in an undue hardship (unusual difficulty) because of physical limitations of the construction site or its utility services.
       </c:if>
      </td>
    </tr>
    <tr>
      <th > </th>
      <td >
     <c:if test="${VARIANCE_VIEW_CODE.varAppHistAffMaj != 'Y'}">
           <input type="checkbox" name="varAppHistAffMaj"  class="switch"  value="Y"/> 
           Imposition of the rule would result in an undue hardship (unusual difficulty) because of major operational problems in the use of the building or structure.
      </c:if>
       <c:if test="${VARIANCE_VIEW_CODE.varAppHistAffMaj == 'Y'}">
              <input type="checkbox" name="varAppHistAffMaj"  class="switch"  value="Y" checked/>
              Imposition of the rule would result in an undue hardship (unusual difficulty) because of major operational problems in the use of the building or structure.
       </c:if>
      </td>
    </tr>
    <tr>
      <th > </th>
      <td >
     <c:if test="${VARIANCE_VIEW_CODE.varAppHistAffExc != 'Y'}">
           <input type="checkbox" name="varAppHistAffExc"  class="switch"  value="Y"/> 
           Imposition of the rule would result in an undue hardship (unusual difficulty) because of excessive costs of additional or altered construction elements.
      </c:if>
       <c:if test="${VARIANCE_VIEW_CODE.varAppHistAffExc == 'Y'}">
              <input type="checkbox" name="varAppHistAffExc"  class="switch"  value="Y" checked/>
              Imposition of the rule would result in an undue hardship (unusual difficulty) because of excessive costs of additional or altered construction elements.
       </c:if>
      </td>
    </tr>
    <tr>
      <th >  </th>
      <td >
     <c:if test="${VARIANCE_VIEW_CODE.varAppHistAffHist != 'Y'}">
     <c:out value="${VARIANCE_VIEW_CODE.varAppHistAffHist}"/>
           <input type="checkbox" name="varAppHistAffHist"  class="switch"  value="Y"/>
     Imposition of the rule would prevent the preservation of an architecturally or a historically significant part of the building or structure. </c:if>
       <c:if test="${VARIANCE_VIEW_CODE.varAppHistAffHist != null}">
              <input type="checkbox" name="varAppHistAffHist"  class="switch"  value="Y" checked/>
              Imposition of the rule would prevent the preservation of an architecturally or a historically significant part of the building or structure.
       </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"   class="required"     >  facts demonstrating that the above selected statement is true:(max 2000 characters), if more upload the document</th>
          <td>
          If you have a document use upload file option below.</br>
            <html:textarea property="varAppHistAffPhyComment" rows="10" cols="70"/>
                 <c:if test="${VIOLATION_ERROR.varAppPhAffirmComment == 'ERROR'}">
              <br/><span class="error">* please enter comment  </span> 
            </c:if>
            </td>
      </tr> 
      <c:if test="${sessionScope.DFBS_SECURITY.groupLevelVar != null || sessionScope.DFBS_SECURITY.groupLevelAll != null}">  
       <tr>
      <th > <b>Staff Comments Category</b> </th>
      <td >
      <html:select property="staffCommentsRec"  onchange="setSelectValue('SELECT_DESCRIPTION','ABBREVIATION')">
          <html:option value = "0" >Please Select</html:option>
          <html:option value = "A" >A</html:option>
          <html:option value = "B" >B</html:option>
          <html:option value = "C" >C</html:option>
          <html:option value = "D" >D</html:option>
          <html:option value = "I" >I</html:option>
          <html:option value = "T" >T</html:option>
          <html:option value = "NVR" >NVR</html:option>
           <html:option value = "VOID" >VOID</html:option>
         </html:select>
      
        </td>
    </tr>
      <tr>
      <th scope="row"  >Staff Comments </th>
          <td>
            <html:textarea property="staffComments" rows="10" cols="70"/>
                 
            </td>
      </tr> 
      </c:if>
 <tr>
     <th scope="row"     > </th>
    <td>
         <c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelVar != null) &&  sessionScope.idhsInsp == null}">  
              <html:submit value="Update" styleClass="button" />
        </c:if>
     </td>
     </tr> 
     </html:form>
   
 </tbody>
</table>



</div>

<div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 
