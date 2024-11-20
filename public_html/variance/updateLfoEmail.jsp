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

<h1><b><u>UpdateLFO Email </u></b></h1></br> </br> 



<html:form action="/start" method="post" >
<p class="messageBox" align="left">

    <input type="hidden" name="method" id="METHOD_KEY" value="updateSaveLfoEmail"/> 
       LBO Email    <html:text property="varAppLboEmail" size="50" maxlength="80"/></br>
           
       LFO Email     <html:text property="varAppLfoEmail" size="50" maxlength="80"/></br>
        </br>    
             <input type="submit" value="Update Email" class="button"/>
</p>
</html:form> 

 



<div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 