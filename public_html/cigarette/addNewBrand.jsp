<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Cigarette Brand Information"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">

<a   href="/dfbs/cigarette/applicationsView.do?method=addSupApp">
              [Cancel]</a>
  <div class="styledBox">
 <div id="mainContentFluid" align="left">

  
 <h2>Add New Cigarette Brand Family Information</h2> 
<b>Company Name: <c:out value="${CIGARETTE_COMPANY.companyName}"/></b></br>
 <html:form action="/brand" method="post">
      <input type="hidden" name="method" id="METHOD_KEY2" value="saveNewBrand"/> 
     
      
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <tr>
      <th scope="row"  class="required"    > * brand&nbsp;family :</th>
      <td>
      <html:text property="cigaretteBrand" size="30" maxlength="30"/>
       </td>
    </tr>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCigarette != null}">
  
     <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="Add New Cigarette Brand" styleClass="button" />
     
     </td>
     </tr>
     </c:if>
   </tbody>
   </table>
   </html:form>
   </div>
   </div>
 
 </div>  
  <div class="clearer">&nbsp;</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>


