<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control />


<c:set var="module" scope="request" value="EMS" />
<c:set var="title" scope="request" value="EMS Operations Online Application" />
 

 <jsp:include page="/app/common/hsPageHeader.jsp" /> 
 <div align="left">
  <c:if test="${sessionScope.DFBS_SECURITY != null}">  
<p class="message">
Welcome <c:out value="${sessionScope.DFBS_SECURITY.userId}"/>, 
</p>
</c:if>
 <p class="messageBox" size="50" width ="50">Click buttons to Navigate the application.</p>

<html:form action="/institution" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="institutionList"/>

  <p> 
          <html:submit value="                    Institutions                    " styleClass="button" />
  </p>
 </html:form> 
 
</p>
<html:form action="/hospital" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="hospitalList"/>
<p>
   
          <html:submit value="                        Hospitals                       " styleClass="button" />
 </p>
 </html:form> 
 

 <%--<a   href="/dfbs/ems/reports.do?method=reportsGroup">
              [ reports]</a>   --%> 
 <html:form action="/provider" method="request">
      <input type="hidden" name="method" id="METHOD_KEY" value="providerList"/>
<p>
   
          <html:submit value="                       Providers                        " styleClass="button" />
    <%--     <a   href="https://oas.dhs.in.gov/dfbs/ems/main.do?method=downLoadFile&fileName=FAQProvider.doc&fileType=.pdf&fileId=256" target="_blank">
          [FAQ provider]</a> --%>
   </p>
 </html:form>
  <c:if test="${EMS_SECURITY.currentUserLevel == 'USER' || EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR'}"> 
  <html:form action="/provider" method="request">
      <input type="hidden" name="method" id="METHOD_KEY" value="providerRenewal"/>
<p>
   
          <html:submit value="                 Provider Renewal               " styleClass="button" />
   <%--      <a   href="https://oas.dhs.in.gov/dfbs/ems/main.do?method=downLoadFile&fileName=How to submit renewal application for Provider.doc&fileType=.doc&fileId=259" target="_blank">
          [How to renew a  provider]</a> --%>
 </p>
 </html:form>
 </c:if>
<%-- 
           <a   href="https://oas.dhs.in.gov/dfbs/ems/main.do?method=downLoadFile&fileName=How to Add EMS course to the database.doc&fileType=.doc&fileId=252" target="_blank">
          [How to add a Course]</a> 

 <html:form action="/search" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="goToSearch"/>
<p>
   
          <html:submit value="               Advance Search               " styleClass="button" />
 </p>
 </html:form> --%>
  
 <c:if test="${sessionScope.DFBS_SECURITY != null}">   
<html:form action="/main" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="showEmsUserList"/>
<p>
   
          <html:submit value="User account management" styleClass="button" />
 </p>
 </html:form> 
 
  <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="courseListAdmin"/>
<p>
   
          <html:submit value="Course management" styleClass="button" />
 </p>
 </html:form> 
</c:if>
</div> 