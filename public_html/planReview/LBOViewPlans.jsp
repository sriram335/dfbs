<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Plan Review Upload Files"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<c:if test="${PLAN_REVIEW_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.PLAN_REVIEW_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${PLAN_REVIEW_APP_STATUS != 'I'}">
<c:if test="${sessionScope.PLAN_LBO_USER != null }"> 
 <a   href="/dfbs/planReview/planUser.do?method=startOver">
              [back to start]</a>  
</c:if>

<div id="sideContentFluid">
<table cellspacing="0" style="width:50%;" summary="sales" >
  <tr>
  </tr>
  <tbody>
   <c:forEach var="file" items="${sessionScope.PLAN_REVIEW_LBO_PROJECT_PLANS}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
      <c:if test="${sessionScope.PLAN_LBO_USER != null || sessionScope.OTHER_USER != null}"> 
      <html:form action="/upload" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="downLoadFileLBO"/> 
    <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
    <input type="hidden" name="fileName"  value="${file.fileName}"/> 
    <input type="hidden" name="fileType"   value="${file.fileType}"/> 
       <c:out value="${file.fileName}"/>&nbsp;For Project Number: <c:out value="${file.fileConnector}"/> &nbsp;<br/> 
       File date:<c:out value="${file.fileDateString}"/>&nbsp;,File sent by:<c:out value="${file.fileLoadedBy}"/>;&nbsp;File Type:<c:out value="${file.filePlanType}"/></br>
         <input type="submit" value="View Plan" class="button"/>
               </html:form>
        </c:if>
          </td>
      </tr>
</c:forEach>
  </tbody>
</table> 
</div>

 <c:if test="${sessionScope.PLAN_LBO_USER != null }"> 
<html:form action="/planUser" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="searchLBOProject"/> 
 <b>Enter the project number and click "Search" to look up a specific project. If project is not in your "Jurisdiction" or no plans for project exist, program will not show the plans</b>
  <p align="left">
          SBC Project Number:<html:text property="idNumber" size="12" maxlength="12"/> </br></br>
          
          <input type="submit" value="Search" class="button"/>
        </br>        
</p>
</html:form>
 </c:if>
  <div id="mainContentFluid" align="left">
  <p class="message"> Default view shows all the plans filed in the last 90 days with IDHS ,List is based on your Jurisdiction. All the plans available online are from March 2012. For older plans or paper filed project plans contact plan review division. </p>

<h2>  Project List </h2>
<table cellspacing="0" style="width:50%;" summary="sales" >
  <tr>
  </tr>
  <tbody>
<c:forEach var="file" items="${sessionScope.PLAN_REVIEW_LBO_PLANS}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td><b>
     Project Number:  <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnhtml&report=plan_design_release_for_web.rdf&p_sbc_number=<c:out value="${file.fileConnector}"/>" target="_blank" ><c:out value="${file.fileConnector}"/><br/>
</a></b></a>Name:<c:out value="${file.filePlanType}"/></br>Address1:<c:out value="${file.fileName}"/><br/>
      City:<c:out value="${file.fileType}"/><br/>

    <html:form action="/upload" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="plansForLBO"/> 
    <input type="hidden" name="idNumber"   value ="${file.fileConnector}"/> 
    <input type="submit" value="Show Plan List" class="button"/>
     </html:form>
          </td>
      </tr>
</c:forEach>
 </tbody>
</table> </br> </br>


</div>
<div class="clearer">&nbsp;</div>
</div>
</c:if>
<jsp:include page="/app/common/hsPageFooter.jsp" />