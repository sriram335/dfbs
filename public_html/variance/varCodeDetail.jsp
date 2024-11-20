<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<html:xhtml/><c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Variance Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>

<div id="mainBox">
<div id="rightContentFluid">
   <p class="messageBox"> <b><u>Status</u></b></br>
    <c:if test="${NEW_VARIANCE_STATUS =='COUNTY'}">
    Application Process-In Progress</br> 
    </c:if>
    <c:if test="${NEW_VARIANCE_STATUS =='AFTER_OWNER'}">
    Application Process-In Progress</br> 
    Owner Information-Completed.</br> 
    </c:if>
     <c:if test="${NEW_VARIANCE_STATUS =='AFTER_DESIGNER'}">
    Application Process-In Progress</br> 
    Owner Information-Completed.</br> 
    Designer Information-Completed.</br> 
    </c:if> </p>
</div>
    
  <div id="leftContentFluid">
  <h1><b><u>Variance Application Process</u></b></h1></br> </br> 
  <h2>Add / update Variance Code Information</h2> 
 <%--  <c:if test="${NEW_VARIANCE_CODE_COUNT != 'Y'}">
                <p class="error">
               <b> Please Add Variance Code, we need atleast one code to complete the application. Use Add New Code button at the top of the screen.</b>
               </p>
  </c:if>
<html:link page="/code.do">[back to start]</html:link>
  <div class="styledBox">
 <html:form action="/code" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="newCode"/> 
      <p class="messageBox">
      Please note that number of codes will determine the amount of fee.
       <html:submit value="Add New Code" styleClass="button" /> </p>
</html:form> --%>
      
  <html:form action="/start" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="compCodeDetail"/> 
     
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <c:forEach var="mapItem" items="${VARIANCE_APPLICATION_SELECTED.codeMap}" varStatus="j">
                      <c:set scope="request" var="code" value="${mapItem.value}"/>
              <tr >
                <td>
               Code : <c:out value="${code.varCode}"/></br>
               Edition: <c:out value="${code.varEdition}"/> </br>
           <%--    <a   href="/dfbs/variance/code.do?method=editCode&codeKey=<c:out value="${code.codeKey}"/>">
              <span style="font-size:x-small">[edit code]</span> </a>  --%>
               <a   href="/dfbs/variance/code.do?method=removeCode&codeKey=<c:out value="${code.codeKey}"/>">
              <span style="font-size:x-small">[remove code]</span> </a></br>  
                </td>
                </tr>
    </c:forEach>
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
           If you have a document use upload file option below.</br>
            <html:textarea property="varAppPhAffirmComment" rows="10" cols="70"/>
                 <c:if test="${VIOLATION_ERROR.varAppPhAffirmComment == 'ERROR'}">
              <br/><span class="error">* please enter description  </span> 
            </c:if>
            </td>
      </tr> 
    <tr>
      <th >  </th>
      <td >
     <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffPhy != 'Y'}">
           <input type="checkbox" name="varAppHistAffPhy"  class="switch"  value="Y"/> 
           Imposition of the rule would result in an undue hardship (unusual difficulty) because of physical limitations of the construction site or its utility services.
      </c:if>
       <c:if test="${VARIANCE_APPLICATION_SELECTED. varAppHistAffPhy == 'Y'}">
              <input type="checkbox" name="varAppHistAffPhy"  class="switch"  value="Y" checked/>
              Imposition of the rule would result in an undue hardship (unusual difficulty) because of physical limitations of the construction site or its utility services.
       </c:if>
      </td>
    </tr>
    <tr>
      <th > </th>
      <td >
     <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffMaj != 'Y'}">
           <input type="checkbox" name="varAppHistAffMaj"  class="switch"  value="Y"/> 
           Imposition of the rule would result in an undue hardship (unusual difficulty) because of major operational problems in the use of the building or structure.
      </c:if>
       <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffMaj == 'Y'}">
              <input type="checkbox" name="varAppHistAffMaj"  class="switch"  value="Y" checked/>
              Imposition of the rule would result in an undue hardship (unusual difficulty) because of major operational problems in the use of the building or structure.
       </c:if>
      </td>
    </tr>
    <tr>
      <th > </th>
      <td >
     <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffExc != 'Y'}">
           <input type="checkbox" name="varAppHistAffExc"  class="switch"  value="Y"/> 
           Imposition of the rule would result in an undue hardship (unusual difficulty) because of excessive costs of additional or altered construction elements.
      </c:if>
       <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffExc == 'Y'}">
              <input type="checkbox" name="varAppHistAffExc"  class="switch"  value="Y" checked/>
              Imposition of the rule would result in an undue hardship (unusual difficulty) because of excessive costs of additional or altered construction elements.
       </c:if>
      </td>
    </tr>
    <tr>
      <th >  </th>
      <td >
     <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffHist != 'Y'}">
           <input type="checkbox" name="varAppHistAffHist"  class="switch"  value="Y"/>
     Imposition of the rule would prevent the preservation of an architecturally or a historically significant part of the building or structure. </c:if>
       <c:if test="${VARIANCE_APPLICATION_SELECTED.varAppHistAffHist == 'Y'}">
              <input type="checkbox" name="varAppHistAffHist"  class="switch"  value="Y" checked/>
              Imposition of the rule would prevent the preservation of an architecturally or a historically significant part of the building or structure.
       </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"   class="required"     >  facts demonstrating that the above selected statement is true:(max 2000 characters), if more upload the document</th>
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
                <c:if test="${NEW_VARIANCE_CODE_COUNT != 'Y'}">
                <p class="error">
               <b> Please Add Variance Code, we need atleast one code to complete the application. Use Add New Code button at the top of the screen.</b>
               </p>
                </c:if>
       
              <html:submit value="Add New Code" styleClass="button" property="completeApplication"/>
              <html:submit value="Upload File(s)" styleClass="button" property="completeApplication"/>
              <html:submit value="Complete Application" styleClass="button" property="completeApplication"/>
     
     </td>
     </tr> 
  </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 </div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
