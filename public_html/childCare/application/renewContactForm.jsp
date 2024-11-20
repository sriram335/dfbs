 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <h2>Verify Permit Information</h2>
 <div id="leftContentFluid">
      <a   href="/dfbs/childCare/childcare.do?method=renewPermit&permitKey=<c:out value="${PERMIT_SELECTED.permitNumber}"/>&ownerKey=<c:out value="${PERMIT_SELECTED.ownerId}" />">
             [cancel ]</a>   
      <html:link page="/start.do">[back to start]</html:link>

  <div class="styledBox">
 <html:form action="/contact" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="renewContactVerified"/> 
      <html:hidden property="ownerId"/> 
     <html:hidden property="personId"/> 
     <html:hidden property="contactKey"/> 
      <html:hidden property="addressId"/>
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
     <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >* person first name:</th>
      <td>
       <html:text property="personFirstName" size="30" maxlength="30"/>
        <c:if test="${PERSON_ERROR.personFirstName == 'ERROR'}">
          <br/>
          <span class="error">* please enter person first name</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* person last name:</th>
      <td>
       <html:text property="personLastName" size="30" maxlength="30"/>
        <c:if test="${PERSON_ERROR.personLastName == 'ERROR'}">
          <br/>
          <span class="error">* please enter person last name</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > person middle intial:</th>
      <td>
       <html:text property="personMi" size="1" maxlength="1"/>
        
      </td>
    </tr>
   
   
     <tr>
      <th scope="row"  style= "width:50%"    class="required" > * person email:</th>
      <td>
       <html:text property="personEmail" size="100" maxlength="100"/>
        <c:if test="${PERSON_ERROR.personEmail == 'ERROR'}">
          <br/>
          <span class="error">* please enter email</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > person fax:</th>
      <td>
       <html:text property="personFax" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required" > * person phone:</th>
      <td>
       <html:text property="personPhone" size="10" maxlength="10"/>
       <c:if test="${PERSON_ERROR.personPhone == 'ERROR'}">
          <br/>
          <span class="error"> phone format error</span> 
        </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > person type:</th>
      <td>
       <html:text property="personType" size="30" maxlength="30"/>
      </td>
    </tr>
     
    <tr>
    <td>
   
  </tbody>
        </table>
        <p>
          <html:submit value="Verified " styleClass="button"/>
        
        </p>
    </td>
    </tr>
    </html:form>   
</div>
 
