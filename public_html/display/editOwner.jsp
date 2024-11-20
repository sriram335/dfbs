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
<h2>Edit Owner Information</h2> 
<a   href="/dfbs/display/start.do?method=backToStart">
              [back to start]</a>
<a   href="/dfbs/display/start.do?method=renewBy">
              [back to search]</a>

<div id="sideContentFluid">

</div>
  <div class="styledBox">
 <html:form action="/ownerListDisplay" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveEditOwner"/> 
      <html:hidden property="ownerId"/> 
       <html:hidden property="ownerAddress1"/> 
        <html:hidden property="ownerAddress2"/> 
         <html:hidden property="ownerCity"/> 
         <html:hidden property="ownerStateId"/> 
         <html:hidden property="ownerZip"/> 
          <html:hidden property="ownerKey"/>
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <%-- existing owner --%>
    <c:if test="${OWNER_SELECTED.ownerId != 0}">
    <tr>
          <th scope="row"     >  owner last name:</th>
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
          <th scope="row"    >  address1:</th>
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
      <th scope="row"    class="required" > *  state:</th>
      <td>
          <html:select property="ownerStateId" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','STATE_ID')" >
          <html:option value="">Please Select</html:option>
          <html:options collection="OWNER_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
      
       </td>
       </tr>
        <tr>
          <th scope="row"    >  zip:</th>
          <td>
           <c:out value="${OWNER_SELECTED.ownerZip}"/></br>
           <html:hidden property="ownerZip"/>
          </td> 
          </th>
        </tr>
    </c:if>
    <%-- new owner --%>
     <c:if test="${OWNER_SELECTED.ownerId == 0}">
        <tr>
          <th scope="row"  class="required"    > * owner last name:</th>
          <td>
           <html:text property="ownerLastName" size="60" maxlength="60"/>
           <html:hidden property="ownerLastName"/> 
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
           <html:text property="ownerDBA" size="80" maxlength="80"/>
          </td> 
          </th>
        </tr>
        <tr>
          <th scope="row"     >  address1:</th>
          <td>
           <html:text property="ownerAddress1" size="30" maxlength="30"/>
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
          <th scope="row"     >  city:</th>
          <td>
           <html:text property="ownerAddress1" size="30" maxlength="30"/>
          </td> 
          </th>
        </tr>
        <tr>
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
           <html:text property="ownerZip" size="5" maxlength="5"/>
          </td> 
          </th>
        </tr>
   </c:if>
  <tr>
   <th scope="row"     >  Note:</th>
   <td class="error">
   
 
  </td> 
    </tr>
    
    
 
        <tr>
          <th scope="row">phone:</th>
          <td>
            <html:text property="ownerPhone" size="10" maxlength="10"/>
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
          </td>
          <c:if test="${OWNER_ERROR.ownerEmail == 'ERROR'}">
              <br/><span class="error">* please enter email  </span> 
            </c:if>
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
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>
