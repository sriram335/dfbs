<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Entertainment Permits"/>
<c:set var="title" scope="request" value="Entertainment Permits"/>
<c:set var="step" scope="request" value="2"/>
 

<jsp:include page="/app/common/hsPageHeader.jsp"/>
 
<div id="mainBox">
<div id="rightContentFluid">
   <p class="messageBox"> <b><u>Status</u></b></br>
    <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
    Application Process-In Progress</br> 
    </c:if>
    <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
    Application Process-In Progress</br> 
    Applicant Information-Completed.</br> 
    </c:if>
     <c:if test="${NEW_VARIANCE_STATUS =='AFTER_DESIGNER'}">
    Application Process-In Progress</br> 
    Applicant Information-Completed.</br> 
    Designer Information-Completed.</br> 
    </c:if> </p>
</div>
    
  <div id="leftContentFluid">
 <h1><b><u>Variance Application Process</u></b></h1></br> </br> 
<html:link page="/start.do?method=fromOwnerApplication">[back]</html:link>
<html:form action="/start" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="saveCompApplication"/> 
 <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
   <tr>
      <th  > Facility County:</th>
      <td >   <html:select property="varProjCounty" >
                <html:option value="">-----</html:option>
                <html:options  collection="VAR_COUNTY_OPTIONS" property="value" labelProperty="label" />
                </html:select>  
</td>
</tr>

 <tr>
      <th >Informed Local Fire Department Official  </th>
      <td >
     <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppLfoNotified != 'Y'}">
           <input type="checkbox" name="varAppLfoNotified"  class="switch"  value="Y"/> 
      </c:if>
       <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppLfoNotified == 'Y'}">
              <input type="checkbox" name="varAppLfoNotified"  class="switch"  value="Y" checked/>
       </c:if>
      </td>
    </tr>
     <tr>
      <th > Local Fire Department Name</th>
      <td >
      
     <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppFDName" size="50" maxlength="100"/>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_DESIGNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppFDName}"/> 
               <html:hidden property="varAppFDName"/> 
       </c:if>
      </td>
    </tr>
     <tr>
      <th >Local Fire Department Official Name </th>
      <td >
      
      <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppLfoName" size="50" maxlength="100"/>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_DESIGNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppLfoName}"/> 
               <html:hidden property="varAppLfoName"/> 
       </c:if>
      </td>
    </tr>
     <tr>
      <th >Local Fire Department Official Email  </th>
      <td >
     
     <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppLfoEmail" size="50" maxlength="100"/>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_DESIGNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppLfoEmail}"/> 
               <html:hidden property="varAppLfoEmail"/> 
       </c:if>
      </td>
    </tr>
    <tr>
      <th >Informed Local Building Official  </th>
      <td >
     
     <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppLboNotified != 'Y'}">
           <input type="checkbox" name="varAppLboNotified"  class="switch"  value="Y"/> 
      </c:if>
       <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppLboNotified == 'Y'}">
              <input type="checkbox" name="varAppLboNotified"  class="switch"  value="Y" checked/>
       </c:if>
      </td>
    </tr>
     <tr>
      <th > Local LBO Department Name </th>
      <td >
     
     <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppBDName" size="50" maxlength="100"/>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_DESIGNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppBDName}"/> 
               <html:hidden property="varAppBDName"/> 
       </c:if>
      </td>
    </tr>
     <tr>
      <th >Local LBO Name  </th>
      <td >
     
      <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppLboName" size="50" maxlength="100"/>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_DESIGNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppLboName}"/> 
               <html:hidden property="varAppLboName"/> 
       </c:if>
      </td>
    </tr>
     <tr>
      <th > Local LBO Email  </th>
      <td >
    
     <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppLboEmail" size="50" maxlength="100"/>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_DESIGNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppLboEmail}"/> 
               <html:hidden property="varAppLboEmail"/> 
       </c:if>
      </td>
    </tr>
    <tr>
      <th > Local LBO Email  </th>
      <td >
    
     <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppLboEmail" size="50" maxlength="100"/>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_DESIGNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppLboEmail}"/> 
       </c:if>
      </td>
    </tr>
     <tr>
      <th >SBC Project Number   </th>
      <td >
     
     <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppSBCNum" size="12" maxlength="12"/>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_DESIGNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppSBCNum}"/> 
               <html:hidden property="varAppSBCNum"/> 
       </c:if>
      </td>
    </tr>
    <tr>
      <th > </th>
      <td >
          <b><u> Submitter Information</u></b>
      </td>
    </tr>
     <tr>
      <th > Submitter Firm Name  </th>
      <td >
           <html:text property="varAppFirmName" size="50" maxlength="80"/>
      </td>
    </tr>
    <tr>
      <th > Submitter First Name  </th>
      <td >
           <html:text property="varAppFirstName()" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th > Submitter Last Name  </th>
      <td >
           <html:text property="varAppLastName()" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th > Submitter Middle Name  </th>
      <td >
           <html:text property="varApppMI" size="1" maxlength="1"/>
      </td>
    </tr>
    <tr>
      <th > Submitter Address1  </th>
      <td >
           <html:text property="varAppAddress1" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th > Submitter Address2  </th>
      <td >
           <html:text property="varAppAddress2" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th > Submitter City  </th>
      <td >
           <html:text property="varAppCity" size="30" maxlength="30"/>
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
      <th scope="row"    class="required"> Submitter Phone  </th>
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
      <th > Submitter Email  </th>
      <td >
           <html:text property="varAppEmail" size="50" maxlength="80"/>
      </td>
    </tr>
     <tr>
      <th > </th>
      <td >
          <b><u> Project Information</u></b>
      </td>
    </tr>
     <tr>
      <th > Project Name  </th>
      <td >
           <html:text property="varProjName" size="50" maxlength="100"/>
      </td>
    </tr>
    <tr>
      <th > Project Address1  </th>
      <td >
           <html:text property="varProjAddress1" size="50" maxlength="100"/>
      </td>
    </tr>
    <tr>
      <th > Project Address2  </th>
      <td >
           <html:text property="varProjAddress2" size="50" maxlength="100"/>
      </td>
    </tr>
    <tr>
      <th > Project Name  </th>
      <td >
           <html:text property="varProjCity" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th > Project State  </th>
      <td >
          IN
      </td>
    </tr>
    <tr>
      <th > Project Zip </th>
      <td >
           <html:text property="varProjZip" size="5" maxlength="5"/>&nbsp;-&nbsp;<html:text property="varProjZip2" size="4" maxlength="4"/>
      </td>
    </tr>
    <tr>
      <th > Project Type  </th>
      <td >
            <html:select property="varProjType" >
                <html:option value="">-----</html:option>
                <html:options  collection="VAR_PROJ_OPTIONS" property="value" labelProperty="label" />
                </html:select>  
      </td>
    </tr>
    <tr>
      <th > Has IDHS Plan Review section issued a Correction Order  </th>
      <td >
            <html:select property="varAppPlanCorrection" >
                <html:option value="">-----</html:option>
                <html:options  collection="YES_NO_OPTIONS" property="value" labelProperty="label" />
                </html:select>  
      </td>
    </tr>
    <tr>
      <th > Has Violation been issued </th>
      <td >
            <html:select property="varAppViolation" >
                <html:option value="">-----</html:option>
                <html:options  collection="YES_NO_OPTIONS" property="value" labelProperty="label" />
                </html:select>  
      </td>
    </tr>
    <tr>
      <th > Violation issued by </th>
      <td >
            <html:select property="varAppVioIssBy" >
                <html:option value="">-----</html:option>
                <html:options  collection="VIO_BY_OPTIONS" property="value" labelProperty="label" />
                </html:select>  
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

