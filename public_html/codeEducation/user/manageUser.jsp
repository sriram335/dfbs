<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Code Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Manage User(s) and register for class</h2>
   <div id="sideContentFluid" align="left">
  
     <c:if test="${VIEW_USER.userType=='LBO' ||VIEW_USER.userType=='LFO'|| VIEW_USER.userType=='STATE INSPECTOR'}">
     Education Plan Menu:</br>
     <a href="/dfbs/codeEducation/codePlan.do?method=addNewPlan&codeEduType=JOBS">
              [Add duties and jobs requirements]  </a> </br>
     <a href="/dfbs/codeEducation/codePlan.do?method=addNewPlan&codeEduType=TRAINING">
              [Add training details]  </a> </br>
     <a href="/dfbs/codeEducation/codePlan.do?method=addNewPlan&codeEduType=EDUCATION">
              [Add education back ground]  </a> </br>          
    <a href="/dfbs/codeEducation/codePlan.do?method=addNewPlan&codeEduType=OTHER">
              [Add other information]  </a> </br>   
    </c:if>
    <a  href="/dfbs/codeEducation/user.do?method=classList&userPersonId=<c:out value="${VIEW_USER.userPersonId}"/>">[Show Reistered Class List] </a>

    </div>
<div id="mainBox">
 <c:if test="${VIEW_USER !=null}">
  <html:link page="/user.do?method=changePassword">[change password]</html:link>
  <html:link page="/user.do?method=logOut">[log out]</html:link>
  </c:if>
  <div id="leftContentFluid">
 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
  <tbody class="rowHeader" >
  <html:form action="/user" method="post">
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
    <tr>
    <th > user type :</th>
    <td>
    <c:out value="${sessionScope.VIEW_USER.userType}"/>
    <html:hidden property="userType"/>
    </td>
    </tr>
     <tr>
      <th  > user id :</th>
      <td>
      <c:out value="${sessionScope.VIEW_USER.userId}"/>
      </td>
    </tr>
    <tr>
      <th  > user PSID :</th>
      <td>
      <c:out value="${sessionScope.VIEW_USER.userPsid}"/>
      </td>
    </tr>
 <tr>
      <th  > user first name:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userFirstName}"/>
      </td>
    </tr>
 <tr>
      <th > user last name:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userLastName}"/>
      </td>
    </tr>
   <tr>
      <th  > mi:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userMI}"/>
      </td>
    </tr>
     <tr>
      <th  > address1:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userAddress1}"/>
      </td>
    </tr>
     <tr>
      <th  > address2:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userAddress2}"/>
      </td>
    </tr>
   <tr>
      <th  > city:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userCity}"/>
      </td>
    </tr>
    <tr>
      <th  > state:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userState}"/>
      </td>
    </tr>
    <tr>
      <th  > county:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userCounty}"/>
      </td>
    </tr>
    <tr>
      <th  > zip:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userZip}"/>
      </td>
    </tr>
     <tr>
      <th  > zip2:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userZip2}"/>
      </td>
    </tr>
    <tr>
      <th  > telephone:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userPhone}"/>
      </td>
    </tr>
    <tr>
      <th  > fax:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userFax}"/>
      </td>
    </tr>
     </html:form>
      <html:form action="/user" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateUser"/>   
       <html:hidden property="userPersonId" value="${sessionScope.VIEW_USER.userPersonId}" />
     <tr>
     <th scope="row"  style= "width:50%"   ></th>
     <td>
              <html:submit value="Edit User" styleClass="button" />
     </td>
     </tr> 
     </html:form>
      
      <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="courseListUser"/>  
      <html:hidden property="userPersonId" value="${sessionScope.VIEW_USER.userPersonId}" />
     <tr>
     <th scope="row"  style= "width:50%"   ></th>
     <td>
              <html:submit value="Register for Class" styleClass="button" />
               <p class="error">
               <c:if test="${REGISTRATION == 'NOT_READY'}">
              You need an education plan to register for class. Use the links below to add the education plan
              for each category; Jobs, Training and Education. You need one item under each category(Jobs, Training and Education) to register for class.
              </c:if>  </p>
             
     </td>
     </tr> 
      </html:form>
       <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="cancelCourse"/>   
      <html:hidden property="userPersonId" value="${sessionScope.VIEW_USER.userPersonId}" />
     <tr>
     <th scope="row"  style= "width:50%"   ></th>
     <td>
              <html:submit value="View / Cancel my class" styleClass="button" />
             
     </td>
     </tr> 
      </html:form>
   <c:if test="${VIEW_USER.userType=='LBO' ||VIEW_USER.userType=='LFO'|| VIEW_USER.userType=='STATE INSPECTOR'}">
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
    </c:if>
</tbody>
    </table>
     
    </div>
   
    </div>
    <jsp:include page="/app/common/hsPageFooter.jsp"/>