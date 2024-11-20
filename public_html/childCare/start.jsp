<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="IDHS Fire and Building Services" />
<c:set var="title" scope="request" value="Daycare Facilities Application" />


<jsp:include page="/app/common/hsPageHeader.jsp" />
<c:if test="${REG_MINI_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.REG_MINI_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${REG_MINI_APP_STATUS != 'I'}">
<div id="leftContentFluid" align="left">

<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null}">  
       <a   href="/dfbs/childCare/childcare.do?method=downLoadFile&fileName=How to add a Inspection.doc&fileId=284&fileType=MASTER" target="_blank">
             [Help file on how to add a inspection]</a></br>
</c:if>
<p class="messageBox">

This computer application provides information for all of the unlicensed registered child care ministries
registered with FSSA and the Fire and Building Safety Division, State of Indiana.</br>
For information on how to apply for registration of an Unlicensed Registered Child Care Ministry visit the web site 
 <a   href="http://www.in.gov/fssa/2552.htm" target="_blank">
             FSSA</a></br>
</p>
<h2>IDHS Online Applications</h2>
<c:if test="${sessionScope.DFBS_SECURITY !=null}">
<p class="message">
Welcome  <c:out value="${sessionScope.DFBS_SECURITY.userId}"/>, 
</p>
</c:if>
<html:form action="/childcare" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="start"/>
   <html:hidden property="structureType" value="Child Care Ministries"/>
         <input type="submit" value="Unlicensed Registered Child Care Ministries" class="button"/>
</html:form></br>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelFire != null ||sessionScope.DFBS_SECURITY.groupLevelAll != null}"> 
<html:form action="/childcare" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="start"/>
   <html:hidden property="structureType" value="DFC/Child Care Center"/>
         <input type="submit" value="DFC/Child Care Center" class="button"/>
</html:form></br>
<html:form action="/childcare" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="start"/>
   <html:hidden property="structureType" value="DFC/Child Caring Institution"/>
         <input type="submit" value="DFC/Child Caring Institution" class="button"/>
</html:form></br>
<html:form action="/childcare" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="start"/>
   <html:hidden property="structureType" value="DFC/Group Home"/>
         <input type="submit" value="DFC/Group Home" class="button"/>
</html:form></br>
<html:form action="/childcare" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="start"/>
   <html:hidden property="structureType" value="DFC/Secure Care Facility"/>
         <input type="submit" value="DFC/Secure Care Facility" class="button"/>
</html:form></br>
<html:form action="/childcare" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="start"/>
   <html:hidden property="structureType" value="DFC/Secure Care Facility"/>
         <input type="submit" value="Migrant Day Care" class="button"/>
</html:form></br>
</c:if>

</div>

<div class="clearer">&nbsp;</div>
</div>
</c:if>
<jsp:include page="/app/common/hsPageFooter.jsp" />
