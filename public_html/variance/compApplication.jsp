<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Variance Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>

<div id="mainBox">

<div id="leftContentFluid">

<h1><b><u>Variance Application(s)</u></b></h1></br> </br> 

<b><u>Variance Complete Application Process</u></b>
<html:form action="/start" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="completeApplication"/> 
 
<p class="messageBox" align="left">
 To complete the Application you need the variance number assigned to your Pre-Application. IDHS will send this variance number by email
 to the pre-application submitter.</br></br>
              Variance Number:<html:text property="varAppVarNumber" size="12" maxlength="12"/></br> </br>  
            <input type="submit" value="Complete Application" class="button"/>
        </br>        
</p>
</html:form> 

<c:if test="${sessionScope.COMP_APPLICATION != null }">  
<p class="messageBox" align="left">
Based on Your Variance Number you searched for 
Your Project Name is :  <c:out value="${COMP_APPLICATION.varProjName}"/>  </br> 
Address is: </br> 
<c:out value="${COMP_APPLICATION.varProjAddress1}"/></br>
<c:out value="${COMP_APPLICATION.varProjAddress2}"/></br>
<c:out value="${COMP_APPLICATION.varProjCity}"/></br>
<c:out value="${COMP_APPLICATION.varProjState}"/></br>
<c:out value="${COMP_APPLICATION.varProjZip}"/></br>
If the project matches the application you filed, select the options below
</p>
<html:form action="/start" method="post" >
<p class="messageBox" align="left">
    <input type="hidden" name="method" id="METHOD_KEY" value="payFees"/> 
          <input type="submit" value="Pay Application Fee(s)" class="button"/>
        </br>        
</p>
</html:form> 
<html:form action="/start" method="post" >
<p class="messageBox" align="left">
    <input type="hidden" name="method" id="METHOD_KEY" value="goToUploadComp"/> 
          <input type="submit" value="Upload Additional Documents" class="button"/>
        </br>        
</p>
</html:form> 
</c:if>

<div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 