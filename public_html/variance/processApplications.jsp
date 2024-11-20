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

<div id="sideContentFluid">
   <b><u>Applications to be processed</u></b></br>
<table cellspacing="0" style="width: 40%;" summary="sales">
  <tr>
    <th>Variance Details</th>
   
  </tr>
  <tbody>
       <c:forEach var="varApp" items="${requestScope.VARIANCE_APPLICATION_LIST}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message" style="font-weight:bold;font-size:medium;">
         <b> <c:out value="${varApp.varId}"/>&nbsp;</span></b></br>
         Project Name:<c:out value="${varApp.varProjName}"/>&nbsp;</br>
         Application Date:<c:out value="${varApp.varAppRecDateString}"/>&nbsp;</br>
          <a   href="/dfbs/variance/start.do?method=viewApp&varId=<c:out value="${varApp.varId}"/>">
             [view  application ]</a> 
          
        </td>
      </tr>
       </c:forEach>
  </tbody>
</table>
    </p>
</div>
    
  <div id="leftContentFluid">
 <h1><b><u>Variance Application Process</u></b></h1></br> </br> 
 
 <b><u>Files Attached to the Variance</u></b></br>
 <c:forEach var="file" items="${sessionScope.VARIANCE_APPLICATION_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
      <c:out value="${file.fileName}"/>,Upload Date:<c:out value="${file.fileDateString}"/></br>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
       <a   href="/dfbs/variance/start.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a>
        </c:if>
        
          </td>
        <td>
</tr><br/>
</c:forEach>
<b><u>Codes List with Variance Application</u></b></br>
 <c:forEach var="code" items="${sessionScope.VARIANCE_APPLICATION_SELECTED.varCodesList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
     Code: <c:out value="${code.varCode}"/>&nbsp;&nbsp;Code Edition:<c:out value="${code.varEdition}"/>&nbsp;&nbsp;
      Code Name: <c:out value="${code.varCodeName}"/>&nbsp;&nbsp;
      <%--Nature of Non-Compliance: <c:out value="${code.varNatureNonComp}"/>--%>
      
      <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
       <a   href="/dfbs/variance/start.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [edit]</a>
        </c:if>
        
          </td>
        <td>
</tr><br/>
</c:forEach>
<a   href="/dfbs/variance/start.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [edit/view Owner]</a>
<a   href="/dfbs/variance/start.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [edit/ view Designer]</a>
<html:link page="/start.do?method=fromOwnerApplication">[back]</html:link>
<html:form action="/start" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="updateApplication"/> 
    <html:hidden property="varId"/> 
    <html:hidden property="varAppProjId"/> 
    <html:hidden property="varProjId"/> 
    <html:hidden property="varAppNumCode"/>
    <html:hidden property="varProjOwnerId"/>
    <html:hidden property="varAppRecDate"/>
 <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <tr>
      <th >Variance Number</th>
      <td >
           <html:text property="varAppVarNumber" size="12" maxlength="12"/>
    
      </td>
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
           <html:text property="varAppFDName" size="50" maxlength="100"/>
    
      </td>
    </tr>
     <tr>
      <th >Local Fire Department Official Name </th>
      <td >
           <html:text property="varAppLfoName" size="50" maxlength="100"/>
      
      </td>
    </tr>
     <tr>
      <th >Local Fire Department Official Email  </th>
      <td >
            <html:text property="varAppLfoEmail" size="50" maxlength="100"/>
      
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
           <html:text property="varAppBDName" size="50" maxlength="100"/>
     
      </td>
    </tr>
     <tr>
      <th >Local LBO Name  </th>
      <td >
          <html:text property="varAppLboName" size="50" maxlength="100"/>
     
      </td>
    </tr>
     <tr>
      <th > Local LBO Email  </th>
      <td >
           <html:text property="varAppLboEmail" size="50" maxlength="100"/>
      </td>
    </tr>
   
     <tr>
      <th >SBC Project Number   </th>
      <td >
           <html:text property="varAppSBCNum" size="12" maxlength="12"/>
      </td>
    </tr>
     <tr>
      <th > Applicant Firm Name  </th>
      <td >
           <html:text property="varAppFirmName" size="50" maxlength="80"/>
      </td>
    </tr>
    <tr>
      <th > Applicant First Name  </th>
      <td >
           <html:text property="varAppFirstName()" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th > Applicant Last Name  </th>
      <td >
           <html:text property="varAppLastName()" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th > Applicant Middle Name  </th>
      <td >
           <html:text property="varApppMI" size="1" maxlength="1"/>
      </td>
    </tr>
    <tr>
      <th > Applicant Address1  </th>
      <td >
           <html:text property="varAppAddress1" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th > Applicant Address2  </th>
      <td >
           <html:text property="varAppAddress2" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th > Applicant City  </th>
      <td >
           <html:text property="varAppCity" size="30" maxlength="30"/>
      </td>
    </tr>
     <tr>
      <th scope="row"    class="required" > *  state:</th>
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
      <th > Applicant Zip  </th>
      <td >
           <html:text property="varAppZip" size="5" maxlength="5"/>&nbsp;-&nbsp;<html:text property="varAppZip2" size="4" maxlength="4"/>
      </td>
    </tr>
    <tr>
      <th > Applicant Phone  </th>
      <td >
           <html:text property="varAppTelephone" size="10" maxlength="10"/>
             <c:if test="${OWNER_ERROR.ownerDBA == 'ERROR'}">
              <br/><span class="error">* please enter DBA  </span> 
            </c:if>
      </td>
    </tr>
    <tr>
      <th > Applicant Fax  </th>
      <td >
           <html:text property="varAppFax" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
      <th > Applicant Email  </th>
      <td >
           <html:text property="varAppEmail" size="50" maxlength="80"/>
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
     <tr>
       <th scope="row" style="width:30%" class="required">DEMONSTRATION THAT PUBLIC HEALTH, SAFETY, AND WELFARE ARE PROTECTED:</th>
      <td>
        <html:radio property="varAppPhAffirm" value="1"  />Non-compliance with rule will not be adverse to the public health, safety oe welfare: or
        <br/>
        <html:radio property="varAppPhAffirm" value="2"  />Applicant will undertake alternate actions in lieu of compliance with the rule to ensure that 
        granting of the variance will not be adverse to  public health, safety oe welfare. Explain why alternate actions would be adequate (be specific).
          <br/> 
      </td>
    </tr>
     <tr>
      <th scope="row"   class="required"     >  facts demonstrating that the above selected statement is true:(max 2000 characters), if more upload the document</th>
          <td>
           <a href="/dfbs/variance/start.do?method=goToUpload&idNumber=<c:out value="${VARIANCE_APPLICATION_SELECTED.varAppTelephone}"/>&idType=Variance">
                    [upload file(s)]</a> </br>
            <html:textarea property="varAppPhAffirmComment" rows="10" cols="70"/>
                 <c:if test="${VIOLATION_ERROR.varAppPhAffirmComment == 'ERROR'}">
              <br/><span class="error">* please enter description  </span> 
            </c:if>
            </td>
      </tr> 
    <tr>
      <th >utility services  </th>
      <td >
     <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffPhy != 'Y'}">
           <input type="checkbox" name=" varAppHistAffPhy"  class="switch"  value="Y"/> 
      </c:if>
       <c:if test="${VARIANCE_APPLICATION_SELECTED. varAppHistAffPhy == 'Y'}">
              <input type="checkbox" name=" varAppHistAffPhy"  class="switch"  value="Y" checked/>
       </c:if>
      </td>
    </tr>
    <tr>
      <th >building or structure  </th>
      <td >
     <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffMaj != 'Y'}">
           <input type="checkbox" name="varAppHistAffMaj"  class="switch"  value="Y"/> 
      </c:if>
       <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffMaj == 'Y'}">
              <input type="checkbox" name="varAppHistAffMaj"  class="switch"  value="Y" checked/>
       </c:if>
      </td>
    </tr>
    <tr>
      <th >construction elements  </th>
      <td >
     <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffExc != 'Y'}">
           <input type="checkbox" name="varAppHistAffExc"  class="switch"  value="Y"/> 
      </c:if>
       <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffExc == 'Y'}">
              <input type="checkbox" name="varAppHistAffExc"  class="switch"  value="Y" checked/>
       </c:if>
      </td>
    </tr>
    <tr>
      <th >historically  </th>
      <td >
     <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffHist != 'Y'}">
           <input type="checkbox" name="varAppHistAffHist"  class="switch"  value="Y"/> 
      </c:if>
       <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffHist == 'Y'}">
              <input type="checkbox" name="varAppHistAffHist"  class="switch"  value="Y" checked/>
       </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"   class="required"     >  facts demonstrating that the above selected statement is true:(max 2000 characters), if more upload the document</th>
          <td>
           <a href="/dfbs/variance/start.do?method=goToUpload&idNumber=<c:out value="${VARIANCE_APPLICATION_SELECTED.varAppTelephone}"/>&idType=Variance">
                    [upload file(s)]</a> </br>
            <html:textarea property="varAppHistAffPhyComment" rows="10" cols="70"/>
                 <c:if test="${VIOLATION_ERROR.varAppPhAffirmComment == 'ERROR'}">
              <br/><span class="error">* please enter description  </span> 
            </c:if>
            </td>
      </tr> 
 </tbody>
</table>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
      <p>
          <html:submit value="Update" styleClass="button"/>
        </p>
  </c:if>
</html:form>
<html:form action="/start" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="updateApplication"/> 
    <html:hidden property="varId"/> 
    <html:hidden property="varAppProjId"/> 
    <html:hidden property="varProjId"/> 
    <html:hidden property="varAppNumCode"/>
    <html:hidden property="varProjOwnerId"/>
    <html:hidden property="varAppRecDate"/>
 <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
 <tr>
      <th scope="row"   class="required"     >  Reviewer Notes</th>
          <td>
            <html:textarea property="varAppHistAffPhyComment" rows="10" cols="70"/>
            </td>
      </tr>
      
     <tr>
      <th scope="row"   class="required"     >  </th>
          <td>
      <p>
          <html:submit value="Generate rejection email" styleClass="button"/>
        </p>
        </td>
        </tr>
    </tbody>
</table>
</html:form>
 </div> 
 <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>

