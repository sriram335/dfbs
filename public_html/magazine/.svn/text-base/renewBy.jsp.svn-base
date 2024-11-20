<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Magazine Permits" />
<c:set var="title" scope="request" value="Renewal of Permit by" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<h2>Select Renewal / View Preference</h2>
<a   href="/dfbs/magazine/start.do?method=backToStart">
              [back to start]</a>
 <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
      <th scope="row"  class="required"    > </th></br>
      <td>
      <html:form action="/start" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="renewByOwner"/>
            <p>
              <input type="submit" value="Select the permit by owner name" class="button"/></br></br>
            </p>
            </html:form>
        </td>
    </tr> 
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null }">   
 
  
   <tr>
      <th scope="row"  class="required"    > </th></br>
      <td>
    <html:form action="/start" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="renewByStreetNumber"/>
      <p>
       Magazine Location Street Address:
           <html:text property="streetNumber" size="10" maxlength="10"/>
          </br>
            <input type="submit" value="Select the permit by street address" class="button"/>
         </br> *Note: for 302 West Washington ST, enter 302 in the box, Rural Road 400 enter Rural
       </p>
         <c:if test="${START_FORM_ERROR.streetNumber == 0}">
         <span class="error">
         * please enter street number</span> 
        </c:if></br>
    </html:form>
    </td>
    </tr> 
     <tr>
      <th scope="row"  class="required"    > </th></br>
      <td>
    <html:form action="/ownerListDisplay" method="post">
       <input type="hidden"  name="method" value="filter" />
        <input type="hidden"  name="filter" value="byOwnerId" />
      <p>
       owner Id:
           <html:text property="ownerId" size="10" maxlength="10"/>
          </br>
            <input type="submit" value="Select the permit(s) by owner Id" class="button"/>
         </br> *Note: for 302 West Washington ST, enter 302 in the box, Rural Road 400 enter Rural
       </p>
         <c:if test="${START_FORM_ERROR.ownerId == 0}">
         <span class="error">
         * please enter owner Id</span> 
        </c:if></br>
    </html:form>
    </td>
    </tr> 
  </c:if>
  <%--   <tr>
      <th scope="row"  class="required"    > </th></br>
      <td>
      <html:form action="/start" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="renewByPermitNumber"/>
            <p>
            Permit Number:
              <html:text property="permitNumber" size="12" maxlength="12"/>
            </br>
              <input type="submit" value="Select the permit by permit number" class="button"/>
            </p>
          
          <c:if test="${START_FORM_ERROR.permitNumber =='ERROR'}">
          <span class="error">* please enter permit number</span>
        </c:if> </br>
      </html:form>
    </td>
    </tr> --%>
     <tr> 
      <th scope="row"  class="required"    > </th></br>
      <td>
      <html:form action="/start" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="renewByIdNumber"/>
            <p>
            Identification Number(Starts with MA):
              <html:text property="idNumber" size="12" maxlength="12"/>
            </br> 
             <input type="submit" value="Select the permit by Id number" class="button"/>
            </p>
          <c:if test="${START_FORM_ERROR.idNumber =='ERROR'}">
          <span class="error">* please enter id number</span> 
        </c:if>
      </html:form>
    </td>
    </tr> 
  <%--   <tr>
      <th scope="row"  class="required"    > </th></br>
      <td>
      <html:form action="/logOn" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="goToUpload"/>
            </br> 
             <input type="submit" value="Upload File(s)" class="button"/>
            </p>
        </html:form>
    </td>
    </tr>  --%>
      </tbody>
   </table>
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
