<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control />
<hs:control />


<c:set var="module" scope="request" value="DHS Web" />
<c:set var="title" scope="request" value="Error 404" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox" style="width: 600px;margin: 25px auto 25px auto; border: 1px #c5c5c5 solid; padding: 25px;"> 
<div id="mainContent" align="left"> 

        
        <h2> Error:</br>
         <c:out value="${sessionScope.APPLICATION_ERROR}"/> </h2>
        
       
        <p>
        You might want to go back to <a href="/dfbs/cigarette/cigarette.do">Cigarette Certification Online Application</a>.
        </p>
       
        
 </div>
 </div>
        

<jsp:include page="/app/common/hsPageFooter.jsp" />

