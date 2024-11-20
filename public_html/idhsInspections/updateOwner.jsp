<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<html:xhtml/><c:set var="module" scope="request" value="Fire Display Permits" />
<c:set var="title" scope="request" value="Owner Information" />
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Add Owner Information</h2> 
 <a   href="/dfbs/idhsInspections/idhsInspection.do?method=updateIdhsInspection&inspectionId=<c:out value="${INSPECTION_SELECTED.inspId}"/>">
             [back to inspection]</a></br>
            <u><b> Create New Owner </b></u>
<div id="sideContentFluid">
<html:form action="/updateOwner" method="post" >
<input type="hidden" name="method" id="METHOD_KEY" value="lookUpOwners"/> 
<u><b> LOOK UP OWNERS</b></u>
<html:text property="ownerLastName" size="30" maxlength="30"/> 
  Enter owner name like for lookup ex: for Eli Lilly enter lilly in the box and click look up owner button</br>
          <br/>
            <input type="submit" value="Lookup Owners" class="button"/>  </br>       
</html:form>
 <c:forEach var="owner" items="${INSPECTIONS_OWNER_LIST.list}" varStatus="i" >
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <c:out value="${owner.ownerLastName}" />
   </h3>
  <p class="listingInfo">
    <c:out value="${owner.ownerAddress1}" /><br />
    <c:if test="${owner.ownerAddress2 != null && owner.ownerAddress2 != ''}">
    <c:out value="${owner.ownerAddress2}" /><br />
    </c:if>
    <c:out value="${owner.ownerCity}" />, <c:out value="${owner.ownerState}" /> <c:out value="${owner.ownerZip}" /><br />
   <a   href="/dfbs/idhsInspections/updateOwner.do?method=attachOwner&ownerId=<c:out value="${owner.ownerId}"/>"> Attach to this owner </a>
  </p>
  </div>
   </c:forEach>
</div>
  <div class="styledBox" align="left">
 <html:form action="/updateOwner" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="newSaveOwner"/> 
      <html:hidden property="ownerId"/> 
       
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
   
        <tr>
          <th scope="row"  class="required"    > * owner last name:</th>
          <td>
           <html:text property="ownerLastName" size="50" maxlength="80"/>
                 <c:if test="${OWNER_ERROR.ownerLastName == 'ERROR'}">
              <br/><span class="error">* please enter last name  </span> 
            </c:if>
            </td>
          </th>
        </tr>
         <tr>
         <th scope="row"  class="required"    > * owner first name:</th>
         <td>
           <html:text property="ownerFirstName" size="30" maxlength="30"/>
            <c:if test="${OWNER_ERROR.ownerFirstName == 'ERROR'}">
              <br/><span class="error">* please enter first name  </span> 
            </c:if>
            </td>
          </th>
        </tr>
         <tr>
          <th  > owner middle intial </th>
          <td>
           <html:text property="ownerMI" size="1" maxlength="1"/>    
           </td>
        </tr>
         <tr>
          <th scope="row"     >  doing business as:</th>
          <td>
           <html:text property="ownerDBA" size="50" maxlength="80"/>
          </td> 
          </th>
        </tr>
         <tr>
         <th scope="row"  class="required"    > * owner address1:</th>
         <td>
           <html:text property="ownerAddress1" size="30" maxlength="30"/>
            <c:if test="${OWNER_ERROR.ownerAddress1 == 'ERROR'}">
              <br/><span class="error">* please enter address1  </span> 
            </c:if>
            </td>
          </th>
        </tr>
        <tr>
          <th scope="row"     >  address2:</th>
          <td>
           <html:text property="ownerAddress2" size="30" maxlength="30"/>
          </td> 
          </th>
        </tr>
         <tr>
         <th scope="row"  class="required"    > * owner city:</th>
         <td>
           <html:text property="ownerCity" size="30" maxlength="30"/>
            <c:if test="${OWNER_ERROR.ownerCity == 'ERROR'}">
              <br/><span class="error">* please enter city  </span> 
            </c:if>
            </td>
          </th>
        </tr>
         <tr>
      <th scope="row"    class="required" > *  state:</th>
      <td>
          <html:select property="ownerStateId" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','STATE_ID')">
          <html:option value = "0" >Please Select</html:option>
          <html:options collection="OWNER_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${OWNER_ERROR.ownerStateId == '0' }">
          <br/>
          <span class="error">* please specify state</span> 
        </c:if> 
       
       </td>
    </tr>
        <tr>
         <th scope="row"  class="required"    > * owner zip:</th>
         <td>
           <html:text property="ownerZip" size="5" maxlength="5"/>
            <c:if test="${OWNER_ERROR.ownerZip == 'ERROR'}">
              <br/><span class="error">* please enter zip  </span> 
            </c:if>
            
            </td>
          </th>
        </tr>
        <tr>
          <th scope="row" >  phone:</th>
          <td>
            <html:text property="ownerPhone" size="10" maxlength="10"/>
            <c:if test="${OWNER_ERROR.ownerPhone == 'ERROR'}">
              <br/><span class="error">* please enter phone  </span> 
            </c:if>
           </td>
        </tr>
        <tr>
          <th scope="row">fax:</th>
          <td>
           <html:text property="ownerFax" size="10" maxlength="10"/>
          </td>
        </tr>
        <tr>
         <th scope="row"     > email:</th>
          <td>
            <html:text property="ownerEmail" size="50" maxlength="100"/>
          
          <c:if test="${OWNER_ERROR.ownerEmail == 'ERROR'}">
              <br/><span class="error">* please enter email in correct format  </span> 
            </c:if>
            </td>
        </tr>
        <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="Create New Owner" styleClass="button" />
     
     </td>
     </tr> 
  </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>
