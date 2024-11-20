<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Apply for FSC Cigarette Certification"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<c:if test="${CIG_PERMITS_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.CIG_PERMITS_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${CIG_PERMITS_APP_STATUS != 'I'}">
<div id="mainBox">

  <div id="sideContentFluid">
    <jsp:include page="shoppingCart.jsp"/>
  </div>
  <div id="mainContentFluid" align="left">
     <a   href="/dfbs/cigarette/cigarette.do?method=startOver">
              [ Cancel and Start Over New ]</a>  
    <a   href="https://oas.dhs.in.gov/dfbs/aepermits/main.do?method=downLoadFile&fileName=Cigarette Instructions.doc&fileId=505&fileType=MASTER" target="_blank">
             [View and Print Detailed Instructions]</a></br></br>
     <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=cigarettes_approved_current_year.rdf" target="_blank" color="blue"><b>[Current Cigarette Brands Authorized for Sale In State of Indiana]</b></a></br>
    </br> Verify the list below before applying for the brands.</br>
     <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=cigarettes_company_brand_list.rdf" target="_blank" color="blue"><b>[Current Cigarette Brands available in the database]</b></a></br>
  <u><h2>STEPS TO COMPLETE THE APPLICATION</h2></u>
 <p class="messageBox">
  Note: This application use is <b><u>RESTRICTED</u></b> to  Cigarette Companies Registered with Indiana State Attorney General's office.
  If you do not find your company or brand names in the online application (program) contact <A title=mailto:amber.degenhart@atg.in.gov href="mailto:amber.degenhart@atg.in.gov">Amber Degenhart </A> or 
  <A href="firesafetycigarette@dhs.in.gov" href="firesafetycigarette@dhs.in.gov">Madison Roe </A></br>
</p>
<p class ="error">
 
<FONT color="#009933"><b> IMPORTANT BEFORE YOU START:</b>  you walk away from the computer during application data entry for 
a long time over 30 minutes, the application connection may be lost and you will be forced to type all the information again. To avoid this
 before you walk away from the computer complete the brand family and its styles you are working on, and click complete application.
 You can come back and add another application for the other brands.</br>2. Application down time during all week ends 8 to 11 AM Eastern Standard time, do not plan to use the application during that time.</FONT></br>
 
  1.Select Application type</br>
  2.Update Manufacturer/Agent information.(Start with "Select Manufacturer Company" link)</br>
  3."Add Contact information".</br>
 <FONT color="#009933"> 4. a)"Add New Cigarette Brand Family information" (more than one cigarette brand family can be added in a session).</br>
  4. b)"Add same brand family different cigarette style" to add more styles to the same Cigarette Brand Family.</FONT></br>
  5.Total fees for the application are shown in the shopping cart. Each Cigarette Brand Family Certification cost is $800.00 for a 3-year certification.<u>(No refunds on certification fees after certification is processed.)</u></br>
  <u> <b>If you are using Credit Card or e-Check </b></u></br>
  6. a) If Paying by credit card or e-check($1 portal fee), please pay the total fees which includes administrative and processing fees in the payment portal.</br>
  6. b) After credit card  or e-check payment is processed, use "View and Print Application" option to print the certification application.</br>
  7.Sign the printed application and mail it together with the cigarette testing lab reports, the markings selected by the manufacturer to the address given below the shopping cart.</br>
 
  <u> <b>If you are using paper check for payment </b></u></br>
  6. a) If paying by check use the "Complete the Application" option and then use "View and Print the application" option to print the completed application.</br>
  6. b) Make the check payable to Indiana Department of Homeland Security and the check has to be drawn on a bank that is headquartered in the USA.</br>
    
  7.Sign the printed application and mail it together with the cigarette testing lab reports, the markings selected by the manufacturer and "check" for payment to the address given below the shopping cart.</br>
  </p>
 
  
 <c:if test="${sessionScope.APPLICATION_TYPE == null }"> 
<u><h2>SELECT APPLICATION TYPE</h2></u><FONT color="#009933"> Note before you start: If you are applying for new brand family, email the company name and cigarette family information to contacts listed on the page. After we add this information to the database, you can apply for new cigarette family application.</FONT></br>
 <html:form action="/cigarette" method="post">
 <input type="hidden" name="method" id="METHOD_KEY" value="newAppType"/>
      <p>
        <input type="submit" value="Initial Application" class="button"/>
      </p>
      
</html:form>
 <html:form action="/cigarette" method="post">
 <input type="hidden" name="method" id="METHOD_KEY" value="supAppType"/>
      <p>
        <input type="submit" value="Supplemental Application" class="button"/>
      </p>
<%--<p class="messageBox">
(Cigarette Company's initial application contact information will be used to set up Cigarette Company user accounts. IDHS will communicate this
information to the application contact before July 15, 2009 by email.Only Authorized Cigarette Company users
will be allowed to submit all supplemental and 3-year re-certification applications for their company. For questions and concerns about this contact
)
</p> --%>
<p class="messageBox">
(Needs user id and password during the application process.Only Authorized Cigarette Company users
will be allowed to submit all supplemental and 3-year re-certification applications for their company.
Go to View previously filed Applications and click on "Authorized users" link to view current list of authorized users.
For questions and concerns about this contact <A title=mailto:trasmith@dhs.in.gov href="mailto:trasmith@dhs.in.gov">Madison Roe </A>)
</p> 
</html:form>
 <html:form action="/cigarette" method="post">
 <input type="hidden" name="method" id="METHOD_KEY" value="3yearRenewal"/>
      <p class ="error">
        <input type="submit" value="3 year Re-certification" class="button" 
               /> New e-check pay can be done with addional $1 fee.
      </p>
     
</html:form>
 <html:form action="/applicationsView" method="post">
      <p>
        <input type="submit" value="View previously filed Applications" class="button"/>
      </p>
     
</html:form>
 <html:form action="/cigUser" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="userAccounts"/>
      <p>
        <input type="submit" value="Manage User Accounts" class="button"/>
      </p>
     
</html:form>
</c:if> 
<c:if test="${sessionScope.DFBS_SECURITY == null}">  
<html:form action="/cigarette" method="post">
<input type="hidden" name="method" id="METHOD_KEY" value="logOn"/>
         <input type="submit" value="For IDHS Employees" class="button"/>
</html:form>
</c:if>

  <c:if test="${sessionScope.APPLICATION_TYPE != null }">
  <STRONG><EM><u>Part I: Type of Application</u></EM></STRONG> :
  <c:out value="${sessionScope.APPLICATION_TYPE}"/>&nbsp; </br></br>
  </br>
  <STRONG><EM><u>Part II: Manufacturer Company identification</u></EM></STRONG> </br>
  <jsp:include page="companyDisplay.jsp"/>
  <c:if test="${sessionScope.CIGARETTE_COMPANY.companyName != null }">
    <STRONG>Contact Information</STRONG> </br>
     <c:if test="${sessionScope.CIGARETTE_COMPANY.receiptId == 0 && sessionScope.APPLICATION_TYPE == 'Initial' }">
      <a href="/dfbs/cigarette/application.do?method=addApplication">[ Add Contact information ]</a> 
    </c:if>
     <c:if test="${sessionScope.CIGARETTE_USER != null && sessionScope.APPLICATION_TYPE == 'Supplemental' }">
      <a href="/dfbs/cigarette/application.do?method=addApplication">[ Add Contact information ]</a> 
    </c:if>
     
    <c:if test="${sessionScope.CIGARETTE_COMPANY.receiptId == 0 && sessionScope.CIGARETTE_APPLICATION.appType != null }">
     <a href="/dfbs/cigarette/application.do?method=editApplication&key=<c:out value="${sessionScope.CIGARETTE_APPLICATION.applicationKey}"/>">[Edit Contact information ]</a>  
    </c:if>
 
    <jsp:include page="applicationDisplay.jsp"/></br></br>
  </c:if>
  <c:if test="${sessionScope.CIGARETTE_APPLICATION.appType != null}">
    <STRONG><EM><u>Part III: Cigarette Certification</u></EM></STRONG></br> Pursuant to Indiana Code IC 22-14-7, please describe each cigarette.</br> 
   </br> <STRONG>Cigarette Brand Family(s) Information</STRONG> </br>
    <c:if test="${sessionScope.CIGARETTE_COMPANY.receiptId == 0}">
      <a href="/dfbs/cigarette/brand.do?method=addBrand">[ Add new cigarette brand family information ]</a> 
    </c:if>
    <jsp:include page="brandDisplay.jsp"/>
  </c:if>
</c:if>
 </div>    
  <div class="clearer">&nbsp;</div>
</div>
</c:if>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 
