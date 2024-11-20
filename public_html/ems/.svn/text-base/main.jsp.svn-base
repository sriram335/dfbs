<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>
<c:set var="module" scope="request" value="EMS" />
<c:set var="title" scope="request" value="EMS Operations Online Application" /> 
 <jsp:include page="/app/common/hsPageHeader.jsp" /> 
  <div id="leftContentFluid" align="left">
<c:if test="${sessionScope.DFBS_SECURITY != null}">  
<p class="message">
Welcome <c:out value="${sessionScope.DFBS_SECURITY.userId}"/>, 
</p>
</c:if>
<html:form action="/main" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="publicAccess"/>
<p>
   
          <html:submit value="public access" styleClass="button" />
 </p>
 </html:form> 

 <p class="messageBox" size="100">
 <b><u>Follow these steps for applying for a User Account**.</u></b></br>
 1. Click on the public Access.</br>
 2. Select option Institutions or Hospitals or Providers. </br>
 3. From the list displayed Select the facility for which you are requesting access.</br>
 4. On the facility detail screen click on "apply for new user account" link.</br>
 5. After IDHS verifies your credentials, they will forward your account information to you.</br>
 Example: Click on Institutions - Click on Letter D to display institutions with name starting with letter D.
 Click on Dukes Memorial Hospital - Click on link apply for new user account link to get user account for Dukes Memorial Hospital.</br>
 ** registered users can apply for permits online,add ems courses, update contact information and
 other tasks considered appropriate by ems certification/operations division of Indiana Homeland
 Security. Persons working with hospitals / medical service providers / medical teaching
 institutions can apply to become registered users. IDHS after verification will grant appropriate
 access to these users. All the questions / concerns for adding ems courses should be addressed to 
 <A title=mailto:rachelmiller@dhs.in.gov href="mailto:rachelmiller@dhs.in.gov">Rachel Miller </A> 
 All the questions / concerns for institution/provider/hospitals should be addressed to 
<A title=mailto:rstump@dhs.in.gov href="mailto:rstump@dhs.in.gov">Robin Stump </A></br>
 </p>
 <html:form action="/main" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="verifySecurity"/>
 <p>
   
          <html:submit value="login to system" styleClass="button" />
 </p>
 </html:form> 


</div>