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

    
  <div id="sideContentFluid">
    <jsp:include page="status.jsp"/>
  </div>
 
    
  <div id="leftContentFluid">
  <h1><b><u>Variance Application Process</u></b></h1></br> </br> 
<h3>Design Professional of Record</h3> 
<html:link page="/start.do?method=fromOwnerApplication">[back]</html:link>
<div id="sideContentFluid">

</div>
  <div class="styledBox">
 <html:form action="/designer" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveDesigner"/> 
      <html:hidden property="desId"/> 
      <html:hidden property="desProjId"/>  
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
   
        <tr>
          <th scope="row"  class="required"    > * design professional last name:</th>
          <td>
           <html:text property="desLastName" size="60" maxlength="60"/>
                 <c:if test="${DES_ERROR.desLastName == 'ERROR'}">
              <br/><span class="error">* please enter last name  </span> 
            </c:if>
            </td>
          </th>
        </tr>
         <tr>
         <th scope="row"  class="required"    > *design professional first name:</th>
         <td>
           <html:text property="desFirstName" size="30" maxlength="30"/>
            <c:if test="${DES_ERROR.desFirstName == 'ERROR'}">
              <br/><span class="error">* please enter first name  </span> 
            </c:if>
            </td>
          </th>
        </tr>
         <tr>
          <th  > design professional middle intial </th>
          <td>
           <html:text property="desMI" size="1" maxlength="1"/>    
           </td>
        </tr>
         <tr>
          <th scope="row"    class="required"  > design professional firm name(if applicable):</th>
          <td>
           <html:text property="desDBA" size="50" maxlength="80"/>
            <c:if test="${DES_ERROR.desDBA == 'ERROR'}">
              <br/><span class="error">* please enter DBA  </span> 
            </c:if>
          </td> 
          </th>
        </tr>
         <tr>
         <th scope="row"  class="required"    > * design professional address1:</th>
         <td>
           <html:text property="desAddress1" size="30" maxlength="30"/>
            <c:if test="${DES_ERROR.desAddress1 == 'ERROR'}">
              <br/><span class="error">* please enter address1  </span> 
            </c:if>
            </td>
          </th>
        </tr>
        <tr>
          <th scope="row"     > design professional address2:</th>
          <td>
           <html:text property="desAddress2" size="30" maxlength="30"/>
          </td> 
          </th>
        </tr>
         <tr>
         <th scope="row"  class="required"    > * design professional city:</th>
         <td>
           <html:text property="desCity" size="30" maxlength="30"/>
            <c:if test="${DES_ERROR.desCity == 'ERROR'}">
              <br/><span class="error">* please enter city  </span> 
            </c:if>
            </td>
          </th>
        </tr>
         <tr>
      <th scope="row"    class="required" > * design professional state:</th>
      <td>
          <html:select property="desState" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','STATE_INITIAL')">
          <html:option value = "0" >Please Select</html:option>
          <html:options collection="DESIGNER_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${DES_ERROR.desState == 'ERROR' }">
          <br/>
          <span class="error">* please specify state</span> 
        </c:if> 
       
       </td>
    </tr>
        <tr>
         <th scope="row"  class="required"    > * design professional zip:</th>
         <td>
           <html:text property="desZip" size="5" maxlength="5"/>
            <c:if test="${DES_ERROR.desZip == 'ERROR'}">
              <br/><span class="error">* please enter zip  </span> 
            </c:if>
            
            </td>
          </th>
        </tr>
        <tr>
          <th scope="row" class="required"> * design professional phone:</th>
          <td>
            <html:text property="desPhone" size="10" maxlength="10"/>
            <c:if test="${DES_ERROR.desPhone == 'ERROR'}">
              <br/><span class="error">* please enter phone  </span> 
            </c:if>
           </td>
        </tr>
       
        <tr>
         <th scope="row"  class="required"   >* design professional email:</th>
          <td>
            <html:text property="desEmail" size="50" maxlength="80"/>
          
          <c:if test="${DES_ERROR.desEmail == 'ERROR'}">
              <br/><span class="error">* please enter email in correct format  </span> 
            </c:if>
            </td>
        </tr>
        <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="Next" styleClass="button" />
     
     </td>
     </tr> 
  </tbody>
   </table>
   </html:form>
   </div>
   </div>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>
