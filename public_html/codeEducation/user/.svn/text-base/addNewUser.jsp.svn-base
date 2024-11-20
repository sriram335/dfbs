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
              [ Cancel ]</a>     
   </div>
 <div id="leftContentFluid">
 <c:if test="${CODE_EDU_USER_TYPE =='NEED_EDU_PLAN'}">
  <p class="messageBox" >

 ** Steps 1.Fill up the person information . </br>
          2.Complete Education Plan, enter at least one item for duites, training, education.</br>
          3.If you complete tha application, IDHS will look up your application and then, confirm your account.</br>
   
</p>
</c:if>
</br> 


 <html:form action="/user" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveNewUser"/>   
       <html:hidden property="userPersonId"/>
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
    <tr>
    <th > User Type</th>
    <td>
    <c:out value="${sessionScope.USER_TYPE}"/>
    <html:hidden property="userType"/>
    </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >* user id (<c:out value="${CODE_EDU_USER_ID_REQUIREMENT}" />):</th>
      <td>
       <html:text property="userId" size="50" maxlength="80"/>
        <c:if test="${USER_ERROR.userId == 'ERROR'}">
          <br/>
          <span class="error">* please enter correct userId /email address</span> 
        </c:if>
        <c:if test="${USER_EXIST == 'TRUE'}">
          <br/>
          <span class="error">* This user id exists in the database , retrieve your user id and password using the password recovery feature</span> 
          <html:link page="/user.do?method=emailPasswordPage">[Forgot Password]</html:link>
        </c:if>
      </td>
     
    </tr>
     <c:if test="${sessionScope.USER_TYPE == 'STATE INSPECTOR'}">
      <tr>
      <th scope="row"  style= "width:50%" class="required"  >*password:</th>
      <td>
       Your password will be same as dfbs password.
      </td>
      </tr>
      </c:if>
    <c:if test="${sessionScope.USER_TYPE == 'LBO' ||sessionScope.USER_TYPE == 'LFO' ||sessionScope.USER_TYPE == 'OTHER' }"> 
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >* password (<c:out value="${CODE_EDU_PASSWORD_REQUIREMENT}" />):</th>
      <td>
       <input type="password" name="userPassword" size="10" maxlength="10"/>
        <c:if test="${USER_ERROR.userPassword == 'ERROR'}">
          <br/>
          <span class="error">* please enter correct password(min length 6)</span> 
        </c:if>
      </td>
    </tr>
    </c:if>
     <c:if test="${sessionScope.USER_TYPE == 'STATE EMPLOYEE'}"> 
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >* password (<c:out value="${CODE_EDU_PASSWORD_REQUIREMENT}" />):</th>
      <td>
       <input type="password" name="userPassword" size="10" maxlength="10"/>
        <c:if test="${USER_ERROR.userPassword == 'ERROR'}">
          <br/>
          <span class="error">* please enter correct password(min length 6)</span> 
        </c:if>
      </td>
    </tr>
    </c:if>
 <tr>
      <th scope="row"  style= "width:50%"   >user PSID( if known):</th>
      <td>
       <html:text property="userPsid" size="15" maxlength="15"/>
       </td>
    </tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user first name:</th>
      <td>
       <html:text property="userFirstName" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.userFirstName == 'ERROR'}">
          <br/>
          <span class="error">* please enter user first name</span> 
        </c:if>
      </td>
     
    </tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user last name:</th>
      <td>
       <html:text property="userLastName" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.userLastName == 'ERROR'}">
          <br/>
          <span class="error">* please enter user last name</span> 
        </c:if>
      </td>
     
    </tr>
   <tr>
      <th scope="row"  style= "width:50%"   >mi:</th>
      <td>
       <html:text property="userMI" size="1" maxlength="1"/>
        
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required" >*address1:</th>
      <td>
       <html:text property="userAddress1" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.userAddress1 == 'ERROR'}">
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
        <c:if test="${USER_ERROR.userCity == 'ERROR'}">
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
      
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required" >*zip:</th>
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
      <th scope="row"  style= "width:50%"   > telephone:</th>
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
     <c:if test="${sessionScope.USER_TYPE != 'OTHER' }"> 
     <tr>
     <th scope="row"  style= "width:50%"   ></th>
     <td>
              <html:submit value="Submit request for new account" styleClass="button" />
     </td>
     </tr> 
     </c:if>
      <c:if test="${sessionScope.USER_TYPE == 'OTHER' }"> 
     <tr>
     <th scope="row"  style= "width:50%"   ></th>
     <td>
              <html:submit value="next" styleClass="button" />
     </td>
     </tr> 
     </c:if>
</tbody>
</table>
  </html:form>  
   
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>
