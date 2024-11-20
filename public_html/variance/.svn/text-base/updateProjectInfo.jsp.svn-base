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

    
  <div id="leftContentFluid">
 <h1><b><u>Variance Project Information</u></b></h1></br> </br> 

<html:form action="/start" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="saveProjectInfo"/> 
 <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
  
     <tr>
      <th > </th>
      <td >
          <b><u> Project Identification</u></b>
      </td>
    </tr>
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelVar != null }">  
    <tr>
      <th scope="row"    class="required"> * Variance Number  </th>
      <td >
           <html:text property="varAppVarNumber" size="12" maxlength="20"/>
       </td>
    </tr>
    </c:if>
     <tr>
      <th scope="row"    class="required" > * Project County:</th>
      <td >   <html:select property="varProjCounty" >
                <html:option value="">-----</html:option>
                <html:options  collection="VAR_COUNTY_OPTIONS" property="value" labelProperty="label" />
                </html:select>  
            <c:if test="${VARIANCE_APPLICATION_ERROR.varProjCounty == 'ERROR'}">
              <br/><span class="error">* please enter county name  </span> 
            </c:if>
</td>
</tr>
     <tr>
      <th scope="row"    class="required"> * Project Name  </th>
      <td >
           <html:text property="varProjName" size="50" maxlength="100"/>
            <c:if test="${VARIANCE_APPLICATION_ERROR.varProjCounty == 'ERROR'}">
              <br/><span class="error">* please enter project name  </span> 
            </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"    class="required"> * Project Address1  </th>
      <td >
           <html:text property="varProjAddress1" size="50" maxlength="100"/>
            <c:if test="${VARIANCE_APPLICATION_ERROR.varProjAddress1 == 'ERROR'}">
              <br/><span class="error">* please enter address1  </span> 
            </c:if>
      </td>
    </tr>
    <tr>
      <th > Project Address2  </th>
      <td >
           <html:text property="varProjAddress2" size="50" maxlength="100"/>
      </td>
    </tr>
    <tr>
      <th scope="row"    class="required"> * Project City  </th>
      <td >
           <html:text property="varProjCity" size="30" maxlength="30"/>
            <c:if test="${VARIANCE_APPLICATION_ERROR.varProjCity == 'ERROR'}">
              <br/><span class="error">* please enter city  </span> 
            </c:if>
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
      <td ><%--
            <html:select property="varProjType" >
                <html:option value="">-----</html:option>
                <html:options  collection="VAR_PROJ_OPTIONS" property="value" labelProperty="label" />
                </html:select>  --%>
             <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppPhAffirm != 'Y'}">
             <input type="checkbox" name="varAppPhAffirm"  class="switch"  value="Y"/> New&nbsp;&nbsp;
            </c:if>
            <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppPhAffirm == 'Y'}">
              <input type="checkbox" name="varAppPhAffirm"  class="switch"  value="Y" checked/> New&nbsp;&nbsp;
            </c:if>
             <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppPhAffirmComment != 'Y'}">
             <input type="checkbox" name="varAppPhAffirmComment"  class="switch"  value="Y"/> Addition&nbsp;&nbsp;
            </c:if>
            <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppPhAffirmComment == 'Y'}">
              <input type="checkbox" name="varAppPhAffirmComment"  class="switch"  value="Y" checked/> Addition&nbsp;&nbsp;
            </c:if>
            <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffPhy != 'Y'}">
             <input type="checkbox" name="varAppHistAffPhy"  class="switch"  value="Y"/> Alteration&nbsp;&nbsp;
            </c:if>
            <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffPhy == 'Y'}">
              <input type="checkbox" name="varAppHistAffPhy"  class="switch"  value="Y" checked/> Alteration&nbsp;&nbsp;
            </c:if></br> 
             <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffExc != 'Y'}">
             <input type="checkbox" name="varAppHistAffExc"  class="switch"  value="Y"/> Change of Occupancy&nbsp;&nbsp;
            </c:if>
            <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffExc == 'Y'}">
              <input type="checkbox" name="varAppHistAffExc"  class="switch"  value="Y" checked/> Change of Occupancy&nbsp;&nbsp;
            </c:if>
            <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffMaj != 'Y'}">
             <input type="checkbox" name="varAppHistAffMaj"  class="switch"  value="Y"/> Existing&nbsp;&nbsp;
            </c:if>
            <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffMaj == 'Y'}">
              <input type="checkbox" name="varAppHistAffMaj"  class="switch"  value="Y" checked/> Existing&nbsp;&nbsp;
            </c:if>
      </td>
    </tr>
    <tr>
       <th scope="row" style="width:30%" class="required">Project Status</th>
      <td>
        <html:radio property="varProjStatus" value="F"  />Filed
        <br/>
        <html:radio property="varProjStatus" value="U"  />Unfiled<br/> 
         <c:if test="${VARIANCE_APPLICATION_ERROR.varProjStatus == 'ERROR'}">
              <br/><span class="error">* please enter status  </span> 
            </c:if>
      </td>
    </tr>
     <tr>
      <th > Is this project State Owned?  </th>
      <td >
            <html:select property="varAppBDName" >
                <html:option value="">-----</html:option>
                <html:options  collection="YES_NO_OPTIONS" property="value" labelProperty="label" />
                </html:select>  
                <c:if test="${VARIANCE_APPLICATION_ERROR.varAppBDName == 'ERROR'}">
              <br/><span class="error">* please state owned  </span> 
            </c:if>
      </td>
    </tr>
     <tr>
      <th > Is this project in City Limits?  </th>
      <td >
            <html:select property="varAppFDName" >
                <html:option value="">-----</html:option>
                <html:options  collection="YES_NO_OPTIONS" property="value" labelProperty="label" />
                </html:select>  
                 <c:if test="${VARIANCE_APPLICATION_ERROR.varAppFDName == 'ERROR'}">
              <br/><span class="error">* please city limits  </span> 
            </c:if>
      </td>
    </tr>
    <tr>
      <th > Is this variance application related to please select related to a regulated lifting device  </th>
      <td >
            <html:select property="varAppLboName" >
                <html:option value="">-----</html:option>
                <html:options  collection="YES_NO_OPTIONS" property="value" labelProperty="label" />
                </html:select>
                 <c:if test="${VARIANCE_APPLICATION_ERROR.varAppLboName == 'ERROR'}">
              <br/><span class="error">* please select related to elevator  </span> 
            </c:if>
      </td>
    </tr>
    
     <tr>
      <th > </th>
      <td >
          <b><u> Violation Information</u></b>
      </td>
    </tr>
    <tr>
      <th > Has IDHS Plan Review Section Issued a Correction Order  </th>
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
           <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelVar != null }">  
              <html:submit value="Update" styleClass="button" />
        </c:if>
        </p>
</html:form>

 </div> 
 <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>

