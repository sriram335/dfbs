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
    <jsp:include page="status.jsp"/>
  </div>
    
    
  <div id="leftContentFluid">
  <h1><b><u>Variance Application Process</u></b></h1></br> </br> 
  <a  href="/dfbs/variance/start.do"> back to start</a> </br></br> 
  <%-- step 1--%>
  <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
  <b><u>Please select County, Fire Department Name and Local Building official Name </u></b></br> </br>
<html:form action="/start" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="nextApplication"/> 
 <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
   <tr>
      <th  class="required">* Facility County:</th>
      <td >   <html:select property="varProjCounty" >
                <html:option value="">-----</html:option>
                <html:options 
                  collection="VAR_COUNTY_OPTIONS" 
                  property="value" 
                  labelProperty="label" />
                </html:select>  
                 <c:if test="${VAR_APPLICATION_ERROR.varProjCounty == 'ERROR' }">
                  <br/>
                  <span class="error">* please specify county</span> 
                 </c:if> 
</td>
</tr>

 <tr>
      <th class="required">* Informed Local Fire Official  </th>
      <td >
     
     <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppLfoNotified != 'Y'}">
           <input type="checkbox" name="varAppLfoNotified"  class="switch"  value="Y"/> 
            <c:if test="${VAR_APPLICATION_ERROR.varAppLfoNotified == 'ERROR' }">
                  <br/>
                  <span class="error">* please specify lfo notified</span> 
                 </c:if>
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
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppFDName}"/> 
               <html:hidden property="varAppFDName"/> 
       </c:if>
      </td>
    </tr>
     <tr>
      <th class="required">* Local Fire Official Name </th>
      <td >
      
      <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppLfoName" size="50" maxlength="100"/>
            <c:if test="${VAR_APPLICATION_ERROR.varAppLfoName == 'ERROR' }">
                  <br/>
                  <span class="error">* please specify lfo name</span> 
                 </c:if>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppLfoName}"/> 
               <html:hidden property="varAppLfoName"/> 
       </c:if>
      </td>
    </tr>
     <tr>
      <th class="required">* Local Fire Official Email  </th>
      <td >
     
     <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppLfoEmail" size="50" maxlength="100"/>
            <c:if test="${VAR_APPLICATION_ERROR.varAppLfoEmail == 'ERROR' }">
                  <br/>
                  <span class="error">* please specify lfo email</span> 
                 </c:if>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppLfoEmail}"/> 
               <html:hidden property="varAppLfoEmail"/> 
       </c:if>
      </td>
    </tr>
    <tr>
      <th class="required">* Informed Local Building Official  </th>
      <td >
     
     <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppLboNotified != 'Y'}">
           <input type="checkbox" name="varAppLboNotified"  class="switch"  value="Y"/> 
           <c:if test="${VAR_APPLICATION_ERROR.varAppLboNotified == 'ERROR' }">
                  <br/>
                  <span class="error">* please specify lbo notified</span> 
                 </c:if>
      </c:if>
       <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppLboNotified == 'Y'}">
              <input type="checkbox" name="varAppLboNotified"  class="switch"  value="Y" checked/>
       </c:if>
      </td>
    </tr>
     <tr>
      <th > LBO Department Name </th>
      <td >
     
     <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppBDName" size="50" maxlength="100"/>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppBDName}"/> 
               <html:hidden property="varAppBDName"/> 
       </c:if>
      </td>
    </tr>
     <tr>
      <th class="required">* Local LBO Name  </th>
      <td >
     
      <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppLboName" size="50" maxlength="100"/>
           <c:if test="${VAR_APPLICATION_ERROR.varAppLboName == 'ERROR' }">
                  <br/>
                  <span class="error">* please specify lbo name</span> 
                 </c:if>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppLboName}"/> 
               <html:hidden property="varAppLboName"/> 
       </c:if>
      </td>
    </tr>
     <tr>
      <th class="required"> * Local LBO Email  </th>
      <td >
    
     <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppLboEmail" size="50" maxlength="100"/>
           <c:if test="${VAR_APPLICATION_ERROR.varAppLboEmail == 'ERROR' }">
                  <br/>
                  <span class="error">* please specify lbo email</span> 
                 </c:if>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppLboEmail}"/> 
               <html:hidden property="varAppLboEmail"/> 
       </c:if>
      </td>
    </tr>
 <tr>
      <th >SBC Project Number   </th>
      <td >
     
     <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppSBCNum" size="12" maxlength="12"/>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppSBCNum}"/> 
               <html:hidden property="varAppSBCNum"/> 
       </c:if>
      </td>
    </tr>
 </tbody>
</table>
      <p>
          <html:submit value="Next" styleClass="button"/>
        </p>
</html:form>
</c:if>
 <%-- step 2--%>
<c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
<html:form action="/start" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="savePreDesApplication"/> 
 <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
   <tr>
      <th  > Facility County:</th>
      <td >   <html:select property="varProjCounty" >
                <html:option value="">-----</html:option>
                <html:options 
                  collection="VAR_COUNTY_OPTIONS" 
                  property="value" 
                  labelProperty="label" />
                </html:select>  
</td>
</tr>

 <tr>
      <th >Informed Local Fire Official  </th>
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
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppFDName}"/> 
              <html:hidden property="varAppFDName"/>
       </c:if>
      </td>
    </tr>
     <tr>
      <th >Local Fire Official Name </th>
      <td >
      
      <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppLfoName" size="50" maxlength="100"/>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
              <c:out value="${VARIANCE_APPLICATION_SELECTED.varAppLfoName}"/> 
              <html:hidden property="varAppLfoName"/>
       </c:if>
      </td>
    </tr>
     <tr>
      <th >Local Fire Official Email  </th>
      <td >
     
     <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
           <html:text property="varAppLfoEmail" size="50" maxlength="100"/>
      </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
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
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
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
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
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
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
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
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
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
      <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
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
 </tbody>
</table>
      <p>
          <html:submit value="Next" styleClass="button"/>
        </p>
</html:form>
</c:if>
 </div> 
 <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>

