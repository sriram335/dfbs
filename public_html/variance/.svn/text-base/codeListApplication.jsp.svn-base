<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Variance Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>

<div id="mainBox">
<div id="sideContentFluid">
<jsp:include page="status.jsp"/>

</div>
<div id="leftContentFluid">

 <h1><b><u>Variance Application Process</u></b></h1></br> </br> 

 

<table cellspacing="0" class="noBorder" summary="OWNER FORM" >
     <tbody class="rowHeader">
     <html:form action="/code" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="compCodeDetail"/>
  <%--   <html:hidden property="varId"/> 
     <html:hidden property="varCodeId"/>  --%>
    <c:forEach var="mapItem" items="${VARIANCE_APPLICATION_SELECTED.codeMap}" varStatus="j">
                      <c:set scope="request" var="code" value="${mapItem.value}"/>
        <tr>
      <th > </th>
      <td >
          <b><u>  Variance Codes Added</u></b></br>
      </td>
          </tr>
              <tr >
                <td>
               Code Name: <c:out value="${code.varCode}"/></br>
               Specific Section: <c:out value="${code.varCodeName}"/> </br>
              <a   href="/dfbs/variance/code.do?method=editCode&codeKey=<c:out value="${code.codeKey}"/>">
              <span style="font-size:x-small">[edit variance code]</span> </a>   
               <a   href="/dfbs/variance/code.do?method=removeCode&codeKey=<c:out value="${code.codeKey}"/>">
              <span style="font-size:x-small">[remove variance code]</span> </a></br>  
                </td>
                </tr>
    </c:forEach>
     
     <tr>
     <th scope="row"     > </th>
    <td>
    <span class="error"> If your project has multiple variances Click "Add Another Variance" , add all the variances. Do not try to add a new project </span>
       </br>  </br> <html:submit value="Add Another Variance" styleClass="button" property="completeApplication"/></br></br>
              <html:submit value="Upload File(s)" styleClass="button" property="completeApplication"/></br></br>
               <b> <u class="error">Make sure you upload all the project plans related variance before clicking complete application button</u></b>
           </br>  </br> <html:submit value="Complete Application" styleClass="button" property="completeApplication"/></br>
     </td>
     </tr> 
     </html:form>
 </tbody>
</table>



</div>

<div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 