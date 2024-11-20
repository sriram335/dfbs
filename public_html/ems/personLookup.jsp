<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>

<c:set var="module" scope="request" value="Fire and EMS Certification(s)"/>
<c:set var="title" scope="request" value=" Lookup person(s) and certifications"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>


<c:if test="${EMS_CERT_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.EMS_CERT_APP_MESSAGE}" />
 </p>
 <p align="left">
 The PSID Lookup System is currently being upgraded and is not available for PSID Lookup. Public Safety Personnel may view and print their certifications at any time through the Indiana Public Safety Personnel Portal at:</br>
 </br><a   href="https://acadisportal.in.gov/AcadisViewer/Login.aspx" >https://acadisportal.in.gov/AcadisViewer/Login.aspx</a>
 </br></br>If you do not currently have an account, you may establish an account at the site above and clicking the "Don't have an account? Signup" link.
 </br></br>Providers or others who wish to verify a person's certification status should email: <a   href="mailto:dhscertifications@dhs.in.gov" >dhscertifications@dhs.in.gov</a> 
 </br></br>The PSID lookup system will be re-established as soon as possible. Thank you for your understanding and patience.
</p>
</c:if>
<c:if test="${EMS_CERT_APP_STATUS != 'I'}">
<div id="mainBox">
<div id="leftContentFluid">  
 <h2 style="font-weight:bold;">Lookup person(s) and Fire and EMS Certifications  </h2>
<p class="messageBox">
1.The view details report opens in a new window, So you need to disable pop-up blocker or add oasdev.dhs.in.gov to safe sites in the pop-up blocker settings.</br>
<b>2. If you use and have adobe X reader, open any adobe document you have, go to edit preferences, under categories click on General , on the screen displayed uncheck the box for Enable Protected Mode at startup, click ok. Close the Adobe Reader. If this box is checked the report will not open.  </b><br>
3.If you have questions or concerns about the data in this online application  email <b><A title=mailto:psidquestions@dhs.in.gov href="mailto:psidquestions@dhs.in.gov">psidquestions@dhs.in.gov.</b></A></br>
4.If you have technical issues (report /data failure errors) other than issues mentioned in 1 and 2 above, you can email <b><A title=mailto:emscertifications@dhs.in.gov href="mailto:emscertifications@dhs.in.gov">Candice Pope </A></b> </br>
</p>

<p class="error"> <b>IDHS has re-designed the PSID look up feature. Use the following options to Search and View PSID and Certification information.</b></br></p>
<b> <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?acadisepypdf&report=acadis_person_lookup.rdf" target="_blank" >[Lookup details in adobe/pdf]</a></br></br>
 <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?acadisepyhtml&report=acadis_person_lookup.rdf" target="_blank" >[Lookup details in html]</a>
</b> 
  </div> 

 </div>
</c:if> 
<jsp:include page="/app/common/hsPageFooter.jsp"/>