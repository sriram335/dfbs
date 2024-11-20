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
    <jsp:include page="status.jsp"/>
  </div>
    
    
  <div id="leftContentFluid">
  <h1><b><u>Variance Application Process</u></b></h1></br> </br> 
  <a  href="/dfbs/variance/start.do"> back to start</a> </br></br> 
  <h2>Person Submitting Application on behalf of the Applicant(If not submtted by the applicant)</h2> 
  
<html:form action="/start" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="saveSubmitter"/> 
 <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
    
    <tr>
      <th > </th>
      <td >
          <b><u> Submitter Information</u></b>
      </td>
    </tr>
    <tr>
     <tr>
      <th scope="row"   > Submitter Firm Name (if applicable) </th>
      <td >
           <html:text property="varAppFirmName" size="50" maxlength="80"/>
           
      </td>
    </tr>
    <tr>
      <th scope="row"    class="required"> * Submitter First Name  </th>
      <td >
           <html:text property="varAppFirstName" size="30" maxlength="30"/>
            <c:if test="${VARIANCE_APPLICATION_ERROR.varAppFirstName == 'ERROR'}">
              <br/><span class="error">* please enter first name  </span> 
            </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"    class="required"> * Submitter Last Name  </th>
      <td >
           <html:text property="varAppLastName" size="30" maxlength="30"/>
            <c:if test="${VARIANCE_APPLICATION_ERROR.varAppLastName == 'ERROR'}">
              <br/><span class="error">* please enter last name  </span> 
            </c:if>
      </td>
    </tr>
    <tr>
      <th > Submitter Middle Initial  </th>
      <td >
           <html:text property="varApppMI" size="1" maxlength="1"/>
      </td>
    </tr>
    <tr>
      <th scope="row"    class="required"> * Submitter Address1  </th>
      <td >
           <html:text property="varAppAddress1" size="30" maxlength="30"/>
            <c:if test="${VARIANCE_APPLICATION_ERROR.varAppAddress1 == 'ERROR'}">
              <br/><span class="error">* please enter address1  </span> 
            </c:if>
      </td>
    </tr>
    <tr>
      <th > Submitter Address2  </th>
      <td >
           <html:text property="varAppAddress2" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"    class="required">* Submitter City  </th>
      <td >
           <html:text property="varAppCity" size="30" maxlength="30"/>
            <c:if test="${VARIANCE_APPLICATION_ERROR.varAppCity == 'ERROR'}">
              <br/><span class="error">* please enter city  </span> 
            </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"    class="required" > * Submitter state:</th>
      <td>
          <html:select property="varAppState" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','STATE_INITIAL')">
          <html:option value = "0" >Please Select</html:option>
          <html:options collection="VAR_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${OWNER_ERROR.desState == 'ERROR' }">
          <br/>
          <span class="error">* please specify state</span> 
        </c:if> 
       
       </td>
    </tr>
     <tr>
      <th > Submitter Zip  </th>
      <td >
           <html:text property="varAppZip" size="5" maxlength="5"/>&nbsp;-&nbsp;<html:text property="varAppZip2" size="4" maxlength="4"/>
      </td>
    </tr>
     <tr>
      <th scope="row"    class="required"> * Submitter Phone  </th>
      <td >
           <html:text property="varAppTelephone" size="10" maxlength="10"/>
             <c:if test="${VARIANCE_APPLICATION_ERROR.varAppTelephone == 'ERROR'}">
              <br/><span class="error">* please enter Telephone  </span> 
            </c:if>
      </td>
    </tr>
    <tr>
      <th > Submitter Fax  </th>
      <td >
           <html:text property="varAppFax" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
      <th scope="row"    class="required">* Submitter Email  </th>
      <td >
           <html:text property="varAppEmail" size="50" maxlength="80"/>
            <c:if test="${VARIANCE_APPLICATION_ERROR.varAppEmail == 'ERROR'}">
              <br/><span class="error">* please enter email  </span> 
            </c:if>
      </td>
    </tr>
 </tbody>
</table>
      <p>
          <html:submit value="Next" styleClass="button"/>
        </p>
</html:form>

 </div> 
 <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>

