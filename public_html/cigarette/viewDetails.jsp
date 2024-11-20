
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION" />
<c:set var="title" scope="request" value="Application Details" />


<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">



<div id="sideContentFluid">

  
    
   
          
</div>


<a   href="/dfbs/cigarette/application.do?method=approveApplication&appId=<c:out value="${CIGARETTE_APPROVE_APPLICATION.appId}" />"> [back]</a>  

<div id="mainContentFluid" align="left">

<h2>Brand Family Details</h2>




  <div class="listing">
  <c:forEach var="cigarette" items="${BRAND_LIST.list}" varStatus="i" >
   

   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCigarette == 'SUPERVISOR'}">  
              <a   href="/dfbs/cigarette/brand.do?method=updateBrand&cigaretteId=<c:out value="${cigarette.cigaretteId}" />"> Edit </a></br>
    </c:if>       
             
             <b> brand family name: </b><c:out value="${cigarette.cigaretteBrand}"/>&nbsp;  
              <b> style: </b><c:out value="${cigarette.cigaretteStyle}"/>&nbsp;  
              <b> marking: </b><c:out value="${cigarette.cigaretteMarking}"/>&nbsp; <br/> 
               <b>flavor: </b><c:out value="${cigarette.cigaretteFlavor}"/>&nbsp;  
              <b> filter: </b><c:out value="${cigarette.cigaretteFilter}"/>&nbsp;  
              <b> package: </b><c:out value="${cigarette.cigarettePackage}"/><br/> </br>
   </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
