<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="View Application Information"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>

<div id="mainBox">

<div id="sideContentFluid">
  <jsp:include page="/cigarette/feeDetails.jsp"/>
   <c:forEach var="brand" items="${BRAND_EXP_DATE_LIST}" varStatus="i" >
  <c:out value="${brand.cigaretteBrand}" /><br />
  </c:forEach>
  </div> 
  <div id="mainContentFluid" align="left">
<h2> view / approve Application</h2> 
<a   href="/dfbs/cigarette/applicationsView.do">
              [back to applications]</a>
<c:if test="${sessionScope.CIGARETTE_APPROVE_APPLICATION.appStatus == null}">
<a   href="/dfbs/cigarette/applicationsView.do?method=viewDetails&appId=<c:out value="${CIGARETTE_APPROVE_APPLICATION.appId}" />">[view brands] </a>
</c:if>
  <div class="styledBox">
 <html:form action="/application" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateApplication"/> 
      <html:hidden property="appId"/> 
      <html:hidden property="applicationKey"/>
      <html:hidden property="companyId"/>
      <html:hidden property="appType"/>
      <html:hidden property="email"/>
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
      <tr>
      <th scope="row"  class="required"    > * application type:</th>
      <td>
       <c:out value="${CIGARETTE_APPROVE_APPLICATION.appType}"/>
       </td>
    </tr>
      <tr>
      <th scope="row"  class="required"    > * application date:</th>
      <td>
       <c:out value="${CIGARETTE_APPROVE_APPLICATION.appDateString}"/>
       </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * contact name:</th>
      <td>
       <c:out value="${CIGARETTE_APPROVE_APPLICATION.contact}"/>
       </td>
    </tr>
     <tr>
      <th scope="row"   class="required"   > * contact title:</th>
      <td>
      <c:out value="${CIGARETTE_APPROVE_APPLICATION.title}"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * contact phone:</th>
      <td>
       <c:out value="${CIGARETTE_APPROVE_APPLICATION.phone}"/>
       </td>
    </tr>
    <tr>
      <th scope="row"     > contact fax:</th>
      <td>
       <c:out value="${CIGARETTE_APPROVE_APPLICATION.fax}"/>
       </td>
    </tr>
    <tr>
      <th scope="row"     > contact email:</th>
      <td>
        <c:out value="${CIGARETTE_APPROVE_APPLICATION.email}"/>
       </td>
   
      <c:if test="${ sessionScope.DFBS_SECURITY == null ||sessionScope.DFBS_SECURITY.groupLevelCigarette == 'USER' }">
      </tr>
      <tr>
      <th scope="row"     > comments:</th>
      <td>
      <c:out value="${CIGARETTE_APPROVE_APPLICATION.comments}"/>
       </td>
     </tr>
     </tr>
      <tr>
      <th scope="row"     > issue date:</th>
      <td>
       <c:out value="${CIGARETTE_APPROVE_APPLICATION.appIssueDate}"/>
       </td>
     </tr>
     </tr>
      <tr>
      <th scope="row"     > expiration date:</th>
      <td>
       <c:out value="${CIGARETTE_APPROVE_APPLICATION.appExpDate}"/>
       </td>
     </tr>
     </tr>
      <tr>
      <th scope="row"     >  status:</th>
      <td>
      <c:out value="${CIGARETTE_APPROVE_APPLICATION.appStatus}"/>
       </td>
     </tr>
      </c:if>
     
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCigarette == 'SUPERVISOR' }">
     <tr>
      <th scope="row"     > comments:</th>
      <td>
       <html:textarea property="comments" /></br>
       </td>
    </tr>
      <tr>
      <th scope="row"     >  issue date:</th>
      <td>
       <html:text property="appIssueDate" size="10" maxlength="10"/>(mm/dd/yyyy)
       </td>
    </tr>
     <tr>
      <th scope="row"     >  expiration date:</th>
      <td>
       <html:text property="appExpDate" size="10" maxlength="10"/>(mm/dd/yyyy)
       </td>
    </tr>
     <tr>
      <th scope="row"     > status:</th>
      <td>
      <c:if test="${sessionScope.CIGARETTE_APPROVE_APPLICATION.feesPending == 0}">
       <html:select property="appStatus" styleId="SELECT_STATUS" onchange="setSelectValue('SELECT_STATUS','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="CIG_APP_STATUS_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
      </c:if>
      <c:if test="${sessionScope.CIGARETTE_APPROVE_APPLICATION.feesPending > 0}">
      Certification Not Ready for Approval( to be posted in accounting)
      </c:if>
       </td>
    </tr>
    
     <tr>
     <th scope="row"     > </th>
     </tr>
       </c:if>
   </tbody>
   </table>
   </html:form>
 
  <div class="clearer">&nbsp;</div>
 </div>  
 </div>
 </div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>