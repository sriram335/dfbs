<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Application Information"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Complete Contact Information</h2> 
  <div class="styledBox">
 <html:form action="/application" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveApplication"/> 
      <html:hidden property="appId"/> 
      <html:hidden property="companyId"/>
      <html:hidden property="applicationKey"/>
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    
      <th scope="row"  class="required"    > * contact name:</th>
      <td>
       <html:text property="contact" size="30" maxlength="30"/>
       <c:if test="${CIGARETTE_APPLICATION_ERROR.contact == 'ERROR'}">
          <br/>
          <span class="error">* please enter contact name</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"   class="required"   > * contact title:</th>
      <td>
       <html:text property="title" size="50" maxlength="50"/>
       <c:if test="${CIGARETTE_APPLICATION_ERROR.title == 'ERROR'}">
          <br/>
          <span class="error">* please enter title </span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * contact phone:</th>
      <td>
       <html:text property="phone" size="10" maxlength="10"/>
       <c:if test="${CIGARETTE_APPLICATION_ERROR.phone == 'ERROR'}">
          <br/>
          <span class="error">* please enter phone</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"     > contact fax:</th>
      <td>
       <html:text property="fax" size="10" maxlength="10"/>
       </td>
    </tr>
    <tr>
      <th scope="row"   class="required"  > * contact email:</th>
      <td>
       <html:text property="email" size="80" maxlength="80"/></br>
       All the electronic correspondence for the application will be sent to this address.
       (<u><b>Note:</b></u> correspondence includes, but is not limited to certification(s), application status and renewal reminders)  
       <c:if test="${CIGARETTE_APPLICATION_ERROR.email == 'ERROR'}">
          <br/>
          <span class="error">* please enter email address</span> 
        </c:if>
       </td>
    </tr>
     
    <tr>
      <th scope="row"     > comments:</th>
      <td>
       <html:textarea property="comments" /></br>
       ( For Supplemental application indicate the reason for the application. Example: Amendment to an existing brand family
       or a new addition to a existing brand family )
       </td>
    </tr>
     <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="Save Application" styleClass="button" />
     
     </td>
     </tr>
   </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
</div> 
<jsp:include page="/app/common/hsPageFooter.jsp"/>