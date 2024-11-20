<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Code Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Select User Type</h2>
  <div id="leftContentFluid">
  <html:form action="/user" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveUserType"/>   
       <html:hidden property="userPersonId"/>
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row" >User Type:</th>
      <td>
        <html:select property="userType" styleId="SELECT_USER" onchange="setSelectValue('SELECT_USER_TYPE','ABBREVIATION')">
          <html:option value="">-----</html:option>
          <html:options collection="USER_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${COURSE_ERROR.userType == 'ERROR'}">
          <br/>
          <span class="error">* please specify user Type</span> 
        </c:if> 
      </td>
    </tr>
    <tr>
    <th scope="row" ></th>
 <td>

          <html:submit value="Next" styleClass="button" />

 </td>
</tr> 
    </tbody>
    </table>
    </html:form>
    </div>
    </div>
    <jsp:include page="/app/common/hsPageFooter.jsp"/>