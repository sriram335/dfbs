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

<h1><b><u>Variance Update Options </u></b></h1></br> </br> 



<html:form action="/start" method="post" >
<p class="messageBox" align="left">
You can just add files to a existing variance application. This process does not need new owner/designer affirmations.</br> 
    <input type="hidden" name="method" id="METHOD_KEY" value="goToUpload"/> 
          <input type="submit" value="Upload additional documents" class="button"/>
        </br>        
</p>
</html:form> 
<html:form action="/start" method="post" >
<p class="messageBox" align="left">
You can just update LFO email addresses. This process does not need new owner/designer affirmations.</br> 
    <input type="hidden" name="method" id="METHOD_KEY" value="updateLfoEmail"/> 
          <input type="submit" value="Update LFO email" class="button"/>
        </br>        
</p>
</html:form>  
<html:form action="/owner" method="post" >
<p class="messageBox" align="left">
Update information in existing variance application and add/upload new documents/plans.You can also add additional variance application(s) for this project. This process will email new owner/designer affirmations.</br> 
    <input type="hidden" name="method" id="METHOD_KEY" value="addNewOwner"/> 
          <input type="submit" value="Update Application" class="button"/>
        </br>        
</p>
</html:form>  
 



<div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 