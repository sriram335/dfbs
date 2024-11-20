<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
<div id="sideContentFluid">
     <a   href="/dfbs/code/editPerson.do?method=backtoview">
              [ Cancel ]</a>               
  </div>
  <div id="leftContentFluid">
  <h2 style="font-weight:bold;">1) Edit Contact Person Information</h2>
 <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">   
    <html:form action="/editPerson" focus="codePersonLastname" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="savePerson"/>
      <html:hidden property="codePersonId"/>
      <html:hidden property="codePersonCompanyId"/>
      <html:hidden property="codePersonType"/>
      <html:hidden property="applicationKey"/>
      
      
      <h3>  person Details</h3>
         
      <tr>
      <th scope="row" class="required">*person last name:</th>
      <td>
        <html:text property="codePersonLastName" size="35" maxlength="60"/>
        <c:if test="${CODE_PERSON_ERROR.codePersonLastName == 'ERROR'}">
          <br/>
          <span class="error">* please specify last name</span> 
        </c:if>
        </br>
      </td>
    </tr>
     <tr>
      <th scope="row" class="required">*person first name:</th>
      <td>
        <html:text property="codePersonFirstName" size="30" maxlength="30"/>
        <c:if test="${CODE_PERSON_ERROR.codePersonFirstName == 'ERROR'}">
          <br/>
          <span class="error">* please specify First name</span> 
        </c:if>
        <br/>
      </td>
    </tr>
      <tr>
      <th >person middle name:</th>
      <td>
        <html:text property="codePersonMiddleName" size="30" maxlength="30"/>
       <br/>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*person title:</th>
      <td>
        <html:text property="codePersonTitle" size="30" maxlength="50"/>
        <c:if test="${CODE_PERSON_ERROR.codePersonTitle == 'ERROR'}">
          <br/>
          <span class="error">* please specify title</span> 
        </c:if>
        <br/>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*person phone:</th>
      <td>
        <html:text property="codePersonTelephone" size="20" maxlength="20"/>
        <c:if test="${CODE_PERSON_ERROR.codePersonTelephone == 'ERROR'}">
          <br/>
          <span class="error">* please specify phone</span> 
        </c:if>
        <br/>
      </td>
    </tr>
    </tr>
      <tr>
      <th >person fax:</th>
      <td>
        <html:text property="codePersonFax" size="20" maxlength="20"/>
       <br/>
      </td>
    </tr>
    </tr>
      <tr>
      <th >person email:</th>
      <td>
        <html:text property="codePersonEmail" size="50" maxlength="80"/>
       <br/>
      </td>
    </tr>

</div>
 <tr>
              <th colspan="2">&nbsp;</th>
            </tr>
          </tbody>
        </table>
     
  <p>
        <html:submit value="save to your application" styleClass="button"/>
</p>
 <p class="messageBox"><span class="error">Note: To remove the contact from database update last name and first name to DELETE, when your transaction is received in DHS office we will process it. </span></p>
 
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
</html:form>
