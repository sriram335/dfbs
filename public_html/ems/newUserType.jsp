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

<html:form action="/main" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="newUserInst"/>
<p>
   
          <html:submit value="Apply for user account for adding new ems courses" styleClass="button" />
 </p>
 </html:form> 

 <p class="messageBox" size="100">
 ** If you want to be a user who adds ems basic/advanced/paramedic courses to database select top option.
 **If you want to be able edit/update  provider,
 institution, hospital information select bottom option.
 **if you want to do both also select bottom option.
 </p>
 <html:form action="/main" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="newUserMD"/>
 <p>
   
          <html:submit value="Apply for user account for  edit/updating Institution/Hospital/Provider" styleClass="button" disabled="true"/>
          <FONT color="#ff6666">(This part of the application is under testing)</FONT>
 </p>
 </html:form> 
