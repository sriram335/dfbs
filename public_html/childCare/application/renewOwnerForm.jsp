<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="Fire Display Permits" />
<c:set var="title" scope="request" value="Owner Information" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Verify Owner Information</h2> 
 <a   href="/dfbs/childCare/childcare.do?method=renewPermit&permitKey=<c:out value="${PERMIT_SELECTED.permitNumber}"/>&ownerKey=<c:out value="${PERMIT_SELECTED.ownerId}" />">
             [cancel ]</a>   
      <html:link page="/start.do">[back to start]</html:link>
<div id="sideContentFluid">

</div>
  <div class="styledBox">
 <html:form action="/application" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="renewOwnerVerified"/> 
      <html:hidden property="ownerId"/> 
      
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
   
        <tr>
          <th scope="row"      >  owner last name:</th>
          <td>
          <c:out value="${OWNER_SELECTED.ownerLastName}"/></br>
           <html:hidden property="ownerLastName"/>
            </td>
          </th>
        </tr>
         <tr>
         <th scope="row"     >  owner first name:</th>
         <td>
          <c:out value="${OWNER_SELECTED.ownerFirstName}"/></br>
           <html:hidden property="ownerFirstName"/>
            </td>
          </th>
        </tr>
         <tr>
          <th  > owner middle intial </th>
          <td>
            <c:out value="${OWNER_SELECTED.ownerMI}"/></br>
           <html:hidden property="ownerMI"/>  
           </td>
        </tr>
         <tr>
          <th scope="row"     >  doing business as:</th>
          <td>
            <c:out value="${OWNER_SELECTED.ownerDBA}"/></br>
           <html:hidden property="ownerDBA"/>
          </td> 
          </th>
        </tr>
     
         <tr>
          <th scope="row"     >  address1:</th>
          <td>
            <c:out value="${OWNER_SELECTED.ownerAddress1}"/></br>
           <html:hidden property="ownerAddress1"/>
          </td> 
          </th>
        </tr>
         <tr>
          <th scope="row"     >  address2:</th>
          <td>
          <c:out value="${OWNER_SELECTED.ownerAddress2}"/></br>
           <html:hidden property="ownerAddress2"/>
          </td> 
          </th>
        </tr>
         <tr>
          <th scope="row"    >  city:</th>
          <td>
          <c:out value="${OWNER_SELECTED.ownerCity}"/></br>
           <html:hidden property="ownerCity"/>
          </td> 
          </th>
        </tr>
         <tr>
      <th scope="row"     >   state:</th>
      <td>
          <html:select property="ownerStateId" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','STATE_ID')">
          <html:option value="">Please Select</html:option>
          <html:options collection="OWNER_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       
       </td>
       </tr>
        <tr>
          <th scope="row"     >  zip:</th>
          <td>
           <c:out value="${OWNER_SELECTED.ownerZip}"/></br>
           <html:hidden property="ownerZip"/>
          </td> 
          </th>
        </tr>
       
           <tr>
          <th scope="row" class="required">* phone:</th>
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
          <th scope="row"  class="required"   >* email:</th>
          <td>
            <html:text property="ownerEmail" size="50" maxlength="80"/>
              <c:if test="${OWNER_ERROR.ownerEmail == 'ERROR'}">
              <br/><span class="error">* please enter email  </span> 
            </c:if>
          </td>
        
        </tr>
        <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="Verified" styleClass="button" />
     
     </td>
     </tr> 
  </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>

