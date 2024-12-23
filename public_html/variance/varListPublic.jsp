<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Entertainment Permits"/>
<c:set var="title" scope="request" value="Entertainment Permits"/>
<c:set var="step" scope="request" value="2"/>
 

<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <link rel="stylesheet" type="text/css" media="all" href="/dfbs/idhsInspections/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="/dfbs/idhsInspections/jsDatePick.min.1.3.js"></script>
<script type="text/javascript">
window.onload = function(){
		new JsDatePick({
			useMode:2,
			target:"varAppLfoName",
			dateFormat:"%m/%d/%Y"
		});
		new JsDatePick({
			useMode:2,
			target:"varAppLboName",
			dateFormat:"%m/%d/%Y"
		});
	};
</script>
<div id="mainBox">
 
 <h1><b><u>Variance Application(s)</u></b></h1></br> </br> 
     <p  align="left" class="error">
 <b><u>Steps:</u></b></br>
 1. First search for the type of variances to be displayed using search options.</br> 
 2. Use Sort buttons to sort the variances.</br> 
 3. For old variances, select Processed Applications along with dates. 
 </p>
 <html:form action="/start" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="viewApplications"/> 
     <p  align="left">
      <b>  Search for Applications <u></br>
         <html:select property="varProjType" >
                <html:option value = "Pre" >Application in process</html:option>
                <html:option value = "Post" >Applications before Commission</html:option>
                <html:option value = "Old" >** Processed Applications</html:option>
             </html:select></br> 
             Fill these options below if you select **Processed applications option above in the list box above.</br>
            To get best response times narrow down search criteria recommended </br>
            Example search for a county or search for a 3 month time period with owner name like.,</u></b></br> 
               Enter Variance Number <html:text property="varAppVarNumber" size="10" maxlength="10"/></br> 
               Enter Project Name like <html:text property="varProjName" size="10" maxlength="10"/>Example for Eli Lilly just enter lilly in the text box</br> 
              ** Enter Application From Date <input type="text" size="10" maxlength="10" name="varAppLfoName" id="varAppLfoName" /> mm/dd/yyyy</br> 
              ** Enter Application To Date &nbsp;&nbsp;&nbsp;
            <input type="text" size="10" maxlength="10" name="varAppLboName" id="varAppLboName" /> mm/dd/yyyy
	</br>
              ** Enter County Name<html:select property="varProjCounty" > 
               <html:option value="">-----</html:option>
                <html:options  collection="VAR_COUNTY_OPTIONS" property="value" labelProperty="label" />
                </html:select>  </br> 
                 <c:if test="${VAR_APP_LIST_FILTER_ERROR == 'Y'}">
                 <span class="error">* please enter from date, end date and county information  </span> 
                 </c:if>
                  ** Enter Code Type<html:text property="varAppCodeType" size="10" maxlength="10"/></br> 
              
              </br>   <input type="submit" value="Search" class="button"/> </br>
              <span class="Error"> Use Arrow buttons to sort the results by the column name.</span>
              </br>   
  </html:form> 
  <b><u>Staff Recommendations Notations Explanation:</u></b></br>
"A" category = staff recommendation is for approval with no equal alternatives because of noncompliance is
not adverse.
"B" category = staff recommendation is for approval with equal alternatives as stated by the proponent.
"C" category = reserved, meaning staff believes Commission needs to discuss entirety.
"D" category = recommendation is for denial.
"I" category = incomplete (with permission of the Chairman).
"NVR" category = no variance required.
"T" category = tabled.
 <table cellspacing="0" style="width:100%;" summary="sales" >
  <tr>
    <th style="width:15%;" >
     <html:form action="/start" method="post" >
                 <input type="hidden" name="method" id="METHOD_KEY" value="listSort"/> 
                 <html:hidden property="checkoutId" value="listSortVarAsc"/>
        Variance Number/Id  </br>   <input type="image" src="../img/upArrow.jpg" name="up" value="listSortVarAsc" />
               
       </html:form> 
        <html:form action="/start" method="post" >
                 <input type="hidden" name="method" id="METHOD_KEY" value="listSort"/> 
                 <html:hidden property="checkoutId" value="listSortVarDesc"/>
           
           <input type="image" src="../img/downArrow.jpg" name="down" value="listSortVarDesc" />
       </html:form>    </th>     
    <th style="width:15%;"> <html:form action="/start" method="post" >
                 <input type="hidden" name="method" id="METHOD_KEY" value="listSort"/> 
                 <html:hidden property="checkoutId" value="listSortProjAsc"/>
           Project Name   </br>   <input type="image" src="../img/upArrow.jpg" name="up" value="listSortProjAsc" />
                 
       </html:form> 
        <html:form action="/start" method="post" >
                 <input type="hidden" name="method" id="METHOD_KEY" value="listSort"/> 
                 <html:hidden property="checkoutId" value="listSortProjDesc"/>
          
           <input type="image" src="../img/downArrow.jpg" name="down" value="listSortProjDesc" />
       </html:form>    </th>
    <th style="width:5%;">County<html:form action="/start" method="post" >
                 <input type="hidden" name="method" id="METHOD_KEY" value="listSort"/> 
                 <html:hidden property="checkoutId" value="listSortCouAsc"/>
            <input type="image" src="../img/upArrow.jpg" name="up" value="listSortCouAsc" />
                 
       </html:form> 
        <html:form action="/start" method="post" >
                 <input type="hidden" name="method" id="METHOD_KEY" value="listSort"/> 
                 <html:hidden property="checkoutId" value="listSortCouDesc"/>
          
           <input type="image" src="../img/downArrow.jpg" name="down" value="listSortCouDesc" />
       </html:form> </th>
    <th style="width:65%;" > <html:form action="/start" method="post" >
                 <input type="hidden" name="method" id="METHOD_KEY" value="listSort"/> 
                 <html:hidden property="checkoutId" value="listSortCodeAsc"/>
          Variance Codes </br>   <input type="image" src="../img/upArrow.jpg" name="up" value="listSortCodeAsc" />
       </html:form> 
        <html:form action="/start" method="post" >
                 <input type="hidden" name="method" id="METHOD_KEY" value="listSort"/> 
                 <html:hidden property="checkoutId" value="listSortCodeDesc"/>
         
           <input type="image" src="../img/downArrow.jpg" name="down" value="listSortCodeDesc" />
       </html:form></th>
  </tr>
  <tbody>
   <c:forEach var="varApp" items="${requestScope.VARIANCE_APPLICATION_LIST}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
      <c:if test="${ varApp.varAppVarNumber != null}">  
      <b> <c:out value="${varApp.varAppVarNumber}"/></b>
      <c:if test="${varApp.varAppType !=null}"> 
      <c:out value="${varApp.varAppType}"/>
      </c:if>
      <br/> 
      </c:if>
       <c:if test="${ varApp.varAppVarNumber ==null}"> 
      <b> <c:out value="${varApp.varId}"/></b> 
      <c:if test="${varApp.varAppType !=null}"> 
      <c:out value="${varApp.varAppType}"/>
      </c:if>
      <br/> 
      </c:if>
      Application Date:<c:out value="${varApp.varAppRecDateString}"/></b> <br/> 
       <c:forEach var="file" items="${varApp.fileList}" varStatus="i">
      Name <c:out value="${file.fileName}"/>&nbsp; Date:<c:out value="${file.fileDateString}"/><br/> 
              <html:form action="/start" method="post" >
                <input type="hidden" name="method" id="METHOD_KEY" value="downLoadFile"/> 
                <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
                <input type="hidden" name="fileName"  value="${file.fileName}"/> 
                <input type="hidden" name="fileType"   value="${file.fileType}"/> 
                    <input type="submit" value="View Plan" class="button"/>
               </html:form>
      </c:forEach>
      </td>
        <td>
       <c:out value="${varApp.varProjName}"/>&nbsp;  
        <c:out value="${varApp.varProjAddress1}"/>&nbsp; <br/><c:out value="${varApp.varProjCity}"/>&nbsp;<c:out value="${varApp.varProjZip}"/>&nbsp;
       <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnhtml&report=variance_view_application.rdf&p_var_id=<c:out value="${varApp.varId}"/>">[ view application in html]</a> <br/> 
       <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=variance_view_application.rdf&p_var_id=<c:out value="${varApp.varId}"/>">[ view app in pdf for IE ]</a> <br/> 
      
       <c:if test="${varApp.varAppOwnerSign == null && varApp.varAppVarNumber ==null}">  
       <span class="error">Owner Affirmation Pending!</span><br/>
       </c:if>
       <c:if test="${varApp.varAppDesSign == null && varApp.varAppVarNumber ==null}">  
      <span class="error"> Designer Affirmation Pending!</span><br/>
       </c:if>
       <c:if test="${varApp.varAppDesSign != null && varApp.varAppOwnerSign != null && varApp.varAppVarNumber == null && varApp.varAppLboNotified == 'A' && varApp.varAppLfoNotified == 'A'}">  
      <span class="message"> Ready for IDHS processing!</span><br/>
       </c:if>
        <c:if test="${varApp.varAppLfoNotified == null && varApp.varAppVarNumber ==null}">  
       <span class="error">LFO Affirmation Pending!</span><br/>
       </c:if>
        <c:if test="${varApp.varAppLfoNotified == 'N' && varApp.varAppVarNumber ==null}">  
       <span class="error">LFO Notified!</span><br/>
       </c:if>
        <c:if test="${varApp.varAppLfoNotified == 'A' && varApp.varAppVarNumber ==null}">  
       <span class="error">LFO acknowledgement received!</span><br/>
       </c:if>
        <c:if test="${varApp.varAppLboNotified == null && varApp.varAppVarNumber ==null}">  
       <span class="error">LBO Affirmation Pending!</span><br/>
       </c:if>
        <c:if test="${varApp.varAppLboNotified == 'N' && varApp.varAppVarNumber ==null}">  
       <span class="error">LBO Notified!</span><br/>
       </c:if>
        <c:if test="${varApp.varAppLboNotified == 'A' && varApp.varAppVarNumber ==null}">  
       <span class="error">LBO acknowledgement received!</span><br/>
       </c:if>
        <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelVar != null }">  
        <a   href="/dfbs/variance/owner.do?method=viewOwner&ownerId=<c:out value="${varApp.varProjOwnerId}"/>"> View Applicant</a>
        <a   href="/dfbs/variance/designer.do?method=viewDesigner&designerId=<c:out value="${varApp.varProjId}"/>"> View Designer</a>
         <a   href="/dfbs/variance/start.do?method=viewSubmitter&varId=<c:out value="${varApp.varId}"/>"> View Submitter</a>
         <a   href="/dfbs/variance/start.do?method=viewProjectInfo&varId=<c:out value="${varApp.varId}"/>" > View Project</a>
         <a   href="/dfbs/variance/start.do?method=goToUploadInternal&varId=<c:out value="${varApp.varId}"/>" > Upload Files</a>
         <a   href="/dfbs/variance/start.do?method=updateLfoEmailInternal&varId=<c:out value="${varApp.varId}"/>" > Update LBO/LFO</a>
         </c:if>
            </td>
        <td>
       <c:out value="${varApp.varProjCounty}"/>&nbsp;   <br/> 
        
        </td>
        <td>
        <c:forEach var="code" items="${varApp.varCodesList}" varStatus="i">
         <span class="error"><b>Code Name:<c:out value="${code.varCode}"/> <a   href="/dfbs/variance/code.do?method=viewCodeDetail&varCodeId=<c:out value="${code.varCodeId}"/>&varId=<c:out value="${varApp.varId}"/>">
             [view code Details]</a><br/> Specific Section:</b><c:out value="${code.varCodeName}"/><br/>
        <c:if test="${code.staffCommentsRec !=null && code.staffCommentsRec !='0'}">  
         Staff Recommendation:<c:out value="${code.staffCommentsRec}"/>&nbsp;(explanation key at the top of the table)
         </c:if></span>
             <br/> 
            <c:if test="${code.staffCommentsRec !=null && code.staffCommentsRec !='0'}"> 
             <b> Staff Comments:</b><c:out value="${code.staffComments}"/>&nbsp;<br/>
            </c:if>
              <c:if test="${code.varReleaseFlag !=null}">  
              <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=variance_commission_report.rdf&p_variance_number=<c:out value="${varApp.varAppVarNumber}"/>&p_commission_date=<c:out value="${code.varCommDateString}"/>">[ Commission Letter in pdf for IE ]</a> <br/> 
              </c:if>
          </c:forEach>
       </td>
       
      </tr>
    </c:forEach>
  </tbody>
</table>



 
 <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>

