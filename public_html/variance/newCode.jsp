<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Variance Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>

<div id="mainBox">
<div id="sideContentFluid" align="left">
<jsp:include page="status.jsp"/>
<c:forEach var="mapItem" items="${VARIANCE_APPLICATION_SELECTED.codeMap}" varStatus="j">
                      <c:set scope="request" var="code" value="${mapItem.value}"/>
        <tr>
      <th > </th>
      <td >
          <b><u>  Variance Codes Added</u></b></br>
      </td>
          </tr>
              <tr >
                <td>
               Code Name: <c:out value="${code.varCode}"/></br>
               Specific Section: <c:out value="${code.varCodeName}"/> </br>
         <%--      <a   href="/dfbs/variance/code.do?method=editCode&codeKey=<c:out value="${code.codeKey}"/>">
              <span style="font-size:x-small">[edit code]</span> </a>   --%>
               <a   href="/dfbs/variance/code.do?method=removeCode&codeKey=<c:out value="${code.codeKey}"/>">
              <span style="font-size:x-small">[remove variance code]</span> </a></br>  
                </td>
                </tr>
    </c:forEach>
    
    <b><u>Pick the Code</u></b></br> 
    <c:forEach var="code" items="${VARIANCE_CODE_STD_LIST}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />" align="left">
        <td>
        <c:out value="${code.varCode}"/>&nbsp;
          <a   href="/dfbs/variance/code.do?method=addStdCode&varStdCode=<c:out value="${code.varCode}"/>">
             [select ]</a> </br>
          
        </td>
      </tr>
       </c:forEach>
</div>
<div id="leftContentFluid">

 <h1><b><u>Variance Application Process</u></b></h1></br> </br> 

 

<table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:50%;" >
     <tbody class="rowHeader">
     <html:form action="/code" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="compCodeDetail"/>
     <html:hidden property="varId"/> 
     <html:hidden property="varCodeId"/> 
      <tr>
    <th style="color:rgb(198,198,198)" >________________</th>
    </tr>
  <tr>
      <th > </th>
      <td >
          <b><u> Description of the requested Variance</u></b></br>
       <span class="error">   Steps:1.Pick the variance code.</br>
          2.Then fill the rest of the text boxes in the page.</span>
      </td>
    </tr>
     <tr>
      <th > <b>Name of Code or standard and edition involved</b> </th>
      <td >
       <html:textarea property="varCode" rows="5" cols="40"
                      readonly="true"/> ==> pick code from list
          
        </td>
    </tr>
    <tr>
      <th > <b>Specific code section</b> </th>
      <td >
            <html:text property="varCodeName" size="30" maxlength="30"/>
        </td>
    </tr>
    
     <tr>
      <th > <b>Nature of non-compliance</b> (max 2000 characters)</th>
      <td >
       <html:textarea property="varNatureNonComp" rows="10" cols="50"/>
        </td>
    </tr>
    <!
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelVar != null || sessionScope.DFBS_SECURITY.groupLevelAll != null}">  
 <tr>
      <th > <b>Variance Commisson Status</b> </th>
      <td >
      <html:select property="varCommStatus"  onchange="setSelectValue('SELECT_DESCRIPTION','ABBREVIATION')">
          <html:option value = "0" >Please Select</html:option>
          <html:options collection="VAR_COMM_ACTION_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        </td>
    </tr>
    <tr>
      <th > <b>Variance Commisson Date</b> </th>
      <td >
           <html:text property="varCommDate" size="10" maxlength="10"/>mm/dd/yyyy
        </td>
    </tr>
    <tr>
      <th > <b>Variance Print Date</b> </th>
      <td >
           <html:text property="varCommPrintDate" size="10" maxlength="10"/>mm/dd/yyyy
        </td>
    </tr>
 </c:if>
 -->
  <tr>
      <th > </th>
      <td >
          <b><u>DEMONSTRATION THAT PUBLIC HEALTH, SAFETY, AND WELFARE ARE PROTECTED:</u></b>
      </td>
    </tr>
 <tr>
       <th scope="row" style="width:30%" class="required">Select one of the following statements</th>
      <td>
        <html:radio property="varAppPhAffirm" value="1"  />Non-compliance with the rule will not be adverse to the public health, safety or welfare; or
        <br/>
        <html:radio property="varAppPhAffirm" value="2"  />Applicant will undertake alternative actions in lieu of compliance with the rule to ensure that granting of the variance 
        will not be adverse to public health, safety, or welfare.  Explain why alternative actions would be adequate (be specific).
          <br/> 
      </td>
    </tr>
     <tr>
      <th scope="row"   class="required"     >  Facts demonstrating that the above selected statement is true:(max 2000 characters), if more upload the document</th>
          <td>
           If you have a document use upload file option below.</br>
            <html:textarea property="varAppPhAffirmComment" rows="10" cols="50"/>
                 <c:if test="${VIOLATION_ERROR.varAppPhAffirmComment == 'ERROR'}">
              <br/><span class="error">* please enter description  </span> 
            </c:if>
            </td>
      </tr> 
      <tr>
      <th > </th>
      <td >
          <b><u>DEMONSTRATION OF UNDUE HARDSHIP OR HISTORICALLY SIGNIFICANT STRUCTURE:</u></b>
      </td>
    </tr>
    <tr>
      <th > Select one or more of the following statements </th>
      <td >
     <c:if test="${VARIANCE_CODE_SELECTED.varAppHistAffPhy != 'Y'}">
           <input type="checkbox" name="varAppHistAffPhy"  class="switch"  value="Y"/> 
           Imposition of the rule would result in an undue hardship (unusual difficulty) because of physical limitations of the construction site or its utility services.
      </c:if>
       <c:if test="${VARIANCE_CODE_SELECTED. varAppHistAffPhy == 'Y'}">
              <input type="checkbox" name="varAppHistAffPhy"  class="switch"  value="Y" checked/>
              Imposition of the rule would result in an undue hardship (unusual difficulty) because of physical limitations of the construction site or its utility services.
       </c:if>
      </td>
    </tr>
    <tr>
      <th > </th>
      <td >
     <c:if test="${VARIANCE_CODE_SELECTED.varAppHistAffMaj != 'Y'}">
           <input type="checkbox" name="varAppHistAffMaj"  class="switch"  value="Y"/> 
           Imposition of the rule would result in an undue hardship (unusual difficulty) because of major operational problems in the use of the building or structure.
      </c:if>
       <c:if test="${VARIANCE_CODE_SELECTED.varAppHistAffMaj == 'Y'}">
              <input type="checkbox" name="varAppHistAffMaj"  class="switch"  value="Y" checked/>
              Imposition of the rule would result in an undue hardship (unusual difficulty) because of major operational problems in the use of the building or structure.
       </c:if>
      </td>
    </tr>
    <tr>
      <th > </th>
      <td >
     <c:if test="${VARIANCE_CODE_SELECTED.varAppHistAffExc != 'Y'}">
           <input type="checkbox" name="varAppHistAffExc"  class="switch"  value="Y"/> 
           Imposition of the rule would result in an undue hardship (unusual difficulty) because of excessive costs of additional or altered construction elements.
      </c:if>
       <c:if test="${VARIANCE_CODE_SELECTED.varAppHistAffExc == 'Y'}">
              <input type="checkbox" name="varAppHistAffExc"  class="switch"  value="Y" checked/>
              Imposition of the rule would result in an undue hardship (unusual difficulty) because of excessive costs of additional or altered construction elements.
       </c:if>
      </td>
    </tr>
    <tr>
      <th >  </th>
      <td >
     <c:if test="${VARIANCE_CODE_SELECTED.varAppHistAffHist != 'Y'}">
           <input type="checkbox" name="varAppHistAffHist"  class="switch"  value="Y"/>
     Imposition of the rule would prevent the preservation of an architecturally or a historically significant part of the building or structure. </c:if>
       <c:if test="${VARIANCE_CODE_SELECTED.varAppHistAffHist == 'Y'}">
              <input type="checkbox" name="varAppHistAffHist"  class="switch"  value="Y" checked/>
              Imposition of the rule would prevent the preservation of an architecturally or a historically significant part of the building or structure.
       </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"   class="required"     >  Facts demonstrating that the above selected statement is true:(max 2000 characters), if more upload the document</th>
          <td>
          If you have a document use upload file option below.</br>
            <html:textarea property="varAppHistAffPhyComment" rows="10" cols="70"/>
                 <c:if test="${VIOLATION_ERROR.varAppPhAffirmComment == 'ERROR'}">
              <br/><span class="error">* please enter description  </span> 
            </c:if>
            </td>
      </tr> 
 <tr>
     <th scope="row"     > </th>
    <td>
         <html:submit value="Save Variance" styleClass="button" property="completeApplication"/>
    <%--     <html:submit value="Add Another Variance" styleClass="button" property="completeApplication"/>
              <html:submit value="Upload File(s)" styleClass="button" property="completeApplication"/>
              <html:submit value="Complete Application" styleClass="button" property="completeApplication"/> --%>
     </td>
     </tr> 
     </html:form>
   
 </tbody>
</table>



</div>

<div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 