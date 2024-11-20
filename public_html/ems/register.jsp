<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control />


<c:set var="module" scope="request" value="EMS" />
<c:set var="title" scope="request" value="EMS Operations Online Application" />
<c:set var="level" scope="request" value="1" />
 

 <jsp:include page="/app/common/hsPageHeader.jsp" /> 
<html:form action="/institution" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="institutionList"/>
<p>
      Option1: I have the PSID
          <html:submit value="Register" styleClass="button" />
 </p>
 </html:form> 
<html:form action="/instructor" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="instructorListLookup"/>
<p>
         Option2: I have the PSID, but for got
          <html:submit value="Look up PSID" styleClass="button" />
 </p>
 </html:form> 
 
 <html:form action="/provider" method="request">
      <input type="hidden" name="method" id="METHOD_KEY" value="providerList"/>
<p>
       Option3: I do not have the PSID, apply for one
          <html:submit value="Apply for PSID" styleClass="button" />
 </p>
 </html:form>
 
