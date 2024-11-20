<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="Select Course Type"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
<c:if test="${sessionScope.NEW_COURSE  != null }"> 
  <jsp:include page="/ems/newCourseStatus.jsp"/>
  </c:if>
  </div>  
<div id="mainBox">

     <a href="/dfbs/ems/main.do?method=authUser">
              [back to main menu]  </a> </br>

  
  <h2>Select Course Type</h2>

  <div id="leftContentFluid">
  

 <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="selectCourse"/> 
     
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
      <th scope="row"  style= "width:100%" class="required"  > * Course Type:</th>
      <td>
       <html:select property="crssCourseId" styleId="SELECT_COURSE" onchange="setSelectValue('SELECT_COURSE','COURSE_ID')">
          <html:option value="">Please Select</html:option>
          <html:options collection="COURSE_LIST_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${COURSE_ERROR.crssCourseId == 0}">
          <br/>
          <span class="error">* please select course name</span> 
        </c:if> 
       </td>
    </tr>
   
    <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
<td>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
<p>
   
          <html:submit value="Next" styleClass="button" />
 </p>
 </c:if>
 
 </td>
 </tr>
  
   </tbody>
   </table>
   </html:form>
   
  
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>


