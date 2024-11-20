<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Edit Company Information</h2> 
<div id="sideContentFluid">
<a   href="/dfbs/cigarette/applicationsView.do?method=addSupApp">
              [Cancel]</a>
</div>
  <div class="styledBox">
 <html:form action="/cigarette" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveChangeAgent"/> 
      <html:hidden property="agentId"/> 
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * agent name:</th>
      <td>
       <html:text property="agentName" size="50" maxlength="50"/>
       <c:if test="${CIGARETTE_AGENT_ERROR.agentName == 'ERROR'}">
          <br/>
          <span class="error">* please enter agent name</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"    class="required" > * agent address1:</th>
      <td>
       <html:text property="agentAddress1" size="80" maxlength="80"/>
       <c:if test="${CIGARETTE_AGENT_ERROR.agentAddress1 == 'ERROR'}">
          <br/>
          <span class="error">* please enter agent address1</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"     > agent address2:</th>
      <td>
       <html:text property="agentAddress2" size="30" maxlength="30"/>
       </td>
    </tr>
    <tr>
      <th scope="row"    class="required" > * agent city:</th>
      <td>
       <html:text property="agentCity" size="30" maxlength="30"/>
       <c:if test="${CIGARETTE_AGENT_ERROR.agentCity == 'ERROR'}">
          <br/>
          <span class="error">* please enter agent city</span> 
        </c:if>
       </td>
    </tr>
  
    <tr>
      <th scope="row"    class="required" > * agent state:</th>
      <td>
         <html:text property="agentState" size="30" maxlength="30"/>
       </td>
    </tr>
 
  
    <tr>
      <th scope="row"    class="required" > * agent zip:</th>
      <td>
       <html:text property="agentZip" size="30" maxlength="30"/>
       <c:if test="${CIGARETTE_AGENT_ERROR.agentZip == 'ERROR'}">
          <br/>
          <span class="error">* please enter agent zip</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"     > agent zip2:</th>
      <td>
       <html:text property="agentZip2" size="4" maxlength="4"/>
       </td>
    </tr>
     
     <tr>
      <th scope="row"     class="required"> * agent  phone:</th>
      <td>
       <html:text property="agentPhone" size="10" maxlength="10"/>
       <c:if test="${CIGARETTE_AGENT_ERROR.agentPhone == 'ERROR'}">
          <br/>
          <span class="error">* phone format error</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"    > agent contact fax:</th>
      <td>
       <html:text property="agentFax" size="10" maxlength="10"/>
       <c:if test="${CIGARETTE_AGENT_ERROR.agentFax == 'ERROR'}">
          <br/>
          <span class="error">* fax format error</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"    > agent contact email:</th>
      <td>
       <html:text property="agentEmail" size="80" maxlength="80"/>
       <c:if test="${CIGARETTE_AGENT_ERROR.agentEmail == 'ERROR'}">
          <br/>
          <span class="error">* Email format error</span> 
        </c:if>
       </td>
    </tr>
    
     
    
        <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCigarette == 'SUPERVISOR'}">
       <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="Save" styleClass="button" />
     
     </td>
     </tr>
     </c:if>
   </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
  </div> 
<jsp:include page="/app/common/hsPageFooter.jsp"/>