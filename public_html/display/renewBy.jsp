<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Fire Display  Permits" />
<c:set var="title" scope="request" value="View of Permit by" />

<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>View Permits</h2>
<a   href="/dfbs/display/start.do?method=backToStart">
              [back to start]</a>
 <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >

   <tr>
      <th scope="row"  class="required"    > </th></br>
      <td>
      <html:form action="/start" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="renewByOwner"/>
            <p>
              <input type="submit" value="Select the permit by owner name" class="button"/></br></br></br>
            </p>
            </html:form>
        </td>
    </tr> 
  
  
  
   <tr>
      <th scope="row"  class="required"    > </th></br>
      <td>
    <html:form action="/start" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="renewByStreetNumber"/>
      <p>
       Fire Display  Location Street Address:
        <SPAN style="background-color:rgb(192,192,192); background-color:rgb(51,51,255);">
           <html:text property="streetNumber" size="10" maxlength="10"/>
         </SPAN> </br>
            <input type="submit" value="Select the permit by street address" class="button"/>
         </br> *Note: for 302 West Washington ST, enter 302 in the box, Rural Road 400 enter Rural
       </p>
         <c:if test="${START_FORM_ERROR.streetNumber == 0}">
         <span class="error">
         * please enter street number</span> 
         </c:if>
        </br></br></br>
    </html:form>
    </td>
    </tr> 
     <tr>
      <th scope="row"  class="required"    > </th></br>
      <td>
      <html:form action="/start" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="renewByPermitNumber"/>
            <p>
            Permit Number:
            <SPAN style="background-color:rgb(192,192,192); background-color:rgb(51,51,255);">
              <html:text property="permitNumber" size="12" maxlength="12"/>
            </SPAN>
            </br>
              <input type="submit" value="Select the permit by permit number" class="button"/>
            </p>
          
          <c:if test="${START_FORM_ERROR.permitNumber =='ERROR'}">
          <span class="error">* please enter permit number</span>
        </c:if> </br></br></br></br>
      </html:form>
    </td>
    </tr> 
  
      </tbody>
   </table>
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
