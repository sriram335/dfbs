<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - DFBS system"/>
<c:set var="title" scope="request" value="Indiana LEPC's"/>
<jsp:include page="/main/IdhsPageHeader.jsp"/></br>
 <a   href="/dfbs/otherUsers/otherUser.do?method=logOutLepc" >
              [log out LEPC]</a>  
  <a   href="/dfbs/lepc/lepc.do?method=viewLepcYear&lepcId=<c:out value="${sessionScope.LEPC_SELECTED_ID}"/>">
                 [back to LEPC]</a>
<div id="mainBox">
  <h2>Add / Update Chemical</h2>
 <div id="sideContentFluid">
    <c:forEach var="chem" items="${sessionScope.STANDARD_CHEMICAL_LIST}" varStatus="i">
     <b> <c:out value="${chem.chemCas}"/>:<c:out value="${chem.chemName}"/></b>  
      <a  href="/dfbs/lepc/chemical.do?method=addChemicalFromList&casNumber=<c:out value="${chem.chemCas}"/>">
                 addChemical</a></br>
    </c:forEach>
  </div>
<div id="leftContentFluid">
<table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader">  
<html:form action="/chemical" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="saveChemical"/>
   <html:hidden property="chemicalId"/> 
    <html:hidden property="exerciseId"/> 
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Is Chemical EHS:</th>
      <td>
       <html:select property="isChemEHS"  >
          <html:option value="">-----</html:option>
          <html:option value="Yes">Yes</html:option>
          <html:option value="No">No</html:option>
        </html:select>
      </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Is Chemical CERCLA:</th>
      <td>
       <html:select property="isChemCercla"  >
          <html:option value="">-----</html:option>
          <html:option value="Yes">Yes</html:option>
          <html:option value="No">No</html:option>
        </html:select>
      </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Chemical Name & CAS:</th>
      <td>
      <html:textarea property="chemName" rows="5" cols="70"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > RQ for Chemical in LBS:</th>
      <td>
      <html:text property="rqForChem" size="30" maxlength="30" readonly="true"/> (enter only number)
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >Amount of Chemical released in LBS:</th>
      <td>
      <html:text property="quantityReleased" size="30" maxlength="30"/> (enter only number)
       <c:if test="${CHEMICAL_QUANTITY_CHECK == 'Y'}"> 
       <p class="error">CHEMICAL QUANTITY MUST GREATER THAN OR EQUAL TO THE RQ</p>
       </c:if>
       </td>     
    </tr>
   
<tr>
<th> </th>
<td>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null}"> 
  <html:submit value="Save Chemical" styleClass="button" />
</c:if>
</td>
</tr>
</html:form>
</tbody>
</table> </br></br>

</div>
  <div class="clearer">&nbsp;</div>
 </div> 