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
 <a   href="/dfbs/planReview/upload.do?method=plansForOthers">
              [back to start]</a>  
 <a   href="/dfbs/otherUsers/otherUser.do?method=updatePassword">
              [change password]</a>  

<div id="sideContentFluid">
<table cellspacing="0" style="width:50%;" summary="sales" >
  <tr>
  </tr>
  <tbody>
   <c:forEach var="file" items="${sessionScope.PLAN_REVIEW_OTHER_PROJECT_PLANS}" varStatus="i">
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
 <c:if test="${sessionScope.OTHER_USER.FDId != null}"> 
<b><u> Authorized Users</u></b>
   <c:forEach var="user" items="${AUTH_USER_LIST}" varStatus="i" >
  <div class="listing">
  <h3 style="margin-bottom:5px;">
  User Id:<c:out value="${user.userLoginId}" />
  </h3>
  <p class="listingInfo">
  Name:
    <c:out value="${user.userLastName}" />,<c:out value="${user.userFirstName}" /><br />
  Password good untill:  <c:out value="${user.userPasswordExpiresDateString}" /><br />
   Phone: <c:out value="${user.userPhone}" /><br />
    <br />
  </p>
</div>
  </c:forEach>
  </c:if>
</div>

  <div id="mainContentFluid" align="left">
  <c:if test="${sessionScope.OTHER_USER != null}"> 
<html:form action="/upload" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="plansForOthers"/> 
 <b>Enter the project number and click "Search" to look up a specific project. If project is not in your "Jurisdiction" or no plans for project exist, program will not show the plans</b>
  <p align="left">
          SBC Project Number:<html:text property="idNumber" size="12" maxlength="12"/> </br>
          Project Street Number/Name:<html:text property="projStreetNumber" size="12" maxlength="12"/> </br>
          <b><u>For 302 W Washington St, enter 302 or Washington to bring all matches.</u></b></br>
          Project City:<html:text property="projCity" size="30" maxlength="30"/> </br>
          <input type="submit" value="Search" class="button"/>
        </br>        
</p>
</html:form>
 </c:if>
  <p class="message"> Default view shows all the plans filed in the last 90 days with IDHS ,List is based on your Jurisdiction. All the plans available online are from March 2012. For older plans or paper filed project plans contact plan review division. </p>

<h2>  Project List </h2>
<table cellspacing="0" style="width:50%;" summary="sales" >
  <tr>
  </tr>
  <tbody>
<c:forEach var="file" items="${sessionScope.PLAN_REVIEW_OTHER_PLANS}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td><b>
      Project Number:  <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnhtml&report=plan_design_release_for_web.rdf&p_sbc_number=<c:out value="${file.fileConnector}"/>" target="_blank" ><c:out value="${file.fileConnector}"/><br/>
</a></b></a>Name:<c:out value="${file.filePlanType}"/></br>Address1:<c:out value="${file.fileName}"/><br/>
      City:<c:out value="${file.fileType}"/><br/>
       
    <html:form action="/upload" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="viewPlansForOthers"/> 
    <input type="hidden" name="idNumber"   value ="${file.fileConnector}"/> 
    <input type="submit" value="Show Plans" class="button"/>
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