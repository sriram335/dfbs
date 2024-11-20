<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Code Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Add New Education Plan Detail</h2>
    <div id="sideContentFluid">
     <a   href="/dfbs/codeEducation/start.do">
              [ back to start ]</a> 
  
   </div>
 <div id="leftContentFluid">
 
 <html:form action="/codePlan" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="savePlan"/>   
       <html:hidden property="codePersonId"/>
        <html:hidden property="eduType"/>
       
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
    <tr>
    <th > Plan Type:</th> 
    <td>
    <c:out value="${sessionScope.PLAN_TYPE}"/>
    </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:100%" class="required"  >*Details(2000 characters) </th>
      <td>
       <html:textarea property="eduDescription" />
        <c:if test="${PLAN_ERROR.eduDescription == 'ERROR'}">
          <br/>
          <span class="error">* please enter education plan detail for<c:out value="${sessionScope.PLAN_TYPE}"/> </span> 
        </c:if>
      </td>
     
      <tr>
     <th scope="row"  style= "width:50%"   ></th>
     <td>
              <html:submit value="save" styleClass="button" />
     </td>
     </tr> 
   
</tbody>
</table>
  </html:form>  
   
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>
