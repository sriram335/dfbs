<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Code Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Code Education Student Information</h2>
 <div id="sideContentFluid">
     <a   href="/dfbs/codeEducation/start.do">
              [ back to start ]</a> 
<c:if test="${SECURITY_SUPER_USER == 'YES'}">  
   <a href="/dfbs/codeEducation/user.do?method=userList">
              [back to user list ]  </a> </br>
   <a href="/dfbs/codeEducation/course.do?method=courseList">
              [back to course list ]  </a> </br>
</c:if>
   </div>

 <div id="leftContentFluid">
 <c:if test="${SECURITY_SUPER_USER != 'YES'}">
 For updating user information like phone /fax/ address details contact <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>" href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>">
          <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/></A>
</c:if>
 <c:if test="${CODE_EDU_USER_TYPE =='NEED_EDU_PLAN'}">
  <p class="messageBox" >

 ** Steps 1.Fill up the person information . </br>
          2.Complete Education Plan, enter at least one item for duites, training, education.</br>
          3.If you complete tha application, IDHS will look up your application and then, confirm your account.</br>
          
   
</p>
</c:if>
</br> 


 <html:form action="/user" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateSaveUser"/>   
       <html:hidden property="userPersonId"/>
       <html:hidden property="userPassword"/>
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
    <tr>
    <th > User Type</th>
    <td>
    <c:out value="${sessionScope.VIEW_USER.userType}"/>
    <html:hidden property="userType"/>
    </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* user id :</th>
      <td>
       <c:out value="${VIEW_USER.userId}"/>
         <html:hidden property="userId"/>
      </td>
     </tr>
        <tr>
      <th scope="row"  style= "width:50%"   > user PSID :</th>
      <td>
      <html:text property="userPsid" size="15" maxlength="15"/>
    </td>
     </tr>
     <c:if test="${SECURITY_SUPER_USER == 'YES'}">
         
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user first name:</th>
      <td>
      <html:text property="userFirstName" size="30" maxlength="30"/>
       </td>
     
    </tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user last name:</th>
      <td>
      <html:text property="userLastName" size="30" maxlength="30"/>
      </td>
     
    </tr>
   <tr>
      <th scope="row"  style= "width:50%"   >*mi:</th>
      <td>
      <html:text property="userMI" size="1" maxlength="1"/>
       </td>
    </tr>
    </c:if>
    <c:if test="${SECURITY_SUPER_USER != 'YES'}">
 <tr> 
      <th scope="row"  style= "width:50%" class="required"  >*user first name:</th>
      <td>
       <c:out value="${VIEW_USER.userFirstName}"/>
         <html:hidden property="userFirstName"/>
      </td>
     
    </tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user last name:</th>
      <td>
       <c:out value="${VIEW_USER.userLastName}"/>
         <html:hidden property="userLastName"/>
      </td>
     
    </tr>
   <tr>
      <th scope="row"  style= "width:50%"   >*mi:</th>
      <td>
       <c:out value="${VIEW_USER.userMI}"/>
         <html:hidden property="userMI"/>
      </td>
    </tr>
    </c:if>
     <tr>
      <th scope="row"  style= "width:50%" class="required" >*address1:</th>
      <td>
       <html:text property="userAddress1" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.address1 == 'ERROR'}">
          <br/>
          <span class="error">* please enter user address1</span> 
        </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  >address2:</th>
      <td>
       <html:text property="userAddress2" size="30" maxlength="30"/>
    </td>
    </tr>
   
     <tr>
      <th scope="row"  style= "width:50%" class="required" >*user city:</th>
      <td>
       <html:text property="userCity" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.address1 == 'ERROR'}">
          <br/>
          <span class="error">* please enter user city</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" >state:</th>
      <td>
        <html:select property="userState">
          <html:option value="IN">INDIANA</html:option>
          <html:options collection="USER_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
      </td>
    </tr>
    <tr>
      <th scope="row" >county:</th>
      <td>
        <html:select property="userCounty" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">-----</html:option>
          <html:options collection="USER_COUNTY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${COURSE_ERROR.county == 'ERROR'}">
          <br/>
          <span class="error">* please specify county</span> 
        </c:if> 
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required" >zip:</th>
      <td>
       <html:text property="userZip" size="5" maxlength="5"/>
       <c:if test="${USER_ERROR.userZip == 'ERROR'}">
          <br/>
          <span class="error">* please enter user zip</span> 
        </c:if>
    </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  >zip2:</th>
      <td>
       <html:text property="userZip2" size="4" maxlength="4"/>
    </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* telephone:</th>
      <td>
       <html:text property="userPhone" size="10" maxlength="10"/>
    </td>
    </tr>  
    <tr>
      <th scope="row"  style= "width:50%"  >fax:</th>
      <td>
       <html:text property="userFax" size="10" maxlength="10"/>
    </td>
    </tr>
    <c:if test="${SECURITY_SUPER_USER == 'YES'}">  
     <tr>
     <th scope="row"  style= "width:50%"   ></th>
     <td>
              <html:submit value="update" styleClass="button" />
     </td>
     </tr> 
     </c:if>
      <tr>
   <td>
  <b><u> Education Plan:</u></b></br>
      <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=code_user_edu_plan&p_user_id=<c:out value="${VIEW_USER.userPersonId}" />">
              [print complete education plan]  </a> </br>
   </td>
   </tr>
    <c:forEach var="plan" items="${VIEW_USER.plans}" varStatus="i" >
    <tr>
    <th>Type : <c:out value="${plan.eduType}" /></th>
    <td>
       <c:out value="${plan.eduDescription}" />  <a href="/dfbs/codeEducation/codePlan.do?method=updatePlan&planId=<c:out value="${plan.codeEduId}" />">
              [edit]  </a> </br></br> 
  
    </td>
    </tr>
    </c:forEach>
</tbody>
</table>
  </html:form>  
   
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>
